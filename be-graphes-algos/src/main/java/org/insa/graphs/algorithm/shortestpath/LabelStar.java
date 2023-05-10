package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{

    protected double estimateCost;


    //Méthodes
    //Calcul de la distance estimée 

    public void setEstimateCost(Node graphDest){
        this.estimateCost = currentNode.getPoint().distanceTo(graphDest.getPoint());
    }

    //Redéfinition de getTotalCost()
    @Override
    public double getTotalCost(){
        double cost = this.getrealCost() + this.estimateCost;
        return cost;
    }
}
