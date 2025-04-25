package cvut.zan.myimdb.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import cvut.zan.myimdb.R
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.ui.home.defaultPadding
import cvut.zan.myimdb.utils.EP

@Composable
fun TopContent(modifier: Modifier = Modifier, movie: Movie, onMovieClick:(id:Int) -> Unit){
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("${EP.BASE_IMAGE_URL}${movie.posterPath}")
        .crossfade(true)
        .build()

    Box(modifier = modifier.fillMaxWidth().clickable { onMovieClick(movie.id) }){
        AsyncImage(
            model = imgRequest,
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
            onError = {
                it.result.throwable.printStackTrace()
            },
            placeholder = painterResource(id = R.drawable.bg_image_movie)
        )
    }
}

@Composable
fun MovieDetail(modifier: Modifier = Modifier, rating: Double, title: String, genre: List<String>) {
    Column(modifier = modifier.padding(defaultPadding)) {

    }

}