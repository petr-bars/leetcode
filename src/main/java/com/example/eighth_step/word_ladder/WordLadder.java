package com.example.eighth_step.word_ladder;

import java.util.List;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
 * beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return the number of words in the shortest transformation sequence from beginWord to endWord,
 * or 0 if no such sequence exists.
 */
public class WordLadder {
    public static void main(String[] args) {
        Solution ladder = new Solution();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        int result = ladder.ladderLength(beginWord, endWord, wordList);
        System.out.println(result);
    }
}
