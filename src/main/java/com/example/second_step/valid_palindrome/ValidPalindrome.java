package com.example.second_step.valid_palindrome;

/**
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase
 * letters and removing all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 * <p>
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * <p>
 * Дана строка s. Нужно вернуть true, если она является палиндромом, и false иначе.
 * Учитываются только буквы и цифры, регистр не важен. Все остальные символы (пробелы, знаки препинания) игнорируются.
 * Пример:
 * Вход: s = "A man, a plan, a canal: Panama"
 * Выход: true
 * Если убрать всё кроме букв и привести к нижнему регистру: "amanaplanacanalpanama" — палиндром.
 * Паттерн
 * Два указателя (left, right).
 * Ключевая идея
 * Ставишь left = 0, right = s.length() - 1.
 * Пока left < right:
 * Пропускаешь слева все символы, которые не буква и не цифра.
 * Пропускаешь справа все символы, которые не буква и не цифра.
 * Сравниваешь s[left] и s[right] (в нижнем регистре).
 * Если не равны → false.
 * Иначе left++, right--.
 * Если дошли до конца → true.
 * Сложность
 * Время: O(n)
 * Память: O(1)
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
//        String s = "race a car";

        System.out.println(isPalindrome1(s));
    }

    public static boolean isPalindrome1(String s) {
        int left = 0;
        int right = s.length() - 1;

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
