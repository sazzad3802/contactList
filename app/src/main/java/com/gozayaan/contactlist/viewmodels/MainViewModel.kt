package com.gozayaan.contactlist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gozayaan.contactlist.models.ContactData
import com.gozayaan.contactlist.network.api.ContactListApiResponse
import com.gozayaan.contactlist.views.adapters.ContactListAdapter
import com.gozayaan.contactlist.views.listeners.ItemClickListener
import com.gozayaan.contactlist.service.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel(), ItemClickListener {

    private val _contactListApiResponse = MutableLiveData<ContactListApiResponse>()
    val contactListApiResponse: LiveData<ContactListApiResponse> get() = _contactListApiResponse

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> get() = _errorMsg

    var contactList = arrayListOf<ContactData>()
    var goToContactDetailsActivity: MutableLiveData<ContactData?> = MutableLiveData()
    var contactListAdapter: ContactListAdapter = ContactListAdapter(this, contactList)
    private val compositeDisposable = CompositeDisposable()

    init {
        loadRxContacts()
    }

    private fun loadRxContacts() {
        compositeDisposable.add(
            RetrofitClient.contactsApi.getContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contactListApiResponse ->
                    _contactListApiResponse.value = contactListApiResponse
                }, {
                    _errorMsg.value = "Something went wrong! please try again"
                })
        )
    }

    override fun onItemClicked(item: Any?) {
        if (item is ContactData) {
            goToContactDetailsActivity.value = item
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}