package android.skills_market.data.network.models

sealed interface VacancyFilter {
    data class BySalary(
        val from: Int = -1,
        val to: Int = Int.MAX_VALUE
    ) : VacancyFilter

    data object None : VacancyFilter
}