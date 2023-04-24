package com.dev_akash.assignmentlistedapp.data

import com.google.gson.annotations.SerializedName

data class DashBoardResponse(

    var status: Boolean? = null,
    var statusCode: Int? = null,
    var message: String? = null,
    @SerializedName("support_whatsapp_number")
    var supportWhatsappNumber: String? = null,
    @SerializedName("extra_income")
    var extraIncome: Double? = null,
    @SerializedName("total_links")
    var totalLinks: Int? = null,
    @SerializedName("total_clicks")
    var totalClicks: Int? = null,
    @SerializedName("today_clicks")
    var todayClicks: Int? = null,
    @SerializedName("top_source")
    var topSource: String? = null,
    @SerializedName("top_location")
    var topLocation: String? = null,
    var startTime: String? = null,
    @SerializedName("links_created_today")
    var linksCreatedToday: Int? = null,
    @SerializedName("applied_campaign")
    var appliedCampaign: Int? = null,
    @SerializedName("data")
    var data: Data? = null

)

data class Data(
    @SerializedName("recent_links")
    var recentLinks: ArrayList<LinksDto> = arrayListOf(),
    @SerializedName("top_links")
    var topLinks: ArrayList<LinksDto> = arrayListOf(),
    @SerializedName("overall_url_chart")
    var overallUrlChart: HashMap<String, Int>? = null

)