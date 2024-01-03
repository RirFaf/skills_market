package android.skills_market.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer {
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = ""/*TODO: Добавить ссылку на API*/

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}