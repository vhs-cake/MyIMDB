package cvut.zan.myimdb.movie.remote.api

import cvut.zan.myimdb.BuildConfig
import cvut.zan.myimdb.movie.remote.models.MovieDto
import cvut.zan.myimdb.utils.Const
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(Const.MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ):MovieDto

    @GET(Const.TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMovie(
        @Query("api_key") apiKey: String = BuildConfig.apiKey,
        @Query("include_adult") includeAdult: Boolean = false
    ):MovieDto
}