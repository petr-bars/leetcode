package com.example.tenth_step.house_robber;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(solution.rob(nums));
    }
}
