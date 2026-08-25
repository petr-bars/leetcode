package com.example.sixth_step.serialize_and_deserialize_binary_tree;

import com.example.sixth_step.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.example.sixth_step.utils.Utils.buildTree;
import static com.example.sixth_step.utils.Utils.treeToString;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that
 * it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed
 * later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized
 * to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format,
 * so please be creative and come up with different approaches yourself.
 */
public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode root = buildTree(new Integer[]{1, 2, 3, null, null, 4, 5});

        TreeNode ans = deserialize(serialize(root));
        System.out.println(treeToString(ans));

        TreeNode ansIterative = deserializeIterative(serializeIterative(root));
        System.out.println(treeToString(ansIterative));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder stringTree = new StringBuilder();
        buildString(root, stringTree);
        String result = stringTree.toString();
        return result.substring(0, result.length() - 1);
    }

    private static void buildString(TreeNode node, StringBuilder accumulator) {
        if (node == null) {
            accumulator.append("#").append(",");
            return;
        }
        accumulator.append(node.val).append(",");
        buildString(node.left, accumulator);
        buildString(node.right, accumulator);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] tokens = data.split(",");
        int[] count = {0};
        return build(tokens, count);
    }

    private static TreeNode build(String[] tokens, int[] index) {
        String token = tokens[index[0]];
        if (token.equals("#")) {
            index[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(token));
        index[0]++;
        node.left = build(tokens, index);
        node.right = build(tokens, index);
        return node;
    }


    public static String serializeIterative(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                sb.append("#,");
            } else {
                sb.append(node.val).append(",");
                // добавляем правого и левого (если они есть, иначе null)
                // но мы не можем добавить null, поэтому проверяем и записываем # сразу
                if (node.right != null) {
                    stack.push(node.right);
                } else {
                    sb.append("#,");
                }
                if (node.left != null) {
                    stack.push(node.left);
                } else {
                    sb.append("#,");
                }
            }
        }
        // удаляем последнюю запятую
        if (!sb.isEmpty()) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    public static TreeNode deserializeIterative(String data) {
        if (data.equals("#")) {
            return null;
        }

        String[] tokens = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Deque<Object[]> stack = new ArrayDeque<>(); // [узел, ожидаемый_ребенок: 0-левый, 1-правый]
        stack.push(new Object[]{root, 0});

        int index = 1;
        while (index < tokens.length) {
            Object[] top = stack.peek();
            TreeNode parent = (TreeNode) top[0];
            int childFlag = (int) top[1];

            if (childFlag == 0) { // ожидаем левого
                if (tokens[index].equals("#")) {
                    top[1] = 1; // просто переключаем на правого
                } else {
                    TreeNode left = new TreeNode(Integer.parseInt(tokens[index]));
                    parent.left = left;
                    top[1] = 1; // теперь ожидаем правого
                    stack.push(new Object[]{left, 0}); // новый узел – ожидаем его левого
                }
                index++;
            } else { // ожидаем правого
                if (tokens[index].equals("#")) {
                    stack.pop(); // родитель полностью обработан
                } else {
                    TreeNode right = new TreeNode(Integer.parseInt(tokens[index]));
                    parent.right = right;
                    stack.pop(); // родитель обработан
                    stack.push(new Object[]{right, 0}); // новый узел
                }
                index++;
            }
        }
        return root;
    }
}
