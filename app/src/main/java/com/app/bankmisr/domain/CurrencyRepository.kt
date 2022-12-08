package com.app.bankmisr.domain

import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.dto.response.CurrencyConvertResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun currencyConvert(
        to: String,
        from: String,
        amount: Double
    ): Flow<DataState<CurrencyConvertResponse>>
}