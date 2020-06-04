package com.example.musicplayer

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.webkit.PermissionRequest
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.adapters.SongListAdapter
import com.example.musicplayer.modle.SongModel
import kotlinx.android.synthetic.main.activity_main.*

import android.Manifest
import java.util.Arrays.sort

class MainActivity : AppCompatActivity() {
    var songModleData:ArrayList<SongModel> = ArrayList()
    var songListAdapter:SongListAdapter?=null

    companion object{
         val PERRMISSION_REQUEST_CODE = 12
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(applicationContext,"on create kbkb " , Toast.LENGTH_SHORT).show()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERRMISSION_REQUEST_CODE)
        }
        else loadData()

//        for(i  in 0 until  songModleData.size step 3){
//            Toast.makeText(applicationContext , songModleData[i].mSongName,Toast.LENGTH_SHORT).show()
//        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERRMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(grantResults.isNotEmpty()) {
                Toast.makeText(applicationContext, "Permission Granted ", Toast.LENGTH_SHORT).show()
                loadData()
            }
        }
        else{
            loadData()
        }
    }
    fun loadData(){

        var songCursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null)
        //Toast.makeText(applicationContext,"load data   " , Toast.LENGTH_SHORT).show()

        while(songCursor!=null && songCursor.moveToNext()){
          //  Toast.makeText(applicationContext,"load data ${songModleData.size} " , Toast.LENGTH_SHORT).show()

            var song_name = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
            var song_duration =  songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
            var songPath = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA))
            if(song_duration.toInt() > 0)
                 songModleData.add(SongModel(song_name, song_duration, songPath))
           // songModleData.add(SongModel(song_name , song_duration))
        }
       // Toast.makeText(applicationContext,)
       // songModleDat=   songModleData.sortedWith(compareBy({ it.mSongDuration }))
        songListAdapter = SongListAdapter(songModleData , applicationContext )
        var layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = songListAdapter

        Toast.makeText(applicationContext,"${songModleData.size} " , Toast.LENGTH_SHORT).show()

    }
}
