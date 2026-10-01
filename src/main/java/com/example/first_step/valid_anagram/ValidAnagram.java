package com.example.first_step.valid_anagram;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise. An anagram is a word or phrase formed by rearranging the letters
 * of a different word or phrase, typically using all the original letters
 * exactly once.</p>
 *
 * <p>Даны две строки s и t. Вернуть true, если t — анаграмма s. Анаграмма —
 * это слово, полученное перестановкой букв другого слова, с использованием
 * всех исходных букв ровно по одному разу.</p>
 *
 * <p>Пример:<br>
 * Вход:  s = "anagram", t = "nagaram"<br>
 * Выход: true</p>
 *
 * <p>Пример:<br>
 * Вход:  s = "rat", t = "car"<br>
 * Выход: false</p>
 *
 * <p>Паттерн:<br>
 * Подсчёт частот через массив фиксированного размера (для строчных латинских
 * букв).</p>
 *
 * <p>Именование переменных:<br>
 * countFreq — массив на 26 элементов. Индекс — буква ('a' = 0, 'b' = 1, ...,
 * 'z' = 25). Значение — насколько частота буквы в s превышает частоту в t.
 * Если строки анаграммы, все элементы в итоге равны нулю. charS — текущая
 * буква из s. charT — текущая буква из t. freq — значение из массива
 * countFreq при проверке.</p>
 *
 * <p>Идея:<br>
 * Анаграмма означает, что обе строки состоят из одних и тех же букв
 * в одинаковом количестве. Порядок букв не важен. Значит, достаточно
 * сравнить частоты каждой буквы в двух строках. Если совпадают — анаграмма.
 * Используем массив на 26, а не сортировку: сортировка работает за
 * O(n log n), а подсчёт частот — за O(n).</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Быстрая проверка длин:
 *       <ul>
 *         <li>Если длины s и t не равны — анаграмма невозможна.
 *             Возвращаем false сразу.</li>
 *       </ul>
 *   </li>
 *   <li>Проход по обеим строкам одновременно:
 *       <ul>
 *         <li>Создаём массив countFreq на 26, изначально все нули.</li>
 *         <li>Идём по индексу index от 0 до длины строки:
 *             <ul>
 *               <li>Берём charS из s, увеличиваем countFreq[charS - 'a'].</li>
 *               <li>Берём charT из t, уменьшаем countFreq[charT - 'a'].</li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 *   <li>Проверка итогового состояния массива:
 *       <ul>
 *         <li>Идём по countFreq. Если все элементы равны нулю — каждая
 *             буква в s и t встречается одинаково часто, значит анаграмма.
 *             Возвращаем true.</li>
 *         <li>Если хотя бы один элемент не ноль — частоты разошлись.
 *             Возвращаем false.</li>
 *       </ul>
 *   </li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Проверка длин — обязательна и в самом начале. Если строки разной
 *       длины, они не могут быть анаграммами, даже если частоты какой-то
 *       буквы совпали. Это O(1) отсечение, экономит время на больших
 *       строках.</li>
 *   <li>Массив на 26 элементов — для строчных латинских букв ('a'–'z').
 *       Если в задаче разрешены заглавные, цифры или Unicode — размер
 *       массива нужно увеличить (например, до 128 или 256) или использовать
 *       HashMap. LeetCode в этой задаче гарантирует только строчные
 *       латинские буквы.</li>
 *   <li>Вычитание 'a' из символа даёт индекс от 0 до 25. Например,
 *       'a' - 'a' = 0, 'z' - 'a' = 25. Это работает потому, что коды
 *       символов в ASCII идут подряд.</li>
 *   <li>Инкремент для s и декремент для t в одном массиве. Так не нужно
 *       два массива и потом их сравнивать — достаточно проверить, что
 *       итоговый массив полностью нулевой.</li>
 *   <li>Проход по обеим строкам в одном цикле — длины уже равны (проверено
 *       выше), значит индексы совпадают, можно идти одновременно.</li>
 *   <li>Пустые строки — анаграммы друг друга. Длины равны (0 == 0), цикл
 *       не выполнится, массив останется нулевым, вернётся true.</li>
 *   <li>Строки из одной буквы — если буквы одинаковые, длины равны,
 *       countFreq обнулится, вернётся true. Если разные — countFreq
 *       не обнулится, вернётся false.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>s = "anagram", t = "nagaram" → true. Классическая анаграмма.</li>
 *   <li>s = "rat", t = "car" → false. Разные наборы букв.</li>
 *   <li>s = "a", t = "a" → true. Одна буква, совпадает.</li>
 *   <li>s = "a", t = "b" → false. Одна буква, разные.</li>
 *   <li>s = "", t = "" → true. Обе пустые.</li>
 *   <li>s = "a", t = "" → false. Разные длины.</li>
 *   <li>s = "aa", t = "a" → false. Разные длины, хотя одна буква.</li>
 *   <li>s = "abc", t = "cba" → true. Перестановка.</li>
 *   <li>s = "aacc", t = "ccac" → false. Частоты не совпадают.</li>
 *   <li>s = "listen", t = "silent" → true. Длинные анаграммы.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — один проход по строке длины n, плюс O(26) на проверку
 *       массива (константа). Итого O(n).<br>
 * Память: O(1) — массив на 26 элементов фиксированного размера, не зависит
 *         от длины строк.</p>
 */
public class ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagramMap(s, t));
        System.out.println(isAnagramInt26(s, t));
    }

    public static boolean isAnagramInt26(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] countFreq = new int[26];
        for (int index = 0; index < s.length(); index++) {
            char charS = s.charAt(index);
            char charT = t.charAt(index);
            countFreq[charS - 'a']++;
            countFreq[charT - 'a']--;
        }

        for (int freq : countFreq) {
            if (freq != 0) {
                return false;
            }
        }

        return true;
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

}
