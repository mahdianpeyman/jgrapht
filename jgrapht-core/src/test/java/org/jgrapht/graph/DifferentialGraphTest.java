package org.jgrapht.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;

import junit.framework.TestCase;

public class DifferentialGraphTest 	
extends TestCase
{
	//differential testing by comparing functionality and output of DiffGraph 
	//(a simple undirected graph created by princeton-http://algs4.cs.princeton.edu/41undirected/) 
	//to jgrapht's SimpleUndirectedGraph
    DiffGraph dg = new DiffGraph(5);
    SimpleGraph<String, DefaultEdge> g =
            new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
    //performs differential test for the add vertex and edge functionality
    //performs differential test for traversal functionality (dfs abd bfs)
    
    private Integer a =  1;			//  1
    private Integer b =  2;		  	//   \
    private Integer c =  3;			//	  2
    private Integer d =  4;			//	/	\
    								//	3	 4
    
    
    public void testDFSAddEdgeAndEdgeVertex(){
    		

		    g.addVertex(a.toString());
		    g.addVertex(b.toString());
		    g.addVertex(c.toString());
		    g.addVertex(d.toString());
		    g.addEdge(a.toString(),b.toString());
		    g.addEdge(b.toString(),d.toString());
		    g.addEdge(b.toString(),c.toString());

		    dg.addEdge(a,b);
		    dg.addEdge(b,c);
		    dg.addEdge(b,d);

      	
            Object[] arr = g.edgesOf(a.toString()).toArray();
            int j = 0;
            Set<Object> s = new HashSet<Object>();
            while(j < arr.length){
            	String [] str = ( arr[j]).toString().split(" : ");
            	s.add(str[1].substring(0,str[1].length()-1));
            	j++;
            }

            Iterator<Object> it2 = s.iterator();
        	Iterator<Object> it3 = s.iterator();
        	   while (it2.hasNext() && it3.hasNext() ) {
        		   Object ob = it2.next();
        		   Object ob2 = it3.next();
                    assertEquals(ob,ob2);     
        	    }
        	   
               Iterator<String> dfs = new DepthFirstIterator<String, DefaultEdge>(g);
               String actualDfs = "";
               while (dfs.hasNext()) {
                   String v = dfs.next();
                   actualDfs += v;
               }

               
               DepthFirstSearch exDfs = new DepthFirstSearch(dg,a);
               String expectedDfs = "";
               for (int v = 0; v < dg.V(); v++) {
                   if (exDfs.marked(v))
                	   expectedDfs += v;
               }
                            
               assertEquals(expectedDfs, actualDfs);

    }
    public void testBFSAddEdgeAndEdgeVertex(){
		

	    g.addVertex(a.toString());
	    g.addVertex(b.toString());
	    g.addVertex(c.toString());
	    g.addVertex(d.toString());
	    g.addEdge(a.toString(),b.toString());
	    g.addEdge(b.toString(),c.toString());
	    g.addEdge(b.toString(),d.toString());

	    dg.addEdge(a,b);
	    dg.addEdge(b,c);
	    dg.addEdge(b,d);

  	
        Object[] arr = g.edgesOf(a.toString()).toArray();
        int j = 0;
        Set<Object> s = new HashSet<Object>();
        while(j < arr.length){
        	String [] str = ( arr[j]).toString().split(" : ");
        	s.add(str[1].substring(0,str[1].length()-1));
        	j++;
        }

        Iterator<Object> it2 = s.iterator();
    	Iterator<Object> it3 = s.iterator();
    	   while (it2.hasNext() && it3.hasNext() ) {
    		   Object ob = it2.next();
    		   Object ob2 = it3.next();
                assertEquals(ob,ob2);     
    	    }           
           
           Iterator<String> bfs = new BreadthFirstIterator<String, DefaultEdge>(g);
           String actualBfs = "";
           while (bfs.hasNext()) {
               String v = bfs.next();
               actualBfs += v;
           }
           
           BreadthFirstSearch exBfs = new BreadthFirstSearch(dg,a);
           String expectedBfs = "";
           for (int v = 0; v < dg.V(); v++) {
               if (exBfs.marked(v))
            	   expectedBfs += v;
           }

           assertEquals(expectedBfs, actualBfs);

}
    
}
