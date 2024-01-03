package android.skills_market.network

import android.skills_market.network.models.VacanciesModel
import retrofit2.http.GET

interface SearchService {
    @GET("")
    suspend fun getVacancies(): VacanciesModel
    //TODO поиск
}