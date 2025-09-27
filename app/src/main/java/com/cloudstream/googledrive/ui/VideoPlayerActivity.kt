package com.cloudstream.googledrive.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cloudstream.googledrive.R
import com.cloudstream.googledrive.databinding.ActivityVideoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.HttpDataSource

class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding
    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val videoId = intent.getStringExtra("video_id")
        val videoName = intent.getStringExtra("video_name")
        val videoUrl = intent.getStringExtra("video_url")
        
        if (videoUrl.isNullOrEmpty()) {
            Toast.makeText(this, "Invalid video URL", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        binding.textViewVideoTitle.text = videoName ?: "Unknown Video"
        initializePlayer(videoUrl)
    }
    
    private fun initializePlayer(videoUrl: String) {
        try {
            val httpDataSourceFactory = DefaultHttpDataSource.Factory()
                .setUserAgent("CloudStream Google Drive Extension")
                .setAllowCrossProtocolRedirects(true)
            
            val mediaSourceFactory = DefaultMediaSourceFactory(httpDataSourceFactory)
            
            player = ExoPlayer.Builder(this)
                .setMediaSourceFactory(mediaSourceFactory)
                .build()
                .apply {
                    val mediaItem = MediaItem.fromUri(videoUrl)
                    setMediaItem(mediaItem)
                    playWhenReady = this@VideoPlayerActivity.playWhenReady
                    seekTo(currentWindow, playbackPosition)
                    prepare()
                }
            
            binding.playerView.player = player
            
            player?.addListener(object : Player.Listener {
                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    when (playbackState) {
                        Player.STATE_READY -> {
                            binding.progressBar.visibility = View.GONE
                        }
                        Player.STATE_BUFFERING -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        Player.STATE_ENDED -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            })
            
        } catch (e: Exception) {
            Toast.makeText(this, "Error initializing player: ${e.message}", Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
        }
    }
    
    override fun onStart() {
        super.onStart()
        if (player == null) {
            val videoUrl = intent.getStringExtra("video_url")
            if (!videoUrl.isNullOrEmpty()) {
                initializePlayer(videoUrl)
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        if (player == null) {
            val videoUrl = intent.getStringExtra("video_url")
            if (!videoUrl.isNullOrEmpty()) {
                initializePlayer(videoUrl)
            }
        }
    }
    
    override fun onPause() {
        super.onPause()
        if (player != null) {
            playbackPosition = player?.currentPosition ?: 0L
            currentWindow = player?.currentMediaItemIndex ?: 0
            playWhenReady = player?.playWhenReady ?: true
        }
        releasePlayer()
    }
    
    override fun onStop() {
        super.onStop()
        releasePlayer()
    }
    
    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentWindow = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }
    
    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}
