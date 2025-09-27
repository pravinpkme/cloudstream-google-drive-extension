package com.cloudstream.googledrive

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import com.lagradost.cloudstream3.extractors.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class GoogleDriveExtension : MainAPI() {
    override var name = "Google Drive"
    override var mainUrl = "https://drive.google.com"
    override val hasMainPage = true
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)
    override val hasDownloadSupport = true
    override val hasQuickSearch = false
    
    private val apiKey = "AIzaSyAbaRKmjHu-A73tdhEXRatmI5WWk-GOwRI"
    private val driveService = GoogleDriveService(apiKey)
    
    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val items = mutableListOf<HomePageList>()
        
        try {
            val videos = driveService.getVideoFiles()
            if (videos.isNotEmpty()) {
                items.add(
                    HomePageList(
                        "My Google Drive Videos",
                        videos.map { video ->
                            newMovieResponse(
                                name = video.name,
                                posterUrl = video.thumbnailUrl,
                                url = video.id
                            ) {
                                this.posterUrl = video.thumbnailUrl
                                this.plot = video.description
                                this.year = video.createdTime?.substring(0, 4)?.toIntOrNull()
                                this.quality = SearchQuality.Unknown
                            }
                        }
                    )
                )
            }
        } catch (e: Exception) {
            log.error("Error loading Google Drive videos: ${e.message}")
        }
        
        return newHomePageResponse(items)
    }
    
    override suspend fun search(query: String): List<SearchResponse> {
        return try {
            val videos = driveService.searchVideos(query)
            videos.map { video ->
                newMovieResponse(
                    name = video.name,
                    posterUrl = video.thumbnailUrl,
                    url = video.id
                ) {
                    this.posterUrl = video.thumbnailUrl
                    this.plot = video.description
                    this.year = video.createdTime?.substring(0, 4)?.toIntOrNull()
                    this.quality = SearchQuality.Unknown
                }
            }
        } catch (e: Exception) {
            log.error("Error searching Google Drive videos: ${e.message}")
            emptyList()
        }
    }
    
    override suspend fun load(url: String): LoadResponse {
        return try {
            val video = driveService.getVideoById(url)
            newMovieLoadResponse(
                name = video.name,
                url = video.downloadUrl,
                apiName = name,
                type = TvType.Movie
            ) {
                this.posterUrl = video.thumbnailUrl
                this.plot = video.description
                this.year = video.createdTime?.substring(0, 4)?.toIntOrNull()
                this.quality = SearchQuality.Unknown
                this.duration = video.duration
                this.episodes = listOf(
                    newEpisode(
                        name = video.name,
                        url = video.downloadUrl
                    )
                )
            }
        } catch (e: Exception) {
            log.error("Error loading video: ${e.message}")
            throw Exception("Failed to load video")
        }
    }
    
    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        return try {
            val video = driveService.getVideoById(data)
            callback(
                ExtractorLink(
                    name = "Google Drive",
                    url = video.downloadUrl,
                    referer = mainUrl,
                    quality = getQualityFromName(video.name),
                    isM3u8 = false,
                    headers = mapOf("Authorization" to "Bearer ${driveService.getAccessToken()}")
                )
            )
            true
        } catch (e: Exception) {
            log.error("Error loading video links: ${e.message}")
            false
        }
    }
    
    private fun getQualityFromName(name: String): Qualities {
        return when {
            name.contains("4K", ignoreCase = true) || name.contains("2160p", ignoreCase = true) -> Qualities.Unknown
            name.contains("1080p", ignoreCase = true) -> Qualities.FullHd
            name.contains("720p", ignoreCase = true) -> Qualities.Hd
            name.contains("480p", ignoreCase = true) -> Qualities.Sd
            else -> Qualities.Unknown
        }
    }
}
