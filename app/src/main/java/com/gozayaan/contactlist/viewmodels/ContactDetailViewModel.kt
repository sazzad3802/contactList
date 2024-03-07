package com.gozayaan.contactlist.viewmodels

import androidx.lifecycle.ViewModel
import com.gozayaan.contactlist.views.listeners.ItemClickListener

class ContactDetailViewModel : ViewModel(), ItemClickListener {
    override fun onItemClicked(item: Any?) {}
}