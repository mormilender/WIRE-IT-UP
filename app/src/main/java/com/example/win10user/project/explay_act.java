package com.example.win10user.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class explay_act extends AppCompatActivity {
    ArrayList<Player> players;
    ArrayList<String> img;
    int id_play;
    private MenuItem Myitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_play_lay);


        ListView scorelist = findViewById(R.id.score_list);

        scorelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_play=players.get(position).getId();
                Intent in = new Intent(explay_act.this, MainActivity.class);
                in.putExtra("id",id_play);
                finish();
                startActivity(in);
            }
        });


        players = new ArrayList<>();

        try {
            FileInputStream input = openFileInput("players");
            ObjectInputStream ois2 = new ObjectInputStream(input);
            players = (ArrayList<Player>) ois2.readObject();
            ois2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        img = new ArrayList<>();

        try {
            FileInputStream input = openFileInput("paths");
            ObjectInputStream ois2 = new ObjectInputStream(input);
            img = (ArrayList<String>) ois2.readObject();
            ois2.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        explay_adapt adapter = new explay_adapt(players, img);
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
