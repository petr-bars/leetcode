package com.example.seventh_step.last_stone_weight;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        while (maxHeap.size() > 1) {
            int first = maxHeap.poll();
            int second = maxHeap.poll();
            if (first != second) {
                maxHeap.offer(first - second);
            }
        }

        return !maxHeap.isEmpty() ? maxHeap.poll() : 0;
    }
}
