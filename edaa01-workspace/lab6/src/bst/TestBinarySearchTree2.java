package bst;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBinarySearchTree2 {
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
