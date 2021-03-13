package evans.dale.spacex.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import evans.dale.spacex.service.LaunchInfo
import evans.dale.spacex.service.SpaceXService
import kotlinx.coroutines.flow.Flow

class SpaceXRepo(
        private val spaceXService: SpaceXService
) {

    suspend fun getCompanyInfo() = spaceXService.companyInfo()

    fun getLaunches(): Flow<PagingData<LaunchInfo>> =
            Pager(
                    PagingConfig(50, enablePlaceholders = true)
            ) {
                SpaceXPagingSource(spaceXService)
            }.flow

}