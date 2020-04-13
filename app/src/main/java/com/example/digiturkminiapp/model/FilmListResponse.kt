package com.example.digiturkminiapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmListResponse(
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("total_results")
    var total_results: Int? = null,
    @SerializedName("total_pages")
    var total_pages: Int? = null,
    @SerializedName("results")
    var results: List<Film> = emptyList()

): Serializable