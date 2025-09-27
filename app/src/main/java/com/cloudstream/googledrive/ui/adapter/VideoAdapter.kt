package com.cloudstream.googledrive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cloudstream.googledrive.DriveVideo
import com.cloudstream.googledrive.R
import com.cloudstream.googledrive.databinding.ItemVideoBinding
import java.text.SimpleDateFormat
import java.util.*

class VideoAdapter(
    private val onVideoClick: (DriveVideo) -> Unit
) : ListAdapter<DriveVideo, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class VideoViewHolder(
        private val binding: ItemVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(video: DriveVideo) {
            binding.apply {
                textViewTitle.text = video.name
                textViewDuration.text = formatDuration(video.duration)
                textViewSize.text = formatFileSize(video.size)
                textViewDate.text = formatDate(video.createdTime)
                
                // Load thumbnail
                Glide.with(imageViewThumbnail.context)
                    .load(video.thumbnailUrl)
                    .placeholder(R.drawable.ic_video_placeholder)
                    .error(R.drawable.ic_video_placeholder)
                    .into(imageViewThumbnail)
                
                root.setOnClickListener {
                    onVideoClick(video)
                }
            }
        }
        
        private fun formatDuration(duration: Int?): String {
            if (duration == null) return "Unknown"
            val hours = duration / 3600
            val minutes = (duration % 3600) / 60
            val seconds = duration % 60
            
            return when {
                hours > 0 -> String.format("%d:%02d:%02d", hours, minutes, seconds)
                else -> String.format("%d:%02d", minutes, seconds)
            }
        }
        
        private fun formatFileSize(size: Long?): String {
            if (size == null) return "Unknown"
            val kb = size / 1024.0
            val mb = kb / 1024.0
            val gb = mb / 1024.0
            
            return when {
                gb >= 1 -> String.format("%.1f GB", gb)
                mb >= 1 -> String.format("%.1f MB", mb)
                else -> String.format("%.1f KB", kb)
            }
        }
        
        private fun formatDate(dateString: String?): String {
            if (dateString == null) return "Unknown"
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val date = inputFormat.parse(dateString)
                outputFormat.format(date ?: Date())
            } catch (e: Exception) {
                "Unknown"
            }
        }
    }
    
    class VideoDiffCallback : DiffUtil.ItemCallback<DriveVideo>() {
        override fun areItemsTheSame(oldItem: DriveVideo, newItem: DriveVideo): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: DriveVideo, newItem: DriveVideo): Boolean {
            return oldItem == newItem
        }
    }
}
