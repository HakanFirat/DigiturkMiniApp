package com.example.digiturkminiapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryResponse(
    @SerializedName("genres")
    var categoryList: List<Category> = emptyList()
): Serializable