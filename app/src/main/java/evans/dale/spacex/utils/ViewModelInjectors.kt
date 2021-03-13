package evans.dale.spacex.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import evans.dale.spacex.home.HomeViewModel
import evans.dale.spacex.repos.SpaceXRepo

class ViewModelInjectors {
    class HomeInjector(
            private val application:Application,
            private val spaceXRepo: SpaceXRepo
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(application, spaceXRepo) as T
        }
    }
}