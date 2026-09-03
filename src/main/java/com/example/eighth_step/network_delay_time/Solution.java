package com.example.eighth_step.network_delay_time;

import java.util.*;

public class Solution {

    /**
     * Находит минимальное время, за которое сигнал из стартового узла достигнет
     * всех остальных узлов в направленном взвешенном графе.
     *
     * <p>Задача эквивалентна поиску кратчайших путей от одной вершины (startNode)
     * до всех остальных в графе с положительными весами рёбер. Для решения используется
     * классический алгоритм Дейкстры с приоритетной очередью (min-heap).</p>
     *
     * <p><b>Пошаговое описание алгоритма:</b></p>
     * <ol>
     *   <li><b>Построение графа</b> – создаётся список смежности {@code adjacencyList},
     *       где для каждого узла хранятся пары (сосед, вес ребра).</li>
     *   <li><b>Инициализация расстояний</b> – массив {@code shortestTime} заполняется
     *       {@code Integer.MAX_VALUE}, кроме стартового узла, для которого устанавливается 0.</li>
     *   <li><b>Приоритетная очередь</b> – хранит пары (время_до_узла, узел) и всегда
     *       отдаёт узел с наименьшим известным временем.</li>
     *   <li><b>Основной цикл</b> – пока очередь не пуста:
     *       <ul>
     *         <li>Извлекается узел с минимальным временем.</li>
     *         <li>Проверяется, не устарела ли запись (если извлечённое время больше
     *             уже известного – пропускаем).</li>
     *         <li>Для каждого соседа текущего узла вычисляется новое время:
     *             {@code newTime = currentTime + edgeWeight}.</li>
     *         <li>Если {@code newTime} меньше текущего сохранённого расстояния до соседа,
     *             то обновляется расстояние и сосед добавляется в очередь с новым временем.</li>
     *       </ul>
     *   </li>
     *   <li><b>Проверка достижимости</b> – после завершения обхода находится максимальное
     *       время среди всех узлов. Если хотя бы один узел остался с {@code Integer.MAX_VALUE}
     *       (недостижим), возвращается {@code -1}.</li>
     *   <li><b>Возврат результата</b> – иначе возвращается максимальное время, которое и
     *       является минимальным временем получения сигнала всеми узлами.</li>
     * </ol>
     *
     * <p><b>Сложность:</b>
     * <ul>
     *   <li>Время: O((V + E) log V), где V – количество узлов (nodeCount),
     *       E – количество рёбер (times.length).</li>
     *   <li>Память: O(V + E) для списка смежности и O(V) для очереди и массива расстояний.</li>
     * </ul>
     *
     * <p><b>Пример использования:</b>
     * <pre>
     * int[][] times = {{2,1,1}, {2,3,1}, {3,4,1}};
     * int nodeCount = 4;
     * int startNode = 2;
     * int result = networkDelayTime(times, nodeCount, startNode);
     * System.out.println(result); // 2
     * </pre>
     *
     * @param times     массив направленных рёбер, где каждое ребро задаётся тройкой
     *                  {@code (source, target, weight)} – откуда, куда, время прохождения.
     * @param nodeCount общее количество узлов в сети (нумеруются с 1 до nodeCount).
     * @param startNode узел, из которого отправляется сигнал.
     * @return минимальное время, за которое сигнал достигнет всех узлов, или -1,
     * если хотя бы один узел недостижим.
     * @see java.util.PriorityQueue
     * @see #networkDelayTime(int[][], int, int)
     */
    public int networkDelayTime(int[][] times, int nodeCount, int startNode) {

        // 1. Строим список смежности
        // Для каждого узла (от 1 до nodeCount) храним список ребер [сосед, вес]
        List<List<int[]>> adjacencyList = new ArrayList<>(nodeCount + 1);
        for (int i = 0; i <= nodeCount; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : times) {
            int sourceNode = edge[0];
            int targetNode = edge[1];
            int travelTime = edge[2];
            adjacencyList.get(sourceNode).add(new int[]{targetNode, travelTime});
        }

        // 2. Массив кратчайших расстояний от startNode до каждого узла
        int[] shortestTime = new int[nodeCount + 1];
        Arrays.fill(shortestTime, Integer.MAX_VALUE);
        shortestTime[startNode] = 0;

        // 3. Приоритетная очередь (min-heap) для Дейкстры
        // Храним пары [время_до_узла, узел]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, startNode});

        // 4. Основной цикл Дейкстры
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int currentTime = current[0];
            int currentNode = current[1];

            // Если эта запись устарела (не актуальное кратчайшее время) – пропускаем
            if (currentTime > shortestTime[currentNode]) {
                continue;
            }

            // Перебираем всех соседей текущего узла
            for (int[] neighbor : adjacencyList.get(currentNode)) {
                int neighborNode = neighbor[0];
                int edgeWeight = neighbor[1];

                int newTime = currentTime + edgeWeight;

                // Если нашли более короткий путь к соседу – обновляем и добавляем в очередь
                if (newTime < shortestTime[neighborNode]) {
                    shortestTime[neighborNode] = newTime;
                    minHeap.offer(new int[]{newTime, neighborNode});
                }
            }
        }

        // 5. Находим максимальное время среди всех узлов
        int maxDelay = 0;
        for (int node = 1; node <= nodeCount; node++) {
            if (shortestTime[node] == Integer.MAX_VALUE) {
                // Есть недостижимый узел
                return -1;
            }
            maxDelay = Math.max(maxDelay, shortestTime[node]);
        }

        return maxDelay;
    }
}
