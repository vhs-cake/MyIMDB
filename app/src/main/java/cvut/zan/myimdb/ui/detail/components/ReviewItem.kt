package cvut.zan.myimdb.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cvut.zan.myimdb.movieDetail.domain.models.Review
import cvut.zan.myimdb.ui.CollapsibleText
import cvut.zan.myimdb.ui.home.itemSpacing
import kotlin.math.round

@Composable
fun ReviewItem(
    modifier: Modifier = Modifier,
    review: Review
) {
    Column (modifier){
        val nameAnnotatedString = buildAnnotatedString {
            append(review.author)
            append(" • ")
            append(review.createdAt)
        }
        val ratingAnnotatedString = buildAnnotatedString {
            pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
            append(round(review.rating).toString())
            pop()

            pushStyle(SpanStyle(fontSize = 10.sp))
            append("10")
            pop()
        }
        Text(
            text = nameAnnotatedString,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(itemSpacing))
        CollapsibleText(text = review.content, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(itemSpacing))
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
            Text(text = ratingAnnotatedString, style = MaterialTheme.typography.bodySmall)
        }
    }

}