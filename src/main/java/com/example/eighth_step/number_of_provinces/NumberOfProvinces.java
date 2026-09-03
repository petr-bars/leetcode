package com.example.eighth_step.number_of_provinces;

/**
 * There are n cities. Some of them are connected, while some are not.
 * If city a is connected directly with city b, and city b is connected directly with city c,
 * then city a is connected indirectly with city c.
 * <p>
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * <p>
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly
 * connected, and isConnected[i][j] = 0 otherwise.
 */
public class NumberOfProvinces {
    public static void main(String[] args) {
        int[][] isConnected = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        Solution solution = new Solution();
//        int result = solution.findCircleNumBfs(isConnected);
//        int result = solution.findCircleNumDfs(isConnected);
        int result = solution.findCircleNumUnionFind(isConnected);
        System.out.println(result);
    }
}
