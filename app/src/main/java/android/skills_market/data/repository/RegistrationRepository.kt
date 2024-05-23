package android.skills_market.data.repository

import android.skills_market.data.network.AuthApiService
import android.skills_market.data.network.models.requests.AuthRequest
import android.skills_market.data.network.models.responses.AuthResponse
import retrofit2.Call

interface RegistrationRepository {
    suspend fun register(
        login: String,
        password: String,
        secondName: String,
        firstName: String,
        patronymicName: String,
        birthDate: String,
        university: String,
        institute: String,
        phoneNumber: String = "",
        aboutMe: String = "",
        gender: String,
        city: String,
        direction: String,
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
    )
}

class NetworkRegistrationRepository() : RegistrationRepository {
    override suspend fun register(
        login: String,
        password: String,
        secondName: String,
        firstName: String,
        patronymicName: String,
        birthDate: String,
        university: String,
        institute: String,
        phoneNumber: String,
        aboutMe: String,
        gender: String,
        city: String,
        direction: String,
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
    ) {

    }
}