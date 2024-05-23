package android.skills_market.data

import android.skills_market.data.constants.URLs
import android.skills_market.data.repository.LoginRepository
import android.skills_market.data.repository.NetworkLoginRepository
import android.skills_market.data.repository.NetworkRegistrationRepository
import android.skills_market.data.repository.RegistrationRepository
import android.skills_market.data.network.AuthApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RepositoryContainer {
    val loginRepository: LoginRepository
    val registrationRepository: RegistrationRepository
}

class DefaultRepositoryContainer : RepositoryContainer {
    private lateinit var authApiService: AuthApiService
    init{
        if (!::authApiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            authApiService = retrofit.create(AuthApiService::class.java)
        }
    }
    override val loginRepository: LoginRepository by lazy {
        NetworkLoginRepository()
    }
    override val registrationRepository: RegistrationRepository by lazy {
        NetworkRegistrationRepository()
    }
}

