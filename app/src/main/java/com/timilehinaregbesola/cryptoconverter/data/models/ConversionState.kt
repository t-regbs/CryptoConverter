package com.timilehinaregbesola.cryptoconverter.data.models

data class ConversionState(
    val isLoading: Boolean = false,
    val coin: String = "",
    val error: String = ""
)
