package android.skills_market.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacancyModel(
    val id: String = "-1",
    val company: CompanyModel = CompanyModel("-1", ""),
    val position: String = "-1",
    val salary: Int = -1,
    val edArea: String = "-1",
    val formOfEmployment: String = "-1",
    val requirements: String = "-1",
    val location: String = "-1",
    val about: String = "",
    var liked: Boolean = false
) : Parcelable