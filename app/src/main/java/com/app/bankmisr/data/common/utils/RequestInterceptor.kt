package com.app.bankmisr.data.common.utils

import com.app.bankmisr.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        request = setAuthHeader(builder).build()
        return chain.proceed(request)
    }

    private fun setAuthHeader(builder: Request.Builder): Request.Builder {
        builder.addHeader("apiKey", BuildConfig.ApiKey)
        return builder
    }
}