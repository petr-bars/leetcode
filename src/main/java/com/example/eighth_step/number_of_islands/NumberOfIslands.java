package com.example.eighth_step.number_of_islands;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        Solution island = new Solution();
        char[][] grid = {
                {'1', '1', '1', '0', '0'},
                {'1', '0', '0', '1', '0'},
                {'0', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}

        };
//        System.out.println(island.numIslandsBfs(grid));
        System.out.println(island.numIslandsDfs(grid));
    }
}
