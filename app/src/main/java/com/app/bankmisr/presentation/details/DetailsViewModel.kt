package com.app.bankmisr.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.bankmisr.data.common.utils.DataState
import com.app.bankmisr.data.remote.dto.response.history.CurrencyHistoryResponse
import com.app.bankmisr.domain.usecases.GetCurrencyHistoryUseCase
import com.app.bankmisr.domain.usecases.GetSymbolsUseCase
import com.app.bankmisr.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val historyUseCase: GetCurrencyHistoryUseCase
) : BaseViewModel() {

    private val _currencyHistoryLiveData: MutableLiveData<CurrencyHistoryResponse> =
        MutableLiveData()
    val currencyHistoryLiveData: LiveData<CurrencyHistoryResponse>
        get() = _currencyHistoryLiveData

    fun getCurrencyHistory(
        base : String,
        symbols : String,
        startDate : String,
        endDate : String
    )= viewModelScope.launch {
        showLoading.value = true
        historyUseCase.execute(GetCurrencyHistoryUseCase.Params(base,startDate,endDate, symbols))
            .collect { response ->
                showLoading.value = false
                when (response) {
                    is DataState.GenericError -> {
                        response.error?.error?.errorMessage.let { err ->
                            showError.value = err
                        }
                    }
                    is DataState.Success -> {
                        response.value?.let { result ->
                            _currencyHistoryLiveData.value = result
                        }
                    }
                }
            }
    }

}
