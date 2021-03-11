package evans.dale.spacex.service

import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXService {

    @GET("info")
    suspend fun companyInfo() : CompanyInfo

    @GET("launches")
    suspend fun getLaunches(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : List<LaunchInfo>

}