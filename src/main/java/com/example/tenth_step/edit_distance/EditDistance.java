package com.example.tenth_step.edit_distance;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 */
public class EditDistance {
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        Solution solution = new Solution();
        System.out.println(solution.minDistance(word1, word2));
        System.out.println(solution.minDistanceOptimized(word1, word2));
    }
}
