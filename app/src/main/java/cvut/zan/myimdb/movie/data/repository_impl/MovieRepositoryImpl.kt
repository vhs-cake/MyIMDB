package cvut.zan.myimdb.movie.data.repository_impl

import cvut.zan.myimdb.common.data.ApiMapper
import cvut.zan.myimdb.movie.data.remote.api.MovieApiService
import cvut.zan.myimdb.movie.data.remote.models.MovieDto
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.movie.domain.repository.MovieRepository
import cvut.zan.myimdb.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val apiMapper: ApiMapper<List<Movie>, MovieDto>
):MovieRepository {
    override fun fetchDiscoverMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchDiscoverMovie()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e -> emit(Response.Error(e)) }

    override fun fetchTrendingMovie(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieApiService.fetchTrendingMovie()
        apiMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e -> emit(Response.Error(e)) }
}