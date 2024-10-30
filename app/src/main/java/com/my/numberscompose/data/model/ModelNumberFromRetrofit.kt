package com.my.numberscompose.data.model

import com.google.gson.annotations.SerializedName

data class ModelNumberFromRetrofit(
    @SerializedName("text") val infoAboutNumber: String,
    val number: String
)