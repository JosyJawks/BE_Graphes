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

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
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


        //Création d'un tableau de labels contenant tous les noeuds du graphe
        Label[] labels = new Label[nbNodes];
        int i;

        //Initialisation de l'algorithme
        for(i=0;i<nbNodes;i++){
            labels[i] = new Label();
            labels[i].currentNode = graph.get(i);
            labels[i].mark = false;
            labels[i].realCost = Double.POSITIVE_INFINITY;
            labels[i].father = null;
        }
        

        labels[data.getOrigin().getId()].realCost = 0; //Initialisation du coût à 0
        heap.insert(labels[data.getOrigin().getId()]); //Ajout du sommet d'origine au tas
        this.notifyOriginProcessed(data.getOrigin());
        
        // Initialisation : On part d'un sommet -> On le marque
        Label labelMin = null; 
        while(!labels[data.getDestination().getId()].getMark() && !heap.isEmpty()){ // Deux conditions de fin : Sommet destination marqué ou sommet destination non accessible
            //On retire la plus petite valeur de la pile
            labelMin = heap.deleteMin(); 

            //On marque le sommet qu'on vient de retirer
            labelMin.mark = true; 
            this.notifyNodeMarked(labelMin.currentNode);

            Node successor = null;

            //On parcours tous les successeurs du sommet marqué
            for(Arc a : labelMin.getcurrentNode().getSuccessors()){ 
                successor = a.getDestination();
               
                //Si le sommet n'est pas encore marqué
                if(!labels[successor.getId()].getMark()){
                    //Si le coût réalisé actuel est inférieur à la distance entre les deux noeuds de l'arc
                        //On actualise le coût réalisé
                        //On actualise le father
                    if(data.isAllowed(a) && data.getCost(a) + labelMin.getrealCost() < labels[successor.getId()].getrealCost()){
                        //On vérifie que le sommet n'est pas déjà dans la pile
                        //S'il est déjà dans la pile, on le met à jour
                        try{
                            heap.remove(labels[successor.getId()]);
                        }
                        //Sinon, on l'insère dans la pile
                        catch(ElementNotFoundException e){
                            this.notifyNodeReached(successor);
                        }
                        labels[successor.getId()].realCost = labelMin.getrealCost()+ data.getCost(a);
                        labels[successor.getId()].father = a;
                        heap.insert(labels[successor.getId()]);
                    }
                }
            }
            
        } 
        this.notifyDestinationReached(data.getDestination());

        if(labels[data.getDestination().getId()].getMark() == true){ 
            //Création de la liste d'arcs finale 
            List<Arc> arcs = new ArrayList<Arc>();
            Arc arc = labels[data.getDestination().getId()].getFather();

            while (arc != null) {
                arcs.add(arc);
                arc = labels[arc.getOrigin().getId()].getFather();
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
