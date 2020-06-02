package com.example.musicplayer.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.musicplayer.adapters.SongListAdapter

class PlayMusicService: Service() {
    var currenPosition:Int = 0
    var musicDataList : ArrayList<String> = ArrayList()
    var mp : MediaPlayer ?=null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        currenPosition = intent!!.getIntExtra(SongListAdapter.MUSICITEMPOS,0)
        musicDataList = intent!!.getStringArrayListExtra(SongListAdapter.MUSICLEST)

        if(mp!=null){
            mp!!.stop()
            mp!!.release()
            mp = null
        }
        mp = MediaPlayer()

        mp!!.setDataSource(musicDataList[currenPosition])
        mp!!.prepare()
        mp!!.setOnPreparedListener{
            mp!!.start()
        }

        return super.onStartCommand(intent, flags, startId)
    }

}