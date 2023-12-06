package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
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

    /**
     * tests the speed of BST methods for tree sizes 5, 10, 25, 50 and 100
     */
    @Test
    @Ignore("Disabled because of the time the tests takes")
    public void testSpeed() {

        // declaring all time variables and the sizes to be tested
        String[] varNames = new String[] { "clearTime", "sizeTime", "heightTime", "printTime", "printTimeRebuilt",
                "rebuildTime", "getTreeTime", "drawTime", "drawTimeRebuilt" };
        int[] nbrOfElements = { 5, 10, 25, 50, 100 };
        ArrayList<String> results = new ArrayList<>();

        // note creates subklass to HashMap. Not optimal, but test is not frequently ran
        // adds all time variables to a hashmap
        Map<String, Long> timeMap = new HashMap<>() {
            {
                for (String s : varNames) {
                    put(s, 0L);
                }
            }
        };

        // runs all speed tests for the different tree sizes
        for (int nbr : nbrOfElements) {
            for (String varName : varNames) {
                try {
                    // Constructs method name and invokes it
                    Method method = this.getClass().getDeclaredMethod("get" + capitalize(varName), int.class);
                    long time = (long) method.invoke(this, nbr);

                    // Updates the map
                    timeMap.put(varName, time);
                } catch (Exception e) {
                    e.printStackTrace();
                    fail("Failed to invoke method for variable name: " + varName);
                }
            }
            // appends all results to a string
            StringBuilder sb = new StringBuilder("\n \n  -   -   -   Runtimes for " + nbr + " elements  -   -   -   ");
            for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
                sb.append("\n");
                sb.append(entry.getKey());
                sb.append("   -   ");
                sb.append((double) entry.getValue() / 1000000);
                sb.append(" ms");
            }
            // saves the completed result to an arraylist
            results.add(sb.toString());
        }
        // prints all results from test
        for (String resultString : results) {
            System.out.println(resultString);
        }
    }

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute the get tree commands
     */
    @SuppressWarnings("unused")
    private long getGetTreeTime(int nbr) {
        long t0 = System.nanoTime();
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        intBst = BinarySearchTree.getIncementedIntTree(nbr);
        long t1 = System.nanoTime();
        return (t1 - t0) / 3;
    }

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute rebuild
     */
    @SuppressWarnings("unused")
    private long getRebuildTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.rebuild();
        strBst.rebuild();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute print
     */
    @SuppressWarnings("unused")
    private long getPrintTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.printTree();
        strBst.printTree();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute print on rebuilt tree
     */
    @SuppressWarnings("unused")
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

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute draw
     */
    @SuppressWarnings("unused")
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

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute draw on a rebuilt tree
     */
    @SuppressWarnings("unused")
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

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute clear
     */
    @SuppressWarnings("unused")
    private long getClearTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.clear();
        strBst.clear();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute size
     */
    @SuppressWarnings("unused")
    private long getSizeTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.size();
        strBst.size();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    /**
     * 
     * @param nbr number of elements in the tested tree
     * @return the average time taken to execute height
     */
    @SuppressWarnings("unused")
    private long getHeightTime(int nbr) {
        intBst = BinarySearchTree.getRandomIntTree(nbr);
        strBst = BinarySearchTree.getDefaultStringTree(nbr);
        long t0 = System.nanoTime();
        intBst.height();
        strBst.height();
        long t1 = System.nanoTime();
        return (t1 - t0) / 2;
    }

    /**
     * capitalizes the first character in a string
     * 
     * @param str string to be capitalized
     * @return the capitalized string
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
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
