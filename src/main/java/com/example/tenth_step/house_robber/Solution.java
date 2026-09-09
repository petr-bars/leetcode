package com.example.tenth_step.house_robber;

public class Solution {

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1){
            return nums[0];
        }

        int prev1 = Math.max(nums[0], nums[1]);
        int prev2 = nums[0];

        for (int index = 2; index < nums.length; index++) {
            int current =  Math.max(prev1, prev2 + nums[index]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}
