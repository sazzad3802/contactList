package com.gozayaan.contactlist.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gozayaan.contactlist.R
import com.gozayaan.contactlist.databinding.ContactItemBinding
import com.gozayaan.contactlist.models.ContactData
import com.squareup.picasso.Picasso

class ContactListViewHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contactData: ContactData) {
        val formattedNumber : String = formatPhoneNumber(contactData.phoneNumber)
        binding.contact = contactData.copyWith(phoneNumber = formattedNumber)
        val placeHolder = ContextCompat.getDrawable(binding.civContactImage.context, R.drawable.ic_account)
        Picasso.get()
            .load(contactData.image)
            .placeholder(placeHolder!!)
            .error(placeHolder)
            .centerCrop()
            .resize(60, 50)
            .into(binding.civContactImage)
        binding.executePendingBindings()
    }

    private fun formatPhoneNumber(phone: String): String {
        return phone.replace(Regex("""(\d{3})-(\d{3})-(\d{4})""")) {
            "(${it.groupValues[1]}) ${it.groupValues[2]}-${it.groupValues[3]}"
        }
    }
}