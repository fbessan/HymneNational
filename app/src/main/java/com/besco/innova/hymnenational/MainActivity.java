package com.besco.innova.hymnenational;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import io.gresse.hugo.vumeterlibrary.VuMeterView;



public class MainActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    private TextSwitcher mSwitcher;
    private TextView textaube;
    Button btnStart,btn_apprendre,btn_apprendre_hide,btnStop;
    MediaPlayer firstSound,voiceSound;
    VuMeterView vmeter;
    SeekBar seekBar,volumeSeekbar;
    private AudioManager audioManager = null;
    String datecreation;
    String MY_PREFS_NAME = "MyPrefsFile";

    Context context = this;
    private MediaRecorder mediaRecorder;
    String voiceStoragePath;


    static final String indexNoteGood = "06";
    static final String indexNoteBad = "12345";
    static Random rnd = new Random();

    MediaPlayer mediaPlayer;
    int val = 0;

    //Array note
    String notes[] = {"bravo","haha","hey","nerveux","pleure","rire","good"};

    // Array of String to Show In TextSwitcher
    String Humnecomplet[]={"Enfants du Bénin debout","La liberté d'un cri sonore", "Chante aux premiers feux de l'aurore","Enfants du Bénin debout", "Jadis à son appel", "Nos aïeux", "Sans faiblesse", "Ont su avec courage et ardeur", "Pleins d'allégresse", "Livrer au prix du sang", "Des combats éclatants", "Accourez vous aussi", "Bâtisseurs du présent", "Plus forts dans l'unité", "Et chaque jour à la tâche", "Pour la postérité", "Construisez sans relâche","Enfants du Bénin debout","La liberté d'un cri sonore", "Chante aux premiers feux de l'aurore","Enfants du Bénin debout","Quand partout", "souffle un vent", "de colère", "et de haine,","Béninois, sois fier,", "et d'une âme sereine,", "Confiant", "dans l'avenir,", "regarde ton drapeau !", "Dans le vert", "tu liras", "l'espoir du renouveau,", "De tes aïeux le rouge évoque le courage;","Des plus riches trésors le jaune est le présage.","Enfants du Bénin debout","La liberté d'un cri sonore", "Chante aux premiers feux de l'aurore","Enfants du Bénin debout","Tes monts ensoleillés,", "tes palmiers," ,"ta verdure,", "Cher Bénin, partout font ta vive parure.", "Ton sol", "offre à chacun", "la richesse des fruits.", "Bénin,", "désormais", "que tes fils tous unis","D'un fraternel élan partagent l'espérance","De te voir à jamais heureux dans l'abondance.","Enfants du Bénin debout","La liberté d'un cri sonore", "Chante aux premiers feux de l'aurore","Enfants du Bénin debout",""};




    int messageCount=Humnecomplet.length;
    int currentIndex=-1; // to keep current Index

    // Declare in and out Animations
    Animation in,out;
    Context mContext;


    private Handler mHandler = new Handler();
    private Handler VoiceHandler = new Handler();

    // Create a Runnable Instance
    Runnable r=new Runnable() {
        // Override the run Method
        public void run() {
            // TODO Auto-generated method stub

            try {
                // Update the TextSwitcher text

                val = updateTextSwitcherText();
                mSwitcher.setText(Humnecomplet[val]);


            } finally {


                if (val == 0){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(0);
                }
                if (val == 1){
                    mHandler.postDelayed(this, 4500);
                    seekBar.setProgress(1);
                }
                if (val == 2){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(2);
                }
                if (val == 3){
                    mHandler.postDelayed(this, 5500);
                    seekBar.setProgress(3);
                }
                if (val == 4){
                    mHandler.postDelayed(this, 4500);
                    seekBar.setProgress(4);
                }
                if (val == 5){
                    mHandler.postDelayed(this, 2200);
                    seekBar.setProgress(5);
                }
                if (val == 6){
                    mHandler.postDelayed(this, 3100);
                    seekBar.setProgress(6);
                }
                if (val == 7){
                    mHandler.postDelayed(this, 5800);
                    seekBar.setProgress(7);
                }
                if (val == 8){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(8);
                }
                if (val == 9){
                    mHandler.postDelayed(this, 4100);
                    seekBar.setProgress(9);
                }
                if (val == 10){
                    mHandler.postDelayed(this, 4700);
                    seekBar.setProgress(10);
                }
                if (val == 11){
                    mHandler.postDelayed(this, 3100);
                    seekBar.setProgress(11);
                }
                if (val == 12){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(12);
                }
                if (val == 13){
                    mHandler.postDelayed(this, 2500);
                    seekBar.setProgress(13);
                }
                if (val == 14){
                    mHandler.postDelayed(this, 3500);
                    seekBar.setProgress(14);
                }
                if (val == 15){
                    mHandler.postDelayed(this, 4200);
                    seekBar.setProgress(15);
                }
                if (val == 16){
                    mHandler.postDelayed(this, 5000);
                    seekBar.setProgress(16);

                }

                if (val == 17){
                    stopIntrumental();
                    playIntrumental();
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(17);
                }
                if (val == 18){
                    mHandler.postDelayed(this, 4500);
                    seekBar.setProgress(18);
                }
                if (val == 19){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(19);
                }
                if (val == 20){
                    mHandler.postDelayed(this, 5500);
                    seekBar.setProgress(20);
                }
                if (val == 21){
                    mHandler.postDelayed(this, 4500);
                    seekBar.setProgress(21);
                }
                if (val == 22){
                    mHandler.postDelayed(this, 2200);
                    seekBar.setProgress(22);
                }
                if (val == 23){
                    mHandler.postDelayed(this, 3100);
                    seekBar.setProgress(23);
                }
                if (val == 24){
                    mHandler.postDelayed(this, 5800);
                    seekBar.setProgress(24);
                }
                if (val == 25){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(25);
                }
                if (val == 26){
                    mHandler.postDelayed(this, 4100);
                    seekBar.setProgress(26);
                }
                if (val == 27){
                    mHandler.postDelayed(this, 4700);
                    seekBar.setProgress(27);
                }
                if (val == 28){
                    mHandler.postDelayed(this, 3100);
                    seekBar.setProgress(28);
                }
                if (val == 29){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(29);
                }
                if (val == 30){
                    mHandler.postDelayed(this, 2500);
                    seekBar.setProgress(30);
                }
                if (val == 31){
                    mHandler.postDelayed(this, 3500);
                    seekBar.setProgress(32);
                }
                if (val == 33){
                    mHandler.postDelayed(this, 4200);
                    seekBar.setProgress(33);
                }
                if (val == 34){
                    mHandler.postDelayed(this, 5000);
                    seekBar.setProgress(34);

                }
                if (val == 35){
                    stopIntrumental();
                    playIntrumental();
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(35);
                }
                if (val == 36){
                    mHandler.postDelayed(this, 4500);
                    seekBar.setProgress(36);
                }
                if (val == 37){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(37);
                }
                if (val == 38){
                    mHandler.postDelayed(this, 5500);
                    seekBar.setProgress(38);
                }
                if (val == 39){
                    mHandler.postDelayed(this, 4500);
                    seekBar.setProgress(39);
                }
                if (val == 40){
                    mHandler.postDelayed(this, 2200);
                    seekBar.setProgress(40);
                }
                if (val == 41){
                    mHandler.postDelayed(this, 3100);
                    seekBar.setProgress(41);
                }
                if (val == 42){
                    mHandler.postDelayed(this, 5800);
                    seekBar.setProgress(42);
                }
                if (val == 43){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(43);
                }
                if (val == 44){
                    mHandler.postDelayed(this, 4100);
                    seekBar.setProgress(44);
                }
                if (val == 45){
                    mHandler.postDelayed(this, 4700);
                    seekBar.setProgress(45);
                }
                if (val == 46){
                    mHandler.postDelayed(this, 3100);
                    seekBar.setProgress(46);
                }
                if (val == 47){
                    mHandler.postDelayed(this, 4000);
                    seekBar.setProgress(47);
                }
                if (val == 48){
                    mHandler.postDelayed(this, 2500);
                    seekBar.setProgress(48);
                }
                if (val == 49){
                    mHandler.postDelayed(this, 3500);
                    seekBar.setProgress(49);
                }
                if (val == 50){
                    mHandler.postDelayed(this, 4200);
                    seekBar.setProgress(50);
                }
                if (val == 51){
                    mHandler.postDelayed(this, 5000);
                    seekBar.setProgress(51);

                }
                if(val==52 ){

                    int eval = generateNote(1);
                    dbinsertion(eval);
                    vmeter.pause();
                    seekBar.setProgress(0);
                    MonDialog(notes[eval]);
                    mHandler.removeCallbacks(r);
                    btn_apprendre_hide.setVisibility(View.GONE);
                    btn_apprendre.setVisibility(View.VISIBLE);

                    textaube.setVisibility(View.VISIBLE);
                    mSwitcher.setVisibility(View.GONE);


                    if (firstSound!=null){
                        firstSound.stop();
                    }
                }



            }
        }
    };


    //Avec voice





    Runnable rv =new Runnable() {
        // Override the run Method
        public void run() {
            // TODO Auto-generated method stub

            try {
                // Update the TextSwitcher text

                val = updateTextSwitcherText();
                mSwitcher.setText(Humnecomplet[val]);


            } finally {


                if (val == 0){
                    VoiceHandler.postDelayed(this, 5000);
                    seekBar.setProgress(0);
                }
                if (val == 1){
                    VoiceHandler.postDelayed(this, 5400);
                    seekBar.setProgress(1);
                }
                if (val == 2){
                    VoiceHandler.postDelayed(this, 4000);
                    seekBar.setProgress(2);
                }
                if (val == 3){
                    VoiceHandler.postDelayed(this, 5400);
                    seekBar.setProgress(3);
                }
                if (val == 4){
                    VoiceHandler.postDelayed(this, 4500);
                    seekBar.setProgress(4);
                }
                if (val == 5){
                    VoiceHandler.postDelayed(this, 2200);
                    seekBar.setProgress(5);
                }
                if (val == 6){
                    VoiceHandler.postDelayed(this, 3100);
                    seekBar.setProgress(6);
                }
                if (val == 7){
                    VoiceHandler.postDelayed(this, 5800);
                    seekBar.setProgress(7);
                }
                if (val == 8){
                    VoiceHandler.postDelayed(this, 4000);
                    seekBar.setProgress(8);
                }
                if (val == 9){
                    VoiceHandler.postDelayed(this, 3800);
                    seekBar.setProgress(9);
                }
                if (val == 10){
                    VoiceHandler.postDelayed(this, 4700);
                    seekBar.setProgress(10);
                }
                if (val == 11){
                    VoiceHandler.postDelayed(this, 3100);
                    seekBar.setProgress(11);
                }
                if (val == 12){
                    VoiceHandler.postDelayed(this, 4000);
                    seekBar.setProgress(12);
                }
                if (val == 13){
                    VoiceHandler.postDelayed(this, 2500);
                    seekBar.setProgress(13);
                }
                if (val == 14){
                    VoiceHandler.postDelayed(this, 3500);
                    seekBar.setProgress(14);
                }
                if (val == 15){
                    VoiceHandler.postDelayed(this, 4200);
                    seekBar.setProgress(15);
                }
                if (val == 16){
                    VoiceHandler.postDelayed(this, 4500);
                    seekBar.setProgress(16);

                }
                //Refrain
                if (val == 17){
                    VoiceHandler.postDelayed(this, 4500);
                    seekBar.setProgress(17);
                }
                if (val == 18){
                    VoiceHandler.postDelayed(this, 5300);
                    seekBar.setProgress(18);
                }
                if (val == 19){
                    VoiceHandler.postDelayed(this, 4000);
                    seekBar.setProgress(19);
                }
                if (val == 20){
                    VoiceHandler.postDelayed(this, 5500);
                    seekBar.setProgress(20);
                }

                //Couplet 2
                if (val == 21){
                    VoiceHandler.postDelayed(this, 1600);
                    seekBar.setProgress(21);
                }
                if (val == 22){
                    VoiceHandler.postDelayed(this, 3300);
                    seekBar.setProgress(22);
                }
                if (val == 23){
                    VoiceHandler.postDelayed(this, 1500);
                    seekBar.setProgress(23);
                }
                if (val == 24){
                    VoiceHandler.postDelayed(this, 3600);
                    seekBar.setProgress(24);
                }
                if (val == 25){
                    VoiceHandler.postDelayed(this, 4600);
                    seekBar.setProgress(25);
                }
                if (val == 26){
                    VoiceHandler.postDelayed(this, 4700);
                    seekBar.setProgress(26);
                }
                if (val == 27){
                    VoiceHandler.postDelayed(this, 1600);
                    seekBar.setProgress(27);
                }
                if (val == 28){
                    VoiceHandler.postDelayed(this, 2500);
                    seekBar.setProgress(28);
                }
                if (val == 29){
                    VoiceHandler.postDelayed(this, 5000);
                    seekBar.setProgress(29);
                }
                if (val == 30){
                    VoiceHandler.postDelayed(this, 1500);
                    seekBar.setProgress(30);
                }
                if (val == 31){
                    VoiceHandler.postDelayed(this, 1300);
                    seekBar.setProgress(31);
                }
                if (val == 32){
                    VoiceHandler.postDelayed(this, 4000);
                    seekBar.setProgress(32);
                }
                if (val == 33){
                    VoiceHandler.postDelayed(this, 5800);
                    seekBar.setProgress(33);
                }
                if (val == 34){
                    VoiceHandler.postDelayed(this, 9500);
                    seekBar.setProgress(34);
                }



                //Refrain
                if (val == 35){
                    VoiceHandler.postDelayed(this, 4500);
                    seekBar.setProgress(35);
                }
                if (val == 36){
                    VoiceHandler.postDelayed(this, 5300);
                    seekBar.setProgress(36);
                }
                if (val == 37){
                    VoiceHandler.postDelayed(this, 4000);
                    seekBar.setProgress(37);
                }
                if (val == 38){
                    VoiceHandler.postDelayed(this, 5500);
                    seekBar.setProgress(38);
                }

                //Couplet 3
                if (val == 39){
                    VoiceHandler.postDelayed(this, 4500);
                    seekBar.setProgress(39);
                }
                if (val == 40){
                    VoiceHandler.postDelayed(this, 1600);
                    seekBar.setProgress(40);
                }
                if (val == 41){
                    VoiceHandler.postDelayed(this, 4000);
                    seekBar.setProgress(41);
                }
                if (val == 42){
                    VoiceHandler.postDelayed(this, 8600);
                    seekBar.setProgress(42);
                }
                if (val == 43){
                    VoiceHandler.postDelayed(this, 1500);
                    seekBar.setProgress(43);
                }
                if (val == 44){
                    VoiceHandler.postDelayed(this, 2500);
                    seekBar.setProgress(44);
                }
                if (val == 45){
                    VoiceHandler.postDelayed(this, 5000);
                    seekBar.setProgress(45);
                }
                if (val == 46){
                    VoiceHandler.postDelayed(this, 1300);
                    seekBar.setProgress(46);
                }
                if (val == 47){
                    VoiceHandler.postDelayed(this, 1700);
                    seekBar.setProgress(47);
                }


                if (val == 48){
                    VoiceHandler.postDelayed(this, 4500);
                    seekBar.setProgress(48);
                }
                if (val == 49){
                    VoiceHandler.postDelayed(this, 5100);
                    seekBar.setProgress(49);
                }
                if (val == 50){
                    VoiceHandler.postDelayed(this, 9300);
                    seekBar.setProgress(50);
                }

                //Refrain
                if (val == 51){
                    VoiceHandler.postDelayed(this, 4500);
                    seekBar.setProgress(51);
                }
                if (val == 52){
                    VoiceHandler.postDelayed(this, 5300);
                    seekBar.setProgress(52);
                }
                if (val == 53){
                    VoiceHandler.postDelayed(this, 4000);
                    seekBar.setProgress(53);
                }
                if (val == 54){
                    VoiceHandler.postDelayed(this, 5500);
                    seekBar.setProgress(54);


                }

                if(val == 55){
                    btnStart.setVisibility(View.VISIBLE);
                    btnStop.setVisibility(View.GONE);
                    stopHymneVoice();
                    VoiceHandler.removeCallbacks(rv);
                    vmeter.stop(true);
                    seekBar.setProgress(0);
                    if(voiceSound!=null){
                        voiceSound.stop();
                    }

                    if(firstSound!=null){
                        firstSound.stop();
                    }
                }

            }
        }
    };

    private void dbinsertion(int eval){

        Listeobject liste = new Listeobject();
        liste.setImagename(getImageId(getApplicationContext(),notes[eval]));
        liste.setFilename(voiceStoragePath);
        liste.setDatecreation(datecreation);
        liste.save();

    }

    private int generateNote( int status ){

        int reponse;
        if(status == 1){
            reponse = rnd.nextInt(indexNoteGood.length());
        }else{
            reponse = rnd.nextInt(indexNoteBad.length());
        }

        return reponse;
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    private void MonDialog(String noteimage){

        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.customdialog);
        dialog.setTitle("Hymne National");

        // set the custom dialog components - text, image and button
        ImageView image = (ImageView) dialog.findViewById(R.id.imagenote);
        image.setImageResource(getImageId(this, noteimage));

        final Button dialogButtonEcouterStop = (Button) dialog.findViewById(R.id.dialogButtonEcouterStop);
        dialogButtonEcouterStop.setVisibility(View.GONE);

        Button dialogButtonOK = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null) {
                    mediaPlayer.stop();
                }

                if (firstSound!=null){
                    firstSound.stop();
                }
                dialog.dismiss();
            }
        });

        final Button dialogButtonEcouter = (Button) dialog.findViewById(R.id.dialogButtonEcouter);
        // if button is clicked, close the custom dialog
        dialogButtonEcouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (firstSound!=null){
                    firstSound.stop();
                }
                if (mediaPlayer!=null){
                    mediaPlayer.stop();
                }

                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("DATE_CREATION", datecreation);
                editor.apply();



                Intent intent = new Intent(com.besco.innova.hymnenational.MainActivity.this, com.besco.innova.hymnenational.ListeActivity.class);
                startActivity(intent);
                dialog.dismiss();


            }
        });


        dialogButtonEcouterStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogButtonEcouterStop.setVisibility(View.GONE);
                dialogButtonEcouter.setVisibility(View.VISIBLE);
                if(mediaPlayer!=null) {
                    mediaPlayer.stop();
                }

                if (firstSound!=null){
                    firstSound.stop();
                }

                stopAudioRecording();


            }
        });

        dialog.show();


    }



    @Override
    protected void onPause() {
        super.onPause();

        seekBar.setProgress(0);
        stopHymneVoice();
        stopIntrumental();
        mHandler.removeCallbacks(r);
        vmeter.stop(true);
        mSwitcher.setText(Humnecomplet[0]);
        btn_apprendre_hide.setVisibility(View.GONE);
        btn_apprendre.setVisibility(View.VISIBLE);
        stopAudioRecording();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        seekBar.setProgress(0);
        stopHymneVoice();
        stopIntrumental();
        mHandler.removeCallbacks(r);
        vmeter.stop(true);
        mSwitcher.setText(Humnecomplet[0]);
        btn_apprendre_hide.setVisibility(View.GONE);
        btn_apprendre.setVisibility(View.VISIBLE);
        stopAudioRecording();
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Hymne National");
        }
        setSupportActionBar(toolbar);
        monprivilege();
        //deleteSugarDB(getApplicationContext());

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);


        seekBar = (SeekBar)findViewById(R.id.seekbar);
        seekBar.setProgress(0);
        //seekBar.incrementProgressBy(10);
        seekBar.setMax(54);

        mContext=this;
        btnStart=(Button)findViewById(R.id.btn_ecouter);
        btnStop=(Button)findViewById(R.id.btn_ecouter_hide);
        btn_apprendre=(Button)findViewById(R.id.btn_apprendre);
        btn_apprendre_hide=(Button)findViewById(R.id.btn_apprendre_hide);

        mSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        textaube = (TextView) findViewById(R.id.textaube);
        Typeface typetextaube = Typeface.createFromAsset(getAssets(),"fonts/Dancing.ttf");
        textaube.setTypeface(typetextaube);
        vmeter = (VuMeterView) findViewById(R.id.vumeter);
        vmeter.pause();

        textaube.setVisibility(View.VISIBLE);
        mSwitcher.setVisibility(View.GONE);
        btn_apprendre_hide.setVisibility(View.GONE);
        btnStop.setVisibility(View.GONE);

        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub
                TextView myText = new TextView(getApplicationContext());
                Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Dancing.ttf");
                myText.setTypeface(type);
                myText.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(24);
                myText.setTextColor(Color.WHITE);
                return myText;
            }
        });

        // initialize the in and out  animations
        in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);


        //Partie recorder
        hasSDCard();

        voiceStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File audioVoice = new File(voiceStoragePath + File.separator + "hymnenational");
        if(!audioVoice.exists()){
            audioVoice.mkdir();
        }
        datecreation = generateVoiceFilename();
        //voiceStoragePath = voiceStoragePath + File.separator + "voices/" + generateVoiceFilename(6) + ".3gpp";
        voiceStoragePath = voiceStoragePath + File.separator + "hymnenational/" + datecreation + ".mp3";

        initializeMediaRecord();


        // Button Start Click Listener
        btnStart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                btnStop.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                stopHymneVoice();
                textaube.setVisibility(View.GONE);
                mSwitcher.setVisibility(View.VISIBLE);
                mSwitcher.setText(Humnecomplet[0]);
                // Start The Text Switcher Animation
                playHymneVoice();
                VoiceHandler.postDelayed(rv, 1200);
                vmeter.resume(true);



            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.GONE);
                stopHymneVoice();
                VoiceHandler.removeCallbacks(rv);
                vmeter.stop(true);
                seekBar.setProgress(0);
                if(voiceSound!=null){
                    voiceSound.stop();
                }
                if(firstSound!=null){
                    firstSound.stop();
                }


            }
        });

        // Button Stop Click Listener
        btn_apprendre_hide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                int eval = generateNote(0);
                dbinsertion(eval);
                MonDialog(notes[eval]);

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);

                btn_apprendre_hide.setVisibility(View.GONE);
                btn_apprendre.setVisibility(View.VISIBLE);

                stopIntrumental();
                stopAudioRecording();

                mHandler.removeCallbacks(r);
                vmeter.stop(true);
                if (mediaRecorder == null) {
                    initializeMediaRecord();
                }
                seekBar.setProgress(0);



            }
        });

        // Button Play Click Listener
        btn_apprendre.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                textaube.setVisibility(View.GONE);
                mSwitcher.setVisibility(View.VISIBLE);
                mSwitcher.setText(Humnecomplet[0]);
                btn_apprendre_hide.setVisibility(View.VISIBLE);
                btn_apprendre.setVisibility(View.GONE);

                playIntrumental();

                if (mediaRecorder == null) {
                    initializeMediaRecord();
                }

                startAudioRecording();
                mHandler.postDelayed(r, 900);
                vmeter.resume(true);

            }
        });

    }

    public void deleteSugarDB(Context context)
    {
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(context);
        schemaGenerator.deleteTables(new SugarDb(context).getDB());
        SugarContext.init(context);
        schemaGenerator.createDatabase(new SugarDb(context).getDB());
    }


    private int updateTextSwitcherText()
    {
        currentIndex++;
        if(currentIndex==messageCount) {
            currentIndex = 0;
        }

        return currentIndex;
    }


    private String generateVoiceFilename(){
        DateFormat df = new SimpleDateFormat("yyyyMMdHHmm");
        return df.format(Calendar.getInstance().getTime());
    }

    private void startAudioRecording(){
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void stopAudioRecording(){

        try{
            if(mediaRecorder != null){
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
            }
        }catch(RuntimeException stopException){
            //handle cleanup here
            Log.e("ErrorStop"," ErrorStop " + stopException.getMessage());
            stopException.getMessage();
        }


    }

    private void playHymneVoice(){
        voiceSound = new MediaPlayer();
        voiceSound = MediaPlayer.create(MainActivity.this, R.raw.hymnevoice);
        voiceSound.start();

    }

    private void stopHymneVoice(){
        if(voiceSound != null){
            voiceSound.stop();
            voiceSound.release();
            voiceSound = null;
        }
    }

    private void playIntrumental(){
        firstSound = new MediaPlayer();
        firstSound = MediaPlayer.create(MainActivity.this, R.raw.instrumental);
        firstSound.start();

    }

    private void stopIntrumental(){
        if(firstSound != null){
            firstSound.stop();
            firstSound.release();
            firstSound = null;
        }
    }

    private void playLastStoredAudioMusic() throws IOException {
        mediaPlayer = new MediaPlayer();
        try {
            //mediaPlayer.reset();
            mediaPlayer.setDataSource(voiceStoragePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepare();
        mediaPlayer.start();

    }

    public void playsong(String currentUrl)
    {
        try {
            mediaPlayer = new MediaPlayer();

            mediaPlayer.reset();
            mediaPlayer.setDataSource(currentUrl);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void stopAudioPlay(){
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void mediaPlayerPlaying(){
        if(!mediaPlayer.isPlaying()){
            stopAudioPlay();
        }
    }

    private void hasSDCard(){
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(isSDPresent)        {
            System.out.println("There is SDCard");
        }
        else{
            System.out.println("There is no SDCard");
        }
    }


    //private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 29;
    private void initializeMediaRecord(){



        //allowPrivileges();
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setAudioEncodingBitRate(128);
        mediaRecorder.setAudioSamplingRate(44100);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(voiceStoragePath);

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void monprivilege(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 29;
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean allowPrivileges()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            int hasReadPhoneStatePermission = checkSelfPermission(android.Manifest.permission.READ_PHONE_STATE);
            int hasSYSTEM_ALERT_WINDOWPermission = checkSelfPermission(android.Manifest.permission.SYSTEM_ALERT_WINDOW);
            int hasVIBRATEPermission = checkSelfPermission(android.Manifest.permission.VIBRATE);
            int hasDISABLE_KEYGUARDPermission = checkSelfPermission(android.Manifest.permission.DISABLE_KEYGUARD);
            int hasGET_ACCOUNTSPermission = checkSelfPermission(android.Manifest.permission.GET_ACCOUNTS);
            int hasRECORD_AUDIOPermission = checkSelfPermission(android.Manifest.permission.RECORD_AUDIO);

            if (hasReadPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_CODE_ASK_PERMISSIONS = 123;
                requestPermissions(new String[]{android.Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }

            if (hasSYSTEM_ALERT_WINDOWPermission != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_CODE_ASK_PERMISSIONS = 123;
                requestPermissions(new String[]{android.Manifest.permission.SYSTEM_ALERT_WINDOW}, REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }

            if (hasVIBRATEPermission != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_CODE_ASK_PERMISSIONS = 123;
                requestPermissions(new String[]{android.Manifest.permission.VIBRATE}, REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }

            if (hasDISABLE_KEYGUARDPermission != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_CODE_ASK_PERMISSIONS = 123;
                requestPermissions(new String[]{android.Manifest.permission.DISABLE_KEYGUARD}, REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }

            if (hasGET_ACCOUNTSPermission != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_CODE_ASK_PERMISSIONS = 123;
                requestPermissions(new String[]{android.Manifest.permission.GET_ACCOUNTS}, REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }
            if (hasRECORD_AUDIOPermission != PackageManager.PERMISSION_GRANTED) {
                int REQUEST_CODE_ASK_PERMISSIONS = 123;
                requestPermissions(new String[]{android.Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_ASK_PERMISSIONS);
                return false;
            }

        }


        return true;
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
                onBackPressed();
                return true;
            case R.id.action_liste:
                Intent intent = new Intent(com.besco.innova.hymnenational.MainActivity.this, com.besco.innova.hymnenational.ListeActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:
                initControls();
                break;
            case R.id.menu_item_histoire:
                Intent intenthistoire = new Intent(com.besco.innova.hymnenational.MainActivity.this, com.besco.innova.hymnenational.HistoireActivity.class);
                startActivity(intenthistoire);
                break;
            case R.id.action_help:
                Intent intentaide = new Intent(com.besco.innova.hymnenational.MainActivity.this, com.besco.innova.hymnenational.AideActivity.class);
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

    private void initapropos(){
        //Contacter
        final Dialog dialogcontact = new Dialog(MainActivity.this);
        dialogcontact.setContentView(R.layout.aproposdialog);
        dialogcontact.setTitle("Contactez-Nous");
        dialogcontact.setCancelable(true);
        dialogcontact.show();


    }

    private void initControls()
    {
        try
        {
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.volumedialog);
            dialog.setTitle("Réglage Volume");

            final TextView levelTxt = (TextView)dialog.findViewById(R.id.level_txt);
            volumeSeekbar = (SeekBar)dialog.findViewById(R.id.volumeSeekbar);
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

            dialog.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
