package evans.dale.spacex

import com.google.gson.GsonBuilder
import evans.dale.spacex.repos.SpaceXRepo
import evans.dale.spacex.service.SpaceXService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// DI Container
class AppContainer {

    private val retrofit: Retrofit by lazy {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v3/")
            .callFactory(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val spaceXService: SpaceXService by lazy {
        retrofit.create(SpaceXService::class.java)
    }

     val spaceXRepo: SpaceXRepo by lazy {
        SpaceXRepo(spaceXService)
    }
}