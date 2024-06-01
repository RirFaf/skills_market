package android.skills_market.view_model.event

import android.skills_market.data.network.models.StudentModel

sealed interface ProfileEvent {
    data object UpdateUserInfo : ProfileEvent
    data class SetUniversity(val input: String) : ProfileEvent
    data class SetInstitute(val input: String) : ProfileEvent
    data class SetDirection(val input: String) : ProfileEvent
    data class SetCourse(val input: String) : ProfileEvent
    data class SetPhoneNumber(val input: String) : ProfileEvent
    data class SetAboutMe(val input: String) : ProfileEvent
}