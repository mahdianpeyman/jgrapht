
package org.jgrapht.graph;

import java.util.*;

import org.jgrapht.DirectedGraph;
import org.jgrapht.EnhancedTestCase;
import org.jgrapht.util.ArrayUnenforcedSet;


public class DirectedGraphUnionTest 	
extends EnhancedTestCase
{
	
	 public void testDirectedGraph()
	 {
			DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);
			  String v1 = "v1";
			  String v2 = "v2";
			  String v3 = "V3";
			  String v4 = "v4";
			  String v5 = "v5";
			  DefaultEdge e = new DefaultEdge();
			  e.source = v1;
			  e.target = v2; 

				 
			 g1.addVertex(v1);
			 g1.addVertex(v2);
			 g1.addEdge(v1, v2);
	
			 
			DirectedGraph<Object, DefaultEdge> g2 = 
						new SimpleDirectedGraph<Object, DefaultEdge>(
				                DefaultEdge.class);
		
			 g2.addVertex(v3);
			 g2.addVertex(v4);
			 g2.addVertex(v5);
			 g2.addEdge(v3, v4);
			 
			DirectedGraph<Object, DefaultEdge> g = 
					new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
			
			DirectedGraph<Object, DefaultEdge> g3 = 
					new DirectedGraphUnion<Object, DefaultEdge>(g1,g2,null);

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
			  
			 edges.add(e);
			 //edges.add(e1);

			assertEquals(edges.size(),g.getAllEdges(v1,v2).size()); //test getAllEdges 
			assertEquals(e.source,g.getEdge(v1,v2).source); //test getEdge 
			assertEquals(e.target,g.getEdge(v1,v2).target); //test getEdge 
			assertEquals(null,g.getEdge(v3,v5)); //test getEdge 
			assertEquals(edges.size(),g.incomingEdgesOf(v2).size()); //test incoming edges
			assertEquals(edges.size(),g.incomingEdgesOf(v4).size()); //test incoming edges
			assertEquals(1,g.inDegreeOf(v2)); //test inDegreeOf
			assertEquals(edges.size(),g.outgoingEdgesOf(v1).size()); //test outgoing edges
			assertEquals(0,g.outgoingEdgesOf(v2).size()); //test outgoing edges
			assertEquals(0,g.outgoingEdgesOf(v4).size()); //test outgoing edges
			assertEquals(edges.size(),g.outgoingEdgesOf(v3).size()); //test outgoing edges
			assertEquals(1,g.outDegreeOf(v1)); //test outDegreeOf

		
	 
	 }
	
}

