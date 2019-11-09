package com.alifyz.beatbox

class Sound(private val assetPath : String) {
    val name = assetPath.split("/").last().removeSuffix(".WAV")
}