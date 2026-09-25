package com.example.second_step.longest_repeating_character_replacement;

/**
 * You are given a string s and an integer k.
 * You can choose any character of the string and change it to any other uppercase English character.
 * You can perform this operation at most k times.
 * Return the length of the longest substring containing the same letter you can get after performing
 * the above operations.
 * <p>
 * Дана строка s и число k. Можно заменить не более k символов на любые другие. Найти длину самой длинной подстроки,
 * которую можно получить, если все символы в ней сделать одинаковыми.
 * Пример:
 * Вход:  s = "ABAB", k = 2
 * Выход: 4
 * Пояснение: заменяем две 'A' на 'B' → "BBBB", длина 4.
 * Паттерн
 * Sliding window (variable) — окно переменного размера + подсчёт частот внутри окна.
 * Идея
 * Держишь окно [left, right]. Внутри окна считаешь, сколько раз встречается каждый символ.
 * Ключевое наблюдение: чтобы сделать окно «все символы одинаковые», нужно заменить все символы, кроме самого частого.
 * Количество замен = длина окна − частота самого частого символа.
 * Если это число ≤ k — окно валидное, можно расширять.
 * Если > k — замен не хватает, окно надо сжать слева.
 * Что нужно
 * right — расширяет окно.
 * left — сжимает, когда замен стало больше k.
 * int[26] — частоты символов внутри окна.
 * maxFreq — частота самого частого символа в окне.
 * Формула
 * left = 0, maxFreq = 0, best = 0
 * int[26] count
 * для right от 0 до n-1:
 * count[s[right]]++
 * maxFreq = max(maxFreq, count[s[right]])
 * если (right - left + 1) - maxFreq > k:
 * count[s[left]]--
 * left++
 * best = max(best, right - left + 1)
 * вернуть best
 * (right - left + 1) — длина окна. длина − maxFreq — сколько замен нужно.
 * Тонкость с maxFreq
 * maxFreq не уменьшается при сжатии окна. Это не баг, а трюк: если окно сжимается, maxFreq мог бы стать меньше,
 * но нам это не важно. Мы ищем максимальную длину, и старое maxFreq даёт верхнюю границу.
 * Окно никогда не сжимается ниже лучшего найденного размера — поэтому ответ не теряется.
 * Проще говоря: maxFreq только растёт.
 * Это позволяет не пересчитывать максимум частот каждый раз (иначе было бы O(26n) или O(n²)).
 * Сложность
 * Время: O(n) — right и left идут только вперёд.
 * Память: O(1) — массив на 26.
 */
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        String s = "AABABB";
        int k = 1;

        System.out.println(characterReplacement(s, k));
    }

    public static int characterReplacement(String s, int k) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int[] freq = new int[26];
        int maxFreq = 0;
        int best = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char value = s.charAt(right);
            freq[value - 'A']++;
            maxFreq = Math.max(maxFreq, freq[value - 'A']);
            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            best = Math.max(best, right - left + 1);
        }

        return best;
    }
}
