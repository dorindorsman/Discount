package com.example.discount.cnn.cnn_item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.discount.cnn.generateRandomColor
import com.example.discount.remote.ArticleFeed

@Composable
fun CnnListItemView(
    cnnItem: ArticleFeed,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(color = generateRandomColor())
            .fillMaxWidth()
            .clickable {
                onItemClick(cnnItem.feedItem.link)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = cnnItem.feedItem.image,
            contentDescription = cnnItem.feedTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(4.dp)
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = cnnItem.feedItem.title,
                style = MaterialTheme.typography.h5,
                maxLines = 2
            )
            Text(
                text = cnnItem.feedItem.pubDate,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
