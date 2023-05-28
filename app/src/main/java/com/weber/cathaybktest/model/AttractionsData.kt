package com.weber.cathaybktest.model

import com.google.gson.annotations.SerializedName


data class AttractionsData(
    @SerializedName("total") var total: Int? = null,
    @SerializedName("data") var attractionsDetailList: List<AttractionsDetail> = arrayListOf()
)