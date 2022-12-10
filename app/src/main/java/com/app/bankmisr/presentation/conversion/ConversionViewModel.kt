package com.app.bankmisr.presentation.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.domain.usecases.ConversionUseCase
import com.app.bankmisr.domain.usecases.GetSymbolsUseCase
import com.app.bankmisr.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversionViewModel @Inject constructor(
    private val conversionUseCase: ConversionUseCase,
    private val symbolsUseCase: GetSymbolsUseCase
) : BaseViewModel() {

    private val _currencyConvertResultLiveData: MutableLiveData<Double> =
        MutableLiveData()
    val currencyConvertResultLiveData: LiveData<Double>
        get() = _currencyConvertResultLiveData

    private val _symbolsLiveData: MutableLiveData<List<String>> =
        MutableLiveData()
    val symbolsLiveData: LiveData<List<String>>
        get() = _symbolsLiveData

    init {
        fetchSymbols()
    }

    fun currencyConvert(
        to: String,
        from: String,
        amount: Double
    ) = viewModelScope.launch {
        showLoading.value = true
        conversionUseCase.execute(ConversionUseCase.Params(to = to, from = from, amount = amount))
            .collect { response ->
                showLoading.value = false
                when (response) {
                    is DataState.GenericError -> {
                        response.error?.message?.let { err ->
                            showError.value = err
                        }
                    }
                    is DataState.Success -> {
                        response.value?.let { result ->
                            _currencyConvertResultLiveData.value = result.result ?: 0.0
                        }
                    }
                }
            }
    }

    private fun fetchSymbols(
    ) = viewModelScope.launch {
        showLoading.value = true
        symbolsUseCase.execute(GetSymbolsUseCase.Params()).collect { response ->
            showLoading.value = false
            when (response) {
                is DataState.GenericError -> {
                    response.error?.message?.let { err ->
                        showError.value = err
                    }
                }
                is DataState.Success -> {
                    response.value?.let { result ->
                        result.symbols?.keys?.toList()?.let {
                            _symbolsLiveData.value = it
                        }
                    }
                }
            }
        }
    }
}
