package com.gozayaan.contactlist.models

import com.google.gson.annotations.SerializedName

data class ErrorData(
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: Int,
)