package com.example.tenth_step.house_robber_II;

public class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int case1 = robLinear(nums, 0, nums.length - 2); // исключаем последний
        int case2 = robLinear(nums, 1, nums.length - 1); // исключаем первый

        return Math.max(case1, case2);
    }

    private int robLinear(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return nums[start];
        }
        if (end - start == 1) {
            return Math.max(nums[start], nums[end]);
        }

        int prev2 = nums[start];
        int prev1 = Math.max(nums[start], nums[start + 1]);

        for (int index = start + 2; index <= end; index++) {
            int current = Math.max(prev1, prev2 + nums[index]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
