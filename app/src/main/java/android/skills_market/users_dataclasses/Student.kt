package android.skills_market.users_dataclasses

data class Student(
    val surname: String,
    val name: String,
    val patronymic: String,
    val city: String,
    val course: Int,
    val email: String,
    val phone: String
)
