package com.example.digiturkminiapp.ui.filmlist

import androidx.lifecycle.MutableLiveData
import com.example.digiturkminiapp.core.BaseViewModel
import com.example.digiturkminiapp.model.Category
import com.example.digiturkminiapp.model.CategoryResponse
import com.example.digiturkminiapp.model.FilmListResponse
import com.example.digiturkminiapp.network.ApiCallback
import com.example.digiturkminiapp.network.ApiClient
import com.example.digiturkminiapp.network.ApiError
import com.example.digiturkminiapp.network.BeinConnectApi

class FilmListViewModel: BaseViewModel() {

    private var service: BeinConnectApi =
        ApiClient.instance.createService(BeinConnectApi::class.java)

    fun getCategoryList(apiKey: String,language: String){
        setLoadingState()

        service.categoryList(apiKey,language).enqueue(object: ApiCallback<CategoryResponse>(){
            override fun onSuccess(data: CategoryResponse?) {
                if (data == null)
                    setErrorState()
                else{
                    setSuccessState(data)
                }
            }

            override fun onError(error: ApiError) {
                setErrorState(error)
            }
        })
    }

    fun getFilmList(gendersId: Int){
        setLoadingState()

        service.getFilmList(with_genres = gendersId).enqueue(object: ApiCallback<FilmListResponse>(){
            override fun onSuccess(data: FilmListResponse?) {
                if (data == null)
                    setErrorState()
                else{
                    setSuccessState(data)
                }
            }

            override fun onError(error: ApiError) {
                setErrorState(error)
            }
        })
    }
}