package android.skills_market.view_model.event

sealed interface ResumeEvent {
    data class SetSalary(val input: String) : ResumeEvent
    data class SetKeySkills(val input: String) : ResumeEvent
    data object UpdateResumeInfo : ResumeEvent
}