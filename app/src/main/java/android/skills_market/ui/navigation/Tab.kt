package android.skills_market.ui.navigation

sealed class Tab(val route: String){
    object SearchTab: Tab ("search_tab")
}
