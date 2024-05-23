package android.skills_market.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacancyModel(
    val id: String = "-1",
    val company: CompanyModel = CompanyModel("-1", ""),
    val position: String = "",
    val salary: Int = -1,
    val edArea: String = "",
    val formOfEmployment: String = "",
    val requirements: String = "",
    val location: String = "",
    val about: String = "",
    val liked: Boolean = false
) : Parcelable