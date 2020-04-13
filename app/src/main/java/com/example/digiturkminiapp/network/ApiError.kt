package com.example.digiturkminiapp.network

import com.example.digiturkminiapp.network.model.Error
import com.example.digiturkminiapp.utils.Constant
import java.io.Serializable

data class ApiError(val Error: Error) {
    companion object {
        fun createErrorFromString(
            errorString: String? = null,
            errorCode: String = "0",
            errorAction: ErrorAction = ErrorAction.UnExpected
        ): ApiError {
            var strError = Constant.API_CALL_ERROR_MESSAGE
            if (!errorString.isNullOrEmpty())
                strError = errorString
            val error = Error(errorAction, errorCode, strError)
            return ApiError(error)
        }
    }
}

data class ApiListError(
    var Error: String? = null,
    var Message: String? = null
) : Serializable