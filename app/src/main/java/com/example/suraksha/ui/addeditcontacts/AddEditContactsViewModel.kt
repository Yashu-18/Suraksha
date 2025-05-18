package com.example.suraksha.ui.addeditcontacts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suraksha.data.Contacts
import com.example.suraksha.data.ContactsDao
import com.example.suraksha.ui.ADD_CONTACT_RESULT_OK
import com.example.suraksha.ui.EDIT_CONTACT_RESULT_OK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditContactsViewModel @Inject constructor(
    private val contactsDao: ContactsDao,
    private val state: SavedStateHandle
) : ViewModel() {

    val contacts = state.get<Contacts>("contact")

    var contactName = state.get<String>("contactName") ?: contacts?.name ?: ""
        set(value) {
            field = value
            state.set("contactName", value)
        }

    var contactPhNumber = state.get<String>("contactPhNumber") ?: contacts?.phNum ?: ""
        set(value) {
            field = value
            state.set("contactPhNumber", value)
        }

    private val addEditContactEventChannel = Channel<AddEditContactEvent>()
    val addEditContactEvent = addEditContactEventChannel.receiveAsFlow()


    fun onSaveClick() {

        if (contactName.isBlank()) {
            showInvalidInputMessage("Name cannot be empty")
            return
        }
        if (contactPhNumber.isBlank()) {
            showInvalidInputMessage("Phone Number cannot be empty")
            return
        }
        if (contactPhNumber.length != 10) {
            showInvalidInputMessage("Invalid Phone number")
            return
        }

        if (contacts != null) {
            val updatedContact = contacts.copy(name = contactName, phNum = contactPhNumber)
            updateContact(updatedContact)
        } else {
            val newContact = Contacts(name = contactName, phNum = contactPhNumber)
            createContact(newContact)
        }

    }

    private fun createContact(contact: Contacts) = viewModelScope.launch {
        contactsDao.insert(contact)
        addEditContactEventChannel.send(
            AddEditContactEvent.NavigateBackWithResult(
                ADD_CONTACT_RESULT_OK
            )
        )
    }

    private fun updateContact(contact: Contacts) = viewModelScope.launch {
        contactsDao.update(contact)
        addEditContactEventChannel.send(
            AddEditContactEvent.NavigateBackWithResult(
                EDIT_CONTACT_RESULT_OK
            )
        )
    }

    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditContactEventChannel.send(AddEditContactEvent.ShowInvalidInputMessage(text))
    }

    private fun showContactExistMessage(text: String) = viewModelScope.launch {
        addEditContactEventChannel.send(AddEditContactEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditContactEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditContactEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditContactEvent()
    }

}