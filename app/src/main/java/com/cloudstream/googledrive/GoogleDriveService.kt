package com.cloudstream.googledrive

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.google.api.services.drive.model.File
import com.google.api.services.drive.model.FileList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*

data class DriveVideo(
    val id: String,
    val name: String,
    val description: String?,
    val thumbnailUrl: String?,
    val downloadUrl: String,
    val createdTime: String?,
    val modifiedTime: String?,
    val size: Long?,
    val duration: Int?,
    val mimeType: String
)

class GoogleDriveService(private val apiKey: String) {
    private var driveService: Drive? = null
    private var accessToken: String? = null
    
    companion object {
        private const val APPLICATION_NAME = "CloudStream Google Drive Extension"
        private val JSON_FACTORY = GsonFactory.getDefaultInstance()
        private val HTTP_TRANSPORT: NetHttpTransport = GoogleNetHttpTransport.newTrustedTransport()
        private val SCOPES = listOf(DriveScopes.DRIVE_READONLY)
    }
    
    suspend fun initialize(): Boolean = withContext(Dispatchers.IO) {
        try {
            // Initialize with API key for public access
            val credential = GoogleCredential.Builder()
                .setTransport(HTTP_TRANSPORT)
                .setJsonFactory(JSON_FACTORY)
                .build()
                .setAccessToken(apiKey)
            
            driveService = Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build()
            
            accessToken = apiKey
            true
        } catch (e: Exception) {
            false
        }
    }
    
    suspend fun getVideoFiles(): List<DriveVideo> = withContext(Dispatchers.IO) {
        try {
            if (driveService == null) {
                initialize()
            }
            
            val files = mutableListOf<DriveVideo>()
            var pageToken: String? = null
            
            do {
                val result: FileList = driveService!!.files().list()
                    .setQ("mimeType contains 'video/' and trashed = false")
                    .setFields("nextPageToken, files(id, name, description, thumbnailLink, webContentLink, createdTime, modifiedTime, size, videoMediaMetadata, mimeType)")
                    .setPageToken(pageToken)
                    .execute()
                
                result.files?.forEach { file ->
                    if (isVideoFile(file)) {
                        files.add(mapToDriveVideo(file))
                    }
                }
                
                pageToken = result.nextPageToken
            } while (pageToken != null)
            
            files
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun searchVideos(query: String): List<DriveVideo> = withContext(Dispatchers.IO) {
        try {
            if (driveService == null) {
                initialize()
            }
            
            val files = mutableListOf<DriveVideo>()
            val result: FileList = driveService!!.files().list()
                .setQ("mimeType contains 'video/' and name contains '$query' and trashed = false")
                .setFields("files(id, name, description, thumbnailLink, webContentLink, createdTime, modifiedTime, size, videoMediaMetadata, mimeType)")
                .execute()
            
            result.files?.forEach { file ->
                if (isVideoFile(file)) {
                    files.add(mapToDriveVideo(file))
                }
            }
            
            files
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getVideoById(id: String): DriveVideo = withContext(Dispatchers.IO) {
        try {
            if (driveService == null) {
                initialize()
            }
            
            val file = driveService!!.files().get(id)
                .setFields("id, name, description, thumbnailLink, webContentLink, createdTime, modifiedTime, size, videoMediaMetadata, mimeType")
                .execute()
            
            if (isVideoFile(file)) {
                mapToDriveVideo(file)
            } else {
                throw Exception("File is not a video")
            }
        } catch (e: Exception) {
            throw Exception("Failed to get video: ${e.message}")
        }
    }
    
    fun getAccessToken(): String? = accessToken
    
    private fun isVideoFile(file: File): Boolean {
        val mimeType = file.mimeType ?: return false
        return mimeType.startsWith("video/")
    }
    
    private fun mapToDriveVideo(file: File): DriveVideo {
        val duration = file.videoMediaMetadata?.durationMillis?.let { 
            (it / 1000).toInt() 
        }
        
        return DriveVideo(
            id = file.id ?: "",
            name = file.name ?: "Unknown",
            description = file.description,
            thumbnailUrl = file.thumbnailLink,
            downloadUrl = file.webContentLink ?: "",
            createdTime = file.createdTime?.toString(),
            modifiedTime = file.modifiedTime?.toString(),
            size = file.size,
            duration = duration,
            mimeType = file.mimeType ?: ""
        )
    }
}
