package com.example.eighth_step.clone_graph;

import com.example.eighth_step.Utils;

import java.util.List;

/**
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * Test case format:
 * <p>
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example,
 * the first node with val == 1, the second node with val == 2, and so on.
 * The graph is represented in the test case using an adjacency list.
 * <p>
 * An adjacency list is a collection of unordered lists used to represent a finite graph.
 * Each list describes the set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1.
 * You must return the copy of the given node as a reference to the cloned graph.
 */
public class CloneGraph {
    public static void main(String[] args) {
        int[][] adjList = {{2, 4}, {1, 3}, {2, 4}, {1, 3}};
        Node node = Utils.buildGraph(adjList);
        Solution clone = new Solution();
        Node clonedGraph = clone.cloneGraph(node);
        Node clonedGraphStack = clone.cloneGraphStack(node);
//        List<List<Integer>> result = Utils.graphToAdjList(clonedGraph);
        List<List<Integer>> result = Utils.graphToAdjList(clonedGraphStack);
        System.out.println(result);
    }
}
