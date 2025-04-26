package cvut.zan.myimdb.movieDetail.data.remote.api

import cvut.zan.myimdb.BuildConfig
import cvut.zan.myimdb.movie.data.remote.models.MovieDto
import cvut.zan.myimdb.movieDetail.data.remote.models.MovieDetailDto
import cvut.zan.myimdb.utils.EP
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val MOVIE_ID = "movie_id"

interface MovieDetailApiService {
    @GET("${EP.MOVIE_DETAIL_ENDPOINT}/{$MOVIE_ID}")
    suspend fun fetchMovieDetail(
        @Path(MOVIE_ID) movieId:Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("append_to_response") appendToResponse: String = "credits,reviews"
    ):MovieDetailDto

    @GET (EP.MOVIE_ENDPOINT)
    suspend fun fetchMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult: Boolean = false
    ) : MovieDto
}