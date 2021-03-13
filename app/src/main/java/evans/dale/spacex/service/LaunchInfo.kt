package evans.dale.spacex.service

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.util.*

data class LaunchInfo(
        @SerializedName("mission_name")
        val missionName: String,
        val rocket: Rocket,
        @SerializedName("launch_date_utc")
        val launchDate: Date,
        @SerializedName("launch_date_unix")
        val unixTimeStamp: Long,
        @SerializedName("launch_success")
        val launchSuccess: Boolean,
        val links: Links,
        @SerializedName("launch_year")
        val launchYear: Int
)

data class Rocket(
        @SerializedName("rocket_name")
        val name: String,
        @SerializedName("rocket_type")
        val type: String
)

data class Links(
        @SerializedName("mission_patch_small")
        val smallMissionPatch: String,
        val wikipedia: String,
        @SerializedName("video_link")
        val video: String,
        @SerializedName("article_link")
        val articleLink: String
)