package com.lagradost.cloudstream3.providers

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import com.lagradost.cloudstream3.network.CloudflareKiller
import org.jsoup.nodes.Document
import java.net.URLEncoder

class GoogleDriveProvider : MainAPI() {
    override var name = "Google Drive"
    override var mainUrl = "https://drive.google.com"
    override val hasMainPage = true
    override val supportedTypes = setOf(TvType.Movie)
    override val hasDownloadSupport = true
    override val hasQuickSearch = true
    
    // Remove hardcoded API key for security
    private val apiKey = "YOUR_GOOGLE_DRIVE_API_KEY"
    
    private fun extractFileIdFromUrl(url: String): String? {
        return when {
            url.contains("/file/d/") -> {
                val regex = "/file/d/([a-zA-Z0-9-_]+)".toRegex()
                regex.find(url)?.groupValues?.get(1)
            }
            url.contains("id=") -> {
                val regex = "id=([a-zA-Z0-9-_]+)".toRegex()
                regex.find(url)?.groupValues?.get(1)
            }
            else -> url // Assume it's already a file ID
        }
    }
    
    private fun getDirectVideoUrl(fileId: String): String {
        return "https://drive.google.com/uc?export=download&id=$fileId"
    }
    
    private fun getThumbnailUrl(fileId: String): String {
        return "https://drive.google.com/thumbnail?id=$fileId&sz=w300-h200"
    }
    
    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val items = mutableListOf<HomePageList>()
        
        try {
            // For demo purposes, create sample videos
            // In a real implementation, you'd fetch from Google Drive API
            val sampleVideos = listOf(
                createSampleVideo("Sample Video 1", "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms"),
                createSampleVideo("Sample Video 2", "1mGgzxByWPDHJTa7KfuoRweMzKdHxT2bC"),
                createSampleVideo("Sample Video 3", "1v2BjZ7Cp3bVzqJ8YgHp1YGRmS2TnKlMx")
            )
            
            items.add(
                HomePageList(
                    "My Google Drive Videos",
                    sampleVideos
                )
            )
            
            // Add recent videos section
            items.add(
                HomePageList(
                    "Recent Videos", 
                    sampleVideos.take(2)
                )
            )
            
        } catch (e: Exception) {
            // Use proper error handling without log.error
            println("Error loading Google Drive videos: ${e.message}")
        }
        
        return newHomePageResponse(items)
    }
    
    private fun createSampleVideo(name: String, fileId: String): AnimeSearchResponse {
        return newAnimeSearchResponse(
            name = name,
            url = fileId,
            type = TvType.Movie
        ) {
            this.posterUrl = getThumbnailUrl(fileId)
        }
    }
    
    override suspend fun search(query: String): List<SearchResponse> {
        return try {
            // For demo purposes, return filtered sample results
            val allVideos = listOf(
                createSampleVideo("Sample Video 1", "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms"),
                createSampleVideo("Sample Video 2", "1mGgzxByWPDHJTa7KfuoRweMzKdHxT2bC"),
                createSampleVideo("Another Video", "1v2BjZ7Cp3bVzqJ8YgHp1YGRmS2TnKlMx"),
                createSampleVideo("Movie Example", "1x3BjZ7Cp3bVzqJ8YgHp1YGRmS2TnKlMy")
            )
            
            // Filter based on query
            allVideos.filter { video ->
                video.name.contains(query, ignoreCase = true)
            }
        } catch (e: Exception) {
            println("Error searching Google Drive videos: ${e.message}")
            emptyList()
        }
    }
    
    override suspend fun load(url: String): LoadResponse {
        return try {
            val fileId = extractFileIdFromUrl(url) ?: url
            
            newMovieLoadResponse(
                name = "Google Drive Video",
                url = url,
                type = TvType.Movie,
                dataUrl = fileId
            ) {
                this.posterUrl = getThumbnailUrl(fileId)
                this.plot = "Video streaming from Google Drive"
                this.year = 2024
                this.rating = null
                this.duration = null
                this.tags = listOf("Google Drive", "Streaming")
                this.recommendations = emptyList()
            }
        } catch (e: Exception) {
            println("Error loading video: ${e.message}")
            throw Exception("Failed to load video from Google Drive")
        }
    }
    
    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ): Boolean {
        return try {
            val fileId = extractFileIdFromUrl(data) ?: data
            
            // Try to get direct download link
            val directUrl = getDirectVideoUrl(fileId)
            
            // Also try alternative Google Drive streaming URL
            val streamingUrl = "https://drive.google.com/file/d/$fileId/preview"
            val downloadUrl = "https://docs.google.com/uc?export=download&id=$fileId"
            
            // Add multiple link options
            callback(
                ExtractorLink(
                    source = name,
                    name = "Google Drive Direct",
                    url = directUrl,
                    referer = mainUrl,
                    quality = Qualities.Unknown.value,
                    isM3u8 = false
                )
            )
            
            callback(
                ExtractorLink(
                    source = name,
                    name = "Google Drive Stream",
                    url = streamingUrl,
                    referer = mainUrl,
                    quality = Qualities.P720.value,
                    isM3u8 = false
                )
            )
            
            callback(
                ExtractorLink(
                    source = name,
                    name = "Google Drive Download",
                    url = downloadUrl,
                    referer = mainUrl,
                    quality = Qualities.Unknown.value,
                    isM3u8 = false
                )
            )
            
            true
        } catch (e: Exception) {
            println("Error loading video links: ${e.message}")
            false
        }
    }
}
