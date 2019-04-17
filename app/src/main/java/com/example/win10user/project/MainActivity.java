package com.example.win10user.project;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> paths ;
    ArrayList<Player> players ;
    private MenuItem Myitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView imageView=findViewById(R.id.profile_image);
        final int id=getIntent().getIntExtra("id",-1);
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
        if(paths!=null && id!=-1)
        {
            imageView.setImageDrawable(Drawable.createFromPath(paths.get(id)));
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
        Button newp=findViewById(R.id.new_play);
        newp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, NewPlayActivity.class);
                startActivity(in);
            }
        });
        Button explay=findViewById(R.id.ex_play);

        explay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, explay_act.class);
                startActivity(in);
            }
        });
        Button play=findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (id == -1) {
                    String msg=getString(R.string.no_play);
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent in = new Intent(MainActivity.this, LevelsActivity.class);
                    in.putExtra("id", id);
                    startActivity(in);
                }
            }
        });

///////////////music
        if(!AudioPlay.isplayingAudio && AudioPlay.firstplay) {
            AudioPlay.playAudio(this, R.raw.mission_impossible_theme_song);
            AudioPlay.firstplay=false;
        }

        TextView textView=findViewById(R.id.pick);
        if(players!=null && id!=-1)
        {
            String msg=getString(R.string.theplayer);
            textView.setText(msg+players.get(id).getName());
        }


        ObjectAnimator animator = ObjectAnimator.ofFloat(newp,"scaleX",1f,1.1f).setDuration(1500);
        ObjectAnimator ianimator = ObjectAnimator.ofFloat(newp,"scaleY",1f,1.1f).setDuration(1500);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        ianimator.setRepeatMode(ValueAnimator.REVERSE);
        ianimator.setRepeatCount(Animation.INFINITE);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(explay,"scaleX",1f,1.1f).setDuration(1500);
        ObjectAnimator ianimator1 = ObjectAnimator.ofFloat(explay,"scaleY",1f,1.1f).setDuration(1500);
        animator1.setRepeatCount(Animation.INFINITE);
        animator1.setRepeatMode(ValueAnimator.REVERSE);
        ianimator1.setRepeatMode(ValueAnimator.REVERSE);
        ianimator1.setRepeatCount(Animation.INFINITE);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(play,"scaleX",1f,1.1f).setDuration(1500);
        ObjectAnimator ianimator2 = ObjectAnimator.ofFloat(play,"scaleY",1f,1.1f).setDuration(1500);
        animator2.setRepeatCount(Animation.INFINITE);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        ianimator2.setRepeatMode(ValueAnimator.REVERSE);
        ianimator2.setRepeatCount(Animation.INFINITE);
        AnimatorSet set1 = new AnimatorSet();

        //set1.playTogether(animator,animator2);
        set1.playTogether(animator,ianimator,animator1,ianimator1,animator2,ianimator2);
        set1.start();



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
                AudioPlay.shouldPlay=false;
                AudioPlay.positionAudio();
                AudioPlay.pauseAudio();

            } else {
                AudioPlay.startAudio();
                AudioPlay.shouldPlay=true;
            }

        }
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
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

        if(AudioPlay.isplayingAudio )
        {
            AudioPlay.positionAudio();
            AudioPlay.pauseAudio();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(AudioPlay.mediaPlayer!=null)
            AudioPlay.firstplay=true;
            AudioPlay.stopAudio();
    }
}
