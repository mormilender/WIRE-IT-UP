package com.example.win10user.project;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Score_adapter  extends BaseAdapter {


    List<Player> players;
    ArrayList<String> paths;
    public Score_adapter(List<Player>players,   ArrayList<String>paths ) {
        super();
        this.players=players;
        this.paths=paths;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null)
        {
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.one_score_layout,parent,false);

        }

        TextView name_player=convertView.findViewById(R.id.name_player);
        TextView score_player=convertView.findViewById(R.id.score_player);
        TextView number=convertView.findViewById(R.id.number);
        ImageView image_player=convertView.findViewById(R.id.image_player);


        int num=position+1;
        String str=num+"";
        number.setText(str);

        Player ply= players.get(position);

        name_player.setText(ply.getName()+"");

        score_player.setText(ply.getScore()+"");

        image_player.setImageDrawable(Drawable.createFromPath(paths.get(ply.getId())));
/*

        if(ply.getFlagResId()!=null)
        {8
            String uri = ply.getFlagResId();
            Uri imageUri = Uri.parse(uri);
            image_player.setImageURI(imageUri);

        }
*/


        /*String uriString= getIntent().getStringExtra("uriString");*/

       /* Uri imageUri = Uri.parse(uriString);
        image_player.setImageURI(imageUri);*/


        return convertView;
    }




}
