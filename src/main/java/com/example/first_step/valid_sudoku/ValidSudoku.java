package com.example.first_step.valid_sudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according
 * to the following rules:
 * <p>
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * <p>
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * <p>
 * Дана доска 9×9 (массив char[][] board). Нужно проверить, является ли она корректной судоку.
 * Правила:
 * В каждой строке цифры 1-9 не повторяются.
 * В каждом столбце цифры 1-9 не повторяются.
 * В каждом из 9 блоков 3×3 цифры 1-9 не повторяются.
 * Пустые клетки обозначены '.' — их игнорируем.
 * Важно: доска не обязана быть решаемой. Проверяем только, что уже заполненные клетки не нарушают правила.
 * Пример корректной доски:
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
 * Паттерн
 * HashSet для проверки дубликатов в строке / столбце / блоке.
 * Ключевая идея
 * Идём по всем клеткам доски. Для каждой заполненной клетки (row, col) с цифрой value формируем три уникальных ключа:
 * Для строки: "row" + row + value — например, "row0_5" означает «в строке 0 встречается цифра 5».
 * Для столбца: "col" + col + value — например, "col3_7".
 * Для блока: "box" + (row / 3) + "_" + (col / 3) + value — например, "box1_0_7".
 * Кладём все три ключа в один HashSet.
 * Если ключ уже есть — значит, дубликат в строке, столбце или блоке → доска невалидна.
 * Если дошли до конца без повторов → доска валидна.
 * Сложность
 * Время: O(1) — доска фиксированного размера 81 клетка.
 * Память: O(1) — максимум 81 × 3 ключей.
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
                char currentSymbol = board[rowIndex][colIndex];
                if (currentSymbol == '.') {
                    continue;
                }

                String row = "row" + rowIndex + currentSymbol;
                String col = "col" + colIndex + currentSymbol;
                String box = "box" + (rowIndex / 3) + "_" + (colIndex / 3) + currentSymbol;

                if (!seen.add(row) || !seen.add(col) || !seen.add(box)) {
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
