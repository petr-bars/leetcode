package com.example.tenth_step.word_break;

import java.util.List;

/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
public class WordBreak {
    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");
        Solution solution = new Solution();
        System.out.println(solution.wordBreak(s, wordDict));
        System.out.println(solution.wordBreakOptimized(s, wordDict));
    }
}
