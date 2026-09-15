package com.example.tenth_step.coin_change_II;

public class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int index = coin; index <= amount; index++) {
                dp[index] = dp[index] + dp[index - coin];
            }
        }
        return dp[amount];
    }
}
