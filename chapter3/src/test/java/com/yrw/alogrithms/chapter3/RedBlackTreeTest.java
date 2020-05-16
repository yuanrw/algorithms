package com.yrw.alogrithms.chapter3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date: 2020-05-16
 * Time: 12:24
 *
 * @author yrw
 */
public class RedBlackTreeTest {

    private static RedBlackTree<Integer> newTree() {
        return new RedBlackTree<>();
    }

    @Test
    public void testFindNode() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(2);
        tree.put(1);
        tree.put(4);
        tree.put(5);
        tree.put(9);
        tree.put(3);
        tree.put(6);
        tree.put(7);
        tree.put(15);

        /*
                         5B
                  ┌───────┴───────┐
                 2B              7B
              ┌───┴───┐       ┌───┴───┐
             1B      4B      6B      15B
                    ┌─┘             ┌─┘
                   3R              9R
         */

        String[] t = {"5B", "2B", "1B", "4B", "3R", "7B", "6B", "15B", "9R"};
        Assert.assertArrayEquals(t, tree.listTree());

        // valid cases
        Assert.assertNotNull(tree.get(5).key);
        Assert.assertNotNull(tree.get(2).key);
        Assert.assertNotNull(tree.get(1).key);
        Assert.assertNotNull(tree.get(4).key);
        Assert.assertNotNull(tree.get(3).key);
        Assert.assertNotNull(tree.get(7).key);
        Assert.assertNotNull(tree.get(6).key);
        Assert.assertNotNull(tree.get(15).key);
        Assert.assertNotNull(tree.get(9).key);

        //invalid cases
        Assert.assertNull(tree.get(-1));
        Assert.assertNull(tree.get(52454225));
        Assert.assertNull(tree.get(0));
        Assert.assertNull(tree.get(401));
    }

    @Test
    public void testInsertBlackLeft() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(10);
        tree.put(7);
        tree.put(20);

        String[] t1 = {"10B", "7B", "20B"};
        Assert.assertArrayEquals(t1, tree.listTree());

        /*
                      10B
                     ┌─┴─┐
                    7B  20B

                      ||    put 5
                      ||    new node should be RED
                      \/

                      10B
                   ┌───┴───┐
                  7B      20B
                 ┌─┘
                5R
         */

        tree.put(5);

        String[] t2 = {"10B", "7B", "5R", "20B"};
        Assert.assertArrayEquals(t2, tree.listTree());
    }

    @Test
    public void testInsertBlackRight() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(10);
        tree.put(-10);
        tree.put(20);

        String[] t1 = {"10B", "-10B", "20B"};
        Assert.assertArrayEquals(t1, tree.listTree());

        /*
                     10B
                    ┌─┴─┐
                  -10B 20B


                     ||
                     ||    put 7, rotate left
                     \/


                     10B
                  ┌───┴───┐
                 7B      20B
                ┌─┘
              -10R
         */

        tree.put(7);

        String[] t2 = {"10B", "7B", "-10R", "20B"};
        Assert.assertArrayEquals(t2, tree.listTree());
    }

    @Test
    public void testInsertRedLeft() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(10);
        tree.put(7);
        tree.put(20);
        tree.put(5);

        String[] t1 = {"10B", "7B", "5R", "20B"};
        Assert.assertArrayEquals(t1, tree.listTree());

        /*
                     10B
                  ┌───┴───┐
                 7B      20B
               ┌─┘
               5R

                      ||  put 1, now node5 has two RED links
                      ||  rotate right and flip color
                      \/

                     10B
                  ┌───┴───┐
                 5R      20B
                ┌─┴─┐
               1B  7B
         */

        tree.put(1);

        String[] t2 = {"10B", "5R", "1B", "7B", "20B"};
        Assert.assertArrayEquals(t2, tree.listTree());
    }

    @Test
    public void testInsertRedRight() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(10);
        tree.put(7);

        String[] t = {"10B", "7R"};
        Assert.assertArrayEquals(t, tree.listTree());

        /*
                 10B
                ┌─┘
               7R

                 ||   put 20, now root has two RED links
                 ||   flip color, and root is always BLACK
                 \/

                 10B
                ┌─┴─┐
               7B  20B
         */

        tree.put(20);

        String[] t2 = {"10B", "7B", "20B"};
        Assert.assertArrayEquals(t2, tree.listTree());
    }

    @Test
    public void testInsertRedMiddle() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(10);
        tree.put(7);
        tree.put(20);
        tree.put(-10);

        String[] t1 = {"10B", "7B", "-10R", "20B"};
        Assert.assertArrayEquals(t1, tree.listTree());

        /*
                     10B
                  ┌───┴───┐
                7B      20B
               ┌─┘
             -10R

                     ||  put 5, now node10 has two RED links
                     ||  rotate left and recursion
                     \/

                    10B
                 ┌───┴───┐
                5R      20B
               ┌─┴─┐
             -10B 7B
         */

        tree.put(5);

        String[] t2 = {"10B", "5R", "-10B", "7B", "20B"};
        Assert.assertArrayEquals(t2, tree.listTree());
    }

    @Test
    public void testDeleteRoot() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(5);
        tree.put(3);
        tree.put(8);

        String[] t1 = {"5B", "3B", "8R"};
        Assert.assertArrayEquals(t1, tree.listTree());

        /*
               5B
              ┌─┴─┐
             3B  8B

               ||
               ||   delete root
               \/

                8B
              ┌──┘
             3R
         */

        tree.delete(5);

        String[] t2 = {"8B", "3R"};
        Assert.assertArrayEquals(t2, tree.listTree());
    }

    @Test
    public void testDeleteRed() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(5);
        tree.put(3);
        tree.put(8);
        tree.put(-1);
        tree.put(4);

        String[] t1 = {"5B", "3R", "-1B", "4B", "8B"};
        Assert.assertArrayEquals(t1, tree.listTree());

        /*
                    5B
                 ┌───┴───┐
                3R      8B
               ┌─┴─┐
              -1B 4B

                    ||
                    ||  delete 3
                    \/

                    5B
                 ┌───┴───┐
                4B      8B
               ┌─┘
              -1R
         */

        tree.delete(3);

        String[] t = {"5B", "4B", "-1R", "8B"};
        Assert.assertArrayEquals(t, tree.listTree());
    }

    @Test
    public void testDeleteBlackHasRedBrother() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(5);
        tree.put(-2);
        tree.put(8);
        tree.put(7);

        String[] t1 = {"5B", "-2B", "8B", "7R"};
        Assert.assertArrayEquals(t1, tree.listTree());

        /*
                    5B
                 ┌───┴───┐
                -2B     8B
                       ┌─┘
                      7R

                    ||
                    ||  delete -2
                    \/

                    7B
                   ┌─┴─┐
                  5B  8B
         */

        tree.delete(-2);

        String[] t2 = {"7B", "5B", "8B"};
        Assert.assertArrayEquals(t2, tree.listTree());
    }

    @Test
    public void testDeleteBlackNoRedBrother() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(7);
        tree.put(5);
        tree.put(8);
        tree.put(-2);
        tree.put(6);

        String[] t1 = {"7B", "5R", "-2B", "6B", "8B"};
        Assert.assertArrayEquals(t1, tree.listTree());

        /*
                    7B
                 ┌───┴───┐
                5R      8B
               ┌─┴─┐
              -2B 6B

                    ||
                    || delete 6
                    \/

                    7B
                 ┌───┴───┐
                5B      8B
               ┌─┘
              -2R
         */

        tree.delete(6);

        String[] t = {"7B", "5B", "-2R", "8B"};
        Assert.assertArrayEquals(t, tree.listTree());
    }

    @Test
    public void testAddDeleteRandomOrder() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(90);
        tree.put(70);
        tree.put(43);

        /*
               70B
               ┌─┴─┐
              43B 90B
         */

        tree.delete(70);

        /*
              90B
              ┌─┘
            43R
         */

        tree.put(24);
        tree.put(14);
        tree.put(93);
        tree.put(47);

        /*
                            90B
                     ┌───────┴───────┐
                    43R             93B
                 ┌───┴───┐
                24B     47B
               ┌─┘
              14R
         */

        tree.delete(47);
        tree.delete(90);

        /*
                    24B
                 ┌───┴───┐
                14B     93B
                       ┌─┘
                      43R
         */

        tree.put(57);
        tree.put(1);
        tree.put(60);
        tree.put(47);

        /*
                            57B
                     ┌───────┴───────┐
                    24R             93B
                 ┌───┴───┐       ┌───┘
                14B     47B     60R
               ┌─┘     ┌─┘
              1R      43R
         */

        tree.delete(47);
        tree.delete(1);
        tree.delete(43);

        /*
                    57B
                 ┌───┴───┐
                24B     93B
               ┌─┘     ┌─┘
              14R     60R
         */

        tree.put(49);

        /*
                    57B
                 ┌───┴───┐
                24R     93B
               ┌─┴─┐   ┌─┘
              14B 49B 60R
         */

        String[] t1 = {"57B", "24R", "14B", "49B", "93B", "60R"};
        Assert.assertArrayEquals(t1, tree.listTree());
    }

    @Test
    public void testMin() {
        RedBlackTree<Integer> tree = buildTree(-10, 40);
        assertEquals(-10, tree.min());
    }

    @Test
    public void testMax() {
        RedBlackTree<Integer> tree = buildTree(-10, 40);
        assertEquals(40, tree.max());
    }

    @Test
    public void testDeleteMin() {
        RedBlackTree<Integer> tree = buildTree(-10, 40);
        Assert.assertNotNull(tree.get(-10));
        tree.deleteMin();
        Assert.assertNull(tree.get(-10));
    }

    @Test
    public void testDeleteMax() {
        RedBlackTree<Integer> tree = buildTree(-10, 40);
        Assert.assertNotNull(tree.get(40));
        tree.deleteMax();
        Assert.assertNull(tree.get(40));
    }

    private static RedBlackTree<Integer> buildTree(int min, int max) {
        RedBlackTree<Integer> tree = newTree();

        List<Integer> list = new ArrayList<Integer>();
        for (int i = min; i < max + 1; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        for (Integer integer : list) {
            tree.put(integer);
        }
        return tree;
    }

    @Test
    public void testCeil() {
        // add all the numbers 0-99 step 2
        RedBlackTree<Integer> tree = newTree();
        for (int i = 0; i < 100; i += 2) {
            tree.put(i);
        }
        // then search for the ceilings, knowing they are 1 up
        for (int i = 1; i < 99; i += 2) {
            assertEquals(i + 1, tree.ceiling(i));
        }
    }

    @Test
    public void testCeilSameValue() {
        RedBlackTree<Integer> tree = newTree();
        tree.put(10);
        tree.put(15);
        tree.put(20);
        tree.put(17);

        for (int i = 0; i < 11; i++) {
            assertEquals(10, tree.ceiling(i));
        }
        for (int i = 11; i < 16; i++) {
            assertEquals(15, tree.ceiling(i));
        }
        for (int i = 16; i < 18; i++) {
            assertEquals(17, tree.ceiling(i));
        }
        for (int i = 18; i < 21; i++) {
            assertEquals(20, tree.ceiling(i));
        }
    }

    @Test
    public void testFloor() {
        // add all the numbers 0-99 step 2
        RedBlackTree<Integer> tree = newTree();
        for (int i = 0; i < 100; i += 2) {
            tree.put(i);
        }

        // then search for the ceilings, knowing they are 1 up
        for (int i = 1; i < 99; i += 2) {
            assertEquals(i - 1, tree.floor(i));
        }
    }

    @Test
    public void testFloorSameValue() {
        RedBlackTree<Integer> tree = newTree();

        tree.put(10);
        tree.put(15);
        tree.put(20);
        tree.put(17);

        for (int i = 11; i < 15; i++) {
            assertEquals(10, tree.floor(i));
        }
        for (int i = 15; i < 17; i++) {
            assertEquals(15, tree.floor(i));
        }
        for (int i = 17; i < 20; i++) {
            assertEquals(17, tree.floor(i));
        }
        for (int i = 20; i < 50; i++) {
            assertEquals(20, tree.floor(i));
        }
    }

    private static void assertEquals(int expected, Integer actual) {
        Assert.assertEquals(expected, (int) actual);
    }
}

