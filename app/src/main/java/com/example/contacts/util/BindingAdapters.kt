package com.example.contacts.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.adapter.ContactsAdapter
import com.example.contacts.data.Contact

@BindingAdapter("name")
fun bindContact(txtView: TextView, name: String?){

    name?.let {
        txtView.text = name.toString()
    }
}