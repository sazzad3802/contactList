package com.gozayaan.contactlist.views.activities

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.gozayaan.contactlist.R
import com.gozayaan.contactlist.databinding.ActivityContactDetailBinding
import com.gozayaan.contactlist.models.ContactData
import com.gozayaan.contactlist.viewmodels.ContactDetailViewModel
import com.squareup.picasso.Picasso

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding
    private lateinit var contactDetailViewModel : ContactDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarTransparent()
        setDarkStatusBarIcons()
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contactData: ContactData? = intent.getSerializableExtra("contact_data") as? ContactData
        val formattedNumber : String = formatPhoneNumber(contactData!!.phoneNumber)
        binding.contact = contactData.copyWith(phoneNumber = formattedNumber)
        val placeHolder = ContextCompat.getDrawable(binding.civContactDetailImage.context, R.drawable.ic_face)
        Picasso.get()
            .load(contactData.image)
            .placeholder(placeHolder!!)
            .error(placeHolder)
            .centerCrop()
            .resize(220, 220)
            .into(binding.civContactDetailImage)
        contactDetailViewModel = ViewModelProviders.of(this).get(ContactDetailViewModel::class.java)

        binding.ivBack.setOnClickListener { finish() }
    }

    private fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }

        // For devices with API level 19 and below
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    private fun setDarkStatusBarIcons() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // For devices with Android version Marshmallow and above
            val window: Window = window
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun formatPhoneNumber(phone: String): String {
        return phone.replace(Regex("""(\d{3})-(\d{3})-(\d{4})""")) {
            "(${it.groupValues[1]}) ${it.groupValues[2]}-${it.groupValues[3]}"
        }
    }
}