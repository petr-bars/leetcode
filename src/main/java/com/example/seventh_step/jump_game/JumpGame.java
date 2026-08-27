package com.example.seventh_step.jump_game;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 */
public class JumpGame {
    public static void main(String[] args) {
        Solution jump = new Solution();
        System.out.println(jump.canJump(new int[]{3, 2, 0, 0, 4}));
    }
}
