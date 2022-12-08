package com.app.bankmisr.data.remote.api

import com.app.bankmisr.data.remote.dto.response.CurrencyConvertResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("convert")
    suspend fun currencyConvert(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: Double
    ): CurrencyConvertResponse
}