package com.example.second_step.longest_substring_without_repeating;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without duplicate characters.
 * <p>
 * Дана строка s. Найти длину самой длинной подстроки без повторяющихся символов.
 * Пример:
 * Вход:  s = "abcabcbb"
 * Выход: 3
 * Пояснение: "abc", длина 3.
 * Идея
 * Окно [left, right] — текущая подстрока без повторов. right расширяет окно вправо.
 * Если новый символ уже внутри окна (его прошлая позиция >= left), прыгаем left за этой позицией. Иначе оставляем.
 * На каждом шаге обновляем максимум длины.
 * lastPos[c] — где символ встречался в последний раз. Проверка lastPos[c] >= left = «символ внутри окна».
 * Сложность
 * Время: O(n) — один проход, right двигается только вперёд, left только прыгает вперёд.
 * Память: O(1) — массив на 256 (фиксированный алфавит).
 */
public class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        String s = "abcabcbb";

        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring256(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        Set<Character> characterSet = new HashSet<>();
        int maxLength = 0;

        while (right < chars.length) {
            while (characterSet.contains(chars[right])) {
                characterSet.remove(chars[left]);
                left++;
            }
            characterSet.add(chars[right]);
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }

    /**
     * Суть подхода
     * Вместо того чтобы хранить множество символов и удалять их по одному при дубликате, мы храним последний индекс,
     * на котором каждый символ встречался. Тогда, когда мы встречаем повтор,
     * мы можем сразу переместить левый указатель на позицию после предыдущего вхождения этого символа,
     * не удаляя по одному все промежуточные символы.
     * <p>
     * Как это работает (пошагово)
     * Создаём массив (или HashMap) для хранения последней позиции каждого символа.
     * Размер массива — 128 или 256, если мы работаем с ASCII/расширенным набором (для всех символов достаточно 256).
     * Индекс — это код символа (например, 'a' = 97).
     * Значение — индекс, на котором этот символ встретился в последний раз.
     * Изначально все значения = -1 (или 0, если мы будем хранить 1-based индексы, но проще -1).
     * <p>
     * Устанавливаем два указателя:
     * left — начало текущего окна (изначально 0).
     * right — текущий обрабатываемый символ (проходим по строке от 0 до конца).
     * maxLength — максимальная длина, найденная до сих пор.
     * В цикле по right (от 0 до n-1):
     * Берём символ c = s[right].
     * Смотрим в массиве lastPos[c]: если это значение >= left, значит, символ уже встречался внутри текущего окна
     * (мы его ещё не «выкинули»).
     * Если lastPos[c] >= left, то мы должны переместить левый указатель на lastPos[c] + 1,
     * чтобы исключить предыдущее вхождение из окна.
     * Затем обновляем lastPos[c] = right (теперь последняя позиция этого символа — текущая).
     * Вычисляем текущую длину окна: right - left + 1.
     * Обновляем maxLength, если текущая длина больше.
     * В конце возвращаем maxLength.
     */
    public static int lengthOfLongestSubstring256(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int[] lastPos = new int[256]; // для ASCII (если нужны все символы, бери 256)
        Arrays.fill(lastPos, -1); // -1 означает, что символ ещё не встречался

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right);

            if (lastPos[index] >= left) {
                left = lastPos[index] + 1;
            }

            lastPos[index] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
