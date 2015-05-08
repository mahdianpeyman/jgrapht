package org.jgrapht.graph;

/*************************************************************************
 *  Compilation:  javac BreadthFirstSearch.java
 *  Execution:    java BreadthFirstSearch filename.txt s
 *  Dependencies: Graph.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/tinyG.txt
 *
 *  Run breadth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *  % java BreadthFirstSearch tinyG.txt 0
 *  0 1 2 3 4 5 6 
 *  NOT connected
 *
 *  % java BreadthFirstSearch tinyG.txt 9
 *  9 10 11 12 
 *  NOT connected
 *
 *************************************************************************/

/**
 *  The <tt>BreadthFirstSearch</tt> class represents a data type for 
 *  determining the vertices connected to a given source vertex <em>s</em>
 *  in an undirected graph. For versions that find the paths, see
 *  {@link DepthFirstPaths} and {@link BreadthFirstPaths}.
 *  <p>
 *  This implementation uses breadth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  It uses extra space (not including the graph) proportional to <em>V</em>.
 *  <p>
 *  For additional documentation, see <a href="/algs4/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
import java.util.*;

public class BreadthFirstSearch {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int count;           // number of vertices connected to s

    /**
     * Computes the vertices in graph <tt>G</tt> that are
     * connected to the source vertex <tt>s</tt>.
     * @param G the graph
     * @param s the source vertex
     */
    public BreadthFirstSearch(DiffGraph G, int s) {
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    // breadth first search from v
    private void bfs(DiffGraph G, int v) {
        Queue<Integer> queue = new LinkedList<Integer>();
        count++;
        marked[v] = true;
        queue.add(v);
        while(!queue.isEmpty())
        {
        	int r = queue.remove();
        	   for (int w : G.adj(r)) {
                   if (!marked[w]) {
                	   queue.add(w);
                       marked[w] = true;
                	   bfs(G, w);
                   }
               }
        }
     
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, <tt>false</tt> otherwise
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex <tt>s</tt>.
     * @return the number of vertices connected to the source vertex <tt>s</tt>
     */
    public int count() {
        return count;
    }
   
}