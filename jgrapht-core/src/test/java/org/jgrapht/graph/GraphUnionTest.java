package org.jgrapht.graph;

import org.jgrapht.DirectedGraph;
import org.jgrapht.EnhancedTestCase;

import java.util.*;



public class GraphUnionTest 
	extends EnhancedTestCase
	{
	
	 public void testGrapConstructor()
	 {
	DirectedGraph<Object, DefaultEdge> g1 = 
			new SimpleDirectedGraph<Object, DefaultEdge>(
	                DefaultEdge.class);
		
		DirectedGraph<Object, DefaultEdge> g2 = 
				new SimpleDirectedGraph<Object, DefaultEdge>(
		                DefaultEdge.class);	
		DirectedGraph<Object, DefaultEdge> g = 
				new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
		
		try{
		DirectedGraph<Object, DefaultEdge> g1Null = 
					new DirectedGraphUnion<Object, DefaultEdge>(null,g2);
		assertFalse(true);
		}
		catch(NullPointerException e)
		{
			assertTrue(true);
		}
	    
		try{
			DirectedGraph<Object, DefaultEdge> g2Null = 
						new DirectedGraphUnion<Object, DefaultEdge>(g1,null);
			assertFalse(true);
			}
			catch(NullPointerException e)
			{
				assertTrue(true);
			}
		
		try{
			DirectedGraph<Object, DefaultEdge> gNull = 
						new DirectedGraphUnion<Object, DefaultEdge>(null,null);
			assertFalse(true);
			}
			catch(NullPointerException e)
			{
				assertTrue(true);
			}
		
		try{
			DirectedGraph<Object, DefaultEdge> gNull = 
						new DirectedGraphUnion<Object, DefaultEdge>(g1,g1);
			assertFalse(true);
			}
			catch(IllegalArgumentException e)
			{
				assertTrue(true);
			}
	}
	
	 public void testGrapGetEdge()
	 {
	DirectedGraph<Object, DefaultEdge> g1 = 
		new SimpleDirectedGraph<Object, DefaultEdge>(
                DefaultEdge.class);
	
	DirectedGraph<Object, DefaultEdge> g2 = 
			new SimpleDirectedGraph<Object, DefaultEdge>(
	                DefaultEdge.class);	
	DirectedGraph<Object, DefaultEdge> g = 
			new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
	
	
    String v1 = "v1";
    String v2 = "v2";

    g1.addVertex(v1);
    g1.addVertex(v2);
    
    String v3 = "v3";
    String v4 = "v4";
    
    g2.addVertex(v3);
    g2.addVertex(v4);
    DefaultEdge e = new DefaultEdge();
    e.source = v1;
    e.target = v2; 
    g2.addEdge(v3, v4, e);
    
    DefaultEdge e1 = g.getEdge(v3, v4);
    g.getEdge(v3, v2);
    g.getEdge(v1, v4);

    assertEquals(e1, e);
	assertTrue(g.containsEdge(e));
	assertTrue(g.containsVertex(v1));
	

    e = new DefaultEdge();
    e.source = v1;
    e.target = v2; 
    g1.addEdge(v1, v2, e);

    e1 = g.getEdge(v1, v2);

    assertEquals(e1, e);   
    
	assertTrue(g.containsEdge(e));
	assertTrue(g.containsVertex(v4));
	
	String v5 = "v5";
	String v6 = "v6";
    DefaultEdge e2 = g.getEdge(v5, v6);
	assertFalse(g.containsEdge(e2));
	assertFalse(g.containsVertex(v5));
	}
	 
	 public void testGrapGetAllEdges()
	 {
		 DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);
				
				DirectedGraph<Object, DefaultEdge> g2 = 
						new SimpleDirectedGraph<Object, DefaultEdge>(
				                DefaultEdge.class);	
				DirectedGraph<Object, DefaultEdge> g = 
						new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
				
				
			    String v1 = "v1";
			    String v2 = "v2";

			    g1.addVertex(v1);
			    g1.addVertex(v2);
			    
			    String v3 = "v3";
			    String v4 = "v4";
			    
			    g2.addVertex(v3);
			    g2.addVertex(v4);
			    DefaultEdge e = new DefaultEdge();
			    e.source = v1;
			    e.target = v2; 
			    g2.addEdge(v3, v4, e);
			    Set<DefaultEdge> s1 = new HashSet<DefaultEdge>();
			    s1.add(e);

			    Set<DefaultEdge> s = g.getAllEdges(v3, v4);


			    assertEquals(s, s1);   

			    e = new DefaultEdge();
			    e.source = v1;
			    e.target = v2; 
			    g1.addEdge(v1, v2, e);
			    s1 = new HashSet<DefaultEdge>();
			    s1.add(e);
			    
			    s = g.getAllEdges(v1, v2);

			    assertEquals(s, s1);  
			    
		}
	 
	 public void testGrapEdgeSet()
	 {
		 DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);
				
				DirectedGraph<Object, DefaultEdge> g2 = 
						new SimpleDirectedGraph<Object, DefaultEdge>(
				                DefaultEdge.class);	
				DirectedGraph<Object, DefaultEdge> g = 
						new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
				
				
			    String v1 = "v1";
			    String v2 = "v2";

			    g1.addVertex(v1);
			    g1.addVertex(v2);
			    
			    String v3 = "v3";
			    String v4 = "v4";
			    
			    g2.addVertex(v3);
			    g2.addVertex(v4);
			    DefaultEdge e = new DefaultEdge();
			    e.source = v1;
			    e.target = v2; 
			    g2.addEdge(v3, v4, e);
			    Set<DefaultEdge> s1 = new HashSet<DefaultEdge>();
			    s1.add(e);

			    Set<DefaultEdge> s = g.edgeSet();


			    assertEquals(s, s1);   
 
		}
	 
	 public void testGrapEdgesOf()
	 {
			 DirectedGraph<Object, DefaultEdge> g1 = 
						new SimpleDirectedGraph<Object, DefaultEdge>(
				                DefaultEdge.class);
					
					DirectedGraph<Object, DefaultEdge> g2 = 
							new SimpleDirectedGraph<Object, DefaultEdge>(
					                DefaultEdge.class);	
					DirectedGraph<Object, DefaultEdge> g = 
							new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
					
					
				    String v1 = "v1";
				    String v2 = "v2";

				    g1.addVertex(v1);
				    g1.addVertex(v2);
				    
				    String v3 = "v3";
				    String v4 = "v4";
				    
				    g2.addVertex(v3);
				    g2.addVertex(v4);
				    DefaultEdge e = new DefaultEdge();
				    e.source = v1;
				    e.target = v2; 
				    g2.addEdge(v3, v4, e);
				    Set<DefaultEdge> s1 = new HashSet<DefaultEdge>();
				    s1.add(e);

				    Set<DefaultEdge> s = g.edgesOf(v4);


				    assertEquals(s, s1);   

				    e = new DefaultEdge();
				    e.source = v1;
				    e.target = v2; 
				    g1.addEdge(v1, v2, e);
				    s1 = new HashSet<DefaultEdge>();
				    s1.add(e);
				    
				    s = g.edgesOf(v1);

				    assertEquals(s, s1);   
			}
		 
	 
	 public void testGrapVertexSet()
	 {

		 DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);
				
				DirectedGraph<Object, DefaultEdge> g2 = 
						new SimpleDirectedGraph<Object, DefaultEdge>(
				                DefaultEdge.class);	
				DirectedGraph<Object, DefaultEdge> g = 
						new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
				
				
			    String v1 = "v1";
			    String v2 = "v2";

			    g1.addVertex(v1);
			    g1.addVertex(v2);
			    
			    String v3 = "v3";
			    String v4 = "v4";
			    
			    g2.addVertex(v3);
			    g2.addVertex(v4);

			    Set<Object> s1 = new HashSet<Object>();
			    s1.add(v1);
			    s1.add(v2);
			    s1.add(v3);
			    s1.add(v4);
			    
			    Set<Object> s = g.vertexSet();

			    assertEquals(s, s1);   
		}
	 
	 public void testGrahEdgeSourceTarget()
	 {
		 DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);
				
				DirectedGraph<Object, DefaultEdge> g2 = 
						new SimpleDirectedGraph<Object, DefaultEdge>(
				                DefaultEdge.class);	
				DirectedGraph<Object, DefaultEdge> g = 
						new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
				
				
			    String v1 = "v1";
			    String v2 = "v2";

			    g1.addVertex(v1);
			    g1.addVertex(v2);
			    
			    String v3 = "v3";
			    String v4 = "v4";
			    
			    g2.addVertex(v3);
			    g2.addVertex(v4);
			    
			    DefaultEdge e = new DefaultEdge();
			    e.source = v1;
			    e.target = v2; 
			    
			    g1.addEdge(v1,v2,e);
			    
			    Object v = g.getEdgeSource(e);
			    Object v_2 = g.getEdgeTarget(e);

			    assertEquals(v1, v);   
			    assertEquals(v2, v_2);   

			    e = new DefaultEdge();
			    e.source = v1;
			    e.target = v2; 
			    
			    g2.addEdge(v3,v4,e);
			    
			    v = g.getEdgeSource(e);
			    v_2 = g.getEdgeTarget(e);

			    assertEquals(v3, v);   
			    assertEquals(v4, v_2);   

			    v = g.getEdgeSource(null);
			    assertEquals(null, v);

			    v = g.getEdgeTarget(null);
			    assertEquals(null, v);
 
		}
	 
	 public void testGrahEdgeWeight()
	 {
		 DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);
				
				DirectedGraph<Object, DefaultEdge> g2 = 
						new SimpleDirectedGraph<Object, DefaultEdge>(
				                DefaultEdge.class);	
				DirectedGraph<Object, DefaultEdge> g = 
						new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
				
				
			    String v1 = "v1";
			    String v2 = "v2";
			    String v5 = "v5";
			    String v6 = "v6";

			    g1.addVertex(v1);
			    g1.addVertex(v2);
			    g1.addVertex(v5);
			    g1.addVertex(v6);

			    String v3 = "v3";
			    String v4 = "v4";
			    
			    g2.addVertex(v3);
			    g2.addVertex(v4);
			    g2.addVertex(v5);
			    g2.addVertex(v6);

			    DefaultEdge e = new DefaultEdge();
			    e.source = v1;
			    e.target = v2; 
			    
			    g1.addEdge(v1,v2,e);
			    
			    Object v = g.getEdgeWeight(e);
			    Object v_2 = g.getEdgeWeight(e);

			    assertEquals(1.0, v);   
			    assertEquals(1.0, v_2);   

			    e = new DefaultEdge();
			    e.source = v1;
			    e.target = v2; 
			    
			    g2.addEdge(v3,v4,e);
			    
			    v = g.getEdgeWeight(e);
			    v_2 = g.getEdgeWeight(e);

			    assertEquals(1.0, v);   
			    assertEquals(1.0, v_2);   

			    e = new DefaultEdge();
			    e.source = v5;
			    e.target = v6; 
			    
			    g2.addEdge(v5,v6,e);
			    g1.addEdge(v5,v6,e);

			    v = g.getEdgeWeight(e);
			    v_2 = g.getEdgeWeight(e);

			    assertEquals(2.0, v);   
			    assertEquals(2.0, v_2);   
			    
			    try{
			    v = g.getEdgeWeight(null);
			    }
			    catch(IllegalArgumentException ex){
			    assertTrue(true);
			    }
		}
	 public void testGrahGetter()
	 {
	DirectedGraph<Object, DefaultEdge> g1 = 
		new SimpleDirectedGraph<Object, DefaultEdge>(
                DefaultEdge.class);
	
	DirectedGraph<Object, DefaultEdge> g2 = 
			new SimpleDirectedGraph<Object, DefaultEdge>(
	                DefaultEdge.class);	
	DirectedGraph<Object, DefaultEdge> g = 
			new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
	
	
    String v1 = "v1";
    String v2 = "v2";

    g1.addVertex(v1);
    g1.addVertex(v2);
    
    String v3 = "v3";
    String v4 = "v4";
    
    g2.addVertex(v3);
    g2.addVertex(v4);
    DefaultEdge e = new DefaultEdge();
    e.source = v1;
    e.target = v2; 
    g2.addEdge(v3, v4, e);
    
    DefaultEdge e1 = g.getEdge(v3, v4);
    g.getEdge(v3, v2);
    g.getEdge(v1, v4);

    DirectedGraph<Object, DefaultEdge> outg1 = ((GraphUnion<Object, DefaultEdge, DirectedGraph<Object, DefaultEdge>>) g).getG1();
    
    assertEquals(g1, outg1);

    e = new DefaultEdge();
    e.source = v1;
    e.target = v2; 
    g1.addEdge(v1, v2, e);

    e1 = g.getEdge(v1, v2);

    DirectedGraph<Object, DefaultEdge> outg2 = ((GraphUnion<Object, DefaultEdge, DirectedGraph<Object, DefaultEdge>>) g).getG2();

    assertEquals(g2, outg2);   
	}
	 
	 public void testGraphThrowsException()
	 {

		 DirectedGraph<Object, DefaultEdge> g1 = 
					new SimpleDirectedGraph<Object, DefaultEdge>(
			                DefaultEdge.class);
				
				DirectedGraph<Object, DefaultEdge> g2 = 
						new SimpleDirectedGraph<Object, DefaultEdge>(
				                DefaultEdge.class);	
				DirectedGraph<Object, DefaultEdge> g = 
						new DirectedGraphUnion<Object, DefaultEdge>(g1,g2);
				
				
			    String v1 = "v1";
			    String v2 = "v2";

			    g1.addVertex(v1);
			    g1.addVertex(v2);
			    
			    String v3 = "v3";
			    String v4 = "v4";
			    
			    g2.addVertex(v3);
			    g2.addVertex(v4);

			    Set<Object> s1 = new HashSet<Object>();
			    s1.add(v1);
			    s1.add(v2);
			    s1.add(v3);
			    s1.add(v4);
			    
			    Set<Object> s = g.vertexSet();

			    assertEquals(s, s1); 

				try{
					g.getEdgeFactory();
				    DefaultEdge e = new DefaultEdge();
				    e.source = v1;
				    e.target = v2; 
					boolean b = g.addEdge(v1,v2, e);
					e = g.addEdge(v1,v2);
					g.addVertex(v1);
					g.removeEdge(e);
					g.removeEdge(v1,v2);
					g.removeVertex(v1);

					assertFalse(true);
					}
					catch(UnsupportedOperationException e)
					{
						assertTrue(true);
					}
		}
	
}



