package android.skills_market.network

import android.skills_market.data.Constants
import android.skills_market.network.models.requests.AuthRequest
import android.skills_market.network.models.responses.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService{
    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    suspend fun login (@Body request: AuthRequest): Call<AuthResponse>

    @POST(Constants.REGISTRATION_URL)
    @FormUrlEncoded
    suspend fun register (@Body request: AuthRequest): Call<AuthResponse>
}