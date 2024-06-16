package com.iscoding.mytask.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetryInterceptor(private val maxRetries: Int, private val retryIntervalSeconds: Long) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var response: Response? = null
        var tryCount = 0

        while (tryCount < maxRetries && (response == null || !response.isSuccessful)) {
            try {
                response = chain.proceed(request)
            } catch (e: IOException) {
                if (tryCount >= maxRetries) {
                    throw e
                }
            }

            tryCount++

            if (response == null || !response.isSuccessful) {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(retryIntervalSeconds))
                } catch (ie: InterruptedException) {
                    Thread.currentThread().interrupt()
                    throw IOException(ie)
                }
            }
        }

        if (response == null) {
            throw IOException("Failed to execute request after $maxRetries attempts")
        }

        return response
    }
}