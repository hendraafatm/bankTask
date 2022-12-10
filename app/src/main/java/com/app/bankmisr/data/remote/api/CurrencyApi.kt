package com.app.bankmisr.data.remote.api

import com.app.bankmisr.data.remote.dto.response.conversion.CurrencyConvertResponse
import com.app.bankmisr.data.remote.dto.response.conversion.SymbolsResponse
import com.app.bankmisr.data.remote.dto.response.history.CurrencyHistoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("symbols")
    suspend fun getSymbols(): SymbolsResponse

    @GET("convert")
    suspend fun currencyConvert(
        @Query("to") to: String,
        @Query("from") from: String,
        @Query("amount") amount: Double
    ): CurrencyConvertResponse

    @GET("timeseries")
    suspend fun getCurrencyHistory(
        @Query("base") base : String,
        @Query("start_date") startDate : String,
        @Query("end_date") endDate : String,
        @Query("symbols") symbols : String,
    ) : CurrencyHistoryResponse
}