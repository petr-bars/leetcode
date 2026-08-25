package com.example.sixth_step.validate_binary_search_tree;

import com.example.sixth_step.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.example.sixth_step.utils.Utils.buildTree;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys strictly less than the node's key.
 * The right subtree of a node contains only nodes with keys strictly greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{2, 1, 3});

        System.out.println(isValidBST(root));
        System.out.println(isValidBSTInorder(root));
        System.out.println(isValidBSTWithStack(root));
    }

    // ============================================================
    // 1. Реализация с диапазонами (min/max)
    // ============================================================

    /**
     * Проверяет, является ли дерево корректным BST, используя рекурсивную
     * проверку допустимых диапазонов значений для каждого узла.
     * <p>
     * Алгоритм для каждого узла передаёт нижнюю и верхнюю границы допустимых
     * значений. При спуске в левое поддерево верхняя граница сужается до
     * значения текущего узла, а в правое – нижняя граница становится значением
     * текущего узла. Если значение узла не попадает в заданный диапазон,
     * дерево не является BST.
     *
     * @param root корень проверяемого дерева
     * @return {@code true}, если дерево является корректным BST, иначе {@code false}
     * @see #isValid(TreeNode, Integer, Integer)
     */
    public static boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    /**
     * Рекурсивный вспомогательный метод для проверки поддерева с учётом
     * допустимых границ (min, max).
     *
     * @param node текущий проверяемый узел (может быть {@code null})
     * @param min  минимальное допустимое значение для этого узла
     *             (или {@code null}, если ограничение отсутствует)
     * @param max  максимальное допустимое значение для этого узла
     *             (или {@code null}, если ограничение отсутствует)
     * @return {@code true}, если поддерево с корнем {@code node} является
     * допустимой частью BST в заданных границах, иначе {@code false}
     */
    private static boolean isValid(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.val <= min) {
            return false;
        }
        if (max != null && node.val >= max) {
            return false;
        }
        boolean left = isValid(node.left, min, node.val);
        boolean right = isValid(node.right, node.val, max);
        return left && right;
    }

    // ============================================================
    // 2. Реализация через инфиксный обход (inorder)
    // ============================================================

    /**
     * Проверяет, является ли дерево корректным BST, выполняя инфиксный обход
     * и проверяя, что последовательность значений строго возрастает.
     * <p>
     * При инфиксном обходе корректного BST узлы посещаются в порядке
     * возрастания их значений. Метод запоминает предыдущее посещённое значение
     * и на каждом шаге убеждается, что текущее значение строго больше
     * предыдущего. Если это условие нарушается – дерево не является BST.
     *
     * <p>Для передачи состояния между рекурсивными вызовами используется
     * массив {@code long[]} (так как примитив не может быть изменён внутри
     * рекурсии иначе). Начальное значение устанавливается в
     * {@link Long#MIN_VALUE}, чтобы первая проверка всегда проходила.
     *
     * @param root корень проверяемого дерева
     * @return {@code true}, если дерево является корректным BST, иначе {@code false}
     * @see #isValidInorder(TreeNode, long[])
     */
    public static boolean isValidBSTInorder(TreeNode root) {
        long[] prev = {Long.MIN_VALUE};
        return isValidInorder(root, prev);
    }

    /**
     * Рекурсивный вспомогательный метод для инфиксного обхода с проверкой
     * строгого возрастания.
     *
     * @param node текущий проверяемый узел (может быть {@code null})
     * @param prev массив из одного элемента, содержащий значение последнего
     *             посещённого узла (или {@code Long.MIN_VALUE} для первого).
     *             Этот массив используется как изменяемый контейнер для
     *             передачи состояния между рекурсивными вызовами.
     * @return {@code true}, если поддерево с корнем {@code node} даёт строго
     * возрастающую последовательность при инфиксном обходе,
     * иначе {@code false}
     */
    private static boolean isValidInorder(TreeNode node, long[] prev) {
        if (node == null) {
            return true;
        }

        if (!isValidInorder(node.left, prev)) {
            return false;
        }

        if (node.val <= prev[0]) {
            return false;
        }

        prev[0] = node.val;

        return isValidInorder(node.right, prev);
    }


    /**
     * Проверяет, является ли дерево корректным BST с использованием итеративного
     * обхода в глубину (DFS) и явного стека.
     * <p>
     * Этот метод имитирует рекурсивный подход с передачей допустимых диапазонов,
     * но использует три параллельных стека:
     * <ul>
     *   <li>{@code nodeStack} – хранит текущие узлы;</li>
     *   <li>{@code minStack} – хранит нижние границы допустимых значений
     *       (минимальное значение, которое может содержать узел);</li>
     *   <li>{@code maxStack} – хранит верхние границы допустимых значений
     *       (максимальное значение, которое может содержать узел).</li>
     * </ul>
     * Границы инициализируются как {@link Long#MIN_VALUE} и {@link Long#MAX_VALUE}
     * для корня, что означает отсутствие ограничений.
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Кладём корень в стек с границами (−∞, +∞).</li>
     *   <li>Пока стек не пуст:
     *     <ul>
     *       <li>Извлекаем узел и его границы.</li>
     *       <li>Если узел равен {@code null} – пропускаем.</li>
     *       <li>Если значение узла не попадает в интервал (min, max) – возвращаем {@code false}.</li>
     *       <li>Если узел имеет левого потомка, кладём его в стек с границами (min, node.val).</li>
     *       <li>Если узел имеет правого потомка, кладём его в стек с границами (node.val, max).</li>
     *     </ul>
     *   </li>
     * </ol>
     * <p>
     * Преимущества перед рекурсивным подходом:
     * <ul>
     *   <li>Не использует системный стек вызовов, поэтому безопасен для
     *       очень глубоких деревьев (избегает {@code StackOverflowError}).</li>
     *   <li>Позволяет явно управлять памятью (вся работа ведётся в куче).</li>
     * </ul>
     * <p>
     * Сложность:
     * <ul>
     *   <li><b>Время:</b> O(n), где n – число узлов в дереве (каждый узел посещается один раз).</li>
     *   <li><b>Память:</b> O(h), где h – высота дерева (в худшем случае O(n) для вырожденного дерева).</li>
     * </ul>
     *
     * @param root корень проверяемого дерева (может быть {@code null})
     * @return {@code true}, если дерево является корректным BST, иначе {@code false}
     * @see #isValidBST(TreeNode) для рекурсивной версии с диапазонами
     * @see #isValidBSTInorder(TreeNode) для рекурсивной версии с инфиксным обходом
     */
    public static boolean isValidBSTWithStack(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> nodeStack = new ArrayDeque<>();
        Deque<Long> minStack = new ArrayDeque<>();
        Deque<Long> maxStack = new ArrayDeque<>();
        nodeStack.push(root);
        minStack.push(Long.MIN_VALUE);
        maxStack.push(Long.MAX_VALUE);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            long min = minStack.pop();
            long max = maxStack.pop();

            if (node.val <= min) {
                return false;
            }

            if (node.val >= max) {
                return false;
            }

            if (node.left != null) {
                nodeStack.push(node.left);
                minStack.push(min);
                maxStack.push((long) node.val);
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                minStack.push((long) node.val);
                maxStack.push(max);
            }
        }
        return true;
    }
}