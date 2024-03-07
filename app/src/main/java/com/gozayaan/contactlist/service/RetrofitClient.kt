package com.gozayaan.contactlist.service

import com.gozayaan.contactlist.network.ContactsApi
import com.gozayaan.contactlist.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val client: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val contactsApi: ContactsApi = client.create(ContactsApi::class.java)
}