package com.example.first_step.valid_anagram;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 * <p>
 * Даны две строки s и t. Нужно вернуть true, если t является анаграммой s, иначе false.
 * Анаграмма — это слово или фраза, образованная из другого слова путём перестановки букв,
 * с использованием всех исходных букв ровно по одному разу.
 * <p>
 * Примеры:
 * s = "anagram", t = "nagaram" → true (те же буквы, разный порядок)
 * s = "rat", t = "car" → false (разные буквы)
 * s = "listen", t = "silent" → true
 * <p>
 * Сложность
 * Время: O(n)
 * Память: O(1)
 * <p>
 * Заметка по паттерну
 * Паттерн: частотный массив int[26] для строчных латинских букв.
 * Ключевая идея:
 * Если длины разные — не анаграмма.
 * Идём по обеим строкам одним циклом: для s прибавляем, для t вычитаем.
 * Если все счётчики обнулились — анаграмма.
 */
public class ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagramMap(s, t));
        System.out.println(isAnagramInt261(s, t));
    }


    public static boolean isAnagramMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (char leftCh : s.toCharArray()) {
            // Берем букву из первой строки, делаем ее ключом значение если null ставим 1 иначе делаем + 1
            map.merge(leftCh, 1, Integer::sum);
        }

        for (char rightCh : t.toCharArray()) {
            if (!map.containsKey(rightCh)) {
                return false;
            }
            //Уменьшаем инкремент т.к у нас нашлась буква по такому ключу
            map.put(rightCh, map.get(rightCh) - 1);
            if (map.get(rightCh) == 0) {
                map.remove(rightCh);
            }
        }
        return map.isEmpty();
    }

    public static boolean isAnagramInt261(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];

        for (int index = 0; index < s.length(); index++) {
            // Берем букву из первой строки, превращаем в индекс и прибавляем 1
            count[s.charAt(index) - 'a']++;

            // Берем букву из второй строки, превращаем в индекс и вычитаем 1
            count[t.charAt(index) - 'a']--;
        }

        // Проверяем: если после всех операций в массиве остались не нули — не анаграмма
        for (int index : count) {
            if (index != 0) {
                return false;
            }
        }
        return true;
    }
}
