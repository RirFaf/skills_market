package android.skills_market.data.network

import android.skills_market.data.constants.URLs
import android.skills_market.data.network.models.requests.AuthRequest
import android.skills_market.data.network.models.responses.AuthResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService{
    @POST("https://sample/api/login")
    suspend fun login (@Body request: AuthRequest): Response<AuthResponse>

    //    @POST(URLs.LOGIN_URL)
//    suspend fun login (@Body request: AuthRequest): Call<AuthResponse>
    @POST(URLs.REGISTRATION_URL)
    suspend fun register (@Body request: AuthRequest): Call<AuthResponse>
}


