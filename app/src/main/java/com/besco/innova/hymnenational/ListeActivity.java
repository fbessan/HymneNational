package com.besco.innova.hymnenational;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.besco.innova.hymnenational.R.id.volumeSeekbar;

/**
 * Created by fbessan on 13/11/2016.
 */

public class ListeActivity extends AppCompatActivity {
    private LinearLayoutManager lLayout;
    SeekBar volumeSeekbar;
    private AudioManager audioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        setTitle(null);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Liste");
        }
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        List<Listeobject> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(ListeActivity.this);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(ListeActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case android.R.id.home:
                Intent i = new Intent(com.besco.innova.hymnenational.ListeActivity.this, com.besco.innova.hymnenational.MainActivity.class);
                startActivity(i);
                finish();
                return true;
            case R.id.action_liste:
                Intent intent = new Intent(com.besco.innova.hymnenational.ListeActivity.this, com.besco.innova.hymnenational.ListeActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                initControls();
                break;
            case R.id.menu_item_histoire:
                Intent intenthistoire = new Intent(com.besco.innova.hymnenational.ListeActivity.this, com.besco.innova.hymnenational.HistoireActivity.class);
                startActivity(intenthistoire);
                break;
            case R.id.action_help:
                Intent intentaide = new Intent(com.besco.innova.hymnenational.ListeActivity.this, com.besco.innova.hymnenational.AideActivity.class);
                startActivity(intentaide);
                break;
            case R.id.menu_item_shared:
                //Partager
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Apprenez et chantez avec nous l'Hymne National du Bénin (L'Aube Nouvelle). Application disponible gratuitement sur PlayStore (Lien vers play store)";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hymne National");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Partager via"));
                break;
            case R.id.action_contact:
                //Contacter
                initapropos();
                break;
            default:
                break;
        }

        return true;
    }

    private List<Listeobject> getAllItemList(){

        List<Listeobject> listeObject = Listeobject.findWithQuery(Listeobject.class, "SELECT * FROM Listeobject ORDER BY datecreation DESC", null);
        List<Listeobject> allItems = new ArrayList<Listeobject>();
        for(int i = 0; i < listeObject.size(); i++) {
            allItems.add(new Listeobject(listeObject.get(i).getImagename(),listeObject.get(i).getDescription(),listeObject.get(i).getFilename(),listeObject.get(i).getDatecreation()));
        }

        return allItems;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    private void initapropos(){
        //Contacter
        final Dialog dialogcontact = new Dialog(ListeActivity.this);
        dialogcontact.setContentView(R.layout.aproposdialog);
        dialogcontact.setTitle("Contactez-Nous");
        dialogcontact.show();
    }

    private void initControls()
    {
        try
        {
            final Dialog dialog1 = new Dialog(ListeActivity.this);
            dialog1.setContentView(R.layout.volumedialog);
            dialog1.setTitle("Réglage Volume");

            final TextView levelTxt = (TextView)dialog1.findViewById(R.id.level_txt);
            volumeSeekbar = (SeekBar)dialog1.findViewById(R.id.volumeSeekbar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            levelTxt.setText(Integer.toString(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)));

            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    levelTxt.setText(Integer.toString(progress));
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            });

            dialog1.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}