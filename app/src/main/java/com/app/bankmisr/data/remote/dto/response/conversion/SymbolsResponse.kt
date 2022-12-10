package com.app.bankmisr.data.remote.dto.response.conversion

import com.app.bankmisr.data.common.utils.BaseResponse
import com.google.gson.annotations.SerializedName

data class SymbolsResponse(

	@field:SerializedName("symbols")
	val symbols: HashMap<String, String>? = null
) : BaseResponse()
