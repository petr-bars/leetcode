package com.example.seventh_step.last_stone_weight;

/**
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * <p>
 * We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together.
 * Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
 * <p>
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 * <p>
 * Return the weight of the last remaining stone. If there are no stones left, return 0.
 */
public class LastStoneWeight {
    public static void main(String[] args) {
        Solution stones = new Solution();
        int result = stones.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1});
        System.out.println(result);
    }


}
