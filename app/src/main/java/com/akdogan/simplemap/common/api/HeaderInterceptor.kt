package com.akdogan.simplemap.common.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderAuthorizationInterceptor(private val token: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(HEADER_KEY_AUTHORIZATION, token)

        return chain.proceed(requestBuilder.build())
    }

    companion object {
        private const val HEADER_KEY_AUTHORIZATION = "Authorization"
    }
}