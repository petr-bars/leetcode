package com.example.tenth_step.longest_increasing_subsequence;

import java.util.Arrays;

public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int length = nums.length;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int maxLength = 1;
        for (int index = 1; index < length; index++) {
            for (int previousIndex = 0; previousIndex < index; previousIndex++) {
                if (nums[previousIndex] < nums[index]) {
                    int previousSeq = dp[previousIndex] + 1;
                    int currentSeq = dp[index];
                    dp[index] = Math.max(currentSeq, previousSeq);
                }
            }
            maxLength = Math.max(maxLength, dp[index]);
        }

        return maxLength;
    }

    public int lengthOfLISPS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // tails[i] — минимальный возможный хвост подпоследовательности длины i+1
        int[] tails = new int[nums.length];
        int size = 0; // текущая длина массива tails

        for (int number : nums) {
            // Бинарный поиск: ищем первый элемент в tails[0..size-1], который >= number
            int left = 0;
            int right = size;

            while (left < right) {
                int middle = left + (right - left) / 2;
                if (tails[middle] < number) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }

            tails[left] = number; // заменяем или добавляем

            if (left == size) {
                size++; // значит, number больше всех — удлиняем LIS
            }
        }

        return size;
    }
}
