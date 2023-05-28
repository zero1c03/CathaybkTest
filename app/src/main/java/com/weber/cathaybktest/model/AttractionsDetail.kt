package com.weber.cathaybktest.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AttractionsDetail(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("name_zh") var nameZh: String? = null,
    @SerializedName("open_status") var openStatus: Int? = null,
    @SerializedName("introduction") var introduction: String? = null,
    @SerializedName("open_time") var openTime: String? = null,
    @SerializedName("zipcode") var zipcode: String? = null,
    @SerializedName("distric") var distric: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("tel") var tel: String? = null,
    @SerializedName("fax") var fax: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("months") var months: String? = null,
    @SerializedName("nlat") var nlat: Double? = null,
    @SerializedName("elong") var elong: Double? = null,
    @SerializedName("official_site") var officialSite: String? = null,
    @SerializedName("facebook") var facebook: String? = null,
    @SerializedName("ticket") var ticket: String? = null,
    @SerializedName("remind") var remind: String? = null,
    @SerializedName("staytime") var staytime: String? = null,
    @SerializedName("modified") var modified: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("images") var images: ArrayList<Images>? = null,
    @SerializedName("links") var links: ArrayList<Link>? = null

) : Serializable

data class Images(

    @SerializedName("src") var src: String? = null,
    @SerializedName("subject") var subject: String? = null,
    @SerializedName("ext") var ext: String? = null

) : Serializable

data class Link(
    @SerializedName("src") var src: String? = null,
    @SerializedName("subject") var subject: String? = null,
) : Serializable
