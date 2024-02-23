package android.skills_market.data

import android.skills_market.network.AuthApiClient

interface AppContainer {
    val loginRepository: LoginRepository
}

class DefaultAppContainer : AppContainer {
    private val authApiClient = AuthApiClient()

    private val retrofitApiService = authApiClient.getApiService()

    override val loginRepository: LoginRepository by lazy {
        NetworkLoginRepository(retrofitApiService)
    }
}