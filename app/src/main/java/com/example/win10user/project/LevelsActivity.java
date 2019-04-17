package com.example.win10user.project;



import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LevelsActivity extends AppCompatActivity {
    ArrayList<String> paths ;
    ArrayList<Player> players ;
    int numoflevels=9;
    ArrayList<ImageView> lev_nums=new ArrayList<>() ;
    int id;
    private MenuItem Myitem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels_act);


        Button btn_score=findViewById(R.id.score);
        id=getIntent().getIntExtra("id",0);

        try {
            FileInputStream fis=openFileInput("paths");
            ObjectInputStream ois=new ObjectInputStream(fis);
            paths=(ArrayList<String>)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fis=openFileInput("players");
            ObjectInputStream ois=new ObjectInputStream(fis);
            players=(ArrayList<Player>)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ImageView imageView=findViewById(R.id.profile_image);
        TextView name=findViewById(R.id.name_player);
        imageView.setImageDrawable(Drawable.createFromPath(paths.get(id)));
        name.setText(players.get(id).getName());




        btn_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LevelsActivity.this, Score_Activity.class);
                startActivity(in);

            }
        });

    }

    private class level_listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            final int id=getIntent().getIntExtra("id",0);
            String i=v.getTag().toString();
            switch (i) {

                case "1": {
                    Intent in = new Intent(LevelsActivity.this, Level1_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
                case "2": {
                    Intent in = new Intent(LevelsActivity.this, Level2_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
                case "3": {
                    Intent in = new Intent(LevelsActivity.this, Level3_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
                case "4": {
                    Intent in = new Intent(LevelsActivity.this, Level4_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
                case "5": {
                    Intent in = new Intent(LevelsActivity.this, Level5_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
                case "6": {
                    Intent in = new Intent(LevelsActivity.this, Level6_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
                case "7": {
                    Intent in = new Intent(LevelsActivity.this, Level7_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
                case "8": {
                    Intent in = new Intent(LevelsActivity.this, Level8_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
                case "9": {
                    Intent in = new Intent(LevelsActivity.this, Level9_Activity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                    break;
                }
            }


        }

    }



    @Override
    public void onResume(){
        super.onResume();
        try {
            FileInputStream fis=openFileInput("players");
            ObjectInputStream ois=new ObjectInputStream(fis);
            players=(ArrayList<Player>)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        int level=players.get(id).getLevel();
        level_listener listener=new level_listener();

        ImageButton lev1 = findViewById(R.id.lev_1);
        ImageButton lev2 = findViewById(R.id.lev_2);
        ImageButton lev3 = findViewById(R.id.lev_3);
        ImageButton lev4 = findViewById(R.id.lev_4);
        ImageButton lev5 = findViewById(R.id.lev_5);
        ImageButton lev6 = findViewById(R.id.lev_6);
        ImageButton lev7 = findViewById(R.id.lev_7);
        ImageButton lev8 = findViewById(R.id.lev_8);
        ImageButton lev9 = findViewById(R.id.lev_9);


        switch(level)
        {
            case 0:{
                lev1.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev1,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 1:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev2,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 2:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setImageResource(R.drawable.on2);
                lev2.setOnClickListener(listener);
                lev3.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev3,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 3:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setImageResource(R.drawable.on2);
                lev2.setOnClickListener(listener);
                lev3.setOnClickListener(listener);
                lev3.setImageResource(R.drawable.on3);
                lev4.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev4,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 4:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setImageResource(R.drawable.on2);
                lev2.setOnClickListener(listener);
                lev3.setOnClickListener(listener);
                lev3.setImageResource(R.drawable.on3);
                lev4.setOnClickListener(listener);
                lev4.setImageResource(R.drawable.on4);
                lev5.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev5,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 5:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setImageResource(R.drawable.on2);
                lev3.setOnClickListener(listener);
                lev3.setOnClickListener(listener);
                lev3.setImageResource(R.drawable.on3);
                lev4.setOnClickListener(listener);
                lev4.setImageResource(R.drawable.on4);
                lev5.setOnClickListener(listener);
                lev5.setImageResource(R.drawable.on5);
                lev6.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev6,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 6:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setImageResource(R.drawable.on2);
                lev2.setOnClickListener(listener);
                lev3.setOnClickListener(listener);
                lev3.setImageResource(R.drawable.on3);
                lev4.setOnClickListener(listener);
                lev4.setImageResource(R.drawable.on4);
                lev5.setOnClickListener(listener);
                lev5.setImageResource(R.drawable.on5);
                lev6.setOnClickListener(listener);
                lev6.setImageResource(R.drawable.on6);
                lev7.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev7,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 7:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setImageResource(R.drawable.on2);
                lev2.setOnClickListener(listener);
                lev3.setOnClickListener(listener);
                lev3.setImageResource(R.drawable.on3);
                lev4.setOnClickListener(listener);
                lev4.setImageResource(R.drawable.on4);
                lev5.setOnClickListener(listener);
                lev5.setImageResource(R.drawable.on5);
                lev6.setOnClickListener(listener);
                lev6.setImageResource(R.drawable.on6);
                lev7.setOnClickListener(listener);
                lev7.setImageResource(R.drawable.on7);
                lev8.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev8,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 8:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setImageResource(R.drawable.on2);
                lev2.setOnClickListener(listener);
                lev3.setOnClickListener(listener);
                lev3.setImageResource(R.drawable.on3);
                lev4.setOnClickListener(listener);
                lev4.setImageResource(R.drawable.on4);
                lev5.setOnClickListener(listener);
                lev5.setImageResource(R.drawable.on5);
                lev6.setOnClickListener(listener);
                lev6.setImageResource(R.drawable.on6);
                lev7.setOnClickListener(listener);
                lev7.setImageResource(R.drawable.on7);
                lev8.setOnClickListener(listener);
                lev8.setImageResource(R.drawable.on8);
                lev9.setOnClickListener(listener);
                ObjectAnimator animator = ObjectAnimator.ofFloat(lev9,"alpha",0.0f,1.0f).setDuration(600);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(2);
                animator.start();
                break;
            }
            case 9:{
                lev1.setImageResource(R.drawable.on1);
                lev1.setOnClickListener(listener);
                lev2.setImageResource(R.drawable.on2);
                lev2.setOnClickListener(listener);
                lev3.setOnClickListener(listener);
                lev3.setImageResource(R.drawable.on3);
                lev4.setOnClickListener(listener);
                lev4.setImageResource(R.drawable.on4);
                lev5.setOnClickListener(listener);
                lev5.setImageResource(R.drawable.on5);
                lev6.setOnClickListener(listener);
                lev6.setImageResource(R.drawable.on6);
                lev7.setOnClickListener(listener);
                lev7.setImageResource(R.drawable.on7);
                lev8.setOnClickListener(listener);
                lev8.setImageResource(R.drawable.on8);
                lev9.setOnClickListener(listener);
                lev9.setImageResource(R.drawable.on9);
                break;

            }
        }

        if(!AudioPlay.isplayingAudio&&   AudioPlay.shouldPlay)
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
