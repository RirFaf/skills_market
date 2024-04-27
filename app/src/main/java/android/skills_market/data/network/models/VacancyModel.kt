package android.skills_market.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacancyModel(
    val id: Int = 0,
    val position: String,
    val salary: Int,
    val companyName: String,
    val edArea: String,
    val formOfEmployment: String,
    val requirements: String,
    val location: String,
    val about: String,
    val liked: Boolean = false
) : Parcelable