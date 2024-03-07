package com.gozayaan.contactlist.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ContactData(
    @SerializedName("full_name") val fullName: String,
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("email") val email: String,
    @SerializedName("image") val image: String,
) : Serializable{
    fun copyWith(fullName: String = this.fullName, email: String = this.email, phoneNumber: String = this.phoneNumber, image: String = this.image): ContactData {
        return ContactData(fullName, phoneNumber, email, image)
    }
}