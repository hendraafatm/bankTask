package com.app.bankmisr.data.remote.dto.response.history

import com.app.bankmisr.data.common.utils.BaseResponse
import com.google.gson.annotations.SerializedName

data class CurrencyHistoryResponse(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("timeseries")
	val timeseries: Boolean? = null,

	@field:SerializedName("rates")
	val rates: HashMap<String,HashMap<String,Double>>? = null,

	@field:SerializedName("base")
	val base: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
):BaseResponse()