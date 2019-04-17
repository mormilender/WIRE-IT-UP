package com.example.win10user.project;


import android.content.Context;

import android.media.MediaPlayer;
import android.os.Build;

public class AudioPlay extends MediaPlayer {

    public static MediaPlayer mediaPlayer;
    //private static SoundPool soundPool;
    public static int time;
    public static boolean isplayingAudio=false;
    public static boolean firstplay=true;
    public static boolean shouldPlay =true;

    public static void playAudio(Context c, int id){
        mediaPlayer = MediaPlayer.create(c,id);
        time=mediaPlayer.getCurrentPosition();



        if(!mediaPlayer.isPlaying())
        {
            isplayingAudio=true;
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
    }
    public static void stopAudio(){
        isplayingAudio=false;
        if(mediaPlayer!=null) {
            mediaPlayer.stop();

        }
    }

    public static void pauseAudio(){
        isplayingAudio=false;
        mediaPlayer.pause();

    }
    public static void startAudio() {
        isplayingAudio = true;
        if (mediaPlayer != null && time!=0) {
            AudioPlay.mediaPlayer.seekTo(AudioPlay.time);
            if (Build.VERSION.SDK_INT >= 26)
                AudioPlay.mediaPlayer.seekTo(AudioPlay.time, SEEK_CLOSEST_SYNC);
            else AudioPlay.mediaPlayer.seekTo(AudioPlay.time);
            mediaPlayer.start();
        }
    }

    public static void positionAudio()
    {
        if(mediaPlayer!=null)
            AudioPlay.time = AudioPlay.mediaPlayer.getCurrentPosition();
    }
}




