package com.example.musicplayer.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.musicplayer.MainActivity
import com.example.musicplayer.R
import com.example.musicplayer.SinglePlayer
import com.example.musicplayer.interfase.CostomItemClickListener
import com.example.musicplayer.modle.SongModel
import com.example.musicplayer.service.PlayMusicService
import kotlin.math.cos

class SongListAdapter(SongModel:ArrayList<SongModel> , context: Context):RecyclerView.Adapter<SongListAdapter.SongListViewHolder>(){
    var mSongModel = SongModel
    var mContext = context
    val allMusicList:ArrayList<String> = ArrayList()

    companion object{
        val MUSICLEST = "musiclist"
        val MUSICITEMPOS = "pos"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {

        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.music_row , parent , false)
        return SongListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mSongModel.size

    }

    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        var model = mSongModel[position]
        var songName = model.mSongName
        var songDuration = model.mSongDuration
        holder!!.songTV.text = songName
        holder.durationTv.text = songDuration
        holder.setOnCostomItemClickListener(object:CostomItemClickListener{

            override fun onCostomClic(view: View, pos: Int) {
                for(i in 0 until mSongModel.size){
                    allMusicList.add(mSongModel[i].mSongPath)
                }
                Log.i("allmusiclist" , allMusicList.toString())
                Toast.makeText(mContext,"id ${pos}  songTitle : " + songName,Toast.LENGTH_SHORT).show()

                /*
                var musicDataIntent = Intent(mContext ,PlayMusicService::class.java)
                musicDataIntent.putStringArrayListExtra(MUSICLEST , allMusicList)
                musicDataIntent.putExtra(MUSICITEMPOS , pos)
                mContext.startService(musicDataIntent)
                */

               // var intent= Intent(this@MainActivity , SinglePlayer.class)

            }
        })

    }

    class SongListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var songTV:TextView
        var durationTv:TextView
        var album: ImageView
        var mcostomItemClickListener : CostomItemClickListener?=null


        init{
            songTV = itemView.findViewById(R.id.song_name_tv)
            durationTv = itemView.findViewById(R.id.song_duration_tv)
            album = itemView.findViewById(R.id.al_img_view )
            itemView.setOnClickListener(this)
        }

        fun setOnCostomItemClickListener(costomItemClickListener: CostomItemClickListener){
            this.mcostomItemClickListener = costomItemClickListener
        }

        override fun onClick(view:View?){
            this.mcostomItemClickListener!!.onCostomClic(view!!,adapterPosition)
        }

    }
}