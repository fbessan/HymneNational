package com.besco.innova.hymnenational;

/**
 * Created by fbessan on 13/11/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageButton play,stop;
    public ImageView image_name;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        play = (ImageButton)itemView.findViewById(R.id.play);
        stop = (ImageButton)itemView.findViewById(R.id.stop);
        image_name = (ImageView)itemView.findViewById(R.id.image_name);
    }

    @Override
    public void onClick(View view) {
        //Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}