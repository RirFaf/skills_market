package android.skills_market.data.repository

import android.skills_market.network.AuthApiService
import android.skills_market.network.models.requests.AuthRequest
import android.skills_market.network.models.responses.AuthResponse
import retrofit2.Call

interface RegistrationRepository {
    suspend fun register(authRequest: AuthRequest): Call<AuthResponse>
}

class NetworkRegistrationRepository(
    private val authApiService: AuthApiService
): RegistrationRepository{
    override suspend fun register(authRequest: AuthRequest): Call<AuthResponse> =
        authApiService.register(authRequest)
}