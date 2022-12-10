package com.app.bankmisr.domain.usecases

import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.common.utils.NetworkHelper
import com.app.bankmisr.data.remote.dto.response.conversion.SymbolsResponse
import com.app.bankmisr.domain.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSymbolsUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) :
    NetworkHelper<GetSymbolsUseCase.Params, SymbolsResponse>() {

    class Params

    override suspend fun buildUseCase(parameter: Params): Flow<DataState<SymbolsResponse>> {
        return currencyRepository.getSymbols()
    }
}