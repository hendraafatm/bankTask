package com.app.bankmisr.domain

import com.app.bankmisr.BaseTest
import com.app.bankmisr.data.common.utils.BaseResponse
import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.dto.response.conversion.CurrencyConvertResponse
import com.app.bankmisr.domain.usecases.ConversionUseCase
import junit.framework.Assert
import junit.framework.Assert.assertSame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

class ConversionUseCaseTest : BaseTest() {

    private val currencyRepository = Mockito.mock(CurrencyRepository::class.java)
    private val currencyUseCase = ConversionUseCase(currencyRepository)

    @Test
    fun `ensure that getting currency conversion use case  is working successfully`() =
        runBlocking {

            val response =
                MutableStateFlow(DataState.Success(CurrencyConvertResponse()))

            // When
            Mockito.`when`(currencyRepository.currencyConvert("", "", 1.0)).thenReturn(response)

            // Then
            val result = currencyUseCase.buildUseCase(ConversionUseCase.Params("", "", 1.0))
            Assert.assertSame(response, result)

        }

    @Test
    fun `ensure that getting currency conversion use case is not working`() = runBlocking {

        val response = MutableStateFlow(DataState.GenericError(400, BaseResponse(success = false)))

        // When
        Mockito.`when`(currencyRepository.currencyConvert("","",-1.0)).thenReturn(response)

        // Then
        val result = currencyUseCase.buildUseCase(ConversionUseCase.Params("", "", -1.0))
        assertSame(response, result)
        assert(response.value.error?.success == false)

    }
}