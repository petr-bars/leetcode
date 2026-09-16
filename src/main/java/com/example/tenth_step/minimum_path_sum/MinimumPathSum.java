package com.example.tenth_step.minimum_path_sum;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        Solution solution = new Solution();
        System.out.println(solution.minPathSum(grid));
        System.out.println(solution.minPathSumOptimized(grid));
    }
}
