package com.example.eighth_step.max_area_of_islands;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int maxArea = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    int[] countArea = {0};
                    dfs(grid, row, col, countArea);
                    maxArea = Math.max(maxArea, countArea[0]);
                }
            }
        }
        return maxArea;
    }

    //Можно упростить убрать массив и возвращать напрямую посчитанный int всех рекурсивных вызовов.
    private void dfs(int[][] grid, int x, int y, int[] countArea) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
            return;
        }
        grid[x][y] = 0;
        countArea[0]++;
        dfs(grid, x - 1, y, countArea);
        dfs(grid, x + 1, y, countArea);
        dfs(grid, x, y - 1, countArea);
        dfs(grid, x, y + 1, countArea);
    }

    public int numIslandsBfs(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        int maxArea = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    int[] countArea = {0};
                    queue.offer(new int[]{row, col});
                    grid[row][col] = 0;
                    countArea[0]++;
                    while (!queue.isEmpty()) {
                        int[] coordinate = queue.poll();
                        detectedIsland(grid, coordinate, queue, countArea);
                    }
                    maxArea = Math.max(maxArea, countArea[0]);
                }
            }
        }
        return maxArea;
    }


    private void detectedIsland(int[][] grid, int[] coordinate, Queue<int[]> visited, int[] countArea) {
        int x = coordinate[0];
        int y = coordinate[1];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 1) {
                grid[nx][ny] = 0;
                countArea[0]++;
                visited.offer(new int[]{nx, ny});
            }
        }
    }
}
