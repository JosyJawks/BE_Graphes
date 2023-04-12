package org.insa.graphs.algorithm.shortestpath;
import java.util.ArrayList;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
    
    protected Node currentNode;

    protected boolean mark;

    protected double realCost;

    protected Arc father;

    public Label(Node currentNode, boolean mark, double realCost, Arc father)
    {
        this.currentNode = currentNode;
        this.mark = mark;
        this.realCost = realCost;
        this.father = father;
    }

    //Getters
    public Node getcurrentNode(){
        return this.currentNode;
    }

    public boolean getMark(){
        return this.mark;
    }

    public double getrealCost(){
        return this.realCost;
    }

    public Arc getFather(){
        return this.father;
    }

    //Methods
    public double getCost(){
        double cost;
        cost = this.realCost;
        return cost;
    }

    /**
     * Compare the cost of this label with the cost of the given label.
     * 
     * @param other Label to compare this node with.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Label other) {
        return Double.compare(getCost(), other.getCost());
    }



}