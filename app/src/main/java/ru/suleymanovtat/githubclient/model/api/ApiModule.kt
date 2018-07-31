package ru.suleymanovtat.githubclient.model.api


import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Arrays
import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.suleymanovtat.githubclient.BuildConfig
import java.io.IOException

object ApiModule {

    val NETWORK_EXCEPTIONS = Arrays.asList<Class<out IOException>>(
            UnknownHostException::class.java,
            SocketTimeoutException::class.java,
            ConnectException::class.java
    ) as List<Class<*>>

    val apiInterface: ApiInterface
        get() {

            val httpClient = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

            val builder = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            return builder.build().create(ApiInterface::class.java)
        }
}