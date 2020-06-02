package com.example.musicplayer.modle

import java.time.Duration

class SongModel(songName:String , songDuration: String , songPath:String) {

    val mSongPath: String = songPath
    var mSongName = songName
    var mSongDuration:String = songdrt(songDuration)

    fun songdrt(songDuration:String):String{
        var k = songDuration.toInt()
        var min = k/1000/60
        var sec = (k/1000) % 60
        var ans = min.toString() +':'
        if(sec < 10) ans += '0'
        ans += sec.toString()
        return ans
    }
}

