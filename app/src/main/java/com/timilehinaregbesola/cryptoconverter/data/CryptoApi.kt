package com.timilehinaregbesola.cryptoconverter.data

import com.timilehinaregbesola.cryptoconverter.data.models.CryptoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("/live?access_key=dce41459f13f0414a49181142defe018")
    suspend fun getRates(@Query("target") target: String): Response<CryptoResponse>
}
