package com.dev_akash.assignmentlistedapp.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class LinksDto(

    @SerializedName("url_id")
    var urlId: Int? = null,
    @SerializedName("web_link")
    var webLink: String? = null,
    @SerializedName("smart_link")
    var smartLink: String? = null,
    var title: String? = null,
    @SerializedName("total_clicks")
    var totalClicks: Int? = null,
    @SerializedName("original_image")
    var originalImage: String? = null,
    var thumbnail: String? = null,
    @SerializedName("times_ago")
    var timesAgo: String? = null,
    @SerializedName("created_at")
    var createdAt: Date? = null,
    @SerializedName("domain_id")
    var domainId: String? = null,
    @SerializedName("url_prefix")
    var urlPrefix: String? = null,
    @SerializedName("url_suffix")
    var urlSuffix: String? = null,
    var app: String? = null

)