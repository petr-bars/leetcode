package com.example.tenth_step.coin_change;

import java.util.Arrays;

public class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int sumOfCoin = coin; sumOfCoin <= amount; sumOfCoin++) {
                if (dp[sumOfCoin - coin] + 1 < dp[sumOfCoin]) {
                    dp[sumOfCoin] = dp[sumOfCoin - coin] + 1;
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
