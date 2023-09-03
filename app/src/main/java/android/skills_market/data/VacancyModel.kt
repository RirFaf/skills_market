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
    val about: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus suscipit mi et lacus finibus finibus. Curabitur laoreet convallis varius. Etiam convallis vehicula blandit. Ut convallis odio nunc, at congue dui tempus sit amet. Aliquam porttitor, ex eget placerat placerat, augue sapien pretium eros, a lobortis quam lorem at lectus. Curabitur suscipit, elit ac vulputate porta, justo lacus tempus eros, ut tincidunt lorem mi ac tellus. Integer tempor dignissim arcu vitae maximus. Nam consectetur, lectus quis maximus dapibus, lorem odio pellentesque nisl, sagittis feugiat mauris purus ut diam. Nullam ultricies id massa in volutpat. Fusce at ullamcorper magna, non pulvinar magna. Mauris ultrices ex id consectetur ornare. Donec lobortis libero a arcu lobortis viverra ut et augue. Praesent auctor gravida finibus. Curabitur maximus pulvinar ipsum, ut hendrerit quam gravida euismod. Sed fermentum sapien et lorem efficitur, at ultricies odio semper.",
) : Parcelable