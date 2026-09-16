package com.example.tenth_step.unique_paths;

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 * <p>
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach
 * the bottom-right corner.
 * <p>
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 */
public class UniquePaths {
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths(m, n));
        System.out.println(solution.uniquePathsOptimized(m, n));
    }
}
