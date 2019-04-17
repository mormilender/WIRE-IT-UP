package com.example.win10user.project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score_Activity extends AppCompatActivity {
    ArrayList<Player> players;
    ArrayList<String> img;
    ArrayList<Player> sorted;
    private MenuItem Myitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);


        ListView scorelist=findViewById(R.id.score_list);


        players= new ArrayList<>();

        try {
            FileInputStream  input=openFileInput("players");
            ObjectInputStream  ois2=new ObjectInputStream(input);
            players=(ArrayList<Player>)ois2.readObject();
            ois2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        img= new ArrayList<>();

        try {
            FileInputStream  input=openFileInput("paths");
            ObjectInputStream  ois2=new ObjectInputStream(input);
            img=(ArrayList<String>)ois2.readObject();
            ois2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        sorted=players;

        Collections.sort(sorted);

        Score_adapter adapter=new Score_adapter(sorted,img);
        scorelist.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!AudioPlay.isplayingAudio &&  AudioPlay.shouldPlay)
        {
            AudioPlay.startAudio();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        if(AudioPlay.isplayingAudio)
        {
            AudioPlay.positionAudio();
            AudioPlay.pauseAudio();
        }


    }

    public void updateSound() {
        if (AudioPlay.isplayingAudio)
            Myitem.setIcon(R.drawable.sound);
        else Myitem.setIcon(R.drawable.mute);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        Myitem = menu.findItem(R.id.music);
        updateSound();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.exit) {

            AudioPlay.isplayingAudio = false;
            if(AudioPlay.mediaPlayer!=null)
                AudioPlay.stopAudio();
            finishAffinity();
        }

        if (item.getItemId() == R.id.music) {

            if (AudioPlay.isplayingAudio && AudioPlay.mediaPlayer != null) {
                AudioPlay.positionAudio();
                AudioPlay.pauseAudio();
                AudioPlay.shouldPlay=false;

            } else {
                AudioPlay.startAudio();
                AudioPlay.shouldPlay=true;
            }

        }
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }

}
