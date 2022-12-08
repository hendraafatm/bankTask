package com.app.bankmisr.data.repository

import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.api.CurrencyApi
import com.app.bankmisr.data.remote.dto.response.CurrencyConvertResponse
import com.app.bankmisr.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi
) : CurrencyRepository {

    override suspend fun currencyConvert(
        apiKey : String,
        to: String,
        from: String,
        amount: Double
    ): Flow<DataState<CurrencyConvertResponse>> =
        flow {
            val response = currencyApi.currencyConvert(to, from, amount)
            emit(DataState.Success(response))
        }


}