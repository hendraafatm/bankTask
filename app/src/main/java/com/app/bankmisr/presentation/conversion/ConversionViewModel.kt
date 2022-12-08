package com.app.bankmisr.presentation.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.dto.response.CurrencyConvertResponse
import com.app.bankmisr.domain.usecases.ConversionUseCase
import com.app.bankmisr.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversionViewModel @Inject constructor(
    private val useCase: ConversionUseCase
) : BaseViewModel() {

    private var _currencyConvertResponse: MutableLiveData<CurrencyConvertResponse> =
        MutableLiveData()
    val currencyConvertResponse: LiveData<CurrencyConvertResponse>
        get() = _currencyConvertResponse

    fun currencyConvert(
        to: String,
        from: String,
        amount: Double
    ) = viewModelScope.launch {
        showLoading.value = true
        useCase.execute(ConversionUseCase.Params(to = to, from= from, amount = amount)).collect { response ->
            showLoading.value = false
            when (response) {
                is DataState.GenericError -> {
                    response.error?.error?.errorMessage.let { err ->
                        showError.value = err
                    }
                }
                is DataState.Success -> {
                    response.value?.let { result ->
                        _currencyConvertResponse.value = result
                    }
                }
            }
        }
    }
}
