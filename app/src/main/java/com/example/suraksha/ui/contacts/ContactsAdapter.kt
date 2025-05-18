package com.example.suraksha.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.suraksha.data.Contacts
import com.example.suraksha.databinding.ItemContactsBinding


class ContactsAdapter(private val listener: onItemClickListner) :
    ListAdapter<Contacts, ContactsAdapter.ContactsViewHolder>(DiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding =
            ItemContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ContactsViewHolder(private val binding: ItemContactsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val contact = getItem(position)
                        listener.onItemCLick(contact)
                    }
                }
            }
        }

        fun bind(contacts: Contacts) {
            binding.apply {
                tvItemName.text = contacts.name
                tvItemPhoneNum.text = contacts.phNum
            }
        }
    }

    interface onItemClickListner {
        fun onItemCLick(contact: Contacts)
    }

    class DiffCallBack : DiffUtil.ItemCallback<Contacts>() {
        override fun areItemsTheSame(oldItem: Contacts, newItem: Contacts) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Contacts, newItem: Contacts) =
            oldItem == newItem
    }
}