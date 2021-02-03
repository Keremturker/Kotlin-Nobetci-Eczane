package com.keremturker.kotlineczane.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Eczane(
    @SerializedName("name")
    val ad: String,
    @SerializedName("dist")
    val ilce: String,
    @SerializedName("address")
    val adres: String,
    @SerializedName("phone")
    val telefon: String,
    @SerializedName("loc")
    val konum: String
) : Parcelable