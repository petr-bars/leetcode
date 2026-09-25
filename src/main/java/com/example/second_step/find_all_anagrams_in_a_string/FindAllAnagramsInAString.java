package com.example.second_step.find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 * <p>
 * Даны две строки s и p. Найти все начальные индексы подстрок в s, которые являются анаграммами p.
 * Анаграмма — перестановка букв. abc и bca — анаграммы.
 * Пример:
 * Вход:  s = "cbaebabacd", p = "abc"
 * Выход: [0, 6]
 * Пояснение:
 * индекс 0 → "cba" — анаграмма "abc"
 * индекс 6 → "bac" — анаграмма "abc"
 * Паттерн
 * Fixed sliding window — размер окна фиксирован и равен длине p.
 * Анаграмма = тот же набор букв с теми же частотами, порядок не важен. Значит, ищем все окна длины p.length(),
 * у которых частоты совпадают с частотами p.
 * Считаем частоты p — эталон. Дальше сдвигаем окно по s: добавили букву справа, убрали слева. Сравниваем частоты.
 * Совпало — записали индекс.
 * Окно всегда одной длины. Не растягиваем, не сжимаем — только сдвигаем.
 * Формула
 * m = p.length()
 * freqP = частоты p
 * freqW = частоты s[0..m-1]
 * если freqP == freqW: result.add(0)
 * для right от m до n-1:
 * freqW[s[right]]++
 * freqW[s[right - m]]--
 * если freqP == freqW:
 * result.add(right - m + 1)
 * Сложность
 * Время: O(n) — скольжение окна, но сравнение двух массивов частот O(26) или O(m).
 * Итого O(26n) = O(n) для фиксированного алфавита.
 * Память: O(1) — два массива на 26 (или O(m) для произвольного алфавита).
 */
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";

        System.out.println(findAnagrams(s, p));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int lengthS = s.length();
        int lengthP = p.length();

        if (lengthS < lengthP) {
            return result;
        }

        // Заполняем шаблон на который будем ориентироваться
        int[] freqP = new int[26];
        for (char symbol : p.toCharArray()) {
            freqP[symbol - 'a']++;
        }

        // Инициализируем первое фиксированное окно
        int[] freqW = new int[26];
        for (int index = 0; index < lengthP; index++) {
            freqW[s.charAt(index) - 'a']++;
        }

        // Если совпали значит начальный индекс 0
        if (Arrays.equals(freqP, freqW)) {
            result.add(0);
        }

        for (int right = lengthP; right < lengthS; right++) {
            // Инкримент текущего символа
            freqW[s.charAt(right) - 'a']++;
            // Дикримент лишнего левого которое выпадает из размера окна
            freqW[s.charAt(right - lengthP) - 'a']--;

            if (Arrays.equals(freqP, freqW)) {
                result.add(right - lengthP + 1);
            }
        }
        return result;
    }
}
