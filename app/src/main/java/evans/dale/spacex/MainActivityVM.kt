package evans.dale.spacex

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.cachedIn
import evans.dale.spacex.repos.SpaceXRepo
import evans.dale.spacex.service.CompanyInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityVM(
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
                name, founder, founded.toString(), employees, launchSites.toString(), valuation.toString()
            )
    }


    fun getLaunchItems() = spaceXRepo.getLaunches().cachedIn(viewModelScope)

}