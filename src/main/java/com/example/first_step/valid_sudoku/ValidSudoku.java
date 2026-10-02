package com.example.first_step.valid_sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need
 * to be validated according to the following rules: each row must contain
 * the digits 1-9 without repetition, each column must contain the digits 1-9
 * without repetition, each of the nine 3 x 3 sub-boxes of the grid must
 * contain the digits 1-9 without repetition.</p>
 *
 * <p>Note: a Sudoku board (partially filled) could be valid but is not
 * necessarily solvable. Only the filled cells need to be validated according
 * to the mentioned rules.</p>
 *
 * <p>Дана доска 9×9 (массив char[][] board). Проверить, является ли она
 * корректной судоку. Правила: в каждой строке, столбце и блоке 3×3 цифры
 * 1–9 не повторяются. Пустые клетки обозначены '.', их игнорируем.
 * Доска не обязана быть решаемой — проверяем только, что уже заполненные
 * клетки не нарушают правила.</p>
 *
 * <p>Пример корректной доски:</p>
 * <pre>
 * 5 3 . | . 7 . | . . .
 * 6 . . | 1 9 5 | . . .
 * . 9 8 | . . . | . 6 .
 * ------+-------+------
 * 8 . . | . 6 . | . . 3
 * 4 . . | 8 . 3 | . . 1
 * 7 . . | . 2 . | . . 6
 * ------+-------+------
 * . 6 . | . . . | 2 8 .
 * . . . | 4 1 9 | . . 5
 * . . . | . 8 . | . 7 9
 * </pre>
 *
 * <p>Паттерн:<br>
 * HashSet для проверки дубликатов в строке, столбце и блоке.</p>
 *
 * <p>Именование переменных:<br>
 * seen — HashSet строковых ключей. Каждый ключ кодирует «где встречается
 * цифра» — в строке, столбце или блоке. rowLength и colLength — размеры
 * доски по строкам и столбцам. Проверяются на равенство в начале.
 * rowIndex — номер строки, colIndex — номер столбца. currentChar — текущий
 * символ клетки. rowKey, colKey, boxKey — три ключа для текущей клетки.</p>
 *
 * <p>Идея:<br>
 * Нужно проверить три вещи для каждой цифры: не встречалась ли она уже
 * в этой строке, в этом столбце, в этом блоке 3×3. Можно было бы держать
 * три отдельные структуры и проверять каждую. Но проще построить для
 * каждой цифры три уникальных строковых ключа и складывать их в один
 * HashSet. Если ключ уже есть — значит, цифра где-то повторилась.</p>
 *
 * <p>Логика и шаги:</p>
 * <ul>
 *   <li>Проверка формы доски:
 *       <ul>
 *         <li>Если rowLength != colLength — доска не квадратная, вернуть
 *             false сразу. По условиям LeetCode доска всегда 9×9, так что
 *             на платформе это не сработает, но защищает от странного
 *             входа.</li>
 *       </ul>
 *   </li>
 *   <li>Создаём пустой HashSet seen — он будет хранить все ключи.</li>
 *   <li>Идём по всем клеткам доски двойным циклом:
 *       <ul>
 *         <li>rowIndex от 0 до rowLength - 1.</li>
 *         <li>colIndex от 0 до rowLength - 1 (используем rowLength,
 *             потому что проверка выше гарантирует rowLength == colLength).</li>
 *       </ul>
 *   </li>
 *   <li>Для каждой клетки берём currentChar = board[rowIndex][colIndex]:
 *       <ul>
 *         <li>Если currentChar == '.', пропускаем клетку через continue.
 *             Пустые клетки не проверяем.</li>
 *         <li>Строим три ключа:
 *             <ul>
 *               <li>rowKey = "row" + rowIndex + "_" + currentChar.</li>
 *               <li>colKey = "col" + colIndex + "_" + currentChar.</li>
 *               <li>boxKey = "box" + (rowIndex / 3) + "_" + (colIndex / 3)
 *                   + "_" + currentChar.</li>
 *             </ul>
 *         </li>
 *         <li>Пытаемся добавить каждый ключ в seen. Если add вернул false
 *             хотя бы для одного — ключ уже был, дубликат найден.
 *             Возвращаем false.</li>
 *       </ul>
 *   </li>
 *   <li>Если дошли до конца без дубликатов — возвращаем true.</li>
 * </ul>
 *
 * <p>Ключевые тонкости:</p>
 * <ul>
 *   <li>Ключи строятся с префиксами "row", "col", "box". Это нужно,
 *       чтобы они не смешивались. Без префиксов ключ "1_5" мог бы означать
 *       и «строка 1, цифра 5», и «столбец 1, цифра 5», и «блок 1, цифра 5» —
 *       коллизия.</li>
 *   <li>Разделитель "_" между частями ключа обязателен. Без него
 *       "row15" могло бы быть «строка 1, цифра 5» или «строка 15, цифра ...».
 *       С разделителем части однозначны.</li>
 *   <li>Номер блока считается как rowIndex / 3 и colIndex / 3. Деление
 *       целочисленное. Сетка 9×9 делится на 9 блоков 3×3. Строки 0–2 дают
 *       блок 0 по строкам, строки 3–5 — блок 1, строки 6–8 — блок 2.
 *       То же для столбцов.</li>
 *   <li>Три условия в if объединены через ||. Java использует
 *       short-circuit: если !seen.add(rowKey) вернул true — остальные два
 *       add не выполнятся. Это не баг, потому что мы сразу возвращаем
 *       false. Но colKey и boxKey в этом случае в seen не попадут.</li>
 *   <li>Пустые клетки ('.') пропускаем через continue. Если бы не
 *       пропускали, ключи для '.' тоже добавлялись бы, и вторая пустая
 *       клетка в той же строке дала бы дубликат. Но пустые клетки — не
 *       нарушение, поэтому их надо игнорировать.</li>
 *   <li>Один HashSet, а не три. Три отдельных множества работали бы так же,
 *       но кода больше. С префиксами всё умещается в один.</li>
 *   <li>Доска фиксированного размера 9×9 — значит, O(1) по времени и памяти.
 *       Не O(n²) в привычном смысле, потому что n здесь константа.</li>
 *   <li>Проверять решаемость не нужно. Доска может быть валидной по
 *       правилам, но не иметь решения. Задача просит только валидность.</li>
 * </ul>
 *
 * <p>Проверки:</p>
 * <ul>
 *   <li>Корректная доска из примера → true.</li>
 *   <li>Дубликат в строке (две "5" в одной строке) → false.</li>
 *   <li>Дубликат в столбце (две "8" в одном столбце) → false.</li>
 *   <li>Дубликат в блоке 3×3 (две "3" в одном блоке) → false.</li>
 *   <li>Пустая доска (все '.') → true.</li>
 *   <li>Доска с одной заполненной клеткой → true.</li>
 *   <li>Доска, где цифры идут по одному разу в каждой строке, но дублируются
 *       в столбцах → false.</li>
 *   <li>Доска, где всё правильно по строкам и столбцам, но дублируется
 *       в блоке → false.</li>
 * </ul>
 *
 * <p>Сложность:<br>
 * Время: O(1) — доска фиксированного размера, 81 клетка, каждая
 *       обрабатывается за константу.<br>
 * Память: O(1) — максимум 81 × 3 = 243 ключа в HashSet.</p>
 */
public class ValidSudoku {
    public static void main(String[] args) {
        char[][] board = new char[][]
                {
                          {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
                };
//        char[][] board = new char[][]
//                     {
//                         {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
//                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
//                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
//                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
//                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
//                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
//                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
//                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
//                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
//                    };
        System.out.println(isValidSudoku(board));
        System.out.println(isValidSudokuBit(board));
        System.out.println(isValidSudokuBoolean(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        int length = board.length;
        if (length != 9 || board[0].length != 9) {
            return false;
        }

        Set<String> seen = new HashSet<>();
        for (int rowIndex = 0; rowIndex < length; rowIndex++) {
            for (int colIndex = 0; colIndex < length; colIndex++) {
                char currentChar = board[rowIndex][colIndex];
                if (currentChar == '.') {
                    continue;
                }
                String rowKey = "row" + rowIndex + "_" + currentChar;
                String colKey = "col" + colIndex + "_" + currentChar;
                String boxKey = "box" + (rowIndex / 3) + "_" + (colIndex / 3) + "_" + currentChar;

                if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isValidSudokuBit(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] boxes = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }

                int digit = c - '1';
                int bit = 1 << digit;

                int boxIndex = (i / 3) * 3 + (j / 3);

                if ((rows[i] & bit) != 0 || (cols[j] & bit) != 0 || (boxes[boxIndex] & bit) != 0) {
                    return false;
                }

                rows[i] |= bit;
                cols[j] |= bit;
                boxes[boxIndex] |= bit;
            }
        }
        return true;
    }

    public static boolean isValidSudokuBoolean(char[][] board) {
        boolean[][] rows = new boolean[9][9];    // rows[i][digit] — была ли цифра digit в строке i
        boolean[][] cols = new boolean[9][9];    // cols[j][digit] — была ли цифра digit в столбце j
        boolean[][] boxes = new boolean[9][9];   // boxes[boxIndex][digit] — была ли цифра digit в блоке

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                char currentSymbol = board[rowIndex][colIndex];
                if (currentSymbol == '.') {
                    continue;
                }

                int digit = currentSymbol - '1'; // чтобы попасть в диапазон 0-8 вместо 1-9
                int boxIndex = (rowIndex / 3) * 3 + (colIndex / 3); // номер блока 0..8

                if (rows[rowIndex][digit] || cols[colIndex][digit] || boxes[boxIndex][digit]) {
                    return false; // дубликат
                }

                rows[rowIndex][digit] = true;
                cols[colIndex][digit] = true;
                boxes[boxIndex][digit] = true;
            }
        }
        return true;
    }
}
