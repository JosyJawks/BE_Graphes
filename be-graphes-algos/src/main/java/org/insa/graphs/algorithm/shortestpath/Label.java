package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label {
    
    protected Node currentNode;

    protected boolean mark;

    protected float realCost;

    protected Arc father;

//Getters

public Node getcurrentNode(){
    return this.currentNode;
}

public boolean getMark(){
    return this.mark;
}

public float getrealCost(){
    return this.realCost;
}

public Arc getFather(){
    return this.father;
}

//Methods
public float getCost(){
    float cost;
    cost = this.realCost;
    return cost;
}


}