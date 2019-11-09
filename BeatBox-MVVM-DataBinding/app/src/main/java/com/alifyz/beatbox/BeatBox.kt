package com.alifyz.beatbox

import android.content.res.AssetManager
import android.util.Log
import java.lang.Exception

private const val TAG = "BeatBox"
private const val PATH = "sounds"

class BeatBox(private val assets : AssetManager) {

    val sounds : List<Sound>

    init {
        sounds = loadSoundsNames()
    }

    fun loadSoundsNames() : List<Sound> {
        return try {
            val soundNames = assets.list(PATH)

            val sounds = mutableListOf<Sound>()

            soundNames?.forEach { fileName ->
                val assetPath = "$PATH/$fileName"
                val sound = Sound(assetPath)
                sounds.add(sound)
            }
            return sounds

        } catch(e : Exception) {
            Log.e(TAG, "Error loading the sounds assets")
            emptyList()
        }
    }
}