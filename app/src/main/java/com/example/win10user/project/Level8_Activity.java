package com.example.win10user.project;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Level8_Activity extends AppCompatActivity {
    int stepscount=0;
    TextView steps;
    long currtimer;
    int rows=8;
    int cols=8;
    CountDownTimer countDownTimer;
    int score;
    int level=8;
    ArrayList<Player>players ;
    int id;
    ArrayList<Node>images;
    updateneighbor updater;
    private MenuItem Myitem;
    MediaPlayer mp;
    boolean Isplaying=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lvl8);
        //intent = getIntent();//new Intent(Level1_Activity.this,Level1_Activity.class);
        id=getIntent().getIntExtra("id",-1);



        mp = MediaPlayer.create(this,R.raw.mission_impossible_theme_song);
        if(AudioPlay.shouldPlay) {
            mp.start();
            Isplaying = true;
        }
        TextView levnum=findViewById(R.id.level);
        levnum.setText(levnum.getText()+" "+level);

        TextView startanim=findViewById(R.id.startanim);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(startanim,"alpha",0f,1f,0f).setDuration(2000);
        animator1.start();

        timerfunc();

        steps=findViewById(R.id.steps_num);
        ImageView imageView00 = findViewById(R.id.b00);
        ImageView imageView01 = findViewById(R.id.b01);
        ImageView imageView02 = findViewById(R.id.b02);
        ImageView imageView03 = findViewById(R.id.b03);
        ImageView imageView04 = findViewById(R.id.b04);
        ImageView imageView05 = findViewById(R.id.b05);
        ImageView imageView06 = findViewById(R.id.b06);
        ImageView imageView07 = findViewById(R.id.b07);
        ImageView imageView10 = findViewById(R.id.b10);
        ImageView imageView11 = findViewById(R.id.b11);
        ImageView imageView12 = findViewById(R.id.b12);
        ImageView imageView13 = findViewById(R.id.b13);
        ImageView imageView14 = findViewById(R.id.b14);
        ImageView imageView15 = findViewById(R.id.b15);
        ImageView imageView16 = findViewById(R.id.b16);
        ImageView imageView17 = findViewById(R.id.b17);
        ImageView imageView20 = findViewById(R.id.b20);
        ImageView imageView21 = findViewById(R.id.b21);
        ImageView imageView22 = findViewById(R.id.b22);
        ImageView imageView23 = findViewById(R.id.b23);
        ImageView imageView24 = findViewById(R.id.b24);
        ImageView imageView25 = findViewById(R.id.b25);
        ImageView imageView26 = findViewById(R.id.b26);
        ImageView imageView27 = findViewById(R.id.b27);
        ImageView imageView30 = findViewById(R.id.b30);
        ImageView imageView31 = findViewById(R.id.b31);
        ImageView imageView32 = findViewById(R.id.b32);
        ImageView imageView33 = findViewById(R.id.b33);
        ImageView imageView34 = findViewById(R.id.b34);
        ImageView imageView35 = findViewById(R.id.b35);
        ImageView imageView36 = findViewById(R.id.b36);
        ImageView imageView37 = findViewById(R.id.b37);
        ImageView imageView40 = findViewById(R.id.b40);
        ImageView imageView41 = findViewById(R.id.b41);
        ImageView imageView42 = findViewById(R.id.b42);
        ImageView imageView43 = findViewById(R.id.b43);
        ImageView imageView44 = findViewById(R.id.b44);
        ImageView imageView45 = findViewById(R.id.b45);
        ImageView imageView46 = findViewById(R.id.b46);
        ImageView imageView47 = findViewById(R.id.b47);
        ImageView imageView50 = findViewById(R.id.b50);
        ImageView imageView51 = findViewById(R.id.b51);
        ImageView imageView52 = findViewById(R.id.b52);
        ImageView imageView53 = findViewById(R.id.b53);
        ImageView imageView54 = findViewById(R.id.b54);
        ImageView imageView55 = findViewById(R.id.b55);
        ImageView imageView56 = findViewById(R.id.b56);
        ImageView imageView57 = findViewById(R.id.b57);
        ImageView imageView60 = findViewById(R.id.b60);
        ImageView imageView61 = findViewById(R.id.b61);
        ImageView imageView62 = findViewById(R.id.b62);
        ImageView imageView63 = findViewById(R.id.b63);
        ImageView imageView64 = findViewById(R.id.b64);
        ImageView imageView65 = findViewById(R.id.b65);
        ImageView imageView66 = findViewById(R.id.b66);
        ImageView imageView67 = findViewById(R.id.b67);
        ImageView imageView70 = findViewById(R.id.b70);
        ImageView imageView71 = findViewById(R.id.b71);
        ImageView imageView72 = findViewById(R.id.b72);
        ImageView imageView73 = findViewById(R.id.b73);
        ImageView imageView74 = findViewById(R.id.b74);
        ImageView imageView75 = findViewById(R.id.b75);
        ImageView imageView76 = findViewById(R.id.b76);
        ImageView imageView77 = findViewById(R.id.b77);

        //shapes num
        imageView00.setTag(R.string.shape,"5");
        imageView01.setTag(R.string.shape,"1");
        imageView02.setTag(R.string.shape,"2");
        imageView03.setTag(R.string.shape,"5");
        imageView04.setTag(R.string.shape,"4");
        imageView05.setTag(R.string.shape,"2");
        imageView06.setTag(R.string.shape,"3");
        imageView07.setTag(R.string.shape,"6");
        imageView10.setTag(R.string.shape,"6");
        imageView11.setTag(R.string.shape,"6");
        imageView12.setTag(R.string.shape,"4");
        imageView13.setTag(R.string.shape,"1");
        imageView14.setTag(R.string.shape,"6");
        imageView15.setTag(R.string.shape,"3");
        imageView16.setTag(R.string.shape,"6");
        imageView17.setTag(R.string.shape,"2");
        imageView20.setTag(R.string.shape,"6");
        imageView21.setTag(R.string.shape,"1");
        imageView22.setTag(R.string.shape,"2");
        imageView23.setTag(R.string.shape,"2");
        imageView24.setTag(R.string.shape,"6");
        imageView25.setTag(R.string.shape,"2");
        imageView26.setTag(R.string.shape,"1");
        imageView27.setTag(R.string.shape,"2");
        imageView30.setTag(R.string.shape,"2");
        imageView31.setTag(R.string.shape,"4");
        imageView32.setTag(R.string.shape,"2");
        imageView33.setTag(R.string.shape,"3");
        imageView34.setTag(R.string.shape,"2");
        imageView35.setTag(R.string.shape,"6");
        imageView36.setTag(R.string.shape,"5");
        imageView37.setTag(R.string.shape,"6");
        imageView40.setTag(R.string.shape,"2");
        imageView41.setTag(R.string.shape,"1");
        imageView42.setTag(R.string.shape,"6");
        imageView43.setTag(R.string.shape,"2");
        imageView44.setTag(R.string.shape,"6");
        imageView45.setTag(R.string.shape,"2");
        imageView46.setTag(R.string.shape,"6");
        imageView47.setTag(R.string.shape,"2");
        imageView50.setTag(R.string.shape,"3");
        imageView51.setTag(R.string.shape,"6");
        imageView52.setTag(R.string.shape,"5");
        imageView53.setTag(R.string.shape,"3");
        imageView54.setTag(R.string.shape,"2");
        imageView55.setTag(R.string.shape,"3");
        imageView56.setTag(R.string.shape,"6");
        imageView57.setTag(R.string.shape,"5");
        imageView60.setTag(R.string.shape,"3");
        imageView61.setTag(R.string.shape,"6");
        imageView62.setTag(R.string.shape,"1");
        imageView63.setTag(R.string.shape,"2");
        imageView64.setTag(R.string.shape,"5");
        imageView65.setTag(R.string.shape,"5");
        imageView66.setTag(R.string.shape,"1");
        imageView67.setTag(R.string.shape,"5");
        imageView70.setTag(R.string.shape,"4");
        imageView71.setTag(R.string.shape,"2");
        imageView72.setTag(R.string.shape,"1");
        imageView73.setTag(R.string.shape,"6");
        imageView74.setTag(R.string.shape,"2");
        imageView75.setTag(R.string.shape,"2");
        imageView76.setTag(R.string.shape,"6");
        imageView77.setTag(R.string.shape,"6");



        images=new ArrayList<Node>();
        images.add(new Node(imageView00,null,null,0,0));
        images.add(new Node(imageView01,null,null,0,0));
        images.add(new Node(imageView02,null,null,0,0));
        images.add(new Node(imageView03,null,null,0,0));
        images.add(new Node(imageView04,null,null,0,0));
        images.add(new Node(imageView05,null,null,0,0));
        images.add(new Node(imageView06,null,null,0,0));
        images.add(new Node(imageView07,null,null,0,0));
        images.add(new Node(imageView10,null,null,0,0));
        images.add(new Node(imageView11,null,null,0,0));
        images.add(new Node(imageView12,null,null,0,0));
        images.add(new Node(imageView13,null,null,0,0));
        images.add(new Node(imageView14,null,null,0,0));
        images.add(new Node(imageView15,null,null,0,0));
        images.add(new Node(imageView16,null,null,0,0));
        images.add(new Node(imageView17,null,null,0,0));
        images.add(new Node(imageView20,null,null,0,0));
        images.add(new Node(imageView21,null,null,0,0));
        images.add(new Node(imageView22,null,null,0,0));
        images.add(new Node(imageView23,null,null,0,0));
        images.add(new Node(imageView24,null,null,0,0));
        images.add(new Node(imageView25,null,null,0,0));
        images.add(new Node(imageView26,null,null,0,0));
        images.add(new Node(imageView27,null,null,0,0));
        images.add(new Node(imageView30,null,null,0,0));
        images.add(new Node(imageView31,null,null,0,0));
        images.add(new Node(imageView32,null,null,0,0));
        images.add(new Node(imageView33,null,null,0,0));
        images.add(new Node(imageView34,null,null,0,0));
        images.add(new Node(imageView35,null,null,0,0));
        images.add(new Node(imageView36,null,null,0,0));
        images.add(new Node(imageView37,null,null,0,0));
        images.add(new Node(imageView40,null,null,0,0));
        images.add(new Node(imageView41,null,null,0,0));
        images.add(new Node(imageView42,null,null,0,0));
        images.add(new Node(imageView43,null,null,0,0));
        images.add(new Node(imageView44,null,null,0,0));
        images.add(new Node(imageView45,null,null,0,0));
        images.add(new Node(imageView46,null,null,0,0));
        images.add(new Node(imageView47,null,null,0,0));
        images.add(new Node(imageView50,null,null,0,0));
        images.add(new Node(imageView51,null,null,0,0));
        images.add(new Node(imageView52,null,null,0,0));
        images.add(new Node(imageView53,null,null,0,0));
        images.add(new Node(imageView54,null,null,0,0));
        images.add(new Node(imageView55,null,null,0,0));
        images.add(new Node(imageView56,null,null,0,0));
        images.add(new Node(imageView57,null,null,0,0));
        images.add(new Node(imageView60,null,null,0,0));
        images.add(new Node(imageView61,null,null,0,0));
        images.add(new Node(imageView62,null,null,0,0));
        images.add(new Node(imageView63,null,null,0,0));
        images.add(new Node(imageView64,null,null,0,0));
        images.add(new Node(imageView65,null,null,0,0));
        images.add(new Node(imageView66,null,null,0,0));
        images.add(new Node(imageView67,null,null,0,0));
        images.add(new Node(imageView70,null,null,0,0));
        images.add(new Node(imageView71,null,null,0,0));
        images.add(new Node(imageView72,null,null,0,0));
        images.add(new Node(imageView73,null,null,0,0));
        images.add(new Node(imageView74,null,null,0,0));
        images.add(new Node(imageView75,null,null,0,0));
        images.add(new Node(imageView76,null,null,0,0));
        images.add(new Node(imageView77,null,null,0,0));







        Level8_Activity.piece_listener piece=new Level8_Activity.piece_listener();

        updater=new updateneighbor(cols);


        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                images.get(i*cols+j).setRow(i);
                images.get(i*cols+j).setCol(j);
                if(!(i==3 && j==0 || i==4 && j==0 || i==3  && j==cols-1  || i==4 && j==cols-1))
                    images.get(i*cols+j).getImage().setOnClickListener(piece);
                images.get(i*cols+j).getImage().setTag(R.string.row,Integer.toString(i));//row
                images.get(i*cols+j).getImage().setTag(R.string.col,Integer.toString(j));//col
                updater.updateneighbor(images,i,j, images.get(i*cols+j).getImage().getTag(R.string.shape).toString(),images.get(i*cols+j).getImage().getTag(R.string.shape).toString());

            }
        }

    }

    private class piece_listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {


            switch (v.getTag(R.string.shape).toString()) {
                case "1": {
                    updater.updateneighbor(images, Integer.parseInt(v.getTag(R.string.row).toString()), Integer.parseInt(v.getTag(R.string.col).toString()), "2", "1");
                    v.setTag(R.string.shape, "2");
                    v.setRotation((v.getRotation() + 90) % 180);
                    break;
                }
                case "2": {
                    updater.updateneighbor(images, Integer.parseInt(v.getTag(R.string.row).toString()), Integer.parseInt(v.getTag(R.string.col).toString()), "1", "2");
                    v.setTag(R.string.shape, "1");
                    v.setRotation((v.getRotation() + 90) % 180);
                    break;
                }
                case "3": {
                    updater.updateneighbor(images, Integer.parseInt(v.getTag(R.string.row).toString()), Integer.parseInt(v.getTag(R.string.col).toString()), "4", "3");
                    v.setTag(R.string.shape, "4");
                    v.setRotation((v.getRotation() + 90) % 360);
                    break;
                }
                case "4": {
                    updater.updateneighbor(images, Integer.parseInt(v.getTag(R.string.row).toString()), Integer.parseInt(v.getTag(R.string.col).toString()), "5", "4");
                    v.setTag(R.string.shape, "5");
                    v.setRotation((v.getRotation() + 90) % 360);
                    break;
                }
                case "5": {
                    updater.updateneighbor(images, Integer.parseInt(v.getTag(R.string.row).toString()), Integer.parseInt(v.getTag(R.string.col).toString()), "6", "5");
                    v.setTag(R.string.shape, "6");
                    v.setRotation((v.getRotation() + 90) % 360);
                    break;
                }
                case "6": {
                    updater.updateneighbor(images, Integer.parseInt(v.getTag(R.string.row).toString()), Integer.parseInt(v.getTag(R.string.col).toString()), "3", "6");
                    v.setTag(R.string.shape, "3");
                    v.setRotation((v.getRotation() + 90) % 360);
                    break;
                }
            }

            stepscount++;
            steps.setText(stepscount + "");


            ArrayList<Integer> prow = new ArrayList<Integer>();
            ArrayList<Integer> pcol = new ArrayList<Integer>();
            ArrayList<Integer> prow1 = new ArrayList<Integer>();
            ArrayList<Integer> pcol1 = new ArrayList<Integer>();
            AbstractSearch searchAlgo = new DepthFirstSearch(images.get(24), images.get(31), prow, pcol);
            AbstractSearch searchAlgo1 = new DepthFirstSearch(images.get(32), images.get(39), prow1, pcol1);

            if (searchAlgo.execute() && searchAlgo1.execute()) {
                countDownTimer.cancel();

                for (int i = 0; i < rows * cols; i++) {
                    images.get(i).getImage().setClickable(false);
                }
                score = (int) currtimer + (level * 10) - stepscount;
                if (score < 0)
                    score = 0;

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

                if (id != -1) {
                    if (players.get(id).getLevel() >= level) {
                        if (players.get(id).getlevscore(level) < score) {
                            players.get(id).setlevscor(score, level);
                            players.get(id).updatescore();
                        }
                    } else {
                        players.get(id).setlevscor(score, level);
                        players.get(id).updatescore();
                        players.get(id).setLevel(level);
                    }


                }
                try {
                    FileOutputStream fos = openFileOutput("players", MODE_PRIVATE);
                    ObjectOutput oos = new ObjectOutputStream(fos);
                    oos.writeObject(players);
                    oos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                levelpass(prow, pcol, prow1, pcol1, score);
            }

        }
    }




    void levelpass(final ArrayList<Integer> prow, final ArrayList<Integer> pcol,final ArrayList<Integer> prow1, final ArrayList<Integer> pcol1, final int score1)
    {

        final ImageView success=findViewById(R.id.sign);
        final AnimationDrawable animationDrawable = (AnimationDrawable) success.getDrawable();

        final CountDownTimer countDownTimersign = new CountDownTimer(5 * 750, 750) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {

                animationDrawable.stop();
                success.setVisibility(View.INVISIBLE);
                if (!Level8_Activity.this.isDestroyed()) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Level8_Activity.this);

                    final View dialogView = getLayoutInflater().inflate(R.layout.success_dialog, null);
                    final Button next = dialogView.findViewById(R.id.next);
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    builder.setView( dialogView).show();
                    TextView scoreview=dialogView.findViewById(R.id.points);
                    scoreview.setText(getText(R.string.yourscore)+" "+score1);
                }
            }
        };


        int size=0;
        if (prow.size()>prow1.size())size=prow.size();
        else size=prow1.size();

        CountDownTimer countDownTimerlight = new CountDownTimer(size * 400, 125) {
            int i = 0,j=0;

            public void onTick(long millisUntilFinished) {
                if (i < prow.size()) {

                    setanim(prow,pcol,i);
                    AnimationDrawable animationDrawable1 = (AnimationDrawable) images.get(prow.get(i)*cols+pcol.get(i)).getImage().getDrawable();
                    animationDrawable1.start();
                    i++;
                }
                if (j < prow1.size()) {

                    setanim(prow1,pcol1,j);
                    AnimationDrawable animationDrawable1 = (AnimationDrawable) images.get(prow1.get(j)*cols+pcol1.get(j)).getImage().getDrawable();
                    animationDrawable1.start();
                    j++;
                }
            }

            public void onFinish() {

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(success,"scaleX",1.4f).setDuration(2000);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(success,"scaleY",1.6f).setDuration(2000);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(success,"translationY",400).setDuration(2000);
                AnimatorSet set1 = new AnimatorSet();
                set1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        animationDrawable.start();
                        countDownTimersign.start();
                        TextView start=findViewById(R.id.startanim);
                        new ParticleSystem(Level8_Activity.this, 150, R.drawable.ic_stop_black_24dp, 3000)//yellow confetti
                                .setSpeedRange(0.1f, 0.25f)
                                .setRotationSpeedRange(90, 180)
                                .setInitialRotationRange(0, 360)
                                .oneShot(start, 150);
                        new ParticleSystem(Level8_Activity.this, 150, R.drawable.ic_stop_blue_24dp, 3000)//blue confetti
                                .setSpeedRange(0.1f, 0.25f)
                                .setRotationSpeedRange(90, 180)
                                .setInitialRotationRange(0, 360)
                                .oneShot(start, 150);


                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                set1.playTogether(animator1,animator2,animator3);
                set1.start();
            }
        }.start();


    }

    void setanim(final ArrayList<Integer> prow, final ArrayList<Integer> pcol,int i)
    {
        switch (images.get(prow.get(i)*cols+pcol.get(i)).getImage().getTag(R.string.shape).toString()) {
            case "1": {
                if (i == 0) {
                    images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.linetbanim);
                } else {
                    if (prow.get(i - 1)< prow.get(i))
                        images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.linetbanim);
                    else images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.linebtanim);
                }

                break;
            }
            case "2": {

                if (i == 0) {
                    images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.linebtanim);
                } else {
                    if (pcol.get(i - 1)< pcol.get(i))
                        images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.linebtanim);
                    else images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.linetbanim);
                }
                break;
            }
            case "3": {
                if (i == 0) {
                    images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.arcbranim);
                } else {
                    if (pcol.get(i - 1) == pcol.get(i))
                        images.get(prow.get(i) * cols + pcol.get(i)).getImage().setImageResource(R.drawable.arcbranim);
                    else
                        images.get(prow.get(i) * cols + pcol.get(i)).getImage().setImageResource(R.drawable.arcrbanim);
                }
                break;
            }
            case "4": {
                if (i == 0) {
                    images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.arcbranim);
                } else {
                    if (prow.get(i - 1) == prow.get(i))
                        images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.arcbranim);
                    else images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.arcrbanim);

                }
                break;
            }
            case "5": {
                if (i == 0) {
                    images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.arcrbanim);
                } else {
                    if (pcol.get(i - 1) == pcol.get(i))
                        images.get(prow.get(i) * cols + pcol.get(i)).getImage().setImageResource(R.drawable.arcbranim);
                    else
                        images.get(prow.get(i) * cols + pcol.get(i)).getImage().setImageResource(R.drawable.arcrbanim);
                }
                break;
            }
            case "6": {
                if (i == 0) {
                    images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.arcrbanim);
                } else {
                    if (prow.get(i - 1) == prow.get(i))
                        images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.arcbranim);
                    else images.get(prow.get(i)*cols+pcol.get(i)).getImage().setImageResource(R.drawable.arcrbanim);

                }

                break;
            }
        }

    }


    void timerfunc()
    {
        final TextView timer=findViewById(R.id.time_count);
        countDownTimer = new CountDownTimer(60*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                currtimer=(millisUntilFinished / 1000);
                if (currtimer== 10)
                    timer.setTextColor(getResources().getColor(R.color.red));
                if (currtimer > 60)
                    timer.setText("01:0" + (currtimer - 60));
                else {
                    if (currtimer<10)
                        timer.setText("00:0" + currtimer);
                    else timer.setText("00:" + currtimer);

                }
            }

            public void onFinish() {

                if (!Level8_Activity.this.isDestroyed()) {
                    for(int i=0;i<rows*cols;i++)
                    {
                        images.get(i).getImage().setClickable(false);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(Level8_Activity.this);

                    View dialogView = getLayoutInflater().inflate(R.layout.restart_dialog, null);
                    //ObjectAnimator animator = ObjectAnimator.ofFloat(dialogView,"alpha",0f,1f).setDuration(1000);

                    final Button restart = dialogView.findViewById(R.id.restartbut);
                    final Button exit = dialogView.findViewById(R.id.exitbut);
                    exit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                    restart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(Level8_Activity.this, Level8_Activity.class);
                            finish();
                            startActivity(intent1);
                        }
                    });

                    builder.setView( dialogView).show();

                }
            }

        };

        countDownTimer.start();

    }


    public void updateSound() {
        if (Isplaying)
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

            if(AudioPlay.mediaPlayer!=null)
                AudioPlay.stopAudio();
            if(mp!=null)
                mp.stop();

            finishAffinity();
        }

        if (item.getItemId() == R.id.music) {

            if (mp != null) {
                if (Isplaying) {
                    Isplaying=false;
                    mp.pause();
                    AudioPlay.shouldPlay=false;

                } else {
                    Isplaying=true;
                    AudioPlay.shouldPlay=true;
                    mp.start();
                }
            }

        }
        invalidateOptionsMenu();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Isplaying)
            mp.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mp!=null) {
            mp.stop();
            mp.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Isplaying)
            mp.start();
    }
}
