package com.example.eighth_step.course_schedule_II;

import java.util.*;

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

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
        List<Integer> courseOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int course = queue.poll();
            courseOrder.add(course);
            processed++;
            for (int next : adj.get(course)) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (processed == numCourses) {
            int[] result = new int[numCourses];
            for (int index = 0; index < numCourses; index++) {
                result[index] = courseOrder.get(index);
            }
            return result;
        }
        return new int[0];
    }

    public int[] findOrderRecursion(int numCourses, int[][] prerequisites) {
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
        List<Integer> courseOrder = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (state[i] == 0) {
                if (hasCycle(i, adj, state, courseOrder)) {
                    return new int[0]; // найден цикл → нельзя пройти все курсы
                }
            }
        }

        int[] result = new int[numCourses];
        for (int index = 0; index < numCourses; index++) {
            result[index] = courseOrder.get(index);
        }
        return result;
    }

    private boolean hasCycle(int node, List<List<Integer>> adj, int[] state, List<Integer> courseOrder) {
        if (state[node] == 1) {
            return true;  // встретили вершину, уже находящуюся в стеке → цикл
        }
        if (state[node] == 2) {
            return false; // уже обработана, цикла нет
        }

        state[node] = 1; // помечаем как "в процессе"
        for (int neighbor : adj.get(node)) {
            if (hasCycle(neighbor, adj, state, courseOrder)) {
                return true;
            }
        }
        state[node] = 2; // обработали всех соседей
        courseOrder.add(0, node);
        return false;
    }
}
