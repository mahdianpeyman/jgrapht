package org.jgrapht.generate;

import java.util.*;

import junit.framework.*;

import org.jgrapht.*;
import org.jgrapht.alg.*;
import org.jgrapht.graph.*;


public class GridGraphGeneratorTest
    extends TestCase
{
	public void testGridGraphGenerator()
    {
        int rows = 3;
        int cols = 2;

        //the form of these two classes helps debugging
        class StringVertexFactory
            implements VertexFactory<String>
        {
            int index = 1;

            @Override public String createVertex()
            {
                return String.valueOf(index++);
            }
        }

        class StringEdgeFactory
            implements EdgeFactory<String, String>
        {
            @Override public String createEdge(
                String sourceVertex,
                String targetVertex)
            {
                return new String(sourceVertex + '-' + targetVertex);
            }
        }

        GridGraphGenerator<String, String> generator =
            new GridGraphGenerator<String, String>(rows, cols);
        Map<String, String> resultMap = new HashMap<String, String>();

        //validating a directed and undirected graph
        Graph<String, String> directedGridGraph =
            new DefaultDirectedGraph<String, String>(new StringEdgeFactory());
        generator.generateGraph(
            directedGridGraph,
            new StringVertexFactory(),
            resultMap);  // resultMap is not null
        validateGridGraphGenerator(rows, cols, directedGridGraph, resultMap);

        generator.generateGraph(
            directedGridGraph,
            new StringVertexFactory(),
            null);   // resultMap is null
    }

    public void validateGridGraphGenerator(
        int rows,
        int cols,
        Graph<String, String> gridGraph,
        Map<String, String> resultMap)
    {
        // graph structure validations
        int expectedVerticeNum = rows * cols;
        assertEquals(
            "number of vertices is wrong (" + gridGraph
            .vertexSet().size()
            + "), should be " + expectedVerticeNum,
            expectedVerticeNum,
            gridGraph.vertexSet().size());
        int expectedEdgesNum =
            (((rows - 1) * cols) + ((cols - 1) * rows))
            * ((gridGraph instanceof UndirectedGraph) ? 1 : 2);
        assertEquals(
            "number of edges is wrong (" + gridGraph
            .edgeSet().size()
            + "), should be " + expectedEdgesNum,
            expectedEdgesNum,
            gridGraph.edgeSet().size());

        int cornerVertices = 0, borderVertices = 0, innerVertices = 0,
            neighborsSize;
        int expCornerVertices = 4;
        int expBorderVertices =
            Math.max(((rows - 2) * 2) + ((cols - 2) * 2), 0);
        int expInnerVertices = Math.max((rows - 2) * (cols - 2), 0);
        Set<String> neighbors = new HashSet<String>();

        for (String v : gridGraph.vertexSet()) {
            neighbors.clear();
            neighbors.addAll(Graphs.neighborListOf(gridGraph, v));
            neighborsSize = neighbors.size();
            assertTrue(
                "vertex with illegal number of neighbors (" + neighborsSize
                + ").",
                (neighborsSize == 2)
                || (neighborsSize == 3)
                || (neighborsSize == 4));
            if (neighborsSize == 2) {
                cornerVertices++;
            } else if (neighborsSize == 3) {
                borderVertices++;
            } else if (neighborsSize == 4) {
                innerVertices++;
            }
        }
        assertEquals(
            "there should be exactly " + expCornerVertices
            + " corner (with two neighbors) vertices. "
            + " actual number is " + cornerVertices + ".",
            expCornerVertices,
            cornerVertices);
        assertEquals(
            "there should be exactly " + expBorderVertices
            + " border (with three neighbors) vertices. "
            + " actual number is " + borderVertices + ".",
            expBorderVertices,
            borderVertices);
        assertEquals(
            "there should be exactly " + expInnerVertices
            + " inner (with four neighbors) vertices. "
            + " actual number is " + innerVertices + ".",
            expInnerVertices,
            innerVertices);

        // result map validations
        Set<String> keys = resultMap.keySet();
        assertEquals(
            "result map contains should contains exactly 4 corner verices",
            4,
            keys.size());

        for (String key : keys) {
            neighbors.clear();
            neighbors.addAll(
                Graphs.neighborListOf(gridGraph, resultMap.get(key)));
            neighborsSize = neighbors.size();
            assertEquals(
                "corner vertex should have exactly 2 neighbors",
                2,
                neighborsSize);
        }
    }

}