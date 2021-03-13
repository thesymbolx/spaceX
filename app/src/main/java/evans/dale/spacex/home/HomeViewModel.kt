package evans.dale.spacex.home

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.cachedIn
import evans.dale.spacex.R
import evans.dale.spacex.repos.SpaceXRepo
import evans.dale.spacex.service.CompanyInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class HomeViewModel(
    application: Application,
    private val spaceXRepo: SpaceXRepo
) : AndroidViewModel(application), LifecycleObserver {

    val companyInfo = MutableLiveData<String>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun getCompanyInfo() =
        CoroutineScope(Dispatchers.IO).launch {
            val spaceXCompanyInfo = spaceXRepo.getCompanyInfo()
            companyInfo.postValue(getCompanyString(spaceXCompanyInfo))
        }

    private fun getCompanyString(info: CompanyInfo) =
        info.run {
            getApplication<Application>().getString(
                R.string.company_info,
                name, founder,
                founded.toString(),
                employees,
                launchSites.toString(),
                NumberFormat.getCurrencyInstance(Locale.US).format(valuation)
            )
        }

    fun getLaunchItems() = spaceXRepo.getLaunches().cachedIn(viewModelScope)
}