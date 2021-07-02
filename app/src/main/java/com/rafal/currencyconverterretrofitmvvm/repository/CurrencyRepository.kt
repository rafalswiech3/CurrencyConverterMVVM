package com.rafal.currencyconverterretrofitmvvm.repository

import com.rafal.currencyconverterretrofitmvvm.model.CurrencyApi
import com.rafal.currencyconverterretrofitmvvm.model.CurrencyResponse
import com.rafal.currencyconverterretrofitmvvm.util.Resource
import retrofit2.awaitResponse
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
    private val api: CurrencyApi
) {
    suspend fun getRates(from: String, amount: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(from, amount).awaitResponse()
            val responseBody = response.body()

            if (response.isSuccessful && responseBody != null) {
                Resource.Success(responseBody)
            } else {
                Resource.Fail(response.message())
            }
        } catch (e: Exception) {
            Resource.Fail(e.message ?: "Error")
        }
    }
}