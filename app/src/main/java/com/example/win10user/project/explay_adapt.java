package com.example.win10user.project;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class explay_adapt extends BaseAdapter {
    List<Player> players;
    ArrayList<String> paths;

    public explay_adapt(List<Player>players, ArrayList<String>paths ) {
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


        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ex_play_act, parent, false);

        }

        TextView name_player = convertView.findViewById(R.id.name_player);
        ImageView image_player = convertView.findViewById(R.id.image_player);

        Player ply = players.get(position);

        name_player.setText(ply.getName() + "");

        image_player.setImageDrawable(Drawable.createFromPath(paths.get(ply.getId())));

        return convertView;
    }
}
