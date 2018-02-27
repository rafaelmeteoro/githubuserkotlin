package com.meteoro.githubkotlin.core.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by meteoro on 22/02/2018.
 **/
class ApiClientUtil(private val builder: Builder) {

    fun <T> create(type: Class<T>): T {

        val clientBuilder = OkHttpClient.Builder()

        if (builder.enableLogging) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }

        clientBuilder.connectTimeout(builder.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(builder.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(builder.writeTimeout, TimeUnit.SECONDS)

        val client = clientBuilder.build()
        val retrofit = Retrofit.Builder()
                .baseUrl(builder.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        return retrofit.create(type)
    }
}

data class Builder(
        val baseUrl: String,
        val enableLogging: Boolean = false,
        val readTimeout: Long = 30,
        val writeTimeout: Long = 30,
        val connectTimeout: Long = 30
)