package com.app.bankmisr.domain.usecases

import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.common.utils.NetworkHelper
import com.app.bankmisr.data.remote.dto.response.conversion.CurrencyConvertResponse
import com.app.bankmisr.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConversionUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) :
    NetworkHelper<ConversionUseCase.Params, CurrencyConvertResponse>() {

    data class Params(
        val to: String,
        val from: String,
        val amount: Double
    )

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<CurrencyConvertResponse>> {
        return currencyRepository.currencyConvert(
            parameter.to,
            parameter.from,
            parameter.amount
        )
    }
}