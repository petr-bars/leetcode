package com.example.sixth_step.kth_smallest_element_in_bst;

import com.example.sixth_step.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.example.sixth_step.utils.Utils.buildTree;

/**
 * Given the root of a binary search tree, and an integer k,
 * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 */
public class KthSmallestElementInBST {
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{3, 1, 4, null, 2});
        int k = 4;
        System.out.println(kthSmallest(root, k));
        System.out.println(kthSmallestRecursive(root, k));
    }


    /**
     * Находит k-й наименьший элемент в бинарном дереве поиска (BST) с использованием итеративного обхода.
     * <p>
     * Алгоритм выполняет симметричный обход (inorder) с помощью явного стека,
     * чтобы посетить узлы в порядке возрастания значений.
     * <p>
     * <b>Как это работает (шаг за шагом):</b>
     * <ol>
     *   <li>Начинаем с корня. Пока есть левый потомок, кладём текущий узел в стек
     *       и переходим к левому потомку – так мы спускаемся до самого левого узла.</li>
     *   <li>Когда дошли до {@code null}, извлекаем верхний узел из стека –
     *       это следующий по порядку (самый маленький из ещё не посещённых).</li>
     *   <li>Увеличиваем счётчик посещённых узлов.</li>
     *   <li>Если счётчик стал равен {@code k}, значит, мы нашли k-й наименьший элемент –
     *       возвращаем значение этого узла.</li>
     *   <li>Если счётчик ещё не достиг {@code k}, переходим к правому поддереву
     *       извлечённого узла и повторяем процесс (снова спускаемся влево, затем извлекаем и т.д.).</li>
     * </ol>
     * <p>
     * Таким образом, мы обходим дерево строго слева направо и останавливаемся на k-м узле,
     * не обрабатывая остальные элементы. Это позволяет найти ответ за O(h + k) времени,
     * где h – высота дерева.
     *
     * @param root корень бинарного дерева поиска (не {@code null})
     * @param k    порядковый номер искомого элемента (нумерация с 1)
     * @return значение k-го наименьшего узла
     * @throws IllegalArgumentException если {@code k} меньше 1 или больше числа узлов в дереве
     * @see #kthSmallestRecursive(TreeNode, int) для рекурсивной версии
     */
    public static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        int count = 0;

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode node = stack.pop();
            count++;

            if (count == k) {
                return node.val;
            }
            current = node.right;
        }
        return -1;
    }


    public static int kthSmallestRecursive(TreeNode root, int k) {
        int[] count = {0};       // счётчик посещённых узлов (массив, чтобы менять внутри рекурсии)
        int[] result = {0};      // массив для хранения найденного значения
        inorder(root, k, count, result);
        return result[0];
    }

    private static void inorder(TreeNode node, int k, int[] count, int[] result) {
        if (node == null) {
            return;
        }

        // 1. Обходим левое поддерево
        inorder(node.left, k, count, result);

        // 2. Обрабатываем текущий узел
        count[0]++;
        if (count[0] == k) {
            result[0] = node.val;
            return; // останавливаемся, дальше не идём
        }

        // 3. Обходим правое поддерево (только если ещё не нашли ответ)
        if (result[0] == 0) {
            inorder(node.right, k, count, result);
        }
    }
}
