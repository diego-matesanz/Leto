package com.dmatesanz.leto.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUtil {

    val instance by lazy { getRetrofit() }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantsUtil.RAWG_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("X-RapidAPI-Key", ConstantsUtil.RAPID_API_KEY)
                    .addHeader("X-RapidAPI-Host", ConstantsUtil.RAPID_API_HOST)
                chain.proceed(request.build())
            }.build())
            .build()
    }
}
