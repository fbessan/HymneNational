package com.besco.innova.hymnenational;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by fbessan on 14/11/2016.
 */

public class HistoireActivity extends AppCompatActivity {

    SeekBar volumeSeekbar;
    private AudioManager audioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histoire);
        setTitle(null);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            //toolbar.setTitle("Histoire de notre Hymne National");
            toolbar.setTitle("Histoire");
        }
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        WebView webView = (WebView)findViewById(R.id.webView1);

        webView.loadDataWithBaseURL(null, getString(R.string.html_content), "text/html", "utf-8", null);

        if (Build.VERSION.SDK_INT >= 11) {
            webView.setBackgroundColor(0x01000000);
        } else {
            webView.setBackgroundColor(0x00000000);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case android.R.id.home:
                Intent i = new Intent(com.besco.innova.hymnenational.HistoireActivity.this, com.besco.innova.hymnenational.MainActivity.class);
                startActivity(i);
                finish();
                return true;
            case R.id.action_liste:
                Intent intent = new Intent(com.besco.innova.hymnenational.HistoireActivity.this, com.besco.innova.hymnenational.ListeActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                initControls();
                break;
            case R.id.menu_item_histoire:
                Intent intenthistoire = new Intent(com.besco.innova.hymnenational.HistoireActivity.this, com.besco.innova.hymnenational.HistoireActivity.class);
                startActivity(intenthistoire);
                break;
            case R.id.action_help:
                Intent intentaide = new Intent(com.besco.innova.hymnenational.HistoireActivity.this, com.besco.innova.hymnenational.AideActivity.class);
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
                initapropos();
                break;
            default:
                break;
        }

        return true;
    }


    private void initapropos(){
        //Contacter
        final Dialog dialogcontact = new Dialog(HistoireActivity.this);
        dialogcontact.setContentView(R.layout.aproposdialog);
        dialogcontact.setTitle("Contactez-Nous");
        dialogcontact.show();
    }


    private void initControls()
    {
        try
        {
            final Dialog dialog1 = new Dialog(HistoireActivity.this);
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
