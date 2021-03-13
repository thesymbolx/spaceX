package evans.dale.spacex.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedEventsVM : ViewModel() {
    enum class Sort {
        ASCENDING,
        DESCENDING
    }

    val filterEvent = MutableLiveData<Event<Pair<Int, Sort>>>()
}