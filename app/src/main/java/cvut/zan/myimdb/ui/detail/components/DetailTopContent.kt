package cvut.zan.myimdb.ui.detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import cvut.zan.myimdb.movieDetail.domain.models.MovieDetail
import cvut.zan.myimdb.utils.EP
import cvut.zan.myimdb.R

@Composable
fun DetailTopContent(
    modifier: Modifier = Modifier,
    movieDetail: MovieDetail
) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("${EP.BASE_IMAGE_URL}${movieDetail.posterPath}")
        .crossfade(true)
        .build()

    Box(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = imgRequest,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize(),
            contentScale = ContentScale.Crop,
            onError = {
                it.result.throwable.printStackTrace()
            },
            placeholder = painterResource(id = R.drawable.bg_image_movie)
        )
    }
}

@Composable
fun MovieDetailComponent(
    modifier: Modifier = Modifier,
    rating: Double,
    releaseDate: String,
    ){

}