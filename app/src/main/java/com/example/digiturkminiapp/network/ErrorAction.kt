package com.example.digiturkminiapp.network

import com.google.gson.annotations.SerializedName


enum class ErrorAction (val value : Int){
    @SerializedName("0")
    UnExpected(0)
}