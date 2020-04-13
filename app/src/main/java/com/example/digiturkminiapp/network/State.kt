package com.example.digiturkminiapp.network

sealed class State {
    class Success<T>(val data:T): State()
    class Error(val error:ApiError?): State()
    class Loading: State()
}