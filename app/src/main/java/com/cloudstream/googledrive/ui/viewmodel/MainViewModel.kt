package com.cloudstream.googledrive.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudstream.googledrive.DriveVideo
import com.cloudstream.googledrive.GoogleDriveService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val driveService = GoogleDriveService("AIzaSyAbaRKmjHu-A73tdhEXRatmI5WWk-GOwRI")
    
    private val _videos = MutableLiveData<List<DriveVideo>>()
    val videos: LiveData<List<DriveVideo>> = _videos
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    
    fun loadVideos() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = ""
                
                val videosList = driveService.getVideoFiles()
                _videos.value = videosList
            } catch (e: Exception) {
                _error.value = "Failed to load videos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun searchVideos(query: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = ""
                
                val videosList = driveService.searchVideos(query)
                _videos.value = videosList
            } catch (e: Exception) {
                _error.value = "Failed to search videos: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
