package org.insa.graphs.algorithm.shortestpath;

import java.util.Collections;
import java.util.ArrayList;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathSolution solution = null;

        // On récupère le Graph
        ShortestPathData data = getInputData();
        Graph graph = data.getGraph();

        // Initialisation du tableau de labels
        ArrayList<Label> labels = new ArrayList<Label>();
        for (Node n : graph.getNodes())
        {
            labels.add(new Label(n,false,Float.MAX_VALUE,null));
        }

        // Init du tas
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        labels.get(data.getOrigin().getId()).realCost = 0;
        labels.get(data.getOrigin().getId()).mark = true;
        tas.insert(labels.get(data.getOrigin().getId()));

        /* ------ ------ ------ Boucle principale ------ ------ ----- */
        int cpt = 0;
        Label labActu = null; Label labOrigine = null;
        while(labels.get(data.getDestination().getId()).mark != true && !tas.isEmpty()) //Tant qu'on a pas atteint la destination ET qu'on a pas marqué tous les noeuds
        {
            
            labOrigine = tas.deleteMin();
            labOrigine.mark = true;
            System.out.println("Successeurs : "+labOrigine.currentNode.getSuccessors().size());

            for (Arc a : labOrigine.currentNode.getSuccessors())
            {
                System.out.println("Iter : "+cpt++);
                labActu = labels.get(a.getDestination().getId());
                if (labActu.mark != true)
                {
                    if (labOrigine.getrealCost() + a.getLength() < labActu.realCost)
                    {
                        try{tas.remove(labActu);} // Permet de tester l'existence de labActu dans le tas
                        catch(ElementNotFoundException e){System.out.println("Exception!!");}
                        labActu.realCost = a.getLength() + labOrigine.getrealCost();
                        labActu.father = a;
                        tas.insert(labActu);
                        if(labActu.getcurrentNode().equals(data.getDestination())){
                            System.out.println("Trouvé : cost =  " + labels.get(data.getDestination().getId()).getCost());
                            System.out.println("         Calculé = " + labActu.realCost);
                        }

                    } 
                }
            }
        }

        //Créer le chemin à l'aide des father
        ArrayList<Arc> FatherList = new ArrayList<Arc>();

        Arc arc = labels.get(data.getDestination().getId()).father;
        while(arc != null)
        {
            //on ajoute l'arc actuel
            FatherList.add(arc);
            //on passe à l'arc suivant
            arc = labels.get(arc.getOrigin().getId()).father;
        }

        System.out.println("Size : " + FatherList.size());

        //Reverse pour pas commencer par la fin
        Collections.reverse(FatherList);

        // Create the final solution.
        solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, FatherList));
        
        return solution;
    }

}
