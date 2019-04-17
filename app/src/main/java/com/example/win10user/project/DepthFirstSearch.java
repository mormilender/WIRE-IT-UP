package com.example.win10user.project;


import java.util.ArrayList;
import java.util.Stack;

class DepthFirstSearch extends AbstractSearch{

    Node startNode;
    Node goalNode;
    ArrayList<Integer> row;
    ArrayList<Integer> col;

    public DepthFirstSearch(Node start, Node goalNode, ArrayList<Integer> r,ArrayList<Integer> c){
        super(start, goalNode);
        this.startNode = start;
        this.goalNode = goalNode;
        row=r;
        col=c;

    }

    public boolean execute(){
        if(this.startNode.equals(goalNode)){
            return true;
        }
        Stack<Node> NodeStack = new Stack<>();
        ArrayList<Node> visitedNodes = new ArrayList<>();

        NodeStack.add(startNode);
        row.add(startNode.getRow());
        col.add(startNode.getCol());

        while(!NodeStack.isEmpty()){
            Node current = NodeStack.pop();
            if(current.equals(goalNode)){
                return true;
            }
            else {
                visitedNodes.add(current);
                if(current.getA()!=null && !visitedNodes.contains(current.getA())) {
                    NodeStack.add(current.getA());
                    row.add(current.getA().getRow());
                    col.add(current.getA().getCol());
                }
                if(current.getB()!=null && !visitedNodes.contains(current.getB())) {
                    NodeStack.add(current.getB());
                    row.add(current.getB().getRow());
                    col.add(current.getB().getCol());
                }
            }
        }

        return false;
    }

}