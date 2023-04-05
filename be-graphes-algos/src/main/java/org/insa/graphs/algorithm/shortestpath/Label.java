package org.insa.graphs.algorithm.shortestpath;
//import org.insa.graphs.model.Arc;
//import org.insa.graphs.model.Node;

public class Label implements Comparable<Label> {
    
    protected int currentNode;

    protected boolean mark;

    protected double realCost;

    protected int father;

    //Getters
    public int getcurrentNode(){
        return this.currentNode;
    }

    public boolean getMark(){
        return this.mark;
    }

    public double getrealCost(){
        return this.realCost;
    }

    public int getFather(){
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