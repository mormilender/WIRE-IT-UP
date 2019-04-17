package com.example.win10user.project;

import java.util.ArrayList;
import java.util.HashMap;

public class updateneighbor {
    int row, col,cols;
    String newshape, oldshape;
    HashMap<String, ArrayList<String>> vertical;
    HashMap<String, ArrayList<String>> horizontal;


    public updateneighbor(int cols) {
        this.cols=cols;
        vertical = new HashMap<>();
        horizontal = new HashMap<>();

        ArrayList<String> temp1 = new ArrayList<>();
        temp1.add("1");
        temp1.add("5");
        temp1.add("6");
        ArrayList<String> temp2 = new ArrayList<>();
        temp2.add("2");
        temp2.add("4");
        temp2.add("5");
        vertical.put("1", temp1);
        vertical.put("3", temp1);
        vertical.put("4", temp1);
        horizontal.put("2", temp2);
        horizontal.put("3", temp2);
        horizontal.put("6", temp2);
    }

    void updateneighbor(ArrayList<Node>images,int row, int col, String newshape, String oldshape) {

        switch (oldshape)
        {
            case "1": {
                if(row!=0) {
                    if (images.get((row - 1) * cols + col).getA()==images.get(row * cols + col))
                        images.get((row - 1) * cols + col).setA(null);
                    else {
                        if(images.get((row - 1) * cols + col).getB() ==images.get(row * cols + col))
                            images.get((row - 1) * cols + col).setB(null);
                    }
                }
                if(row!=cols-1)
                {
                    if (images.get((row + 1) * cols + col).getA()==images.get(row * cols + col))
                        images.get((row + 1) * cols + col).setA(null);
                    else {
                        if (images.get((row + 1) * cols + col).getB()==images.get(row * cols + col))
                            images.get((row + 1) * cols + col).setB(null);
                    }
                }
                break;
            }
            case "2": {
                if(col!=0)
                {
                    if (images.get(row * cols + (col - 1)).getA()==images.get(row * cols + col))
                        images.get(row * cols + (col - 1)).setA(null);
                    else {
                        if (images.get(row * cols + (col - 1)).getB()==images.get(row * cols + col))
                            images.get(row * cols + (col - 1)).setB(null);
                    }
                }
                if(col!=cols-1) {
                    if (images.get(row * cols + (col + 1)).getA()==images.get(row * cols + col))
                        images.get(row * cols + (col + 1)).setA(null);
                    else {
                        if (images.get(row * cols + (col + 1)).getB()==images.get(row * cols + col))
                            images.get(row * cols + (col + 1)).setB(null);
                    }
                }
                break;
            }
            case "3": {

                if(row!=cols-1) {
                    ArrayList<String> temp = vertical.get("3");
                    if (temp!=null && temp.contains(images.get((row + 1) * cols + col).getImage().getTag(R.string.shape).toString())) {
                        if (images.get((row + 1) * cols + col).getA() == images.get(row * cols + col))
                            images.get((row + 1) * cols + col).setA(null);
                        else {
                            if (images.get((row + 1) * cols + col).getB() == images.get(row * cols + col))
                                images.get((row + 1) * cols + col).setB(null);
                        }
                    }
                }
                if(col!=cols-1) {
                    ArrayList<String> temp = horizontal.get("3");
                    if (temp!=null && temp.contains(images.get(row * cols + (col + 1)).getImage().getTag(R.string.shape).toString())) {
                        if (images.get(row * cols + (col + 1)).getA() == images.get(row * cols + col))
                            images.get(row * cols + (col + 1)).setA(null);
                        else {
                            if (images.get(row * cols + (col + 1)).getB() == images.get(row * cols + col))
                                images.get(row * cols + (col + 1)).setB(null);
                        }
                    }
                }
                break;
            }
            case "4": {
                if(row!=cols-1) {
                    ArrayList<String> temp = vertical.get("4");
                    if (temp!=null && temp.contains(images.get((row + 1) * cols + col).getImage().getTag(R.string.shape).toString())) {
                        if (images.get((row + 1) * cols + col).getA() == images.get(row * cols + col))
                            images.get((row + 1) * cols + col).setA(null);
                        else {
                            if (images.get((row + 1) * cols + col).getB() == images.get(row * cols + col))
                                images.get((row + 1) * cols + col).setB(null);
                        }
                    }
                }
                if(col!=0) {
                    ArrayList<String> temp = horizontal.get(images.get(row * cols + (col - 1)).getImage().getTag(R.string.shape).toString());
                    if (temp!=null && temp.contains("4")) {
                        if (images.get(row * cols + (col - 1)).getA() == images.get(row * cols + col))
                            images.get(row * cols + (col - 1)).setA(null);
                        else {
                            if (images.get(row * cols + (col - 1)).getB() == images.get(row * cols + col))
                                images.get(row * cols + (col - 1)).setB(null);
                        }
                    }
                }
                break;
            }
            case "5": {
                if(row!=0) {
                    ArrayList<String> temp=vertical.get(images.get((row - 1) * cols + col).getImage().getTag(R.string.shape).toString());
                    if(temp!=null && temp.contains("5")) {
                        if (images.get((row - 1) * cols + col).getA() == images.get(row * cols + col))
                            images.get((row - 1) * cols + col).setA(null);
                        else {
                            if (images.get((row - 1) * cols + col).getB() == images.get(row * cols + col))
                                images.get((row - 1) * cols + col).setB(null);
                        }
                    }
                }
                if(col!=0) {
                    ArrayList<String> temp = horizontal.get(images.get(row * cols + (col - 1)).getImage().getTag(R.string.shape).toString());
                    if (temp!=null && temp.contains("5")) {
                        if (images.get(row * cols + (col - 1)).getA() == images.get(row * cols + col))
                            images.get(row * cols + (col - 1)).setA(null);
                        else {
                            if (images.get(row * cols + (col - 1)).getB() == images.get(row * cols + col))
                                images.get(row * cols + (col - 1)).setB(null);
                        }
                    }
                }
                break;
            }
            case "6": {
                if(row!=0) {
                    ArrayList<String> temp=vertical.get(images.get((row - 1) * cols + col).getImage().getTag(R.string.shape).toString());
                    if(temp!=null && temp.contains("6")) {
                        if (images.get((row - 1) * cols + col).getA() == images.get(row * cols + col))
                            images.get((row - 1) * cols + col).setA(null);
                        else {
                            if (images.get((row - 1) * cols + col).getB() == images.get(row * cols + col))
                                images.get((row - 1) * cols + col).setB(null);
                        }
                    }
                }
                if(col!=cols-1) {
                    ArrayList<String> temp = horizontal.get("6");
                    if (temp!=null && temp.contains(images.get(row * cols + (col + 1)).getImage().getTag(R.string.shape).toString())) {
                        if (images.get(row * cols + (col + 1)).getA() == images.get(row * cols + col))
                            images.get(row * cols + (col + 1)).setA(null);
                        else {
                            if (images.get(row * cols + (col + 1)).getB() == images.get(row * cols + col))
                                images.get(row * cols + (col + 1)).setB(null);
                        }
                    }
                }
                break;
            }
            default:
                break;
        }

        images.get(row * cols + col).setA(null);
        images.get(row * cols + col).setB(null);

        switch (newshape)
        {
            case "1": {
                if(row!=0) {
                    ArrayList<String> temp=vertical.get(images.get((row - 1) * cols + col).getImage().getTag(R.string.shape).toString());
                    if(temp!=null && temp.contains("1")) {
                        if (images.get((row - 1) * cols + col).getA()==null)
                            images.get((row - 1) * cols + col).setA(images.get((row * cols) + col));
                        else {
                            if(images.get((row - 1) * cols + col).getB() == null)
                                images.get((row - 1) * cols + col).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setA(images.get((row - 1) * cols + col));
                    }
                }
                if(row!=cols-1) {
                    ArrayList<String> temp = vertical.get("1");
                    if (temp!=null && temp.contains(images.get((row + 1) * cols + col).getImage().getTag(R.string.shape).toString())) {
                        if (images.get((row + 1) * cols + col).getA()==null)
                            images.get((row + 1) * cols + col).setA(images.get(row * cols + col));
                        else {
                            if (images.get((row + 1) * cols + col).getB() == null)
                                images.get((row + 1) * cols + col).setB(images.get(row * cols + col));

                        }
                        images.get(row * cols + col).setB(images.get((row + 1) * cols + col));
                    }
                }

                break;
            }
            case "2": {
                if(col!=0) {
                    ArrayList<String> temp=horizontal.get(images.get(row * cols + (col-1)).getImage().getTag(R.string.shape).toString());
                    if(temp!=null && temp.contains("2")) {
                        if (images.get(row * cols + (col - 1)).getA()== null)
                            images.get(row * cols + (col - 1)).setA(images.get((row * cols) + col));
                        else{
                            if (images.get(row * cols + (col - 1)).getB()== null)
                                images.get(row * cols + (col - 1)).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setA(images.get(row * cols + (col - 1)));
                    }
                }
                if(col!=cols-1) {
                    ArrayList<String> temp = horizontal.get("2");
                    if (temp!=null && temp.contains(images.get(row * cols + (col + 1)).getImage().getTag(R.string.shape).toString())) {
                        if (images.get(row * cols + (col + 1)).getA() == null)
                            images.get(row * cols + (col + 1)).setA(images.get((row * cols) + col));
                        else {
                            if (images.get(row * cols + (col + 1)).getB() == null)
                                images.get(row * cols + (col + 1)).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setB(images.get(row * cols + (col + 1)));
                    }
                }
                break;
            }
            case "3": {
                if(row!=cols-1) {
                    ArrayList<String> temp = vertical.get("3");
                    if (temp!=null && temp.contains(images.get((row + 1) * cols + col).getImage().getTag(R.string.shape).toString())) {
                        if (images.get((row + 1) * cols + col).getA()==null)
                            images.get((row + 1) * cols + col).setA(images.get(row * cols + col));
                        else {
                            if (images.get((row + 1) * cols + col).getB() == null)
                                images.get((row + 1) * cols + col).setB(images.get(row * cols + col));
                        }
                        images.get(row * cols + col).setA(images.get((row + 1) * cols + col));
                    }
                }
                if(col!=cols-1) {
                    ArrayList<String> temp = horizontal.get("3");
                    if (temp!=null && temp.contains(images.get(row * cols + (col + 1)).getImage().getTag(R.string.shape).toString())) {
                        if (images.get(row * cols + (col + 1)).getA() == null)
                            images.get(row * cols + (col + 1)).setA(images.get((row * cols) + col));
                        else {
                            if (images.get(row * cols + (col + 1)).getB() == null)
                                images.get(row * cols + (col + 1)).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setB(images.get(row * cols + (col + 1)));
                    }
                }
                break;
            }
            case "4": {
                if(col!=0) {
                    ArrayList<String> temp=horizontal.get(images.get(row * cols + (col-1)).getImage().getTag(R.string.shape).toString());
                    if(temp!=null && temp.contains("4")) {
                        if (images.get(row * cols + (col - 1)).getA()== null)
                            images.get(row * cols + (col - 1)).setA(images.get((row * cols) + col));
                        else{
                            if (images.get(row * cols + (col - 1)).getB()== null)
                                images.get(row * cols + (col - 1)).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setA(images.get(row * cols + (col - 1)));
                    }
                }
                if(row!=cols-1) {
                    ArrayList<String> temp = vertical.get("4");
                    if (temp!=null &&  temp.contains(images.get((row + 1) * cols + col).getImage().getTag(R.string.shape).toString())) {
                        if (images.get((row + 1) * cols + col).getA()==null)
                            images.get((row + 1) * cols + col).setA(images.get(row * cols + col));
                        else {
                            if (images.get((row + 1) * cols + col).getB() == null)
                                images.get((row + 1) * cols + col).setB(images.get(row * cols + col));
                        }
                        images.get(row * cols + col).setB(images.get((row + 1) * cols + col));
                    }
                }
                break;
            }
            case "5": {
                if(row!=0) {
                    ArrayList<String> temp=vertical.get(images.get((row - 1) * cols + col).getImage().getTag(R.string.shape).toString());
                    if(temp!=null && temp.contains("5")) {
                        if (images.get((row - 1) * cols + col).getA()==null)
                            images.get((row - 1) * cols + col).setA(images.get((row * cols) + col));
                        else {
                            if(images.get((row - 1) * cols + col).getB() == null)
                                images.get((row - 1) * cols + col).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setA(images.get((row - 1) * cols + col));
                    }
                }
                if(col!=0) {
                    ArrayList<String> temp=horizontal.get(images.get(row * cols + (col-1)).getImage().getTag(R.string.shape).toString());
                    if(temp!=null && temp.contains("5")) {
                        if (images.get(row * cols + (col - 1)).getA()== null)
                            images.get(row * cols + (col - 1)).setA(images.get((row * cols) + col));
                        else{
                            if (images.get(row * cols + (col - 1)).getB()== null)
                                images.get(row * cols + (col - 1)).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setB(images.get(row * cols + (col - 1)));
                    }
                }
                break;
            }
            case "6": {
                if(row!=0) {
                    ArrayList<String> temp=vertical.get(images.get((row - 1) * cols + col).getImage().getTag(R.string.shape).toString());
                    if(temp!=null && temp.contains("6")) {
                        if (images.get((row - 1) * cols + col).getA()==null)
                            images.get((row - 1) * cols + col).setA(images.get((row * cols) + col));
                        else {
                            if(images.get((row - 1) * cols + col).getB() == null)
                                images.get((row - 1) * cols + col).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setA(images.get((row - 1) * cols + col));
                    }
                }
                if(col!=cols-1) {
                    ArrayList<String> temp = horizontal.get("6");
                    if (temp!=null && temp.contains(images.get(row * cols + (col + 1)).getImage().getTag(R.string.shape).toString())) {
                        if (images.get(row * cols + (col + 1)).getA() == null)
                            images.get(row * cols + (col + 1)).setA(images.get((row * cols) + col));
                        else {
                            if (images.get(row * cols + (col + 1)).getB() == null)
                                images.get(row * cols + (col + 1)).setB(images.get((row * cols) + col));
                        }
                        images.get(row * cols + col).setB(images.get(row * cols + (col + 1)));
                    }
                }
                break;
            }
        }

    }
    }

