package com.yrw.alogrithms.chapter3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树的实现
 * Date: 2020/9/12
 * Time: 12:06
 *
 * @author yrw
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements ST<K, V> {

    private Node<K, V> root;

    @Override
    public void put(K key, V value) {
        root = doPut(root, key, value);
    }

    private Node<K, V> doPut(Node<K, V> root, K key, V value) {
        if (root == null) {
            return new Node<>(key, value, 1);
        }

        if (key.compareTo(root.key) < 0) {
            root.left = doPut(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) {
            root.right = doPut(root.right, key, value);
        } else if (key.equals(root.key)) {
            root.setValue(value);
        }

        //这边要注意左右children可能为空
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    private int size(Node<K, V> node) {
        return node == null ? 0 : node.size;
    }

    @Override
    public V get(K key) {
        if (isEmpty()) {
            return null;
        }

        Node<K, V> node = find(root, key);
        return node == null ? null : node.value;
    }

    private Node<K, V> find(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) < 0) {
            return find(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            return find(root.right, key);
        } else {
            return root;
        }
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return root.size;
    }

    @Override
    public Iterable<K> keys() {
        //先序遍历树，放到queue里
        Queue<K> queue = new LinkedList<>();
        inorder(root, queue);
        return queue;
    }

    private void inorder(Node<K, V> root, Queue<K> queue) {
        if (root == null) {
            return;
        }
        inorder(root.left, queue);
        queue.offer(root.key);
        inorder(root.right, queue);
    }

    public K maxKey() {
        Node<K, V> cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.key;
    }

    public K minKey() {
        Node<K, V> cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }

    //这部分复杂一点

    /**
     * 向下取整
     *
     * @return 小于等于key的最大节点
     */
    public K floor(K key) {
        if (isEmpty()) {
            return null;
        }

        Node<K, V> n = doFloor(root, key);
        return n != null ? n.key : null;
    }

    private Node<K, V> doFloor(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) < 0) {
            return doFloor(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            Node<K, V> n = doFloor(root.right, key);
            return n != null ? n : root;
        } else {
            return root;
        }
    }

    /**
     * 向上取整
     *
     * @return 大于等于key的最小节点
     */
    public K ceiling(K key) {
        if (isEmpty()) {
            return null;
        }

        return doFloor(root, key).key;
    }

    private Node<K, V> doCeiling(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) > 0) {
            return doFloor(root.right, key);
        } else if (key.compareTo(root.key) < 0) {
            Node<K, V> n = doFloor(root.left, key);
            return n != null ? n : root;
        } else {
            return root;
        }
    }

    /**
     * 找到在二叉树中排名第n的key（从1开始）
     *
     * @param n
     * @return
     */
    public K select(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n should be positive");
        }
        if (isEmpty()) {
            throw new RuntimeException("empty tree");
        }
        if (root.size < n) {
            throw new RuntimeException("n too large");
        }

        Node<K, V> node = doSelect(root, n);
        assert node != null;
        return node.key;
    }

    private Node<K, V> doSelect(Node<K, V> root, int n) {
        if (root == null) {
            return null;
        }
        if (n < size(root.left) + 1) {
            return doSelect(root.left, n);
        } else if (n > size(root.left) + 1) {
            int rank = n - 1 - size(root.left);
            return doSelect(root.right, rank);
        } else {
            return root;
        }
    }

    /**
     * 找到key在树中的排名
     *
     * @param key
     * @return
     */
    public int rank(K key) {
        return doRank(root, key);
    }

    private int doRank(Node<K, V> root, K key) {
        if (root == null) {
            throw new RuntimeException("not found");
        }
        if (key.compareTo(root.key) < 0) {
            return doRank(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            return doRank(root.right, key) + 1 + size(root.left);
        } else {
            return size(root.left) + 1;
        }
    }

    private static final class Node<K, V> {
        private K key;
        private V value;

        private Node<K, V> left;
        private Node<K, V> right;

        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();

        System.out.println(tree.get(1));

        tree.put(-20, "a");
        tree.put(0, "b");
        tree.put(3, "c");
        tree.put(Integer.MAX_VALUE, "sdgagag");
        tree.put(Integer.MIN_VALUE, "333");

        System.out.println(tree.get(Integer.MAX_VALUE));
        System.out.println(tree.get(Integer.MIN_VALUE));
        System.out.println(tree.get(-100));
        System.out.println(tree.get(-20));
        System.out.println(tree.get(3));
        System.out.println(tree.get(0));

        //0
        System.out.println(tree.floor(1));
        //3
        System.out.println(tree.floor(4));
        //3
        System.out.println(tree.floor(3));
        //min
        System.out.println(tree.floor(-100));

        System.out.println("select");

        //min
        System.out.println(tree.select(1));
        //-20
        System.out.println(tree.select(2));
        //0
        System.out.println(tree.select(3));
        //3
        System.out.println(tree.select(4));
        //max
        System.out.println(tree.select(5));

        System.out.println("rank");

        System.out.println(tree.rank(Integer.MIN_VALUE));
        System.out.println(tree.rank(-20));
        System.out.println(tree.rank(0));
        System.out.println(tree.rank(3));
        System.out.println(tree.rank(Integer.MAX_VALUE));

    }
}
