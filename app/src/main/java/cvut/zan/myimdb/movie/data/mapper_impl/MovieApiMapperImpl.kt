package cvut.zan.myimdb.movie.data.mapper_impl

import cvut.zan.myimdb.common.data.ApiMapper
import cvut.zan.myimdb.movie.data.remote.models.MovieDto
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.utils.GenreConsts

class MovieApiMapperImpl : ApiMapper<List<Movie>, MovieDto> {
    override fun mapToDomain(apiDto: MovieDto): List<Movie> {
        return apiDto.results?.map {result -> Movie(
            backdropPath = formatEmptyValue(result?.backdropPath),
            genreIds = formatGenre(result?.genreIds),
            id = result?.id ?: 0,
            originalLanguage = formatEmptyValue(result?.originalLanguage, "originalLanguage"),
            originalTitle = formatEmptyValue(result?.originalTitle, "title"),
            overview = formatEmptyValue(result?.overview, "overview"),
            popularity = result?.popularity ?: 0.0,
            posterPath = formatEmptyValue(result?.posterPath),
            releaseDate = formatEmptyValue(result?.releaseDate, "date"),
            title = formatEmptyValue(result?.title, "title"),
            voteAverage = result?.voteAverage ?: 0.0,
            voteCount = result?.voteCount ?: 0,
            video = result?.video ?: false,
        )} ?: emptyList()
    }
    private fun formatEmptyValue(value: String?, default: String = ""): String {
        if (value.isNullOrEmpty()) return "Unknown $default"
        return value
    }

    private fun formatGenre(genreIds: List<Int?>?): List<String> {
        return genreIds?.map { GenreConsts.getGenreNameById(it ?: 0)} ?: emptyList()
    }
}