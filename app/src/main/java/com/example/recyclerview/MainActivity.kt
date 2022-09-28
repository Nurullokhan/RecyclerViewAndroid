package com.example.recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.recyclerview.adapters.ContactRecyclerViewAdapter
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.models.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var contactList: ArrayList<Contact>
    private lateinit var adapter: ContactRecyclerViewAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactList = ArrayList()

        adapter =
            ContactRecyclerViewAdapter(object : ContactRecyclerViewAdapter.OnYourItemClickListener {

                /**
                 * You cannot use these functions at the same time, you must use only one!
                 */

                override fun onYourItemClick(contact: Contact, position: Int) {

                    /**
                     * val intent = Intent(this@MainActivity, SecondActivity::class.java)
                     * startActivity(intent)
                     */

                    contact.name = "Nurik"
                    contact.phoneNumber = "+998907804240"
                    /**
                     * This function updates by index
                     */
                    adapter.notifyItemChanged(position)

                    /**
                     * This function is removed from the list
                     */
                    contactList.remove(contact)
                    adapter.notifyItemRemoved(position)
                    adapter.notifyItemRangeRemoved(position, contactList.size)
                }

                override fun onYourItemLongClick(contact: Contact) {
                    Toast.makeText(this@MainActivity, "$contact", Toast.LENGTH_SHORT).show()
                }

            }, contactList)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        binding.addContact.setOnClickListener {
            val name: String = binding.name.text.toString()
            val phoneNumber: String = binding.phoneNumber.text.toString()

            val contact = Contact(name, phoneNumber)
            contactList.add(contact)

            /**
             * This function updates the entire list
             */

            adapter.notifyDataSetChanged()

            /**
             * This function only updates the last element
             */

            adapter.notifyItemInserted(contactList.size)

            /**
             * This function is used when adding a large number of elements
             */

            adapter.notifyItemRangeChanged(0, contactList.size)
        }

        //GridLayoutManager example

        /**
         * binding.recyclerView.layoutManager =
         *    GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
         * binding.recyclerView.adapter = adapter
         */

        //StaggeredGridLayoutManager example
        /** binding.recyclerView.layoutManager =
         *   StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
         *  binding.recyclerView.adapter = adapter
         */
    }
}