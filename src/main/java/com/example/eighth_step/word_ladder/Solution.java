package com.example.eighth_step.word_ladder;

import java.util.*;

public class Solution {

    /**
     * Находит длину кратчайшей последовательности превращений от beginWord к endWord,
     * где каждое следующее слово отличается от предыдущего ровно на одну букву,
     * и все промежуточные слова присутствуют в wordList.
     * <p>
     * Алгоритм использует поиск в ширину (BFS) на неявном графе:
     * <ul>
     *     <li><b>Вершины</b> – слова (строки).</li>
     *     <li><b>Рёбра</b> – соединяют слова, отличающиеся ровно на одну букву.</li>
     * </ul>
     * BFS гарантирует нахождение кратчайшего пути, так как граф невзвешенный.
     * <p>
     * <b>Основные шаги:</b>
     * <ol>
     *     <li>Проверка наличия endWord в wordList – если нет, возвращается 0.</li>
     *     <li>Создание множества wordSet для быстрых проверок принадлежности.</li>
     *     <li>Удаление beginWord из wordSet, чтобы избежать повторного посещения.</li>
     *     <li>Запуск BFS с очередью, содержащей beginWord.</li>
     *     <li>На каждом уровне BFS генерируются все возможные слова, отличающиеся от текущего
     *         на одну букву (перебор всех позиций и всех 26 букв алфавита).</li>
     *     <li>Если сгенерированное слово присутствует в wordSet, оно добавляется в очередь
     *         и удаляется из множества (это служит одновременно отметкой о посещении).</li>
     *     <li>При достижении endWord возвращается текущая длина пути (level).</li>
     *     <li>Если очередь опустела, возвращается 0 (путь не найден).</li>
     * </ol>
     * <p>
     * <b>Сложность:</b>
     * <ul>
     *     <li>Время: O(n * m * 26), где n – длина слова, m – количество слов в wordList.</li>
     *     <li>Память: O(m) для хранения множества и очереди.</li>
     * </ul>
     *
     * @param beginWord начальное слово
     * @param endWord   целевое слово
     * @param wordList  список допустимых слов (словарь)
     * @return минимальное количество слов в последовательности (включая beginWord и endWord),
     * или 0, если такой последовательности не существует.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord);
        Queue<String> queue = new ArrayDeque<>();

        queue.offer(beginWord);
        return bfs(queue, wordSet, endWord);
    }

    /**
     * Выполняет поиск в ширину (BFS) для нахождения кратчайшей последовательности слов.
     *
     * @param queue   очередь, содержащая текущий уровень слов
     * @param wordSet множество допустимых слов (изменяемое – удаляем использованные)
     * @param endWord целевое слово
     * @return длина пути (количество слов) или 0, если путь не найден
     */
    private int bfs(Queue<String> queue, Set<String> wordSet, String endWord) {
        int level = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int index = 0; index < levelSize; index++) {
                String currentWord = queue.poll();

                if (currentWord.equals(endWord)) {
                    return level;
                }

                char[] chars = currentWord.toCharArray();

                for (int position = 0; position < chars.length; position++) {
                    char original = chars[position];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) {
                            continue;
                        }

                        chars[position] = c;
                        String newWord = new String(chars);

                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                    chars[position] = original;
                }
            }
            level++;
        }
        return 0;
    }
}
