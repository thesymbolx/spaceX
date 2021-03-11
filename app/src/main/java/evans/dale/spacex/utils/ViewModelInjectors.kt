package evans.dale.spacex.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import evans.dale.spacex.MainActivityVM
import evans.dale.spacex.repos.SpaceXRepo
import evans.dale.spacex.service.SpaceXService

class ViewModelInjectors {
    class MainActivityInjector(
            private val application:Application,
            private val spaceXRepo: SpaceXRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityVM(application, spaceXRepo) as T
        }
    }
}