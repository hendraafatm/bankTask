package com.app.bankmisr.domain.entity

import com.google.gson.annotations.SerializedName

data class Info(

	@field:SerializedName("rate")
	val rate: Double? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null
)