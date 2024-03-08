package com.example.discount.cnn.cnn_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.discount.cnn.generateRandomColor
import com.example.discount.remote.ArticleFeed

@Composable
fun CnnGridItemView(
    cnnItem: ArticleFeed,
    onClick: () -> Unit,
) {

    ConstraintLayout(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(color = generateRandomColor())
            .padding(4.dp)
            .semantics(mergeDescendants = true) { }
            .clickable(onClick = onClick)
    ) {
        val (image, text) = createRefs()
        AsyncImage(
            model = cnnItem.feedItem.image,
            contentDescription = cnnItem.feedTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier.constrainAs(image){}
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            modifier = Modifier
                .constrainAs(text) {
                    linkTo(
                        start = image.start,
                        end = image.end,
                        top = image.bottom,
                        bottom = parent.bottom
                    )
                }
                .padding(bottom = 8.dp, top = 4.dp),
            text = cnnItem.feedItem.title,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSecondary,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }



}


//@Composable
//fun ArticleItem(item: Article, onItemClicked: (Article) -> Unit) {
//    Card(
//        modifier = Modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .wrapContentHeight(),
//        shape = MaterialTheme.shapes.medium,
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 2.dp
//        ),
//        onClick = {
//            onItemClicked.invoke(item)
//        }
//    ) {
//        Column {
//            AsyncImage(
//                model = item.imageUrl,
//                contentDescription = item.title,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
//            Text(text = item.title, style = Typography.titleMedium, modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp))
//            Text(text = item.description, style = Typography.bodyMedium, modifier = Modifier.padding(16.dp))
//        }
//    }
//}

//
//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
//@Composable
//fun FeedItemViewPreview() {
//    MdeTheme {
//        Surface {
//            FeedItemView(
//                viewModel = viewModel(
//                    factory = FeedItemViewModelFactory()
//                ),
//                feedItem = FeedItemProvider.getFeedItemList()[1],
//                onClick = { },
//                onCancelClick = { },
//                onErrorClick = { },
//            )
//        }
//    }
//}