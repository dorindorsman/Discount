package com.example.discount.cnn

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.discount.remote.ArticleFeed
import com.example.discount.remote.FeedParser
import com.example.discount.remote.OkHttpWebService
import com.example.discount.repository.LinkRepository
import com.example.discount.website.WebViewLegalLauncher
import com.example.discount.welcome.WelcomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

class CnnViewModel(
    private val okHttpWebService: OkHttpWebService,
    private val linkRepository: LinkRepository,
    private val webViewLegalLauncher: WebViewLegalLauncher,
) : ViewModel() {

    companion object {
        const val TAG = "CnnViewModel"
    }

    init {
        Log.d(TAG, "dorin")
        start()
    }

    private var itemsTravel by mutableStateOf(emptyList<ArticleFeed>())
    private var itemsSportEntertainment by mutableStateOf(emptyList<ArticleFeed>())

    fun handle(event: CnnEvent) {
        Log.d(WelcomeViewModel.TAG, "event: $event")

        when (event) {
            is CnnEvent.WebItemClicked -> navigateToWeb(event.link)
        }
    }

    private fun navigateToWeb(link: String) {
        Log.d(TAG, "navigateToWeb $link")
        linkRepository.setLink(link)
        webViewLegalLauncher.startEulaActivity(url = link)
    }

    fun isGridTab(type: Source) : Boolean {
        return when (type) {
            Source.TRAVEL -> true
            Source.SPORT_ENTERTAINMENT -> false
        }
    }

    fun getItemsByType(type: Source): List<ArticleFeed> {
        return when (type) {
            Source.TRAVEL -> getTravelItems()
            Source.SPORT_ENTERTAINMENT -> getSportEntertainmentItems()
        }
    }

    private suspend fun fetchArticlesFeed(url : String): List<ArticleFeed> =
        coroutineScope {
            val results = mutableListOf<ArticleFeed>()
            val jobs = mutableListOf<Job>()

            val job =
                launch {
                    val xmlString = okHttpWebService.getXMlString(url)
                    val articleFeeds = FeedParser().parse(xmlString)
                    results.addAll(articleFeeds)
                }

            jobs.add(job)

            jobs.joinAll()
            Log.d(TAG, results.toString())
            return@coroutineScope results
        }


    private fun getTravelItems() = itemsTravel

    private fun getSportEntertainmentItems() = itemsSportEntertainment

    private fun start() {
        Log.d(TAG, "start CnnViewModel")
        viewModelScope.launch(Dispatchers.IO) {
            itemsTravel = fetchArticlesFeed("http://rss.cnn.com/rss/cnn_travel.rss")
            itemsSportEntertainment = fetchArticlesFeed("http://rss.cnn.com/rss/edition_sport.rss") + fetchArticlesFeed("http://rss.cnn.com/rss/edition_entertainment.rss")
        }
    }

    fun stop() {
        Log.d(TAG, "stop CnnViewModel")
    }

}