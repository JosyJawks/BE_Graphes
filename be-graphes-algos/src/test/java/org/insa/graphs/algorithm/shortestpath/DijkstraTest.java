package org.insa.graphs.algorithm.shortestpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.insa.graphs.algorithm.ArcInspectorFactory;

public class DijkstraTest {
    

    /* --- --- --- Variables pour tests JUnit --- --- --- */
    // trajet court n°1
    private static ShortestPathSolution bellPath_crt1;
    private static ShortestPathSolution dijkPath_crt1;
    // trajet court n°2
    private static ShortestPathSolution bellPath_crt2;
    private static ShortestPathSolution dijkPath_crt2;

    // trajet long n°1 -- longueur    --> on va comparer leurs temps et longueurs respectifs pour vérifier que les algorithmes sont cohérents
    //private static ShortestPathSolution dijkPath_lglg1;
    // trajet long n°1 -- temps
    //private static ShortestPathSolution dijkPath_lgtps1;
    // trajet long n°2 -- longueur
    private static ShortestPathSolution dijkPath_lglg2;
    // trajet long n°2 -- temps
    private static ShortestPathSolution dijkPath_lgtps2;


    // impossible de rejoindre la destination
    private static ShortestPathSolution dijkPath_infaisable;

    // destination == origine
    private static ShortestPathSolution dijkPath_unnoeud;


    @BeforeClass
    public static void initAll() throws Exception {
    // trajets courts avec chemins trouvés par Bellman-Ford
        // Carte Carré ("Square") -- On prend deux points à l'opposé l'un de l'autre
    final String mapName_crt1 = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr";
    final GraphReader reader_crt1 = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName_crt1))));
    final Graph graph_crt1 = reader_crt1.read();
    Node orig_crt1 = graph_crt1.getNodes().get(9); Node dest_crt1 = graph_crt1.getNodes().get(10);
    final BellmanFordAlgorithm bellAlgo_crt1 = new BellmanFordAlgorithm(new ShortestPathData(graph_crt1, orig_crt1, dest_crt1, ArcInspectorFactory.getAllFilters().get(0)));
    bellPath_crt1 = bellAlgo_crt1.doRun();
    final DijkstraAlgorithm dijkAlgo_crt1 = new DijkstraAlgorithm(new ShortestPathData(graph_crt1, orig_crt1, dest_crt1, ArcInspectorFactory.getAllFilters().get(0)));
    dijkPath_crt1 = dijkAlgo_crt1.doRun();

        // Carte de l'Insa -- On prend un point à l'extrimité nord de la carte et un autre à l'extrémité sud
    final String mapName_crt2 = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
    final GraphReader reader_crt2 = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName_crt2))));
    final Graph graph_crt2 = reader_crt2.read();
    Node orig_crt2 = graph_crt2.getNodes().get(241); Node dest_crt2 = graph_crt2.getNodes().get(332);
    final BellmanFordAlgorithm bellAlgo_crt2 = new BellmanFordAlgorithm(new ShortestPathData(graph_crt2, orig_crt2, dest_crt2, ArcInspectorFactory.getAllFilters().get(0)));
    bellPath_crt2 = bellAlgo_crt2.doRun();
    final DijkstraAlgorithm dijkAlgo_crt2 = new DijkstraAlgorithm(new ShortestPathData(graph_crt2, orig_crt2, dest_crt2, ArcInspectorFactory.getAllFilters().get(0)));
    dijkPath_crt2 = dijkAlgo_crt2.doRun();
    
    
    //trajets longs
        // Carte de la France -- 
        /*final String mapName_lg1 = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/france.mapgr";
        final GraphReader reader_lg1 = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName_lg1))));
        final Graph graph_lg1 = reader_lg1.read();
        Node orig_lg1 = graph_lg1.getNodes().get(241); Node dest_lg1 = graph_lg1.getNodes().get(332); //!\\ Pas les bons points 
            // Parcours en temps (uniquement voiture)
            final DijkstraAlgorithm dijkAlgo_lgtps1 = new DijkstraAlgorithm(new ShortestPathData(graph_lg1, orig_lg1, dest_lg1, ArcInspectorFactory.getAllFilters().get(2)));
            dijkPath_lgtps1 = dijkAlgo_lgtps1.doRun();
            // Parcours en longueur (uniquement voiture)
            final DijkstraAlgorithm dijkAlgo_lglg1 = new DijkstraAlgorithm(new ShortestPathData(graph_lg1, orig_lg1, dest_lg1, ArcInspectorFactory.getAllFilters().get(1)));
            dijkPath_lglg1 = dijkAlgo_lglg1.doRun();*/
        // Carte de la Belgique -- d'est en ouest
        final String mapName_lg2 = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
        final GraphReader reader_lg2 = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName_lg2))));
        final Graph graph_lg2 = reader_lg2.read();
        Node orig_lg2 = graph_lg2.getNodes().get(597909); Node dest_lg2 = graph_lg2.getNodes().get(536278); //!\\ Pas les bons points 
            // Parcours en temps (uniquement voiture)
            final DijkstraAlgorithm dijkAlgo_lgtps2 = new DijkstraAlgorithm(new ShortestPathData(graph_lg2, orig_lg2, dest_lg2, ArcInspectorFactory.getAllFilters().get(2)));
            dijkPath_lgtps2 = dijkAlgo_lgtps2.doRun();
            // Parcours en longueur (uniquement voiture)
            final DijkstraAlgorithm dijkAlgo_lglg2 = new DijkstraAlgorithm(new ShortestPathData(graph_lg2, orig_lg2, dest_lg2, ArcInspectorFactory.getAllFilters().get(1)));
            dijkPath_lglg2 = dijkAlgo_lglg2.doRun();


    // destination == origine
    final DijkstraAlgorithm dijkAlgo_unnoeud = new DijkstraAlgorithm(new ShortestPathData(graph_crt1, orig_crt1, orig_crt1, ArcInspectorFactory.getAllFilters().get(0)));
    dijkPath_unnoeud = dijkAlgo_unnoeud.doRun();

    // impossible de relier destination et origine
    Node orig_impo = graph_crt2.getNodes().get(1282); Node dest_impo = graph_crt2.getNodes().get(864);
    final DijkstraAlgorithm dijkAlgo_impo = new DijkstraAlgorithm(new ShortestPathData(graph_crt2, orig_impo, dest_impo, ArcInspectorFactory.getAllFilters().get(0)));
    dijkPath_infaisable = dijkAlgo_impo.doRun();


    }
    /* --- --- --- --- --- --- --- --- */


    /* --- --- --- ___Tests JUnit___ --- --- --- */
    @Test
    public void testShortPath () {
        assertEquals(null, bellPath_crt1.getPath().getLength(), dijkPath_crt1.getPath().getLength(), 0);
        assertEquals(null, bellPath_crt2.getPath().getLength(), dijkPath_crt2.getPath().getLength(), 0);
    }

    @Test 
    public void testLongPath (){ // On teste la cohérence des résultats, pas s'il s'agit effectivement des trajets les plus courts / rapides
        //assertTrue(dijkPath_lglg1.getPath().getLength() <= dijkPath_lgtps1.getPath().getLength());
        assertTrue(dijkPath_lglg2.getPath().getLength() <= dijkPath_lgtps2.getPath().getLength());
        //assertTrue(dijkPath_lglg1.getPath().getMinimumTravelTime() >= dijkPath_lgtps1.getPath().getMinimumTravelTime());
        assertTrue(dijkPath_lglg2.getPath().getMinimumTravelTime() >= dijkPath_lgtps2.getPath().getMinimumTravelTime());
    } 
    
    @Test 
    public void testOneNodePath (){
        assertEquals(null, dijkPath_unnoeud.getPath().getLength(), 0, 0);
    } 

    @Test 
    public void testInfeasiblePath (){
        assertFalse(dijkPath_infaisable.isFeasible());
    } 
    /* --- --- --- --- --- --- --- --- --- --- */
}
