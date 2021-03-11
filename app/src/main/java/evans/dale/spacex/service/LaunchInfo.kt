package evans.dale.spacex.service

import com.google.gson.annotations.SerializedName

data class LaunchInfo(
    @SerializedName("mission_name")
    val missionName: String,
    val rocket: Rocket
)

data class Rocket(
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String
)