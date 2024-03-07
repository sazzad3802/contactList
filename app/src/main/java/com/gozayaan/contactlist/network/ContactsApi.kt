package com.gozayaan.contactlist.network

import com.gozayaan.contactlist.network.api.ContactListApiResponse
import com.gozayaan.contactlist.utils.Constants
import io.reactivex.Single
import retrofit2.http.*

interface ContactsApi {
    @GET(Constants.GET_CONTACTS)
    fun getContacts(): Single<ContactListApiResponse>
}