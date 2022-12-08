package com.app.bankmisr.domain.entity

import com.google.gson.annotations.SerializedName

data class Query(

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("from")
	val from: String? = null,

	@field:SerializedName("to")
	val to: String? = null
)