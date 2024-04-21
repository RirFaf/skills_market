package android.skills_market.view_model.event

sealed interface ResumeEvent {
    data class SetUniversity(val input: String) : ResumeEvent
    data class SetFaculty(val input: String) : ResumeEvent
    data class SetAbout(val input: String) : ResumeEvent
    data class SetExperience(val input: String) : ResumeEvent
    data class SetCourse(val input: String) : ResumeEvent
    data object SendInfo : ResumeEvent
}