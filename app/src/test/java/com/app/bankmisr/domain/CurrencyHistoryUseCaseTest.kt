package com.app.bankmisr.domain

import com.app.bankmisr.BaseTest
import com.app.bankmisr.data.common.utils.BaseResponse
import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.dto.response.conversion.CurrencyConvertResponse
import com.app.bankmisr.data.remote.dto.response.history.CurrencyHistoryResponse
import com.app.bankmisr.domain.usecases.ConversionUseCase
import com.app.bankmisr.domain.usecases.GetCurrencyHistoryUseCase
import junit.framework.Assert
import junit.framework.Assert.assertSame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class CurrencyHistoryUseCaseTest : BaseTest() {

    private val currencyRepository = Mockito.mock(CurrencyRepository::class.java)
    private val currencyUseCase = GetCurrencyHistoryUseCase(currencyRepository)

    @Test
    fun `ensure that getting currency history use case is working successfully`() =
        runBlocking {

            val response =
                MutableStateFlow(DataState.Success(CurrencyHistoryResponse(base = "EGP")))

            // When
            Mockito.`when`(currencyRepository.getCurrencyHistory("", "", "","")).thenReturn(response)

            // Then
            val result = currencyUseCase.buildUseCase(GetCurrencyHistoryUseCase.Params("", "", "",""))
            assertSame(response, result)

        }

    @Test
    fun `ensure that getting currency history use case is not working`() = runBlocking {

        val response = MutableStateFlow(DataState.GenericError(400, BaseResponse(success = false)))

        // When
        Mockito.`when`(currencyRepository.getCurrencyHistory("EGP","","","USD")).thenReturn(response)

        // Then
        val result = currencyUseCase.buildUseCase(GetCurrencyHistoryUseCase.Params("EGP","","","USD"))
        assertSame(response, result)
        assert(response.value.error?.success == false)

    }
}