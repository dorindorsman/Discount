package com.example.discount.scrollbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.discount.ui.theme.ScrollBarColor
import kotlin.math.ceil

fun Modifier.listScrollbar(
    state: LazyListState
): Modifier = composed {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val animationDurationMs = if (state.isScrollInProgress) 150 else 500
    val animationDelayMs = if (state.isScrollInProgress) 0 else 1000
    val color = ScrollBarColor
    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(animationDelayMs, animationDurationMs),
        label = "AnimationPreviewListScrollBar"
    )

    drawWithContent {
        drawContent()
        state.layoutInfo.visibleItemsInfo.firstOrNull()?.let { firstVisibleItem ->
            if (state.isScrollInProgress || alpha > 0f) {
                val firstItemSize = firstVisibleItem.size
                val estimatedFullListSize = firstItemSize * state.layoutInfo.totalItemsCount
                val viewportOffsetInFullListSpace =
                    state.firstVisibleItemIndex * firstItemSize + state.firstVisibleItemScrollOffset
                val knobPosition =
                    (size.height / estimatedFullListSize) * viewportOffsetInFullListSpace
                val knobSize = ((size.height * size.height) / estimatedFullListSize)

                drawRoundRect(
                    color = color,
                    topLeft = Offset(size.width - 4.dp.toPx(), knobPosition),
                    size = Size(4.dp.toPx(), knobSize),
                    alpha = alpha,
                    cornerRadius = CornerRadius(4f, 4f),
                )
            }
        }
    }
}

fun Modifier.gridScrollbar(
    state: LazyGridState,
    numberOfColumns: Int
): Modifier = composed {
    val targetAlpha = if (state.isScrollInProgress) 1f else 0f
    val animationDurationMs = if (state.isScrollInProgress) 150 else 500
    val animationDelayMs = if (state.isScrollInProgress) 0 else 1000
    val color = Color(0xFF3A3E44) //fixme
    val alpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = tween(animationDelayMs, animationDurationMs),
        label = "AnimationPreviewGridScrollBar"
    )

    drawWithContent {
        drawContent()
        state.layoutInfo.visibleItemsInfo.firstOrNull()?.let { firstVisibleItem ->
            if (state.isScrollInProgress || alpha > 0f) {
                val firstItemSize = firstVisibleItem.size.height.toFloat()
                val estimatedFullListSize =
                    firstItemSize * (ceil(state.layoutInfo.totalItemsCount.toDouble() / numberOfColumns).toInt())
                val viewportOffsetInFullListSpace =
                    ((state.firstVisibleItemIndex / numberOfColumns) * firstItemSize) + (state.firstVisibleItemScrollOffset)
                val knobPosition =
                    (firstItemSize / estimatedFullListSize) * viewportOffsetInFullListSpace * numberOfColumns
                val knobSize =
                    ((size.height * size.height) / estimatedFullListSize)

                drawRoundRect(
                    color = color,
                    topLeft = Offset(size.width - 4.dp.toPx(), knobPosition),
                    size = Size(4.dp.toPx(), knobSize),
                    alpha = alpha,
                    cornerRadius = CornerRadius(4f, 4f),
                )
            }
        }
    }
}