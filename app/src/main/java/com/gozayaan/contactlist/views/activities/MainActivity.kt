package com.gozayaan.contactlist.views.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gozayaan.contactlist.databinding.ActivityMainBinding
import com.gozayaan.contactlist.viewmodels.MainViewModel
import com.gozayaan.contactlist.views.adapters.ContactListAdapter
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel
    private lateinit var contactsAdapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarTransparent()
        setDarkStatusBarIcons()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactsAdapter = mainViewModel.contactListAdapter
        binding.recyclerView.adapter = contactsAdapter

        binding.progressBar.visibility = View.VISIBLE

        observeData()
    }

    private fun observeData() {
        mainViewModel.contactListApiResponse.observe(this) { contactListApiResponse ->
            if(contactListApiResponse != null){
                binding.progressBar.visibility = View.GONE
                if(contactListApiResponse.status && contactListApiResponse.result!!.isNotEmpty()){
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                    contactsAdapter.updateModuleItems(contactListApiResponse.result)
                }else{
                    binding.tvError.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.tvError.text = contactListApiResponse.error.message
                }
            }
        }
        mainViewModel.errorMsg.observe(this) { errorMsg ->
            binding.progressBar.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            binding.tvError.text = errorMsg
        }
        mainViewModel.goToContactDetailsActivity.observe(this) {
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("contact_data", it)
            startActivity(intent)
        }
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

}