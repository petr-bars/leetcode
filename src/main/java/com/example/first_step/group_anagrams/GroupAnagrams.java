package com.example.first_step.group_anagrams;

import java.util.*;


/**
 * <p>Given an array of strings strs, group the anagrams together. You can
 * return the answer in any order. An anagram is a word or phrase formed by
 * rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.</p>
 *
 * <p>Дан массив строк strs. Сгруппировать анаграммы вместе. Порядок групп
 * и порядок строк внутри группы не важен. Анаграмма — слово, полученное
 * перестановкой букв другого слова, с использованием всех исходных букв
 * ровно по одному разу.</p>
 *
 * <p>Пример:<br>
 * Вход:  strs = ["eat","tea","tan","ate","nat","bat"]<br>
 * Выход: [["bat"],["nat","tan"],["ate","eat","tea"]]</p>
 *
 * <p>Пример:<br>
 * Вход:  strs = [""]<br>
 * Выход: [[""]]</p>
 *
 * <p>Пример:<br>
 * Вход:  strs = ["a"]<br>
 * Выход: [["a"]]</p>
 *
 * <p>Паттерн:<br>
 * HashMap с ключом-подписью. Подпись — строка частот, одинаковая для всех
 * анаграмм одной группы.</p>
 *
 * <p>Именование переменных:<br>
 * anagramsByKey — HashMap, где ключ — подпись анаграммы, значение — список
 * строк с этой подписью. str — текущая строка из массива. strSymbols —
 * массив символов текущей строки. freqCounter — массив на 26, частоты букв
 * текущей строки. ch — текущий символ в цикле по строке. freq — значение
 * из freqCounter при построении подписи. stringBuilder — накопитель
 * подписи. uniqueKey — готовая подпись, ключ в anagramsByKey.</p>
 *
 * <p>Идея:<br>
 * Анаграммы — это строки с одинаковым набором букв и одинаковыми частотами.
 * Значит, если для каждой строки построить «подпись» из частот букв, то
 * у всех анаграмм одной группы подпись совпадёт. Используем эту подпись
 * как ключ в HashMap: значение — список строк с такой подписью. Проходим
 * по массиву один раз, для каждой строки считаем подпись, добавляем строку
 * в соответствующую группу.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Создаём пустую HashMap anagramsByKey: ключ — подпись, значение —
 *       список строк.</li>
 *   <li>Идём по массиву strs, берём каждую строку str:
 *       <ul>
 *         <li>Превращаем str в массив символов strSymbols.</li>
 *         <li>Создаём массив freqCounter на 26, изначально нули.</li>
 *         <li>Идём по strSymbols: для каждого ch увеличиваем
 *             freqCounter[ch - 'a'].</li>
 *         <li>Строим подпись: идём по freqCounter, для каждого freq
 *             добавляем в stringBuilder значение freq и разделитель '#'.</li>
 *         <li>Превращаем stringBuilder в строку uniqueKey.</li>
 *         <li>Через computeIfAbsent получаем список для ключа uniqueKey
 *             (создаётся пустой, если ключа ещё нет) и добавляем в него str.</li>
 *       </ul>
 *   </li>
 *   <li>После цикла возвращаем new ArrayList<>(anagramsByKey.values()).
 *       Это список всех групп, порядок не важен.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Разделитель '#' между значениями частот обязателен. Без него
 *       подписи разных наборов частот могут совпасть. Пример: частоты
 *       [1, 11, 0, ...] дадут строку "1110...", и частоты [11, 1, 0, ...]
 *       дадут ту же "1110...". С '#' это "1#11#0#..." и "11#1#0#..." —
 *       разные подписи. Разделитель решает проблему.</li>
 *   <li>Подпись строится по всем 26 позициям, включая нули. Это нужно,
 *       чтобы у строк "ab" и "a" были разные подписи. Если пропускать
 *       нули, "ab" даст "1#1" и "a" тоже даст "1#1" — коллизия.</li>
 *   <li>computeIfAbsent заменяет связку containsKey + get + put. Если
 *       ключа нет — создаёт новый ArrayList и кладёт его в мапу. Если
 *       ключ есть — возвращает существующий список. В обоих случаях
 *       возвращённый список готов принять элемент, поэтому вызов .add(str)
 *       работает без ветвлений.</li>
 *   <li>new ArrayList<>(anagramsByKey.values()) — потому что values()
 *       возвращает view, а не отдельную коллекцию. LeetCode ожидает List,
 *       поэтому оборачиваем в новый ArrayList.</li>
 *   <li>Массив на 26 — для строчных латинских букв. Если бы были заглавные
 *       или другие символы, размер нужно увеличить или использовать Map
 *       вместо массива.</li>
 *   <li>Пустой массив strs — цикл не выполнится, мапа пустая, вернётся
 *       пустой список.</li>
 *   <li>Массив из одного элемента — цикл выполнится один раз, создастся
 *       одна группа с этим элементом.</li>
 *   <li>Все строки анаграммы друг друга — все попадут в одну группу.</li>
 *   <li>Все строки уникальны (не анаграммы) — каждая станет отдельной
 *       группой из одного элемента.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>strs = ["eat","tea","tan","ate","nat","bat"] →
 *       три группы: ["eat","tea","ate"], ["tan","nat"], ["bat"].</li>
 *   <li>strs = [""] → одна группа [""].</li>
 *   <li>strs = ["a"] → одна группа ["a"].</li>
 *   <li>strs = ["","",""] → одна группа ["","",""] — три пустые строки
 *       анаграммы друг друга.</li>
 *   <li>strs = ["ab","ba","abc","cba","bac"] → две группы:
 *       ["ab","ba"], ["abc","cba","bac"].</li>
 *   <li>strs = ["a","b","c"] → три группы по одному элементу.</li>
 *   <li>strs = ["aaa","aaa"] → одна группа ["aaa","aaa"].</li>
 *   <li>строки разной длины не могут быть анаграммами — попадут
 *       в разные группы автоматически.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n * k), где n — число строк, k — средняя длина строки.
 *       На каждую строку: O(k) на подсчёт частот + O(26) на построение
 *       подписи + O(1) в среднем на операции с HashMap. Итого
 *       O(n * (k + 26)) = O(n * k) для фиксированного алфавита.<br>
 * Память: O(n * k) — все строки хранятся в группах, плюс ключи подписей
 *         длиной O(26) на каждую уникальную группу.</p>
 *
 * <p>Альтернативный подход (сортировка):<br>
 * Вместо подсчёта частот можно отсортировать буквы каждой строки и
 * использовать отсортированную строку как ключ. Сложность такого решения
 * — O(n * k log k) из-за сортировки. Асимптотически это медленнее, чем
 * O(n * k) у подсчёта частот. Однако на коротких строках разница почти
 * незаметна: Arrays.sort на маленьких массивах примитивов очень
 * оптимизирован, а подсчёт частот создаёт больше объектов (int[26],
 * StringBuilder). Итог: на длинных строках выигрывает подсчёт частот,
 * на коротких — примерно одинаково.</p>
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(groupAnagramsBySorting(strs));
        System.out.println(groupAnagramsByFrequency(strs));
    }

    public static List<List<String>> groupAnagramsByFrequency(String[] strs) {
        Map<String, List<String>> anagramsByKey = new HashMap<>();
        for (String str : strs) {
            char[] strSymbols = str.toCharArray();
            int[] freqCounter = new int[26];
            for (char ch : strSymbols) {
                freqCounter[ch - 'a']++;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (int freq : freqCounter) {
                stringBuilder.append(freq).append('#');
            }

            String uniqueKey = stringBuilder.toString();
            anagramsByKey.computeIfAbsent(uniqueKey, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(anagramsByKey.values());
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
