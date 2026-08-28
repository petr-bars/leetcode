package com.example.eighth_step.number_of_islands;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public int numIslandsBfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    count++;
                    queue.offer(new int[]{row, col});
                    grid[row][col] = '0';
                    while (!queue.isEmpty()) {
                        int[] coordinate = queue.poll();
                        detectedIsland(grid, coordinate, queue);
                    }
                }
            }
        }
        return count;
    }

    /**
     * Метод для проверки соседей через массив с заранее определенными вариантами
     * @param grid
     * @param coordinate
     * @param visited
     */
    private void detectedIsland(char[][] grid, int[] coordinate, Queue<int[]> visited) {
        int x = coordinate[0];
        int y = coordinate[1];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == '1') {
                grid[nx][ny] = '0';
                visited.offer(new int[]{nx, ny});
            }
        }
    }

    /**
     * Метод определения соседей через обычное ветвление
     * @param grid
     * @param coordinate
     * @param visited
     */
    private void detectedIslandIf(char[][] grid, int[] coordinate, Queue<int[]> visited) {
        int x = coordinate[0];
        int y = coordinate[1];
        if (x - 1 >= 0 && grid[x - 1][y] == '1') {
            grid[x - 1][y] = '0';
            visited.offer(new int[]{x - 1, y});
        }
        if (x + 1 < grid.length && grid[x + 1][y] == '1') {
            grid[x + 1][y] = '0';
            visited.offer(new int[]{x + 1, y});
        }
        if (y - 1 >= 0 && grid[x][y - 1] == '1') {
            grid[x][y - 1] = '0';
            visited.offer(new int[]{x, y - 1});
        }
        if (y + 1 < grid[0].length && grid[x][y + 1] == '1') {
            grid[x][y + 1] = '0';
            visited.offer(new int[]{x, y + 1});
        }
    }

    public int numIslandsDfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    count++;
                    dfs(grid, row, col);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != '1') {
            return;
        }
        grid[x][y] = '0';
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }
}
