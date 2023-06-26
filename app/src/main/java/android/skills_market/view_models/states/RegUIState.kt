package android.skills_market.view_models.states

data class RegUIState(
    val patronymic: String = "",
    val name: String = "",
    val surname: String = "",
    val city: String = "",
    val course: Int = 1,
    val email: String = "",
    val password: String = "",
    val phone: String = "",
)