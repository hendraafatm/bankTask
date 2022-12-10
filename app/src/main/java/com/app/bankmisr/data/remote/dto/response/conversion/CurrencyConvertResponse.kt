package com.app.bankmisr.data.remote.dto.response.conversion

import com.app.bankmisr.data.common.utils.BaseResponse
import com.app.bankmisr.domain.entity.Info
import com.app.bankmisr.domain.entity.Query
import com.google.gson.annotations.SerializedName

data class CurrencyConvertResponse(

    @field:SerializedName("date")
	val date: String? = null,

    @field:SerializedName("result")
	val result: Double? = null,

    @field:SerializedName("query")
	val query: Query? = null,

    @field:SerializedName("historical")
	val historical: Boolean? = null,

    @field:SerializedName("info")
	val info: Info? = null
):BaseResponse()