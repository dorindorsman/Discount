package com.example.discount.cnn.cnn_tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.discount.R
import com.example.discount.cnn.cnn_item.CnnGridItemView
import com.example.discount.cnn.cnn_item.CnnListItemView
import com.example.discount.remote.ArticleFeed
import com.example.discount.scrollbar.gridScrollbar

@Composable
fun CnnTabView(
    modifier: Modifier,
    isGrid: Boolean,
    cnnItems: List<ArticleFeed>,
    numberOfColumns: Int,
    onItemClick: (String) -> Unit
) {
    if (cnnItems.isEmpty()) {
        CnnEmptyView()
    } else {
        if (isGrid) {
            CnnGridView(
                modifier = modifier,
                cnnItems = cnnItems,
                numberOfColumns = numberOfColumns,
                onItemClick = onItemClick,
            )
        } else {
            CnnListView(
                modifier = modifier,
                cnnItems = cnnItems,
                onItemClick = onItemClick,
            )
        }
    }

}

@Composable
fun CnnEmptyView() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.animation_empty_feed
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp)
            .semantics(mergeDescendants = true) {},
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .size(300.dp, 150.dp)
                .padding(bottom = 8.dp),
        )
    }
}

@Composable
fun CnnGridView(
    modifier: Modifier,
    cnnItems: List<ArticleFeed>,
    numberOfColumns: Int,
    onItemClick: (String) -> Unit
) {
    val gridState = rememberLazyGridState()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(numberOfColumns),
            modifier = modifier
                .fillMaxSize()
                .gridScrollbar(gridState, numberOfColumns),
            state = gridState,
            contentPadding = PaddingValues(
                top = 8.dp,
                bottom = 65.dp,
                start = 4.dp,
                end = 4.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cnnItems) { item ->
                CnnGridItemView(
                    cnnItem = item,
                    onClick = {
                        onItemClick(item.feedItem.link)
                    }
                )
                Spacer(modifier = Modifier.size(2.dp))
            }
        }
    }
}

@Composable
fun CnnListView(
    modifier: Modifier,
    cnnItems: List<ArticleFeed>,
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .fillMaxSize(),
        contentPadding = PaddingValues(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 50.dp)
    ) {
        items(cnnItems.size) { item ->
            CnnListItemView(cnnItems[item]) {
                onItemClick(cnnItems[item].feedItem.link)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

//Text(
//text = stringResource(R.string.loading_data),
//style = MaterialTheme.typography.body1,
//color = Color.DarkGray,
//textAlign = TextAlign.Center
//)
//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
//@Composable
//fun FeedTabViewPreview() {
//    MdeTheme {
//        Surface {
//            FeedTabView(
//                modifier = Modifier,
//                isReceived = true,
//                items = FeedItemProvider.getFeedItemList(),
//                emptyIconSize = DpSize(120.dp, 90.dp),
//                numberOfColumns = 4,
//                onItemClick = {},
//                onCancelClick = {},
//                onErrorClick = {},
//                horizontalContentPadding = 20.dp,
//            )
//        }
//    }
//}
//
//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
//@Composable
//fun FeedTabViewEmptyPreview() {
//    MdeTheme {
//        Surface {
//            FeedTabView(
//                modifier = Modifier,
//                isReceived = true,
//                items = listOf(),
//                emptyIconSize = DpSize(200.dp, 100.dp),
//                numberOfColumns = 4,
//                onItemClick = {},
//                onCancelClick = {},
//                onErrorClick = {},
//                horizontalContentPadding = 20.dp,
//            )
//        }
//    }
//}