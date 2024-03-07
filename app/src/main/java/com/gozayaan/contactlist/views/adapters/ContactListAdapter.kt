package com.gozayaan.contactlist.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gozayaan.contactlist.R
import com.gozayaan.contactlist.models.ContactData
import com.gozayaan.contactlist.viewholder.ContactListViewHolder
import com.gozayaan.contactlist.views.listeners.ItemClickListener

class ContactListAdapter (var itemClickListener: ItemClickListener, var contactList: List<ContactData>): RecyclerView.Adapter<ContactListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ContactListViewHolder {
        return ContactListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.contact_item, parent, false))
    }

    override fun onBindViewHolder(holder: ContactListViewHolder, pos: Int) {
        holder.bind(contactList[pos])

        holder.itemView.setOnClickListener {
                itemClickListener.onItemClicked(contactList[pos])
            }
        }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun updateModuleItems(modules: List<ContactData>) {
        contactList = modules
        notifyDataSetChanged()
    }
}