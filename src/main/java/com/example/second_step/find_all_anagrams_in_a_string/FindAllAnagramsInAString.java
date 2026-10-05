package com.example.second_step.find_all_anagrams_in_a_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Given two strings s and p, return an array of all the start indices of
 * p's anagrams in s. You may return the answer in any order.</p>
 *
 * <p>Даны две строки s и p. Найти все начальные индексы подстрок в s,
 * которые являются анаграммами p. Порядок ответа не важен.</p>
 *
 * <p>Пример:<br>
 * Вход:  s = "cbaebabacd", p = "abc"<br>
 * Выход: [0,6]</p>
 *
 * <p>Паттерн:<br>
 * Fixed sliding window. Ищем все окна длины p.length(), у которых частоты
 * символов совпадают с частотами p.</p>
 *
 * <p>Идея:<br>
 * Анаграмма — одинаковый набор букв с одинаковыми частотами. Считаем эталон
 * по p. Первое окно — первые m символов s. Дальше скользим: добавили символ
 * справа, убрали слева, сравнили с эталоном.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>m = длина p.</li>
 *   <li>freqP = частоты символов в p.</li>
 *   <li>freqW = частоты символов первого окна s[0..m-1].</li>
 *   <li>Если совпало — добавляем индекс 0.</li>
 *   <li>На каждом сдвиге вправо: добавляем новый символ справа, убираем
 *       старый слева.</li>
 *   <li>Если совпало — добавляем начало текущего окна (right - m + 1).</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n).<br>
 * Память: O(1).</p>
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
