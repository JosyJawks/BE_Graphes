package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Graph;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    public void Init(Label[] Labels, Graph graph, ShortestPathData data){
        int i;
        //Initialisation de l'algorithme
        for(i=0;i<Labels.length;i++){
            Labels[i] = new LabelStar();
            Labels[i].currentNode = graph.get(i);
            ((LabelStar) Labels[i]).setEstimateCost(data);
            Labels[i].mark = false;
            Labels[i].realCost = Double.POSITIVE_INFINITY;
            Labels[i].father = null;
        }
    }
        

}
