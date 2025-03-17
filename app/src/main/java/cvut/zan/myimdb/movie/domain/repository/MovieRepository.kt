package cvut.zan.myimdb.movie.domain.repository

import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchDiscoverMovie(): Flow<Response<List<Movie>>>
    fun fetchTrendingMovie(): Flow<Response<List<Movie>>>
}