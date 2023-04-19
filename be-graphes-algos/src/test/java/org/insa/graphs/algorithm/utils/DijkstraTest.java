package org.insa.graphs.algorithm.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.beans.Transient;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.model.Node;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest {
    

    /* --- --- --- Variables pour tests JUnit --- --- --- */
    @BeforeClass
    public static void initAll() throws Exception {
    // trajets courts : points d'origine et de destination + PCC trouvé par Bellman-Ford
    final String mapName_crt1 = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/carre.mapgr";
    final GraphReader reader = new BinaryGraphReader(
                new DataInputStream(new BufferedInputStream(new FileInputStream(mapName_crt1))));
    final Graph graph_crt1 = reader.read();
    Node orig_crt1 = graph_crt1.getNodes().get(9); Node dest_crt1 = graph_crt1.getNodes().get(10);

    final String mapName_crt2 = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
    Node orig_crt2 = null; Node dest_crt2 = null;
    
    
    
    //trajets longs


    // destination == origine


    // impossible de relier destination et origine

    }
    /* --- --- --- --- --- --- --- --- */


    /* --- --- --- ___Tests JUnit___ --- --- --- */
    @Test
    public void testTest () {
        assertEquals(null, 0, 0, 0);
    }
    


    /* --- --- --- --- --- --- --- --- --- --- */
}
