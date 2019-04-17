package com.example.win10user.project;

import android.widget.ImageView;

class Node
{
    ImageView image;
    Node a;
    Node b;
    int row;
    int col;

    Node(ImageView image,Node a,Node b,int i,int j)
    {
        this.image=image;
        this.a=a;
        this.b=b;
        row=i;
        col=j;

    }

    public Node getA() {
        return a;
    }

    public Node getB() {
        return b;
    }

    public ImageView getImage() {
        return image;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setA(Node a) {
        this.a = a;
    }

    public void setB(Node b) {
        this.b = b;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            if(this==obj)return true;
        }
        return false;
    }
}