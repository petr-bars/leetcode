package com.example.sixth_step.construct_binary_tree_from_preorder_inorder;

import com.example.sixth_step.TreeNode;

import java.util.HashMap;
import java.util.Map;

import static com.example.sixth_step.utils.Utils.treeToString;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 */
public class ConstructBinaryTreeFromPreorderInorder {
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        System.out.println(treeToString(buildTree(preorder, inorder)));
    }


    /**
     * Строит бинарное дерево по двум обходам: preorder и inorder.
     * <p>
     * Свойства обходов:
     * <ul>
     *   <li><b>Preorder (прямой):</b> сначала посещается корень, затем левое поддерево, затем правое.
     *       Поэтому первый элемент в любом фрагменте preorder – это корень текущего поддерева.</li>
     *   <li><b>Inorder (симметричный):</b> сначала левое поддерево, затем корень, затем правое.
     *       Поэтому корень делит inorder на левую и правую части.</li>
     * </ul>
     * <p>
     * Алгоритм использует рекурсивное построение с передачей границ (индексов) в массивах,
     * чтобы не копировать подмассивы. Основные шаги:
     * <ol>
     *   <li>Строится {@code Map}, где ключ – значение узла, значение – его индекс в массиве inorder.
     *       Это позволяет находить позицию корня в inorder за O(1).</li>
     *   <li>Запускается рекурсивная функция {@code build(preStart, preEnd, inStart, inEnd)},
     *       которая работает с фрагментами массивов, ограниченными этими индексами.</li>
     *   <li>В каждом вызове:
     *     <ul>
     *       <li>Если {@code preStart > preEnd} (или {@code inStart > inEnd}) – возвращается {@code null} (пустое поддерево).</li>
     *       <li>Корень – {@code preorder[preStart]}. Создаётся узел.</li>
     *       <li>По {@code map} находится индекс корня в inorder: {@code rootIndex}.</li>
     *       <li>Размер левого поддерева вычисляется как {@code leftSize = rootIndex - inStart} (количество элементов слева от корня в inorder).</li>
     *       <li>Левое поддерево строится рекурсивно:
     *         <ul>
     *           <li>в preorder: от {@code preStart + 1} до {@code preStart + leftSize} (первые leftSize элементов после корня);</li>
     *           <li>в inorder: от {@code inStart} до {@code rootIndex - 1}.</li>
     *         </ul>
     *       </li>
     *       <li>Правое поддерево строится рекурсивно:
     *         <ul>
     *           <li>в preorder: от {@code preStart + leftSize + 1} до {@code preEnd} (оставшиеся элементы);</li>
     *           <li>в inorder: от {@code rootIndex + 1} до {@code inEnd}.</li>
     *         </ul>
     *       </li>
     *     </ul>
     *   </li>
     * </ol>
     * <p>
     * <b>Почему индексы работают именно так?</b>
     * <ul>
     *   <li>В preorder после корня идут все узлы левого поддерева, потом все узлы правого.
     *       Зная размер левого поддерева, мы точно определяем границу между ними в preorder.</li>
     *   <li>В inorder корень разбивает массив на левую и правую части, что даёт нам диапазоны для inorder.</li>
     * </ul>
     * <p>
     * <b>Пример:</b>
     * <pre>
     * preorder = [3, 9, 20, 15, 7]
     * inorder  = [9, 3, 15, 20, 7]
     *
     * 1. Корень = 3 (preorder[0])
     * 2. Индекс 3 в inorder = 1
     * 3. leftSize = 1 - 0 = 1 (элемент 9)
     * 4. Левое поддерево: preorder[1..1] = [9], inorder[0..0] = [9]
     * 5. Правое поддерево: preorder[2..4] = [20,15,7], inorder[2..4] = [15,20,7]
     * </pre>
     * <p>
     * <b>Сложность:</b>
     * <ul>
     *   <li>Время: O(n), так как каждый узел создаётся один раз, поиск по map – O(1).</li>
     *   <li>Память: O(n) для map и O(h) для стека рекурсии (h – высота дерева).</li>
     * </ul>
     *
     * @param preorder массив прямого обхода (корень, левое, правое), не {@code null}
     * @param inorder  массив симметричного обхода (левое, корень, правое), не {@code null}
     * @return корень построенного бинарного дерева, или {@code null} если массивы пусты
     * @throws IllegalArgumentException если массивы имеют разную длину или содержат дубликаты
     * @see TreeNode
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < inorder.length; index++) {
            map.put(inorder[index], index);
        }

        return build(preorder, map, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] preorder, Map<Integer, Integer> map, int preStart, int preEnd, int inStart, int inEnd) {
        // Базовый случай: пустой диапазон
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // Корень – первый элемент в preorder
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Находим индекс корня в inorder
        int rootIndex = map.get(rootVal);

        // Количество узлов в левом поддереве
        int leftSize = rootIndex - inStart;

        // Левое поддерево:
        // preorder: от preStart+1 до preStart+leftSize
        // inorder: от inStart до rootIndex-1
        root.left = build(preorder, map, preStart + 1, preStart + leftSize, inStart, rootIndex - 1);

        // Правое поддерево:
        // preorder: от preStart+leftSize+1 до preEnd
        // inorder: от rootIndex+1 до inEnd
        root.right = build(preorder, map, preStart + leftSize + 1, preEnd, rootIndex + 1, inEnd);

        return root;
    }
}
