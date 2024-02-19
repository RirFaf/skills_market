package android.skills_market.data

import android.skills_market.network.ApiService
import android.skills_market.network.models.AuthRequest
import android.skills_market.network.models.AuthResponse
import android.skills_market.view_model.LoginUIState
import android.telecom.Call

interface LoginRepository {
    suspend fun login(authRequest: AuthRequest): AuthResponse
}

class NetworkLoginRepository(
    private val apiService: ApiService
) : LoginRepository {
    override suspend fun login(authRequest: AuthRequest): AuthResponse =
        apiService.login(authRequest)
}