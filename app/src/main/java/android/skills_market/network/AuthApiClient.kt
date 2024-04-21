package android.skills_market.network

import android.skills_market.data.constants.URLs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthApiClient {
    private lateinit var authApiService: AuthApiService

    fun getApiService(): AuthApiService {
        if (!::authApiService.isInitialized) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            authApiService = retrofit.create(AuthApiService::class.java)
        }
        return authApiService
    }
}