package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBinarySearchTree {
    private BinarySearchTree<Integer> intBst;
    private BinarySearchTree<String> strBst;

    @BeforeEach
    public void setUp() {
        this.intBst = new BinarySearchTree<Integer>();
        this.strBst = new BinarySearchTree<String>();
    }

    @AfterEach
    public void tearDown() {
        this.intBst = null;
        this.strBst = null;
    }

    @Test
    public void testEmpty() {
        assertEquals("Size not 0", 0, intBst.size());
        assertEquals("Size not 0", 0, strBst.size());
        assertNull("Root node not null", intBst.root);
        assertNull("Root node not null", strBst.root);
        assertEquals("Height not 0", 0, strBst.height());
        assertEquals("Height not 0", 0, intBst.height());
    }

    @Test
    public void testClear() {
        addDefault();
        intBst.clear();
        strBst.clear();
        assertEquals("Size not 0", 0, intBst.size());
        assertEquals("Size not 0", 0, strBst.size());
        assertNull("Root node not null", intBst.root);
        assertNull("Root node not null", strBst.root);
    }

    @Test
    public void testSize() {
        addDefault();
        assertEquals("Size not correct", 5, intBst.size());
        assertEquals("Size not correct", 5, strBst.size());
        assertNotNull("Root node is Null", intBst.root);
        assertNotNull("Root node is Null", strBst.root);
    }

    @Test
    public void testHeight() {
        addDefault();
        assertEquals("Height not correct", 3, strBst.height());
        assertEquals("Height not correct", 3, intBst.height());
    }

    @Test
    public void testAdd() {
        intBst.add(7);
        intBst.add(2);
        strBst.add("dog");
        strBst.add("cat");
        intBst.add(2);
        strBst.add("cat");
        assertEquals("Size not correct", 2, intBst.size());
        assertEquals("Size not correct", 2, strBst.size());
        assertEquals("Root node is not correct", (Integer) 7, intBst.root.element);
        assertEquals("Root node is not correct", "dog", strBst.root.element);
        assertEquals("Left node is not correct", (Integer) 2, intBst.root.left.element);
        assertEquals("Left node is not correct", "cat", strBst.root.left.element);
        assertNull("Right node is not Null", intBst.root.right);
        assertNull("Right node is not Null", intBst.root.right);
        assertEquals("Height not correct", 2, strBst.height());
        assertEquals("Height not correct", 2, intBst.height());

    }

    // change print to test results instead of debug terminal
    @Test
    public void testSpeed() {
        long clearTime, sizeTime, heightTime, addTime, printTime, printTimerebuilt, rebuildTime, getTreeTime, drawTime,
                drawTimeRebuilt, approxTotalTime;
        int[] nbrOfElements = { 5, 10, 25 };

        for (int nbr : nbrOfElements) {
            long t0 = System.nanoTime();
            getTreeTime = getGetTime(nbr);
            rebuildTime = getRebuildTime(nbr);
            printTime = getPrintTime(nbr);
            printTimerebuilt = getPrintTimeRebuilt(nbr);
            drawTime = getDrawTime(nbr);
            drawTimeRebuilt = getDrawTimeRebuilt(nbr);
            clearTime = getClearTime(nbr);
            sizeTime = getSizeTime(nbr);
            heightTime = getHeightTime(nbr);
            long t1 = System.nanoTime();
            approxTotalTime = t1 - t0;

            long[] timeArr = { clearTime, sizeTime, heightTime, printTime, printTimerebuilt, rebuildTime,
                    getTreeTime, drawTime, drawTimeRebuilt, approxTotalTime };
            System.out.println("    -   -   -   times (" + nbr + "elements)-   -   -   ");
            for (long t : timeArr) {
                System.out.println(t);
            }
        }

    }

    private long getGetTime(int nbr) {
        long t0 = System.nanoTime();
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private long getRebuildTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.rebuild();
        strBst.rebuild();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private long getPrintTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.printTree();
        strBst.printTree();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private long getPrintTimeRebuilt(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        intBst.rebuild();
        strBst.rebuild();
        long t0 = System.nanoTime();
        intBst.printTree();
        strBst.printTree();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private long getDrawTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        intBst.rebuild();
        strBst.rebuild();
        BSTVisualizer bv = new BSTVisualizer("Binary Search Tree", 500, 500);
        BSTVisualizer bv2 = new BSTVisualizer("Binary Search Tree", 500, 500);
        long t0 = System.nanoTime();
        bv.drawTree(intBst);
        bv2.drawTree(strBst);
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private long getDrawTimeRebuilt(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        intBst.rebuild();
        strBst.rebuild();
        BSTVisualizer bv = new BSTVisualizer("Binary Search Tree", 500, 500);
        BSTVisualizer bv2 = new BSTVisualizer("Binary Search Tree", 500, 500);
        long t0 = System.nanoTime();
        bv.drawTree(intBst);
        bv2.drawTree(strBst);
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private long getClearTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.clear();
        strBst.clear();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private long getSizeTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.size();
        strBst.size();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private long getHeightTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.height();
        strBst.height();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    private void addDefault() {
        intBst.add(7);
        intBst.add(2);
        intBst.add(9);
        intBst.add(8);
        intBst.add(14);
        strBst.add("dog");
        strBst.add("cat");
        strBst.add("fish");
        strBst.add("bass");
        strBst.add("tuna");
    }

}
