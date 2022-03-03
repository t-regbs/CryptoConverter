package com.timilehinaregbesola.cryptoconverter.main

import com.timilehinaregbesola.cryptoconverter.data.models.CryptoResponse
import com.timilehinaregbesola.cryptoconverter.util.Resource

interface MainRepository {
    suspend fun getRates(target: String): Resource<CryptoResponse>
}
