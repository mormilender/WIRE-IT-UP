package com.example.win10user.project;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class NewPlayActivity extends AppCompatActivity {

    ImageView imageView;
    Uri imageUri;
    final int CAMERA_REQ=1;
    final int GALLERY_PIC=2;
    ArrayList<Player>players;
    ArrayList<String>names;
    int WRITE_PREMITION_REQ=1;
    File file;
    int id;
    boolean picsave;
    ArrayList<String> paths ;
    private MenuItem Myitem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_play);

        imageView = findViewById(R.id.profile_image);
        picsave=false;

        final TextView name = findViewById(R.id.user_name);
        if(names==null)
           names=new  ArrayList<>();
        Button take_pic = findViewById(R.id.btn_pic);
        ImageButton gallary_pic = findViewById(R.id.btn_gallery);
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
        try {
            FileInputStream fis=openFileInput("names");
            ObjectInputStream ois=new ObjectInputStream(fis);
            names=(ArrayList<String>)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(names==null)
            names=new ArrayList<>();
        if(players==null)
            players=new ArrayList<>();
        if(paths==null)
            paths=new  ArrayList<>();
        if(players.isEmpty())id=0;
        else id=players.get(players.size()-1).getId()+1;

        take_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                file=new File(Environment.getExternalStorageDirectory(),id+".jpg");
                Uri photoURI = FileProvider.getUriForFile(NewPlayActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider", file);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, CAMERA_REQ);
            }
        });
        gallary_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALLERY_PIC);
            }
        });

        Button save=findViewById(R.id.save);
        if(Build.VERSION.SDK_INT>=23)
        {
            int hasWritePre=checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(hasWritePre!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_PREMITION_REQ);

            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((name.getText()==null) || (names.contains(name.getText().toString()))){
                    String msg;
                    if(names.contains(name.getText().toString()))msg=getString(R.string.u_ex);
                    else msg=getString(R.string.no_name);
                    Toast.makeText(NewPlayActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent in = new Intent(NewPlayActivity.this, MainActivity.class);
                    String uname = name.getText().toString();
                    names.add(uname);
                    Player player = new Player(uname, 0,id,0);
                    players.add(player);
                    in.putExtra("id",id);
                    if(!picsave)
                    {
                        Bitmap bm = BitmapFactory.decodeResource( getResources(), R.drawable.user);
                        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                        File file = new File(extStorageDirectory, id+".jpg");
                        FileOutputStream outStream = null;
                        try {
                            outStream = new FileOutputStream(file);
                            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
                            outStream.flush();
                            outStream.close();
                            paths.add(file.getAbsolutePath());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        FileOutputStream fos=openFileOutput("names",MODE_PRIVATE);
                        ObjectOutput oos=new ObjectOutputStream(fos);
                        oos.writeObject(names);
                        oos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
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
                    try {
                        FileOutputStream fos=openFileOutput("paths",MODE_PRIVATE);
                        ObjectOutput oos=new ObjectOutputStream(fos);
                        oos.writeObject(paths);
                        oos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    name.setText("");
                    imageView.setImageResource(R.drawable.user);
                    finish();
                    startActivity(in);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_PREMITION_REQ) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "cant save picture", Toast.LENGTH_SHORT).show();
            }
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQ && resultCode == RESULT_OK) {
            if(file.getAbsolutePath()!=null)
            {
                if(!picsave)paths.add(file.getAbsolutePath());
                else paths.set(id,file.getAbsolutePath());
                picsave=true;
            }
            imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        }
        if (requestCode == GALLERY_PIC && resultCode == RESULT_OK) {
            imageUri = data.getData();
            String path=getRealPathFromURI(imageUri);
            if(path!=null) {
                if(!picsave)paths.add(path);
                else paths.set(id,path);
                picsave=true;
                }
            imageView.setImageURI(imageUri);
            }
        }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!AudioPlay.isplayingAudio &&   AudioPlay.shouldPlay)
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
