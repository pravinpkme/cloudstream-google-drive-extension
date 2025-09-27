package com.cloudstream.googledrive.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cloudstream.googledrive.R
import com.cloudstream.googledrive.databinding.ActivityMainBinding
import com.cloudstream.googledrive.ui.adapter.VideoAdapter
import com.cloudstream.googledrive.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var videoAdapter: VideoAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupViewModel()
        setupRecyclerView()
        setupClickListeners()
        observeViewModel()
        
        viewModel.loadVideos()
    }
    
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
    
    private fun setupRecyclerView() {
        videoAdapter = VideoAdapter { video ->
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("video_id", video.id)
            intent.putExtra("video_name", video.name)
            intent.putExtra("video_url", video.downloadUrl)
            startActivity(intent)
        }
        
        binding.recyclerViewVideos.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = videoAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.fabRefresh.setOnClickListener {
            viewModel.loadVideos()
        }
        
        binding.buttonAuth.setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }
    }
    
    private fun observeViewModel() {
        viewModel.videos.observe(this) { videos ->
            videoAdapter.submitList(videos)
            binding.progressBar.visibility = View.GONE
        }
        
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        viewModel.error.observe(this) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}
