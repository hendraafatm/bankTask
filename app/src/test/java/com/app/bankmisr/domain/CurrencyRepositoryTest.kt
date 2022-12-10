package com.app.bankmisr.domain

import com.app.bankmisr.BaseTest
import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.api.CurrencyApi
import com.app.bankmisr.data.remote.dto.response.conversion.CurrencyConvertResponse
import com.app.bankmisr.data.remote.dto.response.conversion.SymbolsResponse
import com.app.bankmisr.data.remote.dto.response.history.CurrencyHistoryResponse
import com.app.bankmisr.data.repository.CurrencyRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CurrencyRepositoryTest : BaseTest() {
    private val apiService = Mockito.mock(CurrencyApi::class.java)
    private val currencyRepo = CurrencyRepositoryImpl(apiService)

    @Test
    fun `ensure that execute currencyConvert is working successfully`() = runBlocking {
        val response = CurrencyConvertResponse(
            result = 1.0
        )

        Mockito.`when`(apiService.currencyConvert("","",1.0))
            .thenReturn(response)

        val result = currencyRepo.currencyConvert("","",1.0)

        result.collect{
            MatcherAssert.assertThat(it, CoreMatchers.instanceOf(DataState.Success::class.java))
            val dataResponse = (it as DataState.Success).value
            Assert.assertEquals(response.result,dataResponse?.result)
        }
    }

    @Test
    fun `ensure that execute getSymbols is working successfully`() = runBlocking {
        val symbolsHashMap = HashMap<String,String>()
        symbolsHashMap["EGP"] = "Egypt"

        val response = SymbolsResponse(
            symbolsHashMap
        )

        Mockito.`when`(apiService.getSymbols())
            .thenReturn(response)

        val result = currencyRepo.getSymbols()

        result.collect{
            MatcherAssert.assertThat(it, CoreMatchers.instanceOf(DataState.Success::class.java))
            val dataResponse = (it as DataState.Success).value
            Assert.assertEquals(response.symbols,dataResponse?.symbols)
        }
    }

    @Test
    fun `ensure that execute getCurrencyHistory is working successfully`() = runBlocking {
        val response = CurrencyHistoryResponse(
            base = "EGP"
        )

        Mockito.`when`(apiService.getCurrencyHistory("","","",""))
            .thenReturn(response)

        val result = currencyRepo.getCurrencyHistory("","","","")

        result.collect{
            MatcherAssert.assertThat(it, CoreMatchers.instanceOf(DataState.Success::class.java))
            val dataResponse = (it as DataState.Success).value
            Assert.assertEquals(response.base,dataResponse?.base)
        }
    }
}