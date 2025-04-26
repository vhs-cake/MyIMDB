package cvut.zan.myimdb.movieDetail.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Reviews(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<Result?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)