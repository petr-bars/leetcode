package com.example.tenth_step.coin_change_II;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing
 * a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * The final answer is guaranteed to fit into a signed 32-bit integer.
 */
public class CoinChangeII {
    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1, 2, 5};
        Solution solution = new Solution();
        System.out.println(solution.change(amount, coins));
    }
}
