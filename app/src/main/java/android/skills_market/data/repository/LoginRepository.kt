package android.skills_market.data.repository

import android.skills_market.data.network.AuthApiService
import android.skills_market.data.network.models.requests.AuthRequest
import android.skills_market.data.network.models.responses.AuthResponse
import retrofit2.Call
import retrofit2.Response

interface LoginRepository {
    suspend fun login(authRequest: AuthRequest): Response<AuthResponse>
}

class NetworkLoginRepository(
    private val authApiService: AuthApiService
) : LoginRepository {
    override suspend fun login(authRequest: AuthRequest): Response<AuthResponse> =
        authApiService.login(authRequest)
}