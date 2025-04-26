package cvut.zan.myimdb.ui.home.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.ui.home.itemSpacing

@Composable
fun BodyContent(
    modifier: Modifier = Modifier,
    discoverMovies: List<Movie>,
    trendingMovies: List<Movie>,
    onMovieClick: (id: Int) -> Unit
) {
    val discoverListState = rememberLazyListState()
    val trendingListState = rememberLazyListState()

    val discoverCenterIndex = rememberSaveable { mutableStateOf(0) }
    val trendingCenterIndex = rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(discoverListState.firstVisibleItemIndex) {
        val newIndex = discoverListState.firstVisibleItemIndex + (discoverListState.layoutInfo.visibleItemsInfo.size / 2)
        discoverCenterIndex.value = newIndex.coerceIn(0, discoverMovies.size - 1)
    }

    LaunchedEffect(trendingListState.firstVisibleItemIndex) {
        val newIndex = trendingListState.firstVisibleItemIndex + (trendingListState.layoutInfo.visibleItemsInfo.size / 2)
        trendingCenterIndex.value = newIndex.coerceIn(0, trendingMovies.size - 1)
    }

    LazyColumn(modifier = modifier) {
        item {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(itemSpacing)
                    .shadow(6.dp, shape = MaterialTheme.shapes.medium)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(itemSpacing),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Discover Movies",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "More discover movies"
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(250.dp)
                    ) {
                        LazyRow(
                            modifier = Modifier.fillMaxHeight(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            state = discoverListState
                        ) {
                            itemsIndexed(discoverMovies) { index, movie ->
                                val isCentered = index == discoverCenterIndex.value
                                val animatedHeight by animateDpAsState(
                                    targetValue = if (isCentered) 250.dp else 220.dp,
                                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                                )
                                MovieCoverImage(
                                    movie = movie,
                                    onMovieClick = onMovieClick,
                                    modifier = Modifier
                                        .height(animatedHeight)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(Color.Gray.copy(alpha = 0.3f))
                                        .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                                )
                            }
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(itemSpacing)
                    .shadow(6.dp, shape = MaterialTheme.shapes.medium)
            ) {
                Column {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(itemSpacing),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Trending Now",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Trending now"
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(250.dp)
                    ) {
                        LazyRow(
                            modifier = Modifier.fillMaxHeight(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            state = trendingListState
                        ) {
                            itemsIndexed(trendingMovies) { index, movie ->
                                val isCentered = index == trendingCenterIndex.value
                                val animatedHeight by animateDpAsState(
                                    targetValue = if (isCentered) 250.dp else 220.dp,
                                    animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
                                )
                                MovieCoverImage(
                                    movie = movie,
                                    onMovieClick = onMovieClick,
                                    modifier = Modifier
                                        .height(animatedHeight)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(Color.Gray.copy(alpha = 0.3f))
                                        .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}