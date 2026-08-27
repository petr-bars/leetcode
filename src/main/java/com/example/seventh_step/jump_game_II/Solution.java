package com.example.seventh_step.jump_game_II;

public class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentJumpedEnd = 0;
        int farthest = 0;

        for (int index = 0; index < nums.length - 1; index++) {
            farthest = Math.max(farthest, index + nums[index]);
            if (index == currentJumpedEnd) {
                jumps++;
                currentJumpedEnd = farthest;
            }
        }
        return jumps;
    }
}
