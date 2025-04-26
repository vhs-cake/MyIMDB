package cvut.zan.myimdb.ui.detail.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.movieDetail.domain.models.MovieDetail

@Composable
fun DetailBodyContent(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail,
    movies: List<Movie>,
    isMovieLoading: Boolean,
    fetchMovies: () -> Unit,
    onMovieClick: (Int) -> Unit,
    onActorClick: (Int) -> Unit,
) {

}