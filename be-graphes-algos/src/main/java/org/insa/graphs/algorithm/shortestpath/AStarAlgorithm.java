package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;

        //Récupération du graphe et de sa taille
        Graph graph = data.getGraph();

        final int nbNodes = graph.size();

        //Dans le cas ou le point de départ et le point de destination sont les mêmes, ont retourne directement une solution 
        if(data.getDestination() == data.getOrigin()){
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, data.getDestination()));
            return solution;
        } 

        //Création d'un tas
        BinaryHeap<Label> heap = new BinaryHeap<Label>();


        //Création d'un tableau de LabelStars contenant tous les noeuds du graphe
        LabelStar[] LabelStars = new LabelStar[nbNodes];
        int i;

        //Initialisation de l'algorithme
        for(i=0;i<nbNodes;i++){
            LabelStars[i] = new LabelStar();
            LabelStars[i].currentNode = graph.get(i);
            LabelStars[i].setEstimateCost(data.getDestination());
            LabelStars[i].mark = false;
            LabelStars[i].realCost = Double.POSITIVE_INFINITY;
            LabelStars[i].father = null;
        }
        

        LabelStars[data.getOrigin().getId()].realCost = 0; //Initialisation du coût à 0
        heap.insert(LabelStars[data.getOrigin().getId()]); //Ajout du sommet d'origine au tas
        this.notifyOriginProcessed(data.getOrigin());
        
        // Initialisation : On part d'un sommet -> On le marque
        Label LabelStarMin = null; 
        while(!LabelStars[data.getDestination().getId()].getMark() && !heap.isEmpty()){ // Deux conditions de fin : Sommet destination marqué ou sommet destination non accessible
            //On retire la plus petite valeur de la pile
            LabelStarMin = heap.deleteMin(); 

            //On marque le sommet qu'on vient de retirer
            LabelStarMin.mark = true; 
            this.notifyNodeMarked(LabelStarMin.currentNode);

            Node successor = null;

            //On parcours tous les successeurs du sommet marqué
            for(Arc a : LabelStarMin.getcurrentNode().getSuccessors()){ 
                successor = a.getDestination();
               
                //Si le sommet n'est pas encore marqué
                if(!LabelStars[successor.getId()].getMark()){
                    //Si le coût réalisé actuel est inférieur à la distance entre les deux noeuds de l'arc
                        //On actualise le coût réalisé
                        //On actualise le father
                    if(data.isAllowed(a) && data.getCost(a) + LabelStarMin.getrealCost() < LabelStars[successor.getId()].getrealCost()){
                        //On vérifie que le sommet n'est pas déjà dans la pile
                        //S'il est déjà dans la pile, on le met à jour
                        try{
                            heap.remove(LabelStars[successor.getId()]);
                        }
                        //Sinon, on l'insère dans la pile
                        catch(ElementNotFoundException e){
                            this.notifyNodeReached(successor);
                        }
                        LabelStars[successor.getId()].realCost = LabelStarMin.getrealCost()+ data.getCost(a);
                        LabelStars[successor.getId()].father = a;
                        heap.insert(LabelStars[successor.getId()]);
                    }
                }
            }
            
        } 
        this.notifyDestinationReached(data.getDestination());

        if(LabelStars[data.getDestination().getId()].getMark() == true){ 
            //Création de la liste d'arcs finale 
            List<Arc> arcs = new ArrayList<Arc>();
            Arc arc = LabelStars[data.getDestination().getId()].getFather();

            while (arc != null) {
                arcs.add(arc);
                arc = LabelStars[arc.getOrigin().getId()].getFather();
            }

            //Reverse pour pas commencer par la fin
            Collections.reverse(arcs);

            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        } 

        else{
            solution = new ShortestPathSolution(data, Status.INFEASIBLE, null);
        }
        
        return solution;

    }
}
