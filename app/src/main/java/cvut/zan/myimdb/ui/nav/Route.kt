package cvut.zan.myimdb.ui.nav

import cvut.zan.myimdb.utils.EP

sealed class Route {
    data class HomeScreen(val route: String = "homeScreen") : Route()
    data class FilmScreen(
        val route: String = "FilmScreen",
        val routeWithArgs: String = "$route/{${EP.MOVIE_ID}}",
    ) : Route() {
        fun getRouteWithArgs(id: Int): String {
            return "$route/$id"
        }
    }
}