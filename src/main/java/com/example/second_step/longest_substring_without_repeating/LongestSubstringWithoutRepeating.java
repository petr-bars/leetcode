package com.example.second_step.longest_substring_without_repeating;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Given a string s, find the length of the longest substring without
 * duplicate characters.</p>
 *
 * <p>Дана строка s. Найти длину самой длинной подстроки без повторяющихся
 * символов.</p>
 *
 * <p>Пример:<br>
 * Вход:  s = "abcabcbb"<br>
 * Выход: 3<br>
 * Пояснение: "abc", длина 3.</p>
 *
 * <p>Пример:<br>
 * Вход:  s = "bbbbb"<br>
 * Выход: 1<br>
 * Пояснение: "b".</p>
 *
 * <p>Пример:<br>
 * Вход:  s = "pwwkew"<br>
 * Выход: 3<br>
 * Пояснение: "wke".</p>
 *
 * <p>Паттерн:<br>
 * Sliding window (переменного размера) + массив последних позиций.</p>
 *
 * <p>Именование переменных:<br>
 * lastPos — массив на 256 элементов. Индекс — код символа, значение —
 * последняя позиция, где символ встречался. Изначально все -1. left —
 * левая граница окна, right — правая. maxLength — текущий максимум длины
 * подстроки без повторов. currentSymbol — текущий символ на позиции right.</p>
 *
 * <p>Идея:<br>
 * Ищем кусок строки, в котором все символы разные. Идём слева направо
 * одним проходом, держа окно [left, right]. Внутри окна все символы
 * уникальны — это инвариант, который мы поддерживаем. right идёт вперёд
 * и пытается расширить окно. Если новый символ уже есть внутри окна —
 * расширять нельзя, будет повтор. Тогда прыгаем left за прошлую позицию
 * этого символа. Прыжок именно за позицию, а не на неё: сам повторяющийся
 * символ должен остаться в окне, а всё что было до него — выкинуть.</p>
 *
 * <p>Формула:</p>
 * <ul>
 *   <li>lastPos заполнить -1.</li>
 *   <li>right: 0 → n-1.</li>
 *   <li>Если lastPos[currentSymbol] &gt;= left →
 *       left = lastPos[currentSymbol] + 1.</li>
 *   <li>lastPos[currentSymbol] = right.</li>
 *   <li>maxLength = max(maxLength, right - left + 1).</li>
 * </ul>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Инициализация:
 *       <ul>
 *         <li>maxLength = 0.</li>
 *         <li>lastPos = new int[256], заполнить -1. Значение -1 означает
 *             «символ ещё не встречался».</li>
 *         <li>left = 0.</li>
 *       </ul>
 *   </li>
 *   <li>Проход, right от 0 до n-1:
 *       <ul>
 *         <li>currentSymbol = s.charAt(right).</li>
 *         <li>Проверяем: lastPos[currentSymbol] &gt;= left?
 *             <ul>
 *               <li>Да → символ уже внутри окна, повтор. Прыгаем left
 *                   за прошлую позицию: left = lastPos[currentSymbol] + 1.</li>
 *               <li>Нет → символ либо не встречался, либо был до окна.
 *                   Ничего не делаем.</li>
 *             </ul>
 *         </li>
 *         <li>Записываем новую позицию: lastPos[currentSymbol] = right.</li>
 *         <li>Обновляем максимум: maxLength = max(maxLength,
 *             right - left + 1).</li>
 *       </ul>
 *   </li>
 *   <li>Возвращаем maxLength.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Проверка именно lastPos[currentSymbol] &gt;= left, а не != -1.
 *       Символ мог встречаться раньше, но уже выпасть из окна. Тогда это
 *       не повтор, и двигать left не надо. Пример: s = "abba", right=3
 *       (символ 'a'), lastPos['a']=0, left=2. Условие 0 &gt;= 2 ложно —
 *       'a' был до окна, не мешает. Если бы проверяли != -1, сдвинули бы
 *       left неправильно.</li>
 *   <li>lastPos заполняется -1 обязательно. Без этого массив инициализирован
 *       нулями, и проверка lastPos &gt;= left будет ложно срабатывать
 *       для символов, которых ещё не видели. Пример: s = "abc", right=1,
 *       lastPos['b']=0 (по умолчанию), 0 &gt;= 0 истинно — сдвинем left,
 *       хотя не должны.</li>
 *   <li>left двигается только вперёд, никогда назад. Если lastPos меньше
 *       текущего left, сдвигать не надо — символ уже не в окне.</li>
 *   <li>Обновление maxLength идёт после возможного сдвига left. Порядок
 *       важен: если считать длину до сдвига, получим завышенное значение
 *       с повтором внутри.</li>
 *   <li>Проверка выполняется по коду символа, а не по самому char.
 *       lastPos[s.charAt(right)] — char неявно приводится к int, что даёт
 *       его ASCII-код. Для Unicode свыше 255 массив на 256 не подойдёт,
 *       но LeetCode гарантирует английские буквы.</li>
 *   <li>Прыжок left = lastPos + 1, а не lastPos. Мы хотим оставить сам
 *       повторяющийся символ в окне (он только что добавлен справа),
 *       выкинуть всё до него. Если сделать left = lastPos, символ останется
 *       дважды — повторится на позиции lastPos и на позиции right.</li>
 *   <li>maxLength = max(maxLength, right - left + 1), а не просто
 *       right - left + 1. Мы ищем максимум по всем окнам, а не последнее
 *       окно. Пример: s = "abckkkkk", последнее окно "k" длиной 1,
 *       а максимум был 3 ("abc").</li>
 *   <li>Пустая строка — цикл не выполнится, вернётся 0.</li>
 *   <li>Строка из одного символа — цикл один раз, left не сдвигается,
 *       maxLength = 1.</li>
 *   <li>Все символы одинаковые ("bbbb") — каждый раз повтор, left прыгает,
 *       maxLength остаётся 1.</li>
 *   <li>Все символы уникальны ("abcd") — ни одного повтора, maxLength = n.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>s = "abcabcbb" → 3. Подстрока "abc".</li>
 *   <li>s = "bbbbb" → 1. Все одинаковые.</li>
 *   <li>s = "pwwkew" → 3. Подстрока "wke".</li>
 *   <li>s = "" → 0. Пустая строка.</li>
 *   <li>s = "a" → 1. Один символ.</li>
 *   <li>s = "ab" → 2. Все уникальные.</li>
 *   <li>s = "aa" → 1. Повтор.</li>
 *   <li>s = "abba" → 2. Окно после 'b' — "b", потом "ba".</li>
 *   <li>s = "dvdf" → 3. Подстрока "vdf".</li>
 *   <li>s = "tmmzuxt" → 5. Подстрока "mzuxt".</li>
 *   <li>s = " " → 1. Пробел — тоже символ.</li>
 *   <li>s = "!@#$%" → 5. Спецсимволы в диапазоне 0-255.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — один проход, right двигается только вперёд, left только
 *       прыгает вперёд. Каждый символ обрабатывается один раз.<br>
 * Память: O(1) — массив на 256 фиксированного размера (для ASCII/Latin-1).</p>
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
