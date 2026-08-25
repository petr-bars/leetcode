package com.example.sixth_step.binary_tree_maximum_path_sum;

import com.example.sixth_step.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static com.example.sixth_step.utils.Utils.buildTree;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 */
public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{-10, 9, 20, null, null, 15, 7});

        System.out.println(maxPathSum(root));
        System.out.println(maxPathSumIterative(root));
    }


    /**
     * Находит максимальную сумму пути в бинарном дереве (любые два узла, путь может не проходить через корень).
     * <p>
     * Рекурсивное решение обходит дерево снизу вверх. Для каждого узла вычисляются:
     * <ul>
     *   <li><b>Вклад узла вверх (gain):</b> максимальная сумма, которую этот узел может передать родителю,
     *       идя вниз только по одной ветке (либо левой, либо правой). Если обе ветки отрицательны,
     *       вклад равен значению самого узла (отрицательные ветки игнорируются).</li>
     *   <li><b>Путь через узел (currentPathSum):</b> сумма, получаемая при соединении левой и правой веток
     *       через текущий узел (или только одной ветки, или только самого узла). Этот путь не передаётся выше,
     *       но является кандидатом на глобальный максимум.</li>
     * </ul>
     * <p>
     * Отрицательные вклады детей заменяются на 0, чтобы не ухудшать сумму.
     * <p>
     * Сложность: O(n) по времени, O(h) по памяти (h – высота дерева).
     * Для вырожденного дерева O(n) памяти, для сбалансированного – O(log n).
     *
     * @param root корень дерева (может быть {@code null}, тогда возвращается 0)
     * @return максимальная сумма пути (значение помещается в 32-битное целое)
     * @see #maxGain(TreeNode, long[]) вспомогательный метод для рекурсивного обхода
     */
    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        long[] maxSum = {Long.MIN_VALUE};
        maxGain(root, maxSum);
        return Math.toIntExact(maxSum[0]);
    }

    /**
     * Рекурсивно вычисляет вклад узла вверх и обновляет глобальный максимум.
     *
     * @param node   текущий узел (не {@code null})
     * @param maxSum массив из одного элемента для хранения глобального максимума
     *               (используется для изменения значения внутри рекурсии)
     * @return вклад текущего узла вверх (максимальная сумма по одной ветке)
     */
    private static int maxGain(TreeNode node, long[] maxSum) {
        if (node == null) {
            return 0;
        }

        // Рекурсивно получаем лучшие вклады от детей
        int leftGain = Math.max(0, maxGain(node.left, maxSum));   // если отрицательно, берём 0
        int rightGain = Math.max(0, maxGain(node.right, maxSum));

        // Путь, который проходит через текущий узел (соединяет левое и правое)
        int currentPathSum = node.val + leftGain + rightGain;
        maxSum[0] = Math.max(maxSum[0], currentPathSum); // обновляем глобальный максимум

        // Вклад вверх – только одна ветка
        return node.val + Math.max(leftGain, rightGain);
    }


    /**
     * Находит максимальную сумму пути в бинарном дереве (итеративная версия).
     * <p>
     * Используется явный стек для имитации пост-обхода (сначала дети, потом родитель).
     * Каждый узел обрабатывается дважды:
     * <ol>
     *   <li>При первом извлечении – помечается как "не обработан", затем кладётся обратно
     *       с флагом {@code processed = true}, а его дети добавляются в стек для обработки.</li>
     *   <li>При втором извлечении – все дети уже обработаны, их вклады известны (хранятся в {@code Map}).
     *       Тогда вычисляются {@code leftGain}, {@code rightGain}, {@code currentPathSum} и {@code gain}.</li>
     * </ol>
     * <p>
     * Отрицательные вклады детей заменяются на 0, как и в рекурсивной версии.
     * <p>
     * Преимущество: не использует системный стек вызовов, безопасен для очень глубоких деревьев.
     * <p>
     * Сложность: O(n) по времени, O(n) по памяти (стек и мапа для хранения вкладов).
     *
     * @param root корень дерева (может быть {@code null}, возвращается 0)
     * @return максимальная сумма пути (значение помещается в 32-битное целое)
     */
    public static int maxPathSumIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        Deque<Object[]> stack = new ArrayDeque<>();
        Map<TreeNode, Integer> gainMap = new HashMap<>();
        stack.push(new Object[]{root, false});

        while (!stack.isEmpty()) {
            Object[] entry = stack.pop();
            TreeNode node = (TreeNode) entry[0];
            boolean processed = (boolean) entry[1];

            if (!processed) {
                // Кладём узел обратно как обработанный
                stack.push(new Object[]{node, true});
                // Добавляем детей (порядок не важен)
                if (node.right != null) {
                    stack.push(new Object[]{node.right, false});
                }
                if (node.left != null) {
                    stack.push(new Object[]{node.left, false});
                }
            } else {
                // Дети уже обработаны, вычисляем вклад
                int leftGain = gainMap.getOrDefault(node.left, 0);
                int rightGain = gainMap.getOrDefault(node.right, 0);
                int left = Math.max(0, leftGain);
                int right = Math.max(0, rightGain);

                int currentPath = node.val + left + right;
                if (currentPath > maxSum) {
                    maxSum = currentPath;
                }

                int gain = node.val + Math.max(left, right);
                gainMap.put(node, gain);
            }
        }
        return maxSum;
    }
}
