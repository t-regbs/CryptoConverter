package com.timilehinaregbesola.cryptoconverter.main

import com.timilehinaregbesola.cryptoconverter.data.CryptoApi
import com.timilehinaregbesola.cryptoconverter.data.models.CryptoResponse
import com.timilehinaregbesola.cryptoconverter.util.Resource

class DefaultRepository(private val api: CryptoApi) : MainRepository {
    override suspend fun getRates(target: String): Resource<CryptoResponse> {
        return try {
            val response = api.getRates(target)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}
