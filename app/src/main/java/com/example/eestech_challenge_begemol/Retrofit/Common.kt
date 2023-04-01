package com.example.eestech_challenge_begemol.Retrofit

object Common {
    private val BASE_URL = "http://80.64.174.33:8080/api/v2/" //TODO name server
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}