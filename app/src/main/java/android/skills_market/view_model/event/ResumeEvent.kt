package android.skills_market.view_model.event

sealed interface ResumeEvent {
    data class SetAbout(val input: String) : ResumeEvent
    data class SetKeySkills(val input: String) : ResumeEvent
    data class SetCourse(val input: String) : ResumeEvent
    data object SendInfo : ResumeEvent
}