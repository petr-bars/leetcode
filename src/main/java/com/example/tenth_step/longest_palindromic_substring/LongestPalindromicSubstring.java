package com.example.tenth_step.longest_palindromic_substring;

/**
 * Given a string s, return the longest palindromic substring in s.
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "babad";
//        System.out.println(solution.longestPalindrome2D(s));
        System.out.println(solution.longestPalindrome(s));
    }
}
