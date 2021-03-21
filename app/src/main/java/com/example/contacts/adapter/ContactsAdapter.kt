package com.example.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.data.Contact
import com.example.contacts.databinding.ListItemContactBinding

class ContactsAdapter : ListAdapter<Contact, ContactsAdapter.ContactViewHolder>(DiffUtilCallback()){

    class ContactViewHolder(private var binding: ListItemContactBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(contact: Contact){
            binding.contact = contact
            binding.executePendingBindings()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ListItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item)
    }

}

class DiffUtilCallback: DiffUtil.ItemCallback<Contact>(){
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {

        return oldItem.id == newItem.id
    }

}