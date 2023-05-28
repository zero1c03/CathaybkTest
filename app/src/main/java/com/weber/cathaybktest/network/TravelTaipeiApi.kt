package com.weber.cathaybktest.network

import com.weber.cathaybktest.model.AttractionsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TravelTaipeiApi {
    @GET("/open-api/{lang}/Attractions/all")
    @Headers("Accept: application/json")
    suspend fun getAttractions(
        @Path("lang") lang: String,
        @Query("page") page: Int,
    ): Response<AttractionsData>
}