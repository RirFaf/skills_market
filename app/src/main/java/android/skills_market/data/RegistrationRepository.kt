package android.skills_market.data

interface RegistrationRepository {
    suspend fun postCredentials()
}

class NetworkRegistrationRepository(
)