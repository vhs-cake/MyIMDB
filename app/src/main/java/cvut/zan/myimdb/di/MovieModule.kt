package cvut.zan.myimdb.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cvut.zan.myimdb.common.data.ApiMapper
import cvut.zan.myimdb.movie.data.mapper_impl.MovieApiMapperImpl
import cvut.zan.myimdb.movie.data.remote.api.MovieApiService
import cvut.zan.myimdb.movie.data.remote.models.MovieDto
import cvut.zan.myimdb.movie.data.repository_impl.MovieRepositoryImpl
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.movie.domain.repository.MovieRepository
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
object MovieModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieApiService: MovieApiService,
        mapper:ApiMapper<List<Movie>, MovieDto>
    ):MovieRepository = MovieRepositoryImpl(
        movieApiService, mapper
    )

    @Provides
    @Singleton
    fun provideMovieMapper():ApiMapper<List<Movie>, MovieDto> = MovieApiMapperImpl()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideMovieApiService():MovieApiService{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder().baseUrl(EP.BASE_URL).addConverterFactory(json.asConverterFactory(contentType)).build().create(MovieApiService::class.java)
    }
}