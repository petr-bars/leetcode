package com.example.tenth_step.minimum_path_sum;

public class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        // Базовый случай: старт
        dp[0][0] = grid[0][0];

        // Первая строка: только вправо
        for (int col = 1; col < cols; col++) {
            dp[0][col] = dp[0][col - 1] + grid[0][col];

        }

        // Первый столбец: только вниз
        for (int row = 1; row < rows; row++) {
            dp[row][0] = dp[row - 1][0] + grid[row][0];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                dp[row][col] = grid[row][col] + Math.min(dp[row - 1][col], dp[row][col - 1]);
            }
        }

        return dp[rows - 1][cols - 1];
    }


    public int minPathSumOptimized(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];

        dp[0] = grid[0][0];

        // Первая строка: только вправо
        for (int col = 1; col < cols; col++) {
            dp[col] = dp[col - 1] + grid[0][col];
        }

        for (int row = 1; row < rows; row++) {
            // Первый столбец: только сверху
            dp[0] = dp[0] + grid[row][0];
            for (int col = 1; col < cols; col++) {
                dp[col] = grid[row][col] + Math.min(dp[col], dp[col - 1]);
            }
        }

        return dp[cols - 1];
    }
}
