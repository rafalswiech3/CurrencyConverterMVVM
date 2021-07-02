package com.rafal.currencyconverterretrofitmvvm.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("/latest")
    fun getRates(
        @Query("from") from : String,
        @Query("amount") amount : String
    ) : Call<CurrencyResponse>
}