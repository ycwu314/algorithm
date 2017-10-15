package com.example.tree.binarytree;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Administrator on 2017/10/15.
 */
public class BinaryTree<K extends Comparable<K>, V> {

    private TreeNode<K, V> root;
    private int size = 0;

    public void fromPreorder(List<TreeNode<K, V>> list) {
        if (list.isEmpty()) {
            return;
        }

        root = new TreeNode<>(list.get(0).key, list.get(0).value);

    }

    private TreeNode<K, V> buildFromPreorder(TreeNode<K, V> x, List<TreeNode<K, V>> list, int[] curr) {


        return x;
    }


    @Data
    @NoArgsConstructor
    public static class TreeNode<K extends Comparable<K>, V> {

        private K key;
        private V value;
        private TreeNode<K, V> left;
        private TreeNode<K, V> right;

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
