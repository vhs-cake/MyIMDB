package cvut.zan.myimdb.movieDetail.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    @SerialName("cast")
    val cast: List<Cast?>? = null,
    @SerialName("crew")
    val crew: List<Crew?>? = null
)