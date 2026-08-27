package com.example.seventh_step.k_closest_points_to_origin;

import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );

        for (int[] point : points) {
            maxHeap.offer(new int[]{point[0], point[1]});
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.toArray(new int[0][]);
    }
}
