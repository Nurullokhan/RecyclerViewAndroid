package com.example.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ContactItemBinding
import com.example.recyclerview.models.Contact

class ContactRecyclerViewAdapter(
    var onYourItemClickListener: OnYourItemClickListener,
    var contactList: ArrayList<Contact>
) :
    RecyclerView.Adapter<ContactRecyclerViewAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(contact: Contact, position: Int) {

            binding.root.setOnClickListener {
                onYourItemClickListener.onYourItemClick(contact, position)
            }

            binding.root.setOnLongClickListener {
                onYourItemClickListener.onYourItemLongClick(contact)
                true
            }

            binding.name.text = contact.name
            binding.phoneNumber.text = contact.phoneNumber
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ContactItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(contactList[position], position)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    interface OnYourItemClickListener {
        fun onYourItemClick(contact: Contact, position: Int)
        fun onYourItemLongClick(contact: Contact)
    }

}