package android.skills_market.data

import android.skills_market.network.SearchService
import android.skills_market.network.models.VacanciesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchRepository {
    suspend fun getVacanciesList(): Flow<VacanciesModel>
}

class NetworkSearchRepository(
    private val searchService: SearchService
) : SearchRepository {
    override suspend fun getVacanciesList(): Flow<VacanciesModel> =
        flow { searchService.getVacancies() }

}
