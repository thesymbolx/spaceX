package evans.dale.spacex

import evans.dale.spacex.repos.SpaceXRepo
import evans.dale.spacex.service.SpaceXService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// DI Container
class AppContainer {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .callFactory(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val spaceXService: SpaceXService by lazy {
        retrofit.create(SpaceXService::class.java)
    }

     val spaceXRepo: SpaceXRepo by lazy {
        SpaceXRepo(spaceXService)
    }
}