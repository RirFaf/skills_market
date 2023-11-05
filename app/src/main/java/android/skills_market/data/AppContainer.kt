package android.skills_market.data

import android.skills_market.network.SearchService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer {
    val searchRepository: SearchRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = ""/*TODO: Добавить ссылку на API*/

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    private val retrofitSearchService: SearchService by lazy {
        retrofit.create(SearchService::class.java)
    }

    override val searchRepository: SearchRepository by lazy {
        NetworkSearchRepository(retrofitSearchService)
    }
}