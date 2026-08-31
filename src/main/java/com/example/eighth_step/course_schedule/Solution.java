package com.example.eighth_step.course_schedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. Строим список смежности и считаем inDegree
        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        for (int index = 0; index < numCourses; index++) {
            adj.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            int course = pre[0];        // курс, который нужно пройти
            int prereq = pre[1];        // курс, который требуется
            adj.get(prereq).add(course); // prereq -> course (prereq нужен для course)
            inDegree[course]++;          // у course увеличиваем количество предпосылок
        }

        // 2. Очередь всех курсов с inDegree == 0
        Queue<Integer> queue = new LinkedList<>();
        for (int index = 0; index < numCourses; index++) {
            if (inDegree[index] == 0) {
                queue.offer(index);
            }
        }

        // 3. Обрабатываем
        int processed = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            processed++;
            for (int next : adj.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // 4. Результат
        return processed == numCourses;
    }

    public boolean canFinishRecursion(int numCourses, int[][] prerequisites) {
        // Строим граф: для каждого курса храним список тех, кто от него зависит
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            int course = pre[0];    // курс, который требует предпосылку
            int prereq = pre[1];    // предпосылка
            adj.get(prereq).add(course); // ребро от предпосылки к зависимому курсу
        }

        // Состояния: 0 - не посещен, 1 - в процессе, 2 - обработан
        int[] state = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(i, adj, state)) {
                    return false; // найден цикл → нельзя пройти все курсы
                }
            }
        }
        return true;
    }

    private boolean hasCycle(int node, List<List<Integer>> adj, int[] state) {
        if (state[node] == 1) {
            return true;  // встретили вершину, уже находящуюся в стеке → цикл
        }
        if (state[node] == 2) {
            return false; // уже обработана, цикла нет
        }

        state[node] = 1; // помечаем как "в процессе"
        for (int neighbor : adj.get(node)) {
            if (hasCycle(neighbor, adj, state)) {
                return true;
            }
        }
        state[node] = 2; // обработали всех соседей
        return false;
    }
}
