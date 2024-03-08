package com.example.discount.cnn

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.discount.cnn.cnn_tab.CnnTabItem
import com.example.discount.cnn.cnn_tab.CnnTabView
import com.example.discount.ui.theme.CnnBlueColor
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CnnView(
    viewModel: CnnViewModel
) {

    val tabs = listOf(CnnTabItem.Received, CnnTabItem.Send)
    val pagerState = rememberPagerState { tabs.size }
    val coroutineScope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val (
            tabsRow,
            tabsContent
        ) = createRefs()

        TabsRow(
            Modifier
                .constrainAs(tabsRow) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(parent.top)
                },
            tabs = tabs,
            pagerState = pagerState,
            coroutineScope = coroutineScope
        )
        TabsContent(
            modifier = Modifier
                .constrainAs(tabsContent) {
                linkTo(
                    start = parent.start,
                    end = parent.end
                )
                top.linkTo(tabsRow.bottom)
            },
            viewModel = viewModel,
            tabs = tabs,
            pagerState = pagerState
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsRow(
    modifier: Modifier,
    tabs: List<CnnTabItem>,
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        contentColor = Color.Gray,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions)
                    .height(3.dp)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            val isSelected = pagerState.currentPage == index
            Tab(
                modifier = Modifier.testTag(tab.id),
                selected = isSelected,
                selectedContentColor = CnnBlueColor,
                unselectedContentColor = Color.DarkGray,
                text = {
                    Text(
                        text = stringResource(id = tab.title),
                        style = if (isSelected) {
                            MaterialTheme.typography.body1.copy(
                                color = CnnBlueColor,
                                fontSize = 18.sp
                            )
                        } else {
                            MaterialTheme.typography.body1.copy(
                                color = Color.DarkGray
                            )
                        },
                    )
                },
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(
    modifier: Modifier,
    viewModel: CnnViewModel,
    tabs: List<CnnTabItem>,
    pagerState: PagerState,
) {

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        beyondBoundsPageCount = tabs.size
    ) { page ->
        CnnTabView(
            modifier = Modifier,
            isGrid = viewModel.isGridTab(tabs[page].type),
            cnnItems = viewModel.getItemsByType(tabs[page].type),
            onItemClick = {
                viewModel.handle(CnnEvent.WebItemClicked(it))
            },
            numberOfColumns = 2
        )
    }
}

