package com.timilehinaregbesola.cryptoconverter.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timilehinaregbesola.cryptoconverter.data.models.ConversionState
import com.timilehinaregbesola.cryptoconverter.data.models.Rates
import com.timilehinaregbesola.cryptoconverter.util.Resource
import kotlinx.coroutines.launch
import kotlin.math.round

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _conversion = mutableStateOf(ConversionState())
    val conversion: State<ConversionState> = _conversion

    fun convert(amount: String, fromCurrency: String, toCurrency: String) {
        val fromAmount = amount.toFloatOrNull()
        if (fromAmount == null) {
            _conversion.value = _conversion.value.copy(error = "Invalid amount supplied")
        }

        viewModelScope.launch {
            when (val ratesResponse = repository.getRates(fromCurrency)) {
                is Resource.Error -> _conversion.value = _conversion.value.copy(error = ratesResponse.message!!)
                is Resource.Success -> {
                    val rates = ratesResponse.data!!.rates
                    val rate = getRateForCrypto(toCurrency, rates)
                    if (rate == null) {
                        _conversion.value = _conversion.value.copy(error = "Unexpected error")
                    } else {
                        val convertedCurrency = round(fromAmount!! * rate * 100) / 100
                        _conversion.value = _conversion.value.copy(
                            coin = "$fromAmount $fromCurrency = $convertedCurrency $toCurrency"
                        )
                    }
                }
            }
        }
    }

    private fun getRateForCrypto(toCurrency: String, rates: Rates) = when (toCurrency) {
        "ADA" -> rates.ADA
        "BCD" -> rates.BCD
        "BCH" -> rates.BCH
        "BNB" -> rates.BNB
        "BTC" -> rates.BTC
        "BTCA" -> rates.BTCA
        "DOGE" -> rates.DOGE
        "DRGN" -> rates.DRGN
        "EOS" -> rates.EOS
        "ERT" -> rates.ERT
        "ETC" -> rates.ETC
        "ETH" -> rates.ETH
        "LEO" -> rates.LEO
        "LINDA" -> rates.LINDA
        "LINK" -> rates.LINK
        "LTC" -> rates.LTC
        "LUN" -> rates.LUN
        "MANA" -> rates.MANA
        "MIOTA" -> rates.MIOTA
        "TESLA" -> rates.TESLA
        "THC" -> rates.THC
        "THETA" -> rates.THETA
        "THS" -> rates.THS
        "TRUMP" -> rates.TRUMP
        "TRX" -> rates.TRX
        "USDT" -> rates.USDT
        "WAVES" -> rates.WAVES
        "WAX" -> rates.WAX
        "WTC" -> rates.WTC
        "XLM" -> rates.XLM
        "XTZ" -> rates.XTZ
        "XMR" -> rates.XMR
        "XRP" -> rates.XRP
        else -> null
    }
}
