package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.AbstractInputData.Mode;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;

public class LabelStar extends Label{

    protected double estimateCost;


    //Méthodes
    //Calcul de la distance estimée 

    public void setEstimateCost(ShortestPathData data){
        Graph graph = data.getGraph();
        Node graphDest = data.getDestination();
        Mode mode = data.getMode();
        if(mode == Mode.LENGTH){
            this.estimateCost = currentNode.getPoint().distanceTo(graphDest.getPoint());
        }
        if(mode == Mode.TIME){
            this.estimateCost = currentNode.getPoint().distanceTo(graphDest.getPoint()) / graph.getGraphInformation().getMaximumSpeed();
        }
    }


    //Redéfinition de getTotalCost()
    @Override
    public double getTotalCost(){
        double cost = this.getrealCost() + this.estimateCost;
        return cost;
    }
}
