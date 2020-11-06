package com.besco.innova.hymnenational;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;


import java.io.IOException;
import java.util.List;

import static android.R.id.button1;
import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Listeobject> itemList;
    private Context context;
    MediaPlayer mediaPlayer;
    String MY_PREFS_NAME = "MyPrefsFile";

    public RecyclerViewAdapter(Context context, List<Listeobject> itemList) {
        this.itemList = itemList;
        this.context = context;

    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_liste_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {
        //holder.play.setText(itemList.get(position).getFilename());
        holder.image_name.setImageResource(itemList.get(position).getImagename());
        holder.stop.setVisibility(View.GONE);

        holder.play.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                mediaPlayerStoping();
                playLastStoredAudioMusic(itemList.get(position).getFilename());
                holder.stop.setVisibility(View.VISIBLE);
                holder.play.setVisibility(View.GONE);




            }
        });

        holder.stop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                mediaPlayerStoping();
                holder.stop.setVisibility(View.GONE);
                holder.play.setVisibility(View.VISIBLE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    private void playLastStoredAudioMusic(String voiceStoragePath){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(voiceStoragePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

    }

    private void stopAudioPlay(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void mediaPlayerStoping(){
        if(mediaPlayer!= null && !mediaPlayer.isPlaying()){

            stopAudioPlay();
        }
    }
}