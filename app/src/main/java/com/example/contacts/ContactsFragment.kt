package com.example.contacts

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.adapter.ContactsAdapter
import com.example.contacts.data.ContactViewModel
import com.example.contacts.databinding.FragmentContactsBinding


class ContactsFragment : Fragment() {

    private lateinit var viewModel: ContactViewModel
    private lateinit var binding: FragmentContactsBinding

    companion object{
        private const val REQUEST_CODE = 786
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactsBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        binding.viewModel = viewModel

        if(arePermissionGranted()){
            val adapter = ContactsAdapter()
            binding.contactsList.adapter = adapter
            viewModel.contacts.observe(this.viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })
        }

        else{
            ActivityCompat.requestPermissions(this.requireActivity(),
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUEST_CODE
            )
        }

        return binding.root
    }

    private fun arePermissionGranted() = ContextCompat.checkSelfPermission(
        this.requireContext(),
        android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if(requestCode == REQUEST_CODE){

            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val adapter = ContactsAdapter()
                binding.contactsList.adapter = adapter
            }

            else{
                Toast.makeText(this.requireContext(), "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }

    }

}