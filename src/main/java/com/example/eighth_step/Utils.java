package com.example.eighth_step;

import com.example.eighth_step.clone_graph.Node;

import java.util.*;

public class Utils {
    public static Node buildGraph(int[][] adjList) {
        if (adjList == null || adjList.length == 0) {
            return null;
        }
        Node[] nodes = new Node[adjList.length + 1]; // 1‑индексация
        // Создаём узлы
        for (int i = 1; i <= adjList.length; i++) {
            nodes[i] = new Node(i);
        }
        // Заполняем соседей
        for (int i = 0; i < adjList.length; i++) {
            Node node = nodes[i + 1];
            for (int neighborVal : adjList[i]) {
                node.neighbors.add(nodes[neighborVal]);
            }
        }
        return nodes[1];
    }


    public static List<List<Integer>> graphToAdjList(Node node) {
        if (node == null) {
            return new ArrayList<>();
        }

        // 1. Собрать все узлы графа (BFS)
        Map<Integer, Node> nodeMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        queue.offer(node);
        visited.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            nodeMap.put(cur.val, cur);
            for (Node neighbor : cur.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        // 2. Определить максимальный номер узла (можно просто взять размер nodeMap)
        int maxVal = nodeMap.size(); // т.к. значения идут от 1 до n
        List<List<Integer>> result = new ArrayList<>();

        // 3. Для каждого val от 1 до maxVal получить отсортированный список соседей
        for (int i = 1; i <= maxVal; i++) {
            Node cur = nodeMap.get(i);
            if (cur == null) {
                // Если каких-то номеров нет (не должно быть), добавляем пустой список
                result.add(new ArrayList<>());
            } else {
                List<Integer> neighbors = new ArrayList<>();
                for (Node neighbor : cur.neighbors) {
                    neighbors.add(neighbor.val);
                }
                Collections.sort(neighbors); // для однозначного порядка
                result.add(neighbors);
            }
        }
        return result;
    }
}
