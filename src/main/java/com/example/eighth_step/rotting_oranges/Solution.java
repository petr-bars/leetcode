package com.example.eighth_step.rotting_oranges;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    public int orangesRotting(int[][] grid) {
        int[] freshCount = {0};

        Queue<int[]> rottenOrangeQueue = new ArrayDeque<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    freshCount[0]++;
                }
                if (grid[row][col] == 2) {
                    rottenOrangeQueue.offer(new int[]{row, col});
                }
            }
        }

        if (freshCount[0] == 0) {
            return 0;
        }

        return bfs(freshCount, grid, rottenOrangeQueue);
    }

    private int bfs(int[] freshCount, int[][] grid, Queue<int[]> rottenOrangeQueue) {
        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!rottenOrangeQueue.isEmpty() && freshCount[0] > 0) {
            int queueSize = rottenOrangeQueue.size();
            minutes++;
            for (int index = 0; index < queueSize; index++) {
                int[] currentCell = rottenOrangeQueue.poll();
                int currentRow = currentCell[0];
                int currentCol = currentCell[1];

                for (int[] direction : directions) {
                    int neighborRow = currentRow + direction[0];
                    int neighborCol = currentCol + direction[1];

                    if (neighborRow >= 0 && neighborRow < grid.length
                            && neighborCol >= 0 && neighborCol < grid[0].length
                            && grid[neighborRow][neighborCol] == 1) {
                        grid[neighborRow][neighborCol] = 2;
                        freshCount[0]--;
                        rottenOrangeQueue.offer(new int[]{neighborRow, neighborCol});
                    }
                }
            }

        }
        if (freshCount[0] == 0) {
            return minutes;
        }
        return -1;
    }
}
