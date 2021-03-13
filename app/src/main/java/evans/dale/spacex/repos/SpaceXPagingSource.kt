package evans.dale.spacex.repos

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import evans.dale.spacex.service.LaunchInfo
import evans.dale.spacex.service.SpaceXService

class SpaceXPagingSource(
        private val spaceXService: SpaceXService
) : PagingSource<Int, LaunchInfo>() {

    override fun getRefreshKey(state: PagingState<Int, LaunchInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LaunchInfo> {
        val nextPageNumber = params.key ?: 0
        val response = spaceXService.getLaunches(50, nextPageNumber)

        return LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 1
        )
    }
}