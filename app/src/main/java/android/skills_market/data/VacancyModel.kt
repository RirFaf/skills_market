package android.skills_market.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VacancyModel(
    val position: String,
    val salary: Int,
    val companyName: String,
    val edArea: String,
    val formOfEmployment: String,
    val requirements: String,
    val location: String,
    val about: String = "",
) : Parcelable