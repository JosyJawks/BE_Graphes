package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;


public class Label implements Comparable<Label>{
    
    protected Node currentNode;

    protected boolean mark;

    protected double realCost;

    protected Arc father;

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

public double getTotalCost(){
    return this.getrealCost();
}

/**
     * Compare the ID of this node with the ID of the given node.
     * 
     * @param other Node to compare this node with.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Label other) {
        return Double.compare(getTotalCost(), other.getTotalCost());
    }
}
