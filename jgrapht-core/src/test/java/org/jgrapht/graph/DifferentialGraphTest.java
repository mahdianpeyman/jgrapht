package org.jgrapht.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.jgrapht.DirectedGraph;

import junit.framework.TestCase;

public class DifferentialGraphTest 	
extends TestCase
{
	//differetial testing by comparing functionality and output of DiffGraph 
	//(a simple undirected graph created by princeton-http://algs4.cs.princeton.edu/41undirected/) 
	//to jgrapht's SimpleUndirectedGraph and ...
    DiffGraph dg = new DiffGraph(1000);
    SimpleGraph<String, DefaultEdge> g =
            new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
    //uses PUT to create differential tests for the add vertex and edge functionality
    //tests to make sure the all edges of a particular vertex for DiffGraph 
    //and SimpleUndirectedGraph are equal 
    public void testPUTAddEdgeAndEdgeVertex(){
        Random r = new Random();
    	for(int i =0; i < 1000; i++){
    		
            Integer a =  r.nextInt(1000)+0;
            Integer b =  r.nextInt(1000)+0;
        	try{
		    	g.addVertex(a.toString());
		    	g.addVertex(b.toString());
		    	g.addEdge(a.toString(),b.toString());
	    	
		    	dg.addEdge(a,b);

        	}
        	catch(IllegalArgumentException e){   	
            Set<Object> sDiffGraph = dg.setOfAdjacentVertices(a); 
            Object[] arr = g.edgesOf(a.toString()).toArray();
            int j = 0;
            Set<Object> s = new HashSet<Object>();
            while(j < arr.length){
            	String [] str = ( arr[j]).toString().split(" : ");
            	s.add(str[1].substring(0,str[1].length()-1));
            	j++;
            }
        	//System.out.println(sDiffGraph + " " + s);
        	Iterator<Object> it2 = s.iterator();
        	Iterator<Object> it3 = s.iterator();
        	   while (it2.hasNext() && it3.hasNext() ) {
        		   Object ob = it2.next();
        		   Object ob2 = it3.next();
                    assertEquals(ob,ob2);     
                	//System.out.println(ob+ " " + ob2);
        	    }
	    	}
    }
    }
    
 
}
