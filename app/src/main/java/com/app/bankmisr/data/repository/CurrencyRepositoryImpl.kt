package com.app.bankmisr.data.repository

import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.api.CurrencyApi
import com.app.bankmisr.data.remote.dto.response.conversion.CurrencyConvertResponse
import com.app.bankmisr.data.remote.dto.response.conversion.SymbolsResponse
import com.app.bankmisr.data.remote.dto.response.history.CurrencyHistoryResponse
import com.app.bankmisr.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi
) : CurrencyRepository {

    override suspend fun getSymbols(): Flow<DataState<SymbolsResponse>> = flow {
        val response = currencyApi.getSymbols()
        emit(DataState.Success(response))
    }

    override suspend fun currencyConvert(
        to: String,
        from: String,
        amount: Double
    ): Flow<DataState<CurrencyConvertResponse>> =
        flow {
            val response = currencyApi.currencyConvert(to, from, amount)
            emit(DataState.Success(response))
        }

    override suspend fun getCurrencyHistory(
        base: String,
        startDate: String,
        endDate: String,
        symbols: String
    ): Flow<DataState<CurrencyHistoryResponse>>  = flow {
        val response = currencyApi.getCurrencyHistory(base, startDate, endDate, symbols)
        emit(DataState.Success(response))
    }


}