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


public class Level3_Activity extends AppCompatActivity {
    int stepscount=0;
    TextView steps;
    long currtimer;
    int rows=4;
    int cols=4;
    CountDownTimer countDownTimer;
    int score;
    int level=3;
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
        setContentView(R.layout.activity_lvl3);
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
        ImageView imageView10 = findViewById(R.id.b10);
        ImageView imageView11 = findViewById(R.id.b11);
        ImageView imageView12 = findViewById(R.id.b12);
        ImageView imageView13 = findViewById(R.id.b13);
        ImageView imageView20 = findViewById(R.id.b20);
        ImageView imageView21 = findViewById(R.id.b21);
        ImageView imageView22 = findViewById(R.id.b22);
        ImageView imageView23 = findViewById(R.id.b23);
        ImageView imageView30 = findViewById(R.id.b30);
        ImageView imageView31 = findViewById(R.id.b31);
        ImageView imageView32 = findViewById(R.id.b32);
        ImageView imageView33 = findViewById(R.id.b33);

        //shapes num
        imageView00.setTag(R.string.shape,"2");
        imageView01.setTag(R.string.shape,"1");
        imageView02.setTag(R.string.shape,"4");
        imageView03.setTag(R.string.shape,"2");
        imageView10.setTag(R.string.shape,"1");
        imageView11.setTag(R.string.shape,"3");
        imageView12.setTag(R.string.shape,"6");
        imageView13.setTag(R.string.shape,"6");
        imageView20.setTag(R.string.shape,"3");
        imageView21.setTag(R.string.shape,"6");
        imageView22.setTag(R.string.shape,"4");
        imageView23.setTag(R.string.shape,"4");
        imageView30.setTag(R.string.shape,"3");
        imageView31.setTag(R.string.shape,"5");
        imageView32.setTag(R.string.shape,"3");
        imageView33.setTag(R.string.shape,"2");


        images=new ArrayList<Node>();
        images.add(new Node(imageView00,null,null,0,0));
        images.add(new Node(imageView01,null,null,0,0));
        images.add(new Node(imageView02,null,null,0,0));
        images.add(new Node(imageView03,null,null,0,0));
        images.add(new Node(imageView10,null,null,0,0));
        images.add(new Node(imageView11,null,null,0,0));
        images.add(new Node(imageView12,null,null,0,0));
        images.add(new Node(imageView13,null,null,0,0));
        images.add(new Node(imageView20,null,null,0,0));
        images.add(new Node(imageView21,null,null,0,0));
        images.add(new Node(imageView22,null,null,0,0));
        images.add(new Node(imageView23,null,null,0,0));
        images.add(new Node(imageView30,null,null,0,0));
        images.add(new Node(imageView31,null,null,0,0));
        images.add(new Node(imageView32,null,null,0,0));
        images.add(new Node(imageView33,null,null,0,0));



        Level3_Activity.piece_listener piece=new Level3_Activity.piece_listener();

        updater=new updateneighbor(cols);


        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                images.get(i*cols+j).setRow(i);
                images.get(i*cols+j).setCol(j);
                if(!(i==0 && j==0 || i==cols-1 && j==cols-1))
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


            switch (v.getTag(R.string.shape).toString())
            {
                case "1": {
                    updater.updateneighbor(images,Integer.parseInt(v.getTag(R.string.row).toString()),Integer.parseInt(v.getTag(R.string.col).toString()),"2","1");
                    v.setTag(R.string.shape,"2");
                    v.setRotation((v.getRotation() + 90) % 180);
                    break;
                }
                case "2": {
                    updater.updateneighbor(images,Integer.parseInt(v.getTag(R.string.row).toString()),Integer.parseInt(v.getTag(R.string.col).toString()),"1","2");
                    v.setTag(R.string.shape,"1");
                    v.setRotation((v.getRotation() + 90) % 180);
                    break;
                }
                case "3": {
                    updater.updateneighbor(images,Integer.parseInt(v.getTag(R.string.row).toString()),Integer.parseInt(v.getTag(R.string.col).toString()),"4","3");
                    v.setTag(R.string.shape,"4");
                    v.setRotation((v.getRotation() + 90) % 360);
                    break;
                }
                case "4": {
                    updater.updateneighbor(images,Integer.parseInt(v.getTag(R.string.row).toString()),Integer.parseInt(v.getTag(R.string.col).toString()),"5","4");
                    v.setTag(R.string.shape,"5");
                    v.setRotation((v.getRotation() + 90) % 360);
                    break;
                }
                case "5": {
                    updater.updateneighbor(images,Integer.parseInt(v.getTag(R.string.row).toString()),Integer.parseInt(v.getTag(R.string.col).toString()),"6","5");
                    v.setTag(R.string.shape,"6");
                    v.setRotation((v.getRotation() + 90) % 360);
                    break;
                }
                case "6": {
                    updater.updateneighbor(images,Integer.parseInt(v.getTag(R.string.row).toString()),Integer.parseInt(v.getTag(R.string.col).toString()),"3","6");
                    v.setTag(R.string.shape,"3");
                    v.setRotation((v.getRotation() + 90) % 360);
                    break;
                }
            }

            stepscount++;
            steps.setText(stepscount + "");



            ArrayList<Integer> prow=new ArrayList<Integer>();
            ArrayList<Integer> pcol=new ArrayList<Integer>();
            AbstractSearch searchAlgo = new DepthFirstSearch(images.get(0),images.get(15),prow,pcol);

            if(searchAlgo.execute())
            {
                countDownTimer.cancel();

                for(int i=0;i<rows*cols;i++)
                {
                    images.get(i).getImage().setClickable(false);
                }
                score=(int)currtimer+(level*10)-stepscount;
                if (score<0)
                    score=0;

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

                if(id!=-1) {
                    if(players.get(id).getLevel()>=level) {
                        if(players.get(id).getlevscore(level)<score) {
                            players.get(id).setlevscor(score, level);
                            players.get(id).updatescore();
                        }
                    }
                    else {
                        players.get(id).setlevscor(score,level);
                        players.get(id).updatescore();
                        players.get(id).setLevel(level);
                    }


                }
                try {
                    FileOutputStream fos=openFileOutput("players",MODE_PRIVATE);
                    ObjectOutput oos=new ObjectOutputStream(fos);
                    oos.writeObject(players);
                    oos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                levelpass(prow,pcol,score);

            }


        }
    }



    void levelpass(final ArrayList<Integer> prow,final ArrayList<Integer> pcol,final int score1)
    {

        final ImageView success=findViewById(R.id.sign);
        final AnimationDrawable animationDrawable = (AnimationDrawable) success.getDrawable();

        final CountDownTimer countDownTimersign = new CountDownTimer(5 * 750, 750) {
            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {

                animationDrawable.stop();
                success.setVisibility(View.INVISIBLE);
                if (!Level3_Activity.this.isDestroyed()) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Level3_Activity.this);

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

        CountDownTimer countDownTimerlight = new CountDownTimer((prow.size()) * 400, 125) {
            int i = 0;

            public void onTick(long millisUntilFinished) {
                if (i < prow.size()) {
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

                    AnimationDrawable animationDrawable1 = (AnimationDrawable) images.get(prow.get(i)*cols+pcol.get(i)).getImage().getDrawable();
                    animationDrawable1.start();
                    i++;
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
                        new ParticleSystem(Level3_Activity.this, 150, R.drawable.ic_stop_black_24dp, 3000)//yellow confetti
                                .setSpeedRange(0.1f, 0.25f)
                                .setRotationSpeedRange(90, 180)
                                .setInitialRotationRange(0, 360)
                                .oneShot(start, 150);
                        new ParticleSystem(Level3_Activity.this, 150, R.drawable.ic_stop_blue_24dp, 3000)//blue confetti
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


    void timerfunc()
    {
        final TextView timer=findViewById(R.id.time_count);
        countDownTimer = new CountDownTimer(30*1000, 1000) {
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

                if (!Level3_Activity.this.isDestroyed()) {
                    for(int i=0;i<rows*cols;i++)
                    {
                        images.get(i).getImage().setClickable(false);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(Level3_Activity.this);

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
                            Intent intent1 = new Intent(Level3_Activity.this, Level3_Activity.class);
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
                    AudioPlay.shouldPlay=false;
                    Isplaying=false;
                    mp.pause();

                } else {
                    AudioPlay.shouldPlay=true;
                    Isplaying=true;
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
