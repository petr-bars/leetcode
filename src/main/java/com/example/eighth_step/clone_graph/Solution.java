package com.example.eighth_step.clone_graph;

import java.util.*;

public class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        Node copy = new Node(node.val);
        map.put(node, copy);
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Node currentCopy = map.get(current);
            for (Node neighbor : current.neighbors) {
                if (!map.containsKey(neighbor)) {
                    Node neighborCopy = new Node(neighbor.val);
                    map.put(neighbor, neighborCopy);
                    queue.offer(neighbor);
                }
                Node neighborCopy = map.get(neighbor);
                currentCopy.neighbors.add(neighborCopy);
            }
        }
        return copy;
    }

    public Node cloneGraphStack(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Deque<Node> stack = new ArrayDeque<>();

        // Копируем стартовый узел
        Node copy = new Node(node.val);
        map.put(node, copy);
        stack.push(node);

        while (!stack.isEmpty()) {
            Node current = stack.pop();          // извлекаем из стека (LIFO)
            Node currentCopy = map.get(current);

            for (Node neighbor : current.neighbors) {
                if (!map.containsKey(neighbor)) {
                    Node neighborCopy = new Node(neighbor.val);
                    map.put(neighbor, neighborCopy);
                    stack.push(neighbor);        // добавляем в стек для дальнейшего обхода
                }
                // Добавляем копию соседа в список соседей текущей копии
                currentCopy.neighbors.add(map.get(neighbor));
            }
        }
        return copy;
    }
}
