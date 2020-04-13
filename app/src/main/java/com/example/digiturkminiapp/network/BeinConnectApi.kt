package com.example.digiturkminiapp.network

import com.example.digiturkminiapp.model.CategoryResponse
import com.example.digiturkminiapp.model.FilmListResponse
import retrofit2.Call
import retrofit2.http.*

interface BeinConnectApi {

    @GET("genre/movie/list")
    fun categoryList(@Query("api_key") api_key: String,
                     @Query("language") language: String) : Call<CategoryResponse>

    @GET("discover/movie")
    fun getFilmList(@Query("api_key") api_key: String = "3bb3e67969473d0cb4a48a0dd61af747",
                    @Query("sort_by") sort_by: String = "popularity.desc",
                    @Query("include_adult") include_adult: Boolean = false,
                    @Query("include_video") include_video: Boolean = false,
                    @Query("page") page: Int = 1,
                    @Query("with_genres") with_genres: Int): Call<FilmListResponse>
}