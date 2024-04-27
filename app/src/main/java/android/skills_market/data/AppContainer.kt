package android.skills_market.data

import android.skills_market.data.repository.LoginRepository
import android.skills_market.data.repository.NetworkLoginRepository
import android.skills_market.data.repository.NetworkRegistrationRepository
import android.skills_market.data.repository.RegistrationRepository
import android.skills_market.data.network.AuthApiClient

interface AppContainer {
    val loginRepository: LoginRepository
    val registrationRepository: RegistrationRepository
}

class DefaultAppContainer : AppContainer {
    private val authApiClient = AuthApiClient()

    private val retrofitApiService = authApiClient.getApiService()

    override val loginRepository: LoginRepository by lazy {
        NetworkLoginRepository(retrofitApiService)
    }
    override val registrationRepository: RegistrationRepository by lazy {
        NetworkRegistrationRepository(retrofitApiService)
    }
}