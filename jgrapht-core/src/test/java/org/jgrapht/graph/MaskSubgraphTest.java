package org.jgrapht.graph;

import java.util.*;

import org.jgrapht.DirectedGraph;
import org.jgrapht.EnhancedTestCase;
import org.jgrapht.util.ArrayUnenforcedSet;

public class MaskSubgraphTest 	
extends EnhancedTestCase
{
	  private String v1 = "v1";
	  private String v2 = "v2";
	  private String v3 = "V3";
	  private String v4 = "v4";
	  private String v5 = "v5";
	  private String v6 = "v6";

	 public void testDirectedSubgraphGraph()
	 {
			DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);

			  DefaultEdge e = new DefaultEdge();
			  e.source = v1;
			  e.target = v2; 

				 
			 g1.addVertex(v1);
			 g1.addVertex(v2);
			 g1.addEdge(v1, v2);
			 g1.addVertex(v3);
			 g1.addVertex(v4);
			 g1.addVertex(v5);
			 g1.addEdge(v3, v4);
			 
			 MaskFunctor<Object, DefaultEdge> mask =
					 new Mask<Object, DefaultEdge>();
		
			 
			MaskSubgraph<Object, DefaultEdge> g = 
					new MaskSubgraph<Object, DefaultEdge>(g1,mask);
			

			 /**g.addVertex(v1);
			 g.addVertex(v2);
			 g.addEdge(v1, v2);
			 g.addVertex(v3);
			 g.addVertex(v4);
			 g.addEdge(v3, v4);**/
			 
			 Set<Object> vertices = new HashSet<Object>();
			 Set<DefaultEdge> edges = new ArrayUnenforcedSet<DefaultEdge>();
				 
			 vertices.add(v1);
			 vertices.add(v2);
			 vertices.add(v3);
			 vertices.add(v4);
			  DefaultEdge e1 = new DefaultEdge();
			  e1.source = v3;
			  e1.target = v4;
			  
			  DefaultEdge e2 = new DefaultEdge();
			  e1.source = v1;
			  e1.target = v2;
			  
			 edges.add(e);
			 //edges.add(e1);

			assertEquals(edges.size(),g.getAllEdges(v1,v2).size()); //test getAllEdges 
			assertEquals(e.source,g.getEdge(v1,v2).source); //test getEdge 
			assertEquals(e.target,g.getEdge(v1,v2).target); //test getEdge 
			assertEquals(null,g.getEdge(v3,v5)); //test getEdge 
			assertEquals(edges.size(),g.incomingEdgesOf(v2).size()); //test incoming edges
			assertEquals(1,g.inDegreeOf(v2)); //test inDegreeOf
			assertEquals(edges.size(),g.outgoingEdgesOf(v1).size()); //test outgoing edges
			assertEquals(1,g.outDegreeOf(v1)); //test outDegreeOf
			assertEquals(1,g.degreeOf(v1)); //test degreeOf
			assertFalse(g.containsEdge(e1));
			assertTrue(g.containsVertex(v1));
			assertFalse(g.containsVertex(v6));


	 
	 }
	 
	    public void testUnmodifiable()
	    {
	    	
	    	DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);
	   	 MaskFunctor<Object, DefaultEdge> mask =
				 new Mask<Object, DefaultEdge>();
	
		 
		MaskSubgraph<Object, DefaultEdge> sub = 
				new MaskSubgraph<Object, DefaultEdge>(g1,mask);
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
	
	

	    private static class Mask<V, E>
	        implements MaskFunctor<V, E>
	    {
	        private Set<E> maskedEdges;

	        private Set<V> maskedVertices;

	        /**
	         * Creates a mask for all the edges and the vertices of the path
	         * (including the 2 extremity vertices).
	         *
	         * @param pathElement
	         */
	        Mask()
	        {
	            this.maskedEdges = new HashSet<E>();
	            this.maskedVertices = new HashSet<V>();
	        }

	        // implement MaskFunctor
	        @Override public boolean isEdgeMasked(E edge)
	        {
	            return this.maskedEdges.contains(edge);
	        }

	        // implement MaskFunctor
	        @Override public boolean isVertexMasked(V vertex)
	        {
	            return this.maskedVertices.contains(vertex);
	        }
	    }
	
}

