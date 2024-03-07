package com.gozayaan.contactlist.network.api

import com.google.gson.annotations.SerializedName
import com.gozayaan.contactlist.models.ContactData
import com.gozayaan.contactlist.models.ErrorData

data class ContactListApiResponse(
    @SerializedName("error") val error: ErrorData,
    @SerializedName("status") val status: Boolean,
    @SerializedName("result") val result: List<ContactData>?
)