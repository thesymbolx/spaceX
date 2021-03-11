package evans.dale.spacex.service

import com.google.gson.annotations.SerializedName

data class CompanyInfo(
    val name: String,
    val founder: String,
    val founded: Int,
    val employees: String,
    @SerializedName("launch_sites")
    val launchSites: Int,
    val valuation: Long
)