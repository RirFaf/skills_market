package android.skills_market.network

import android.skills_market.data.constants.URLs
import android.skills_market.network.models.requests.AuthRequest
import android.skills_market.network.models.responses.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService{
    @POST(URLs.LOGIN_URL)
    suspend fun login (@Body request: AuthRequest): Call<AuthResponse>

    @POST(URLs.REGISTRATION_URL)
    suspend fun register (@Body request: AuthRequest): Call<AuthResponse>
}