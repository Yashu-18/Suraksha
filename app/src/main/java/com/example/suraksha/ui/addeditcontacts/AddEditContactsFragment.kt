package com.example.suraksha.ui.addeditcontacts

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.suraksha.R
import com.example.suraksha.databinding.FragmentAddEditContactsBinding
import com.example.suraksha.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditContactsFragment : Fragment(R.layout.fragment_add_edit_contacts) {
    private val viewModel: AddEditContactsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddEditContactsBinding.bind(view)

        binding.apply {
            etName.setText(viewModel.contactName)
            etPhoneNum.setText(viewModel.contactPhNumber)

            etName.addTextChangedListener {
                viewModel.contactName = it.toString()
            }

            etPhoneNum.addTextChangedListener {
                viewModel.contactPhNumber = it.toString()
            }

            fabSaveContacts.setOnClickListener {
                viewModel.onSaveClick()
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addEditContactEvent.collect { event ->
                when (event) {
                    is AddEditContactsViewModel.AddEditContactEvent.ShowInvalidInputMessage -> {
                        Snackbar.make(requireView(), event.msg, Snackbar.LENGTH_LONG).show()
                    }

                    is AddEditContactsViewModel.AddEditContactEvent.NavigateBackWithResult -> {
                        binding.etName.clearFocus()
                        setFragmentResult(
                            "add_edit_request",
                            bundleOf("add_edit_result" to event.result)
                        )
                        findNavController().popBackStack()
                    }
                }.exhaustive
            }
        }
    }
}