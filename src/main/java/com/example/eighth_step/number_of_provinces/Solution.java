package com.example.eighth_step.number_of_provinces;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Решение задачи "Number of Provinces" (LeetCode 547).
 * <p>
 * Задача: дана матрица isConnected размера n x n, где isConnected[i][j] = 1,
 * если города i и j напрямую соединены. Нужно найти количество провинций
 * (компонент связности) в неориентированном графе.
 * <p>
 * В классе представлены три реализации:
 * <ul>
 *   <li>BFS (поиск в ширину) – итеративный обход с очередью;</li>
 *   <li>DFS (поиск в глубину) – рекурсивный обход;</li>
 *   <li>Union-Find (система непересекающихся множеств) – объединение и поиск корней.</li>
 * </ul>
 * Все реализации имеют сложность O(n²) по времени и O(n) по памяти,
 * где n – количество городов.
 */
public class Solution {

    // ============================================================
    // 1. РЕАЛИЗАЦИЯ ЧЕРЕЗ BFS
    // ============================================================

    /**
     * Находит количество провинций с использованием поиска в ширину (BFS).
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Создаётся массив {@code visited} для отслеживания посещённых городов.</li>
     *   <li>Для каждого непосещённого города запускается BFS:
     *       <ul>
     *         <li>Увеличивается счётчик провинций.</li>
     *         <li>Город добавляется в очередь и помечается как посещённый.</li>
     *         <li>Пока очередь не пуста, извлекается город и просматриваются все его
     *             соседи (столбцы матрицы). Если сосед связан и не посещён, он
     *             помечается и добавляется в очередь.</li>
     *       </ul>
     *   </li>
     *   <li>Возвращается количество провинций.</li>
     * </ol>
     * <p>
     * Сложность: O(n²) по времени (для каждого города проверяются все n соседей),
     * O(n) по памяти (для очереди и массива visited).
     *
     * @param isConnected матрица смежности размером n x n,
     *                    где 1 означает прямую связь между городами
     * @return количество провинций (компонент связности)
     */
    public int findCircleNumBfs(int[][] isConnected) {
        int cityCount = isConnected.length;
        int provinces = 0;
        boolean[] visited = new boolean[cityCount];

        for (int city = 0; city < cityCount; city++) {
            if (!visited[city]) {
                provinces++;
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(city);
                visited[city] = true;

                while (!queue.isEmpty()) {
                    int currentCity = queue.poll();
                    // Проверяем всех потенциальных соседей
                    for (int neighbor = 0; neighbor < cityCount; neighbor++) {
                        if (isConnected[currentCity][neighbor] == 1 && !visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }
        return provinces;
    }


    // ============================================================
    // 2. РЕАЛИЗАЦИЯ ЧЕРЕЗ DFS (РЕКУРСИВНАЯ)
    // ============================================================

    /**
     * Находит количество провинций с использованием рекурсивного поиска в глубину (DFS).
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Создаётся массив {@code visited} для отслеживания посещённых городов.</li>
     *   <li>Для каждого непосещённого города запускается рекурсивный DFS:
     *       <ul>
     *         <li>Увеличивается счётчик провинций.</li>
     *         <li>Вызывается вспомогательный метод {@code dfs}, который
     *             помечает текущий город и рекурсивно обходит всех непосещённых соседей.</li>
     *       </ul>
     *   </li>
     *   <li>Возвращается количество провинций.</li>
     * </ol>
     * <p>
     * Сложность: O(n²) по времени, O(n) по памяти (глубина рекурсии в худшем случае).
     *
     * @param isConnected матрица смежности размером n x n
     * @return количество провинций
     */
    public int findCircleNumDfs(int[][] isConnected) {
        int cityCount = isConnected.length;
        boolean[] visited = new boolean[cityCount];
        int provinces = 0;
        for (int city = 0; city < cityCount; city++) {
            if (!visited[city]) {
                provinces++;
                dfs(city, visited, isConnected);
            }
        }
        return provinces;
    }

    /**
     * Вспомогательный рекурсивный метод для DFS.
     * <p>
     * Помечает текущий город как посещённый, затем для каждого соседа,
     * с которым есть связь и который ещё не посещён, рекурсивно вызывает себя.
     *
     * @param currentCity текущий обрабатываемый город
     * @param visited     массив флагов посещения
     * @param isConnected матрица смежности
     */
    public void dfs(int currentCity, boolean[] visited, int[][] isConnected) {
        visited[currentCity] = true;
        int cityCount = isConnected.length;

        for (int neighbor = 0; neighbor < cityCount; neighbor++) {
            if (isConnected[currentCity][neighbor] == 1 && !visited[neighbor]) {
                dfs(neighbor, visited, isConnected);
            }
        }
    }


    // ============================================================
    // 3. РЕАЛИЗАЦИЯ ЧЕРЕЗ UNION-FIND
    // ============================================================

    /**
     * Находит количество провинций с использованием системы непересекающихся множеств (Union-Find).
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Инициализируется массив {@code cityRoot}, где каждый город является корнем своей группы.</li>
     *   <li>Для каждой пары городов {@code (i, j)}, где {@code i < j}, если они связаны,
     *       выполняется объединение их групп.</li>
     *   <li>После обработки всех связей подсчитывается количество корней
     *       (элементов, для которых {@code cityRoot[i] == i}).</li>
     *   <li>Возвращается количество корней – это и есть число провинций.</li>
     * </ol>
     * <p>
     * Для оптимизации используется массив {@code groupSize} для балансировки дерева
     * (присоединение меньшей группы к большей), а также сжатие путей в {@code findRoot}.
     * <p>
     * Сложность: O(n² + n * α(n)) ≈ O(n²), где α(n) – обратная функция Аккермана (почти константа).
     * Память: O(n).
     *
     * @param isConnected матрица смежности размером n x n
     * @return количество провинций
     */
    public int findCircleNumUnionFind(int[][] isConnected) {
        int cityCount = isConnected.length;
        int[] cityRoot = new int[cityCount];
        int[] groupSize = new int[cityCount]; // для оптимизации (ранг/размер)

        // Изначально каждый город — корень своей группы
        for (int city = 0; city < cityCount; city++) {
            cityRoot[city] = city;
            groupSize[city] = 1;
        }

        // Проходим по всем парам городов и объединяем связанные
        for (int cityA = 0; cityA < cityCount; cityA++) {
            for (int cityB = cityA + 1; cityB < cityCount; cityB++) {
                if (isConnected[cityA][cityB] == 1) {
                    union(cityRoot, groupSize, cityA, cityB);
                }
            }
        }

        // Считаем количество корней (уникальных групп)
        int provinceCount = 0;
        for (int city = 0; city < cityCount; city++) {
            if (cityRoot[city] == city) {
                provinceCount++;
            }
        }
        return provinceCount;
    }

    /**
     * Находит корень (главного представителя) группы, к которой принадлежит город.
     * <p>
     * Применяется сжатие пути: после нахождения корня ссылка для всех промежуточных
     * элементов обновляется напрямую на корень, что ускоряет последующие вызовы.
     *
     * @param cityRoot массив, хранящий родителя для каждого города
     * @param city     город, для которого нужно найти корень
     * @return корень группы, содержащей данный город
     */
    private int findRoot(int[] cityRoot, int city) {
        // Если город сам себе корень — возвращаем его
        if (cityRoot[city] == city) {
            return city;
        }
        // Иначе рекурсивно поднимаемся к корню и сжимаем путь
        cityRoot[city] = findRoot(cityRoot, cityRoot[city]);
        return cityRoot[city];
    }

    /**
     * Объединяет две группы, содержащие города cityA и cityB.
     * <p>
     * Сначала находятся корни каждой группы. Если они различаются,
     * то корень меньшей группы присоединяется к корню большей (балансировка по размеру).
     * Это сохраняет дерево плоским и обеспечивает почти константное время операций.
     *
     * @param cityRoot  массив, хранящий родителя для каждого города
     * @param groupSize массив, хранящий размер каждой группы (по корню)
     * @param cityA     первый город
     * @param cityB     второй город
     */
    private void union(int[] cityRoot, int[] groupSize, int cityA, int cityB) {
        int rootA = findRoot(cityRoot, cityA);
        int rootB = findRoot(cityRoot, cityB);

        // Если они уже в одной группе — ничего не делаем
        if (rootA == rootB) {
            return;
        }

        // Присоединяем меньшую группу к большей (для баланса дерева)
        if (groupSize[rootA] < groupSize[rootB]) {
            cityRoot[rootA] = rootB;
            groupSize[rootB] += groupSize[rootA];
        } else {
            cityRoot[rootB] = rootA;
            groupSize[rootA] += groupSize[rootB];
        }
    }
}
