package com.app.bankmisr.utils

import com.app.bankmisr.data.CurrencyHistory
import com.app.bankmisr.data.remote.dto.response.history.CurrencyHistoryResponse
import java.text.SimpleDateFormat
import java.util.*

fun getTodayDateFormatted(): String {
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
}

fun getDateAfterThreeDaysFormatted(): String {
    val  currentDate = Date()
    val c = Calendar.getInstance()
    c.time = currentDate
    c.add(Calendar.DAY_OF_MONTH, 3)

    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(c.time)
}

// mapper to convert the hashmap coming from the json object to custom list.
fun getCurrencyCustomList(historyResponse: CurrencyHistoryResponse): List<CurrencyHistory> {
    val currencyList = mutableListOf<CurrencyHistory>()
    val ratesMap = historyResponse.rates
    ratesMap?.let { map ->
        for ((key, value) in map) {
            val currencyHistory = CurrencyHistory(
                date = key,
                currency = value.keys.first(),
                rate = value[value.keys.first()]
            )

            currencyList.add(currencyHistory)
        }
    }

    return currencyList
}