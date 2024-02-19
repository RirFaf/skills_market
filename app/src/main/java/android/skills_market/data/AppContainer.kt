package android.skills_market.data

import android.skills_market.network.ApiClient
import android.skills_market.network.ApiService

interface AppContainer {
    val loginRepository: LoginRepository
}

class DefaultAppContainer : AppContainer {
    private val apiClient = ApiClient()

    private val retrofitApiService = apiClient.getApiService()

    override val loginRepository: LoginRepository by lazy {
        NetworkLoginRepository(retrofitApiService)
    }
}