package com.example.win10user.project;
import android.widget.ImageView;

import java.io.Serializable;

public class Player implements Serializable,Comparable<Player>
{

    private String name;
    private int score;
    private int id;
    private int level;
    int [] score_des;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player() { }

    public Player(String name, int score,int id,int level) {
        this.name = name;
        this.score = score;
        this.id=id;
        this.level=level;
        score_des=new int[9];

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getLevel()
    {
        return level;
    }
    public void setLevel(int lev)
    {
        level=lev;
    }

    public int[] getScore_des() {
        return score_des;
    }

    public void setScore_des(int[] score_des) {
        this.score_des = score_des;
    }
    public void setlevscor(int s,int lev)
    {
        score_des[lev-1]=s;
    }
    public void updatescore()
    {
        int temp=0;
        for(int i=0;i<9;i++)
            if(score_des[i]!=0)
                temp+=score_des[i];
        score=temp;
    }
    public int getlevscore(int lev){
        return score_des[lev-1];
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Player o) {
        if(this.score>o.score)
            return -1;
        if(this.score<o.score)
            return 1;
        return 0;
    }
}
