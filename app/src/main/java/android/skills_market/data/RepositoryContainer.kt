package android.skills_market.data

import android.skills_market.data.constants.URLs
import android.skills_market.data.repository.LoginRepository
import android.skills_market.data.repository.FirebaseLoginRepository
import android.skills_market.data.repository.FirebaseRegistrationRepository
import android.skills_market.data.repository.RegistrationRepository
import android.skills_market.data.network.AuthApiService
import android.skills_market.data.repository.FirebaseSearchRepository
import android.skills_market.data.repository.SearchRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RepositoryContainer {
    val loginRepository: LoginRepository
    val registrationRepository: RegistrationRepository
    val searchRepository: SearchRepository
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
        FirebaseLoginRepository()
    }
    override val registrationRepository: RegistrationRepository by lazy {
        FirebaseRegistrationRepository()
    }
    override val searchRepository: SearchRepository by lazy {
        FirebaseSearchRepository()
    }
}

