package com.example.tenth_step.word_break;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    /**
     * Проверяет, можно ли разбить строку s на слова из словаря wordDict.
     * <p>
     * Классическое решение через одномерное динамическое программирование (1D DP).
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Создаётся массив dp длиной n + 1, где dp[i] означает, можно ли разбить
     *       первые i символов строки на слова из словаря.</li>
     *   <li>dp[0] = true, потому что пустую строку можно разбить (ничего не брать).</li>
     *   <li>Для каждого endIndex от 1 до n перебираются все возможные startIndex
     *       от 0 до endIndex-1. Если левая часть до startIndex уже разбита
     *       (dp[startIndex] == true) и подстрока s[startIndex..endIndex-1] есть
     *       в словаре, то dp[endIndex] = true.</li>
     *   <li>Возвращается dp[n].</li>
     * </ol>
     * <p>
     * Сложность:
     * <ul>
     *   <li>Время: O(n² × L), где n — длина строки, L — длина слова
     *       (из-за substring и проверки в HashSet).</li>
     *   <li>Память: O(n + размер словаря).</li>
     * </ul>
     *
     * @param s        входная строка, которую нужно разбить
     * @param wordDict список допустимых слов (может использоваться многократно)
     * @return true, если строку можно разбить на слова из словаря, иначе false
     * @see #wordBreakOptimized(String, List)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);

        for (int endIndex = 1; endIndex <= s.length(); endIndex++) {
            for (int startIndex = 0; startIndex < endIndex; startIndex++) {
                if (dp[startIndex] && set.contains(s.substring(startIndex, endIndex))) {
                    dp[endIndex] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    /**
     * Оптимизированная версия {@link #wordBreak(String, List)}.
     * <p>
     * Отличие от базовой версии: внутренний цикл ограничен длиной самого длинного
     * слова в словаре. Это позволяет не проверять подстроки, которые заведомо
     * не могут быть словом из словаря.
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Заранее вычисляется maxWordLength — длина самого длинного слова в словаре.</li>
     *   <li>Для каждого endIndex от 1 до n вычисляется нижняя граница startLimit
     *       как max(0, endIndex - maxWordLength).</li>
     *   <li>startIndex идёт вниз от endIndex-1 до startLimit, то есть перебираются
     *       только подстроки длиной не больше maxWordLength.</li>
     *   <li>Если dp[startIndex] == true и подстрока s[startIndex..endIndex-1]
     *       есть в словаре, то dp[endIndex] = true и внутренний цикл прерывается.</li>
     *   <li>Возвращается dp[n].</li>
     * </ol>
     * <p>
     * Преимущества:
     * <ul>
     *   <li>Внутренний цикл сокращается с O(n) до O(maxWordLength) итераций.</li>
     *   <li>На длинных строках с короткими словами даёт значительное ускорение.</li>
     *   <li>Обход startIndex в обратном порядке часто находит валидное разбиение
     *       быстрее и чаще срабатывает break.</li>
     * </ul>
     * <p>
     * Сложность:
     * <ul>
     *   <li>Время: O(n × maxWordLength × L) в худшем случае, на практике значительно
     *       быстрее базовой версии.</li>
     *   <li>Память: O(n + размер словаря).</li>
     * </ul>
     *
     * @param s        входная строка, которую нужно разбить
     * @param wordDict список допустимых слов (может использоваться многократно)
     * @return true, если строку можно разбить на слова из словаря, иначе false
     * @see #wordBreak(String, List)
     */
    public boolean wordBreakOptimized(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        int maxWordLength = 0;
        for (String word : wordDict) {
            maxWordLength = Math.max(maxWordLength, word.length());
        }

        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;

        for (int endIndex = 1; endIndex <= length; endIndex++) {
            int startLimit = Math.max(0, endIndex - maxWordLength);
            for (int startIndex = endIndex - 1; startIndex >= startLimit; startIndex--) {
                if (dp[startIndex] && set.contains(s.substring(startIndex, endIndex))) {
                    dp[endIndex] = true;
                    break;
                }
            }
        }
        return dp[length];
    }
}
