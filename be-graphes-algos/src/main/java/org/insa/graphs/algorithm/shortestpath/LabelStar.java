package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Point;

public class LabelStar extends Label{

    protected double estimateCost;

    //Méthodes
    //Calcul de la distance estimée 

    //Redéfinition de getTotalCost()
    public double getTotalCost(){
        double cost = this.getrealCost() + this.estimateCost;
        return cost;
    }
}
