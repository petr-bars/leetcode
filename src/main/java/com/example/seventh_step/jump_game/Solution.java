package com.example.seventh_step.jump_game;

public class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int index = 0; index < nums.length; index++) {
            if (index > maxReach) {
                return false;
            }

            maxReach = Math.max(maxReach, index + nums[index]);

            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
