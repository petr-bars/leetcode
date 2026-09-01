package com.example.eighth_step.pacific_atlantic_water_flow;

import java.util.*;

public class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        // 1. Добавляем границы в очереди
        for (int index = 0; index < m; index++) {
            pacific[index][0] = true;// левая граница → Тихий
            atlantic[index][n - 1] = true;// правая граница → Атлантический
            pacificQueue.offer(new int[]{index, 0});
            atlanticQueue.offer(new int[]{index, n - 1});
        }

        for (int index = 0; index < n; index++) {
            pacific[0][index] = true;// верхняя граница → Тихий
            atlantic[m - 1][index] = true; // нижняя граница → Атлантический
            pacificQueue.offer(new int[]{0, index});
            atlanticQueue.offer(new int[]{m - 1, index});
        }

        // 2. BFS для Тихого океана
        bfs(heights, pacific, pacificQueue);

        // 3. BFS для Атлантического океана
        bfs(heights, atlantic, atlanticQueue);

        // 4. Находим пересечение
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (pacific[row][col] && atlantic[row][col]) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }
        return result;
    }

    private void bfs(int[][] heights, boolean[][] reachableFromOcean, Queue<int[]> queue) {
        // Четыре направления движения: вверх, вниз, влево, вправо
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];

            for (int[] direction : directions) {
                int neighborRow = currentRow + direction[0];
                int neighborCol = currentCol + direction[1];

                // Проверяем, что соседняя клетка находится внутри острова
                boolean isInsideGrid = neighborRow >= 0 && neighborRow < heights.length &&
                        neighborCol >= 0 && neighborCol < heights[0].length;

                if (!isInsideGrid) {
                    continue; // выходим за границы – пропускаем
                }

                // Если соседняя клетка уже отмечена как достижимая из этого океана – пропускаем
                if (reachableFromOcean[neighborRow][neighborCol]) {
                    continue;
                }

                // Проверяем условие обратного течения:
                // вода может течь из соседней клетки в текущую, если высота соседа >= высота текущей
                boolean canFlowToOcean = heights[neighborRow][neighborCol] >= heights[currentRow][currentCol];

                if (canFlowToOcean) {
                    reachableFromOcean[neighborRow][neighborCol] = true;
                    queue.offer(new int[]{neighborRow, neighborCol});
                }
            }
        }
    }

    public List<List<Integer>> pacificAtlanticRecursion(int[][] heights) {
        // Если матрица пустая, возвращаем пустой список
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int rows = heights.length;
        int cols = heights[0].length;

        // Две булевы матрицы для отметок
        boolean[][] fromPacific = new boolean[rows][cols];
        boolean[][] fromAtlantic = new boolean[rows][cols];

        // Запускаем DFS от верхней и левой границ (Тихий океан)
        for (int row = 0; row < rows; row++) {
            dfs(heights, row, 0, fromPacific);
        }
        for (int col = 0; col < cols; col++) {
            dfs(heights, 0, col, fromPacific);
        }

        // Запускаем DFS от нижней и правой границ (Атлантический океан)
        for (int row = 0; row < rows; row++) {
            dfs(heights, row, cols - 1, fromAtlantic);
        }
        for (int col = 0; col < cols; col++) {
            dfs(heights, rows - 1, col, fromAtlantic);
        }

        // Собираем пересечение (клетки, достижимые из обоих океанов)
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (fromPacific[row][col] && fromAtlantic[row][col]) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }
        return result;
    }

    // Рекурсивный DFS: помечаем все достижимые клетки из океана
    private void dfs(int[][] heights, int row, int col, boolean[][] reachable) {
        // Помечаем текущую клетку
        reachable[row][col] = true;

        // Четыре направления: вверх, вниз, влево, вправо
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Проверяем, что соседняя клетка внутри сетки
            if (newRow < 0 || newRow >= heights.length || newCol < 0 || newCol >= heights[0].length) {
                continue;
            }

            // Если сосед уже отмечен – пропускаем
            if (reachable[newRow][newCol]) {
                continue;
            }

            // Проверяем условие "обратного потока": вода может течь из соседа в текущую,
            // если высота соседа >= высота текущей (двигаемся в гору от океана)
            if (heights[newRow][newCol] >= heights[row][col]) {
                dfs(heights, newRow, newCol, reachable);
            }
        }
    }

    public List<List<Integer>> pacificAtlanticRecursionIterable(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] fromPacific = new boolean[rows][cols];
        boolean[][] fromAtlantic = new boolean[rows][cols];

        // Инициализируем стеки и добавляем граничные клетки
        Deque<int[]> pacificStack = new ArrayDeque<>();
        Deque<int[]> atlanticStack = new ArrayDeque<>();

        // Тихий океан: левая и верхняя границы
        for (int row = 0; row < rows; row++) {
            fromPacific[row][0] = true;
            pacificStack.push(new int[]{row, 0});
        }
        for (int col = 0; col < cols; col++) {
            fromPacific[0][col] = true;
            pacificStack.push(new int[]{0, col});
        }

        // Атлантический океан: правая и нижняя границы
        for (int row = 0; row < rows; row++) {
            fromAtlantic[row][cols - 1] = true;
            atlanticStack.push(new int[]{row, cols - 1});
        }
        for (int col = 0; col < cols; col++) {
            fromAtlantic[rows - 1][col] = true;
            atlanticStack.push(new int[]{rows - 1, col});
        }

        // Запускаем итеративный DFS для каждого океана
        dfsIterative(heights, fromPacific, pacificStack);
        dfsIterative(heights, fromAtlantic, atlanticStack);

        // Находим пересечение
        List<List<Integer>> result = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (fromPacific[row][col] && fromAtlantic[row][col]) {
                    result.add(Arrays.asList(row, col));
                }
            }
        }
        return result;
    }

    // Итеративный DFS с использованием стека
    private void dfsIterative(int[][] heights, boolean[][] reachable, Deque<int[]> stack) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Проверяем границы и уже посещённые клетки
                if (newRow < 0 || newRow >= heights.length || newCol < 0 || newCol >= heights[0].length) {
                    continue;
                }
                if (reachable[newRow][newCol]) {
                    continue;
                }

                // Условие обратного течения: высота соседа >= высота текущей
                if (heights[newRow][newCol] >= heights[row][col]) {
                    reachable[newRow][newCol] = true;
                    stack.push(new int[]{newRow, newCol});
                }
            }
        }
    }
}
