package com.example.tenth_step.climbing_stairs;

public class Solution {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        int oneStepBefore = 1;
        int twoStepsBefore = 1;

        for (int index = 2; index <= n; index++) {
            int current = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = current;
        }

        return oneStepBefore;
    }
}
