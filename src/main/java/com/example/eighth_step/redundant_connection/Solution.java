package com.example.eighth_step.redundant_connection;

public class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int vertexCount = edges.length;
        int[] parent = new int[vertexCount + 1];
        int[] groupSize = new int[vertexCount + 1];

        for (int index = 1; index <= vertexCount; index++) {
            parent[index] = index;
            groupSize[index] = 1;
        }

        for (int[] edge : edges) {
            int vertexA = edge[0];
            int vertexB = edge[1];

            if (find(vertexA, parent) == find(vertexB, parent)) {
                return edge;
            }

            union(vertexA, vertexB, parent, groupSize);
        }

        return new int[0];
    }

    // Находит корень группы, к которой принадлежит узел
    private int find(int node, int[] parent) {
        if (parent[node] != node) {
            parent[node] = find(parent[node], parent); // сжатие пути
        }
        return parent[node];
    }

    private int findIterative(int node, int[] parent) {
        // Идём по цепочке родителей, пока не найдём корень
        while (parent[node] != node) {
            // Сжатие пути: переподключаем элемент к его "дедушке"
            parent[node] = parent[parent[node]];
            node = parent[node];
        }
        return node;
    }

    // Объединяет две группы
    private void union(int nodeA, int nodeB, int[] parent, int[] groupSize) {
        int rootA = find(nodeA, parent);
        int rootB = find(nodeB, parent);

        if (rootA == rootB) {
            return; // уже в одной группе (этот случай не должен произойти, т.к. мы проверили выше)
        }

        // Присоединяем меньшую группу к большей
        if (groupSize[rootA] < groupSize[rootB]) {
            parent[rootA] = rootB;
            groupSize[rootB] += groupSize[rootA];
        } else {
            parent[rootB] = rootA;
            groupSize[rootA] += groupSize[rootB];
        }
    }
}
