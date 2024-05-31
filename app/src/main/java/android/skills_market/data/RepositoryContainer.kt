package android.skills_market.data

import android.skills_market.data.constants.URLs
import android.skills_market.data.repository.LoginRepository
import android.skills_market.data.repository.FirebaseLoginRepository
import android.skills_market.data.repository.FirebaseRegistrationRepository
import android.skills_market.data.repository.RegistrationRepository
import android.skills_market.data.network.AuthApiService
import android.skills_market.data.repository.FavouritesRepository
import android.skills_market.data.repository.FirebaseFavouritesRepository
import android.skills_market.data.repository.FirebaseMessengerRepository
import android.skills_market.data.repository.FirebaseResponsesRepository
import android.skills_market.data.repository.FirebaseSearchRepository
import android.skills_market.data.repository.MessengerRepository
import android.skills_market.data.repository.ResponsesRepository
import android.skills_market.data.repository.SearchRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RepositoryContainer {
    val loginRepository: LoginRepository
    val registrationRepository: RegistrationRepository
    val searchRepository: SearchRepository
    val favouritesRepository: FavouritesRepository
    val messengerRepository: MessengerRepository
    val responsesRepository: ResponsesRepository
}

class DefaultRepositoryContainer : RepositoryContainer {
    private lateinit var authApiService: AuthApiService
    init{
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
    override val favouritesRepository: FavouritesRepository by lazy {
        FirebaseFavouritesRepository()
    }
    override val messengerRepository: MessengerRepository by lazy {
        FirebaseMessengerRepository()
    }
    override val responsesRepository: ResponsesRepository by lazy {
        FirebaseResponsesRepository()
    }
}


//if (!::authApiService.isInitialized) {
//    val retrofit = Retrofit.Builder()
//        .baseUrl(URLs.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    authApiService = retrofit.create(AuthApiService::class.java)
//}