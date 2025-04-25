package cvut.zan.myimdb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cvut.zan.myimdb.movie.domain.models.Movie
import cvut.zan.myimdb.movie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    private fun fetchDiscoverMovie() = viewModelScope.launch {
        repository.fetchDiscoverMovie()
    }
}

data class HomeState(
    val discoverMovies:List<Movie> = emptyList(),
    val trendingMovies:List<Movie> = emptyList(),
    val error: String? = null,
    val isLoading:Boolean = false
)