package evans.dale.spacex.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import evans.dale.spacex.R
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue


class LaunchVM(
        val missionName: String,
        launchDate: Long,
        val patchImageURL: String,
        successful: Boolean?,
        rocketName: String,
        rocketType: String,
        application: Application
) : AndroidViewModel(application) {

    private val localDateTime: LocalDateTime =
            LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(launchDate),
                    ZoneId.systemDefault()
            )

    val launchDayTime: String
        get() {
            val formattedDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val formattedTime = DateTimeFormatter.ofPattern("HH:mm")
            return "${formattedDay.format(localDateTime)} at ${formattedTime.format(localDateTime)}"
        }


    val daysToLaunch: String
        get() {
            val application = getApplication<Application>()
            val noOfDaysBetween = ChronoUnit.DAYS.between(ZonedDateTime.now().toLocalDate(), localDateTime.toLocalDate())

            return application.getString(
                    R.string.launch_day,
                    noOfDaysBetween.absoluteValue.toString(),
                    when {
                        noOfDaysBetween == 0L -> application.getString(R.string.launch_today)
                        noOfDaysBetween > 0L -> application.getString(R.string.launch_from)
                        noOfDaysBetween < 0L -> application.getString(R.string.launch_since)
                        else -> ""
                    }
            )
        }

    val successIcon: Int? =
            when (successful) {
                true -> R.drawable.ic_tick_sign
                false -> R.drawable.ic_cross
                else -> null
            }

    val rocketInfo = "$rocketName / $rocketType"

}