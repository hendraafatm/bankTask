package com.app.bankmisr.data.common.utils

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
        builder.addHeader("apiKey", "HO2QvZxJ7U1uGHAW1oOrxtL2mJjSXBfd")
        return builder
    }
}