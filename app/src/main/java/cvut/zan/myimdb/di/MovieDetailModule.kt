package cvut.zan.myimdb.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cvut.zan.myimdb.common.data.ApiMapper
import cvut.zan.myimdb.movie.data.remote.models.MovieDto
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.movieDetail.data.mapperImpl.MovieDetailMapperImpl
import cvut.zan.myimdb.movieDetail.data.remote.api.MovieDetailApiService
import cvut.zan.myimdb.movieDetail.data.remote.models.MovieDetailDto
import cvut.zan.myimdb.movieDetail.data.repoImpl.MovieDetailRepositoryImpl
import cvut.zan.myimdb.movieDetail.domain.models.MovieDetail
import cvut.zan.myimdb.movieDetail.domain.repository.MovieDetailRepository
import cvut.zan.myimdb.utils.EP
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        movieDetailApiService: MovieDetailApiService,
        mapper: ApiMapper<MovieDetail, MovieDetailDto>,
        movieMapper: ApiMapper<List<Movie>, MovieDto>
    ): MovieDetailRepository = MovieDetailRepositoryImpl(
    movieDetailApiService = movieDetailApiService,
    apiDetailMapper = mapper,
    apiMovieMapper = movieMapper,
    )

    @Provides
    @Singleton
    fun provideMovieMapper(): ApiMapper<MovieDetail, MovieDetailDto> = MovieDetailMapperImpl()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideMovieDetailApiService(): MovieDetailApiService {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(EP.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(MovieDetailApiService::class.java)
    }
}