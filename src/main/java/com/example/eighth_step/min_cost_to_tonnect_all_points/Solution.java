package com.example.eighth_step.min_cost_to_tonnect_all_points;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Решение задачи "Min Cost to Connect All Points" (LeetCode 1584).
 * <p>
 * Задача: даны точки на плоскости, необходимо соединить их все таким образом,
 * чтобы общая стоимость соединений была минимальной. Стоимость соединения двух точек
 * равна манхэттенскому расстоянию между ними: |xi - xj| + |yi - yj|.
 * <p>
 * Алгоритм: используется алгоритм Прима (Prim's algorithm) для построения
 * минимального остовного дерева (MST) в полном взвешенном графе,
 * где вершины — точки, а вес рёбер — манхэттенское расстояние.
 * <p>
 * Прим работает следующим образом:
 * <ol>
 *   <li>Выбирается стартовая вершина (первая точка).</li>
 *   <li>Поддерживается массив минимальных расстояний от каждой непосещённой вершины
 *       до уже построенной части дерева.</li>
 *   <li>На каждом шаге выбирается непосещённая вершина с наименьшим расстоянием
 *       до дерева, добавляется в дерево, а затем обновляются расстояния для всех
 *       оставшихся непосещённых вершин через неё.</li>
 *   <li>Процесс повторяется, пока все вершины не будут добавлены.</li>
 * </ol>
 * <p>
 * Сложность:
 * <ul>
 *   <li>Время: O(n²), где n — количество точек. Это связано с тем, что на каждом
 *       шаге мы просматриваем все непосещённые точки для обновления расстояний.</li>
 *   <li>Память: O(n) для массивов visited, minDistance и приоритетной очереди.</li>
 * </ul>
 * <p>
 * Альтернативный подход — алгоритм Краскала с сортировкой всех рёбер,
 * но для плотных графов (полный граф) Прим обычно эффективнее по памяти и времени.
 *
 * @see <a href="https://leetcode.com/problems/min-cost-to-connect-all-points/">LeetCode 1584</a>
 */
public class Solution {

    /**
     * Вычисляет минимальную стоимость соединения всех точек на плоскости.
     * <p>
     * Точки заданы массивом {@code points}, где {@code points[i] = [xi, yi]}.
     * Стоимость соединения двух точек равна манхэттенскому расстоянию.
     * <p>
     * Алгоритм Прима:
     * <ol>
     *   <li>Инициализация:
     *       <ul>
     *         <li>Создаются массивы {@code visited} (false) и {@code minDistance} (бесконечность).</li>
     *         <li>Расстояние до стартовой точки (индекс 0) устанавливается в 0.</li>
     *         <li>Создаётся приоритетная очередь (min-heap), в которую помещается
     *             стартовая точка с расстоянием 0.</li>
     *       </ul>
     *   </li>
     *   <li>Основной цикл:
     *       <ul>
     *         <li>Из очереди извлекается точка с наименьшим расстоянием до текущего дерева.</li>
     *         <li>Если точка уже посещена, она пропускается (устаревшая запись).</li>
     *         <li>Иначе точка помечается как посещённая, а её расстояние прибавляется
     *             к общей стоимости ({@code totalCost}).</li>
     *         <li>Для всех непосещённых точек вычисляется манхэттенское расстояние
     *             от текущей точки. Если оно меньше текущего сохранённого расстояния
     *             до этой точки, то значение обновляется, и точка добавляется в очередь
     *             с новым расстоянием.</li>
     *       </ul>
     *   </li>
     *   <li>Завершение: когда очередь опустеет, все точки будут посещены,
     *       а {@code totalCost} содержит минимальную стоимость соединения.</li>
     * </ol>
     *
     * @param points массив координат точек, где points[i] = [xi, yi]
     * @return минимальная стоимость соединения всех точек
     * @throws IllegalArgumentException если массив точек пуст или содержит null
     */
    public int minCostConnectPoints(int[][] points) {
        int length = points.length;

        if (length == 0) {
            return 0;
        }

        boolean[] visited = new boolean[length];
        Arrays.fill(visited, false);

        int[] minDistance = new int[length];
        Arrays.fill(minDistance, Integer.MAX_VALUE);
        minDistance[0] = 0;

        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, 0});

        int totalCosts = 0;

        while (!minHeap.isEmpty()) {
            int[] cellPoint = minHeap.poll();
            int dist = cellPoint[0];
            int index = cellPoint[1];

            if (visited[index]) {
                continue;
            }

            visited[index] = true;
            minDistance[index] = dist;
            totalCosts += dist;

            for (int point = 0; point < length; point++) {
                int newDist = Math.abs(points[index][0] - points[point][0]) + Math.abs(points[index][1] - points[point][1]);
                if (newDist < minDistance[point]) {
                    minDistance[point] = newDist;
                    minHeap.offer(new int[]{newDist, point});
                }
            }
        }
        return totalCosts;
    }
}
