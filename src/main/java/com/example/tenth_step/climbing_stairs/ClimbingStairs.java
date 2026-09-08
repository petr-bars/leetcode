package com.example.tenth_step.climbing_stairs;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 * <p>
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        System.out.println(solution.climbStairs(n));
    }
}
