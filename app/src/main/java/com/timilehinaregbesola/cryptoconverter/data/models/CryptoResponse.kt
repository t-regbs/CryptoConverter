package com.timilehinaregbesola.cryptoconverter.data.models

data class CryptoResponse(
    val timestamp: Int,
    val target: String,
    val rates: Rates
)
