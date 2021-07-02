package com.rafal.currencyconverterretrofitmvvm.model

data class CurrencyResponse(
    val amount: Double,
    val base: String,
    val date: String,
    val rates: Rates
)