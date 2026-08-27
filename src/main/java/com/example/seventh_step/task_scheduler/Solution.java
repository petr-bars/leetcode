package com.example.seventh_step.task_scheduler;

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        int maxCount = 0;

        for (char task : tasks) {
            freq[task - 'A']++;
        }

        for (int count : freq) {
            if (count > maxFreq) {
                maxFreq = count;
            }
        }

        for (int count : freq) {
            if (count == maxFreq) {
                maxCount++;
            }
        }

        int time = (maxFreq - 1) * (n + 1) + maxCount;

        return Math.max(time, tasks.length);
    }
}
