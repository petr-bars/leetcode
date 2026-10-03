package com.example.second_step.valid_palindrome;

/**
 * <p>A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters and removing all non-alphanumeric characters, it reads
 * the same forward and backward. Alphanumeric characters include letters and
 * numbers.</p>
 *
 * <p>Given a string s, return true if it is a palindrome, or false otherwise.</p>
 *
 * <p>Дана строка s. Вернуть true, если она является палиндромом, и false
 * иначе. Учитываются только буквы и цифры, регистр не важен. Все остальные
 * символы (пробелы, знаки препинания) игнорируются.</p>
 *
 * <p>Пример:<br>
 * Вход:  s = "A man, a plan, a canal: Panama"<br>
 * Выход: true<br>
 * Пояснение: если убрать всё кроме букв и привести к нижнему регистру,
 * получится "amanaplanacanalpanama" — палиндром.</p>
 *
 * <p>Пример:<br>
 * Вход:  s = "race a car"<br>
 * Выход: false<br>
 * Пояснение: "raceacar" — не палиндром.</p>
 *
 * <p>Пример:<br>
 * Вход:  s = " "<br>
 * Выход: true<br>
 * Пояснение: после фильтрации остаётся пустая строка, она считается
 * палиндромом.</p>
 *
 * <p>Паттерн:<br>
 * Два указателя навстречу.</p>
 *
 * <p>Именование переменных:<br>
 * left — указатель с начала строки, right — с конца. leftChar — текущий
 * символ слева, rightChar — текущий символ справа. Метод
 * Character.isLetterOrDigit проверяет, буква это или цифра.
 * Character.toLowerCase приводит к нижнему регистру для сравнения.</p>
 *
 * <p>Идея:<br>
 * Палиндром читается одинаково в обе стороны. Значит, первый символ равен
 * последнему, второй — предпоследнему, и так далее. Ставим два указателя
 * на концы строки и сравниваем символы, двигаясь навстречу. Но строка
 * содержит мусор — пробелы, запятые, двоеточия. Их надо пропускать:
 * если символ слева не буква и не цифра — двигаем left вправо, не трогая
 * right. Симметрично справа. Сравниваем только буквы и цифры, регистр
 * не важен.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Инициализация:
 *       <ul>
 *         <li>left = 0.</li>
 *         <li>right = s.length() - 1.</li>
 *       </ul>
 *   </li>
 *   <li>Пока left &lt; right:
 *       <ul>
 *         <li>Проверяем leftChar = s.charAt(left):
 *             <ul>
 *               <li>Если не буква и не цифра — left++, continue. Переходим
 *                   к следующей итерации, не трогая right.</li>
 *             </ul>
 *         </li>
 *         <li>Проверяем rightChar = s.charAt(right):
 *             <ul>
 *               <li>Если не буква и не цифра — right--, continue.</li>
 *             </ul>
 *         </li>
 *         <li>Сравниваем Character.toLowerCase(leftChar) с
 *             Character.toLowerCase(rightChar):
 *             <ul>
 *               <li>Если не равны — return false.</li>
 *               <li>Если равны — left++, right--.</li>
 *             </ul>
 *         </li>
 *       </ul>
 *   </li>
 *   <li>Если цикл закончился без несовпадений — return true.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Порядок проверок в цикле: сначала проверяем левый символ на «мусор»,
 *       потом правый, только потом сравниваем. Если left указывает на мусор,
 *       мы его пропускаем и уходим на следующую итерацию через continue,
 *       не трогая right. Только когда оба указателя на валидных символах,
 *       происходит сравнение.</li>
 *   <li>continue после left++ и right-- обязателен. Без него мы бы после
 *       пропуска мусора сразу перешли к сравнению, используя старый
 *       leftChar или rightChar. Это дало бы неверный результат.</li>
 *   <li>Используем continue, а не else. Так код плоский и читается как
 *       последовательность независимых проверок.</li>
 *   <li>left++ и right-- выполняются только при успешном сравнении. Если
 *       символы равны — двигаем оба указателя. Если мусор — двигаем
 *       только один. Если не равны — выходим.</li>
 *   <li>Character.toLowerCase применяется к обоим символам перед сравнением.
 *       Без этого 'A' и 'a' считались бы разными. Можно было бы сравнивать
 *       через Character.toLowerCase в обеих частях if, как в коде.</li>
 *   <li>Character.isLetterOrDigit — встроенный метод, покрывает все буквы
 *       и цифры Unicode. Не нужно самому проверять диапазоны 'a'-'z',
 *       'A'-'Z', '0'-'9'.</li>
 *   <li>Пустая строка или строка только из мусора — цикл не выполнится
 *       ни разу или сразу пройдёт через все пропуски, left дойдёт до right
 *       или перейдёт его. Возвращается true. По условиям LeetCode пустая
 *       строка считается палиндромом.</li>
 *   <li>Строка из одного символа — left = right изначально, условие
 *       left &lt; right ложно, цикл не выполняется, возвращается true.</li>
 *   <li>Регистр не важен, но цифры сравниваются как есть. '1' и '1' равны,
 *       '1' и '2' — нет.</li>
 *   <li>Альтернативный подход — собрать новую строку из букв и цифр
 *       в нижнем регистре, потом сравнить её с обратной. Работает, но
 *       использует O(n) дополнительной памяти. Два указателя дают O(1).</li>
 *   <li>Альтернатива с StringBuilder.reverse — тоже O(n) памяти.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>s = "A man, a plan, a canal: Panama" → true. Классический пример.</li>
 *   <li>s = "race a car" → false. Не палиндром.</li>
 *   <li>s = " " → true. Только пробел, после фильтрации пустая строка.</li>
 *   <li>s = "" → true. Пустая строка.</li>
 *   <li>s = "a" → true. Один символ.</li>
 *   <li>s = "ab" → false.</li>
 *   <li>s = "aa" → true.</li>
 *   <li>s = "aA" → true. Регистр не важен.</li>
 *   <li>s = "0P" → false. Цифра и буква разные.</li>
 *   <li>s = ".," → true. Только знаки, после фильтрации пустая строка.</li>
 *   <li>s = "1a2" → false.</li>
 *   <li>s = "121" → true. Цифры тоже проверяются.</li>
 *   <li>s = "!!!aba!!!" → true. Мусор по краям, палиндром внутри.</li>
 *   <li>s = "a.b,a" → true. Точка и запятая пропускаются, остаётся "aba".</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(n) — каждый символ обрабатывается один раз. left и right
 *       двигаются навстречу, суммарно проходят n шагов.<br>
 * Память: O(1) — только два указателя и две временные переменные
 *         для символов.</p>
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
//        String s = "race a car";

        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        while (left < right) {
            char leftCh = s.charAt(left);
            char rightCh = s.charAt(right);
            if (!Character.isLetterOrDigit(leftCh)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(rightCh)) {
                right--;
                continue;
            }
            if (Character.toLowerCase(leftCh) != Character.toLowerCase(rightCh)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
