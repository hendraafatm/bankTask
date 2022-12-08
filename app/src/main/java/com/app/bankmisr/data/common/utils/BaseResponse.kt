package com.app.bankmisr.data.common.utils

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseResponse(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("error")
    val error: ErrorData? = null
) : Serializable

data class ErrorData(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("info")
    val errorMessage: String? = null
)
