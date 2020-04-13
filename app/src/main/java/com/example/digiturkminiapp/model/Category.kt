package com.example.digiturkminiapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null
): Serializable