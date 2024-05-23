package android.skills_market.data.repository

import android.skills_market.data.network.AuthApiService
import android.skills_market.data.network.models.requests.AuthRequest
import android.skills_market.data.network.models.responses.AuthResponse
import retrofit2.Call
import retrofit2.Response

interface LoginRepository {
    suspend fun login(
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
        login: String,
        password: String
    )
}

class NetworkLoginRepository(
) : LoginRepository {
    override suspend fun login(
        onSuccessAction: () -> Unit,
        onFailureAction: () -> Unit,
        login: String,
        password: String
    ) {
        TODO("Not yet implemented")
    }
}


