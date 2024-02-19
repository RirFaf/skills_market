package android.skills_market.network

import android.skills_market.data.Constants
import android.skills_market.network.models.AuthRequest
import android.skills_market.network.models.AuthResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService{
    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    suspend fun login (@Body request: AuthRequest): AuthResponse

    @POST(Constants.REGISTRATION_URL)
    @FormUrlEncoded
    suspend fun register (@Body request: AuthRequest): AuthResponse
}