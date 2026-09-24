package com.example.first_step.group_anagrams;

import java.util.*;


/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * strs[i] consists of lowercase English letters.
 * <p>
 * Дан массив строк strs. Нужно сгруппировать анаграммы вместе.
 * Вернуть список списков строк. Порядок групп и порядок строк внутри группы — любой.
 * Анаграмма — слово, полученное из другого перестановкой букв (все буквы используются ровно по разу).
 * Пример:
 * <p>
 * Вход: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * Выход: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
 * <p>
 * Заметка по паттерну
 * Паттерн: HashMap + уникальный ключ для группировки.
 * Ключевая идея:
 * Строим ключ, одинаковый для всех анаграмм.
 * Вариант 1: частотный массив int[26] → строка с разделителем — O(k).
 * Что важно запомнить:
 * Разделитель # в ключе обязателен: без него [1,11] и [11,1] склеятся в "111".
 * computeIfAbsent(key, k -> new ArrayList<>()).add(str) — стандартный приём.
 * new ArrayList<>(map.values()) — возврат без лишних проверок.
 * Сложность:
 * O(n × k) время
 * O(n × k) память.
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(groupAnagramsBySorting(strs));
        System.out.println(groupAnagramsByFrequency(strs));
    }

    public static List<List<String>> groupAnagramsByFrequency(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            char[] strChars = str.toCharArray();
            int[] frequency = new int[26];
            for (char strChar : strChars) {
                frequency[strChar - 'a']++;
            }
            StringBuilder keyBuilder = new StringBuilder();
            for (int freq : frequency) {
                keyBuilder.append(freq).append('#');
            }
            String key = keyBuilder.toString();
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(groups.values());
    }

    public static List<List<String>> groupAnagramsBySorting(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(groups.values());
    }
}
