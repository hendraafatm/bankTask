package com.app.bankmisr.domain

import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.dto.response.conversion.CurrencyConvertResponse
import com.app.bankmisr.data.remote.dto.response.conversion.SymbolsResponse
import com.app.bankmisr.data.remote.dto.response.history.CurrencyHistoryResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getSymbols(): Flow<DataState<SymbolsResponse>>

    suspend fun currencyConvert(
        to: String,
        from: String,
        amount: Double
    ): Flow<DataState<CurrencyConvertResponse>>

    suspend fun getCurrencyHistory(
        base : String,
        startDate : String,
        endDate : String,
        symbols : String,
    ): Flow<DataState<CurrencyHistoryResponse>>
}