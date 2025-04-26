package cvut.zan.myimdb.movieDetail.domain.repository

import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.movieDetail.domain.models.MovieDetail
import cvut.zan.myimdb.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>>
    fun fetchMovies(): Flow<Response<List<Movie>>>
}