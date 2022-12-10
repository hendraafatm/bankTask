package com.app.bankmisr.domain

import com.app.bankmisr.BaseTest
import com.app.bankmisr.data.common.utils.BaseResponse
import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.dto.response.conversion.CurrencyConvertResponse
import com.app.bankmisr.data.remote.dto.response.conversion.SymbolsResponse
import com.app.bankmisr.domain.usecases.ConversionUseCase
import com.app.bankmisr.domain.usecases.GetSymbolsUseCase
import junit.framework.Assert
import junit.framework.Assert.assertSame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class SymbolsUseCaseTest : BaseTest() {

    private val currencyRepository = Mockito.mock(CurrencyRepository::class.java)
    private val symbolsUseCase = GetSymbolsUseCase(currencyRepository)

    @Test
    fun `ensure that getting currency conversion use case  is working successfully`() =
        runBlocking {

            val response =
                MutableStateFlow(DataState.Success(SymbolsResponse()))

            // When
            Mockito.`when`(currencyRepository.getSymbols()).thenReturn(response)

            // Then
            val result = symbolsUseCase.buildUseCase(GetSymbolsUseCase.Params())
            assertSame(response, result)

        }

    @Test
    fun `ensure that getting currency conversion use case is not working`() = runBlocking {

        val response = MutableStateFlow(DataState.GenericError(400, BaseResponse(success = false)))

        // When
        Mockito.`when`(currencyRepository.getSymbols()).thenReturn(response)

        // Then
        val result = symbolsUseCase.buildUseCase(GetSymbolsUseCase.Params())
        assertSame(response, result)
        assert(response.value.error?.success == false)

    }
}