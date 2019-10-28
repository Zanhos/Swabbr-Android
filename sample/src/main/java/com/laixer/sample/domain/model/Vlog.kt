package com.laixer.sample.domain.model

data class Vlog(
    val userId: String,
    val id: String,
    val duration: String,
    val startDate: String,
    val totalViews: Int,
    val totalReactions: Int,
    val totalLikes: Int,
    val isLive: Boolean,
    val isPrivate: Boolean
) {
    val url: String = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
}
