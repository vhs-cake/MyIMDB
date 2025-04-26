package cvut.zan.myimdb.movieDetail.data.repoImpl

import cvut.zan.myimdb.common.data.ApiMapper
import cvut.zan.myimdb.movie.data.remote.models.MovieDto
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.movie.domain.repository.MovieRepository
import cvut.zan.myimdb.movieDetail.data.remote.api.MovieDetailApiService
import cvut.zan.myimdb.movieDetail.data.remote.models.MovieDetailDto
import cvut.zan.myimdb.movieDetail.domain.models.MovieDetail
import cvut.zan.myimdb.movieDetail.domain.repository.MovieDetailRepository
import cvut.zan.myimdb.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieDetailRepositoryImpl (
    private val movieDetailApiService: MovieDetailApiService,
    private val apiDetailMapper: ApiMapper<MovieDetail, MovieDetailDto>,
    private val apiMovieMapper: ApiMapper<List<Movie>, MovieDto>,
): MovieDetailRepository {
    override fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>> = flow {
        emit(Response.Loading())
        val movieDetailDto = movieDetailApiService.fetchMovieDetail(movieId)
        apiDetailMapper.mapToDomain(movieDetailDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

    override fun fetchMovies(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val movieDto = movieDetailApiService.fetchMovie()
        apiMovieMapper.mapToDomain(movieDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }
}