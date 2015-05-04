package org.jgrapht.graph;

import junit.framework.TestCase;

import org.jgrapht.Graph;

public class UnmodifiableGraphTest     
extends TestCase
{
	
	private Graph<String, DefaultEdge> g = 
			new SimpleDirectedGraph<String, DefaultEdge>(
            DefaultEdge.class);
	UnmodifiableGraph<String, DefaultEdge> sub =
            new UnmodifiableGraph<String, DefaultEdge>(g);

    private String v1 = "v1";
    private String v2 = "v2";
    private String v3 = "v3";
    private String v4 = "v4";

    
    public void testIfModifiable()
    {
    	try{
    	sub.addVertex(v1);
    	sub.addVertex(v2);
		assertFalse(true);
    	}
    	catch(UnsupportedOperationException e){
    		assertTrue(true);
    	}
    	try{sub.addEdge(v1,v2);
    	assertFalse(true);
    	}catch(UnsupportedOperationException e){
    		assertTrue(true);
    	}
    	try{
        DefaultEdge e = sub.getEdgeFactory().createEdge(v1,v2);
        sub.addEdge(v1,v2, e);
    	assertFalse(true);
    	}catch(UnsupportedOperationException e){
    		assertTrue(true);
    	}
    	try{sub.removeAllEdges(v1,v2);
    	assertFalse(true);
    	}catch(UnsupportedOperationException e){
    		assertTrue(true);
    	}
    	try{
        DefaultEdge e = sub.getEdgeFactory().createEdge(v1,v2);
        sub.removeEdge(e);
    	assertFalse(true);
    	}catch(UnsupportedOperationException e){
    		assertTrue(true);
    	}
    	try{sub.removeEdge(v1,v2);
    	assertFalse(true);
    	}catch(UnsupportedOperationException e){
    		assertTrue(true);
    	}
    	try{sub.removeVertex(v1);
    	assertFalse(true);
    	}catch(UnsupportedOperationException e){
    		assertTrue(true);
    	}
    	
    
    	
    }
     

}
