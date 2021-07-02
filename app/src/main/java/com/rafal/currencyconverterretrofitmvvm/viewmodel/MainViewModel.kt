package com.rafal.currencyconverterretrofitmvvm.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafal.currencyconverterretrofitmvvm.model.Rates
import com.rafal.currencyconverterretrofitmvvm.repository.CurrencyRepository
import com.rafal.currencyconverterretrofitmvvm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: CurrencyRepository
) : ViewModel() {
    private val currencyRates: MutableLiveData<String> = MutableLiveData()

    fun getCurrencyRates(): LiveData<String> = currencyRates

    fun loadRates(from: String, to: String, amount: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = when (val response = repository.getRates(from, amount)) {
                is Resource.Success ->
                    "$amount $from = ${getCurrencyRate(to, response.data!!.rates).toString()} $to"
                is Resource.Fail -> response.message!!
            }
            withContext(Dispatchers.Main) {
                currencyRates.value = result
            }
        }
    }

    private fun getCurrencyRate(rate: String, rates: Rates) = when (rate) {
        "USD" -> rates.USD
        "EUR" -> rates.EUR
        "AUD" -> rates.AUD
        "BGN" -> rates.BGN
        "BRL" -> rates.BRL
        "CAD" -> rates.CAD
        "CHF" -> rates.CHF
        "CNY" -> rates.CNY
        "CZK" -> rates.CZK
        "DKK" -> rates.DKK
        "GBP" -> rates.GBP
        "HKD" -> rates.HKD
        "HRK" -> rates.HRK
        "HUF" -> rates.HUF
        "IDR" -> rates.IDR
        "ILS" -> rates.ILS
        "INR" -> rates.INR
        "ISK" -> rates.ISK
        "JPY" -> rates.JPY
        "KRW" -> rates.KRW
        "MXN" -> rates.MXN
        "MYR" -> rates.MYR
        "NOK" -> rates.NOK
        "NZD" -> rates.NZD
        "PHP" -> rates.PHP
        "PLN" -> rates.PLN
        "RON" -> rates.RON
        "RUB" -> rates.RUB
        "SEK" -> rates.SEK
        "SGD" -> rates.SGD
        "THB" -> rates.THB
        "TRY" -> rates.TRY
        "ZAR" -> rates.ZAR
        else -> rates.USD
    }
}