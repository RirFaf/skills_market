package android.skills_market.data

import android.skills_market.network.AuthApiService
import android.skills_market.network.models.requests.AuthRequest
import android.skills_market.network.models.responses.AuthResponse
import retrofit2.Call

interface LoginRepository {
    suspend fun login(authRequest: AuthRequest): Call<AuthResponse>
}

class NetworkLoginRepository(
    private val authApiService: AuthApiService
) : LoginRepository {
    override suspend fun login(authRequest: AuthRequest): Call<AuthResponse> =
        authApiService.login(authRequest)
}