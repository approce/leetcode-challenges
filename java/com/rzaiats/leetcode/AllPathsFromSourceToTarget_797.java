package com.rzaiats.leetcode;
//Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find
//all possible paths from node 0 to node n - 1, and return them in any order.
//
// The graph is given as follows: graph[i] is a list of all nodes you can visit
//from node i (i.e., there is a directed edge from node i to node graph[i][j]).
//
//
// Example 1:
//
//
//Input: graph = [[1,2],[3],[3],[]]
//Output: [[0,1,3],[0,2,3]]
//Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
//
//
// Example 2:
//
//
//Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
//Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//
//
// Example 3:
//
//
//Input: graph = [[1],[]]
//Output: [[0,1]]
//
//
// Example 4:
//
//
//Input: graph = [[1,2,3],[2],[3],[]]
//Output: [[0,1,2,3],[0,2,3],[0,3]]
//
//
// Example 5:
//
//
//Input: graph = [[1,3],[2],[3],[]]
//Output: [[0,1,2,3],[0,3]]
//
//
//
// Constraints:
//
//
// n == graph.length
// 2 <= n <= 15
// 0 <= graph[i][j] < n
// graph[i][j] != i (i.e., there will be no self-loops).
// The input graph is guaranteed to be a DAG.
//
// Related Topics Backtracking Depth-first Search Graph
// 👍 1317 👎 80


import java.util.*;
import java.util.stream.Collectors;

public class AllPathsFromSourceToTarget_797 {
    public static void main(String[] args) {
        int[][] params = new int[5][4];
        params[0] = new int[]{4, 3, 1};
        params[1] = new int[]{3, 2, 4};
        params[2] = new int[]{3};
        params[3] = new int[]{4};
        params[4] = new int[0];

        System.out.println(new JavaStreamsSolution().allPathsSourceTarget(params));
    }

    /**
     * Clean implementation based on Java 8 streams.
     * But performance is low: 23ms execution. Faster than 6%
     */
    static class JavaStreamsSolution {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            if (graph.length == 0) {
                return Collections.emptyList();
            }

            return new ArrayList<>(findPaths(graph, 0, graph.length - 1));
        }

        public List<LinkedList<Integer>> findPaths(int[][] graph, int node, int target) {
            if (node == target) {
                return Collections.singletonList(new LinkedList<Integer>() {{
                    add(node);
                }});
            }

            int[] links = graph[node];

            return Arrays.stream(links)
                         .boxed()
                         .map(singleLink -> findPaths(graph, singleLink, target))
                         .flatMap(Collection::stream)
                         .peek(integers -> integers.push(node))
                         .collect(Collectors.toList());
        }
    }

    /**
     * Simple for loop implementation
     * Performance: 4ms. 5 times faster than int boxing + Java 8 streams.
     */
    static class JavaSimpleSolution {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            if (graph.length == 0) {
                return Collections.emptyList();
            }

            return new ArrayList<>(findPaths(graph, 0, graph.length - 1));
        }

        public List<LinkedList<Integer>> findPaths(int[][] graph, int node, int target) {
            if (node == target) {
                return Collections.singletonList(new LinkedList<Integer>() {{
                    add(node);
                }});
            }

            int[] links = graph[node];

            List<LinkedList<Integer>> result = new ArrayList<>();
            for (int singleLink : links) {
                List<LinkedList<Integer>> paths = findPaths(graph, singleLink, target);

                if (!paths.isEmpty()) {
                    paths.forEach(singlePath -> {
                        singlePath.push(node);
                        result.add(singlePath);

                    });
                }
            }

            return result;
        }
    }
}
