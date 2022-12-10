package com.app.bankmisr.domain.usecases

import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.common.utils.NetworkHelper
import com.app.bankmisr.data.remote.dto.response.history.CurrencyHistoryResponse
import com.app.bankmisr.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrencyHistoryUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) :
    NetworkHelper<GetCurrencyHistoryUseCase.Params, CurrencyHistoryResponse>() {

    data class Params(
        val base: String,
        val startData: String,
        val endDate: String,
        val symbols: String
    )

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<CurrencyHistoryResponse>> {
        return currencyRepository.getCurrencyHistory(
            base = parameter.base,
            startDate = parameter.startData,
            endDate = parameter.endDate,
            symbols = parameter.symbols
        )
    }
}