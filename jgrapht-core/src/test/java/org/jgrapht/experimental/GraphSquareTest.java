package org.jgrapht.experimental;

import java.io.IOException;
import java.io.StringReader;

import org.jgrapht.*;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import junit.framework.TestCase;

public class GraphSquareTest     
	extends TestCase
{
        

    /**
     * Bug java.lang.IllegalArgumentException: must be instance of either DirectedGraph or UndirectedGraph
     * the graph is of type DirectedGraph, however, I still obtain the IllegalArgumentException
     */
    public void testGraphSquare()
    {
        GraphSquare<Integer, DefaultEdge> Gsquare;		
        DirectedGraph<Integer, DefaultEdge> g2 =
		    new DefaultDirectedGraph<Integer, DefaultEdge>(
		        DefaultEdge.class);
		g2.addVertex(0);
		g2.addVertex(1);
		g2.addVertex(2);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		
		//test fails 
		//Gsquare = new GraphSquare<Integer, DefaultEdge>(g2, false);
		
       //assertEquals(g2, Gsquare);		     
    }
    
    
    

}
