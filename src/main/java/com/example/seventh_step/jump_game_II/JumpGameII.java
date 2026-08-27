package com.example.seventh_step.jump_game_II;

/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
 * <p>
 * Each element nums[i] represents the maximum length of a forward jump from index i.
 * In other words, if you are at index i, you can jump to any index (i + j) where:
 * <p>
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach index n - 1.
 * The test cases are generated such that you can reach index n - 1.
 */
public class JumpGameII {
    public static void main(String[] args) {
        Solution jumpGame = new Solution();
        System.out.println(jumpGame.jump(new int[]{2, 3, 1, 1, 4}));
    }
}
