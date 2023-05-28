package com.weber.cathaybktest.apitest

import android.os.Build
import com.weber.cathaybktest.network.TravelTaipeiApi
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class AttractionsRepositoryTest {

    private lateinit var travelTaipeiApi: TravelTaipeiApi

    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.travel.taipei/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        travelTaipeiApi = retrofit.create(TravelTaipeiApi::class.java)
    }

    @Test
    fun testTravelTaipeiApi() {
        val result = runBlocking { travelTaipeiApi.getAttractions("en", 1) }
        assertNotNull(result)
    }
}