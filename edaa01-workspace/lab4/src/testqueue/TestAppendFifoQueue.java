package testqueue;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Iterator;

//todo
//() kontrollera size vid varje
//() kontrollera ordning vid varje
//() kontrollera att andra är tom

class TestAppendFifoQueue {
    private FifoQueue<Integer> q1;
    private FifoQueue<Integer> q2;

    @BeforeEach
    void setUp() {
        q1 = new FifoQueue<Integer>();
        q2 = new FifoQueue<Integer>();
    }

    @AfterEach
    void tearDown() {
        q1 = null;
        q2 = null;
    }

    @Test
    void testEmptyAppendedToEmpty() {
        q1.append(q2);
        assertEquals(0, q1.size());
    }

    @Test
    void testEmptyAppendedToFilled() {
        q2.offer(1);
        q2.offer(5);
        q2.offer(3);
        q1.append(q2);
        Iterator<Integer> iter1 = q1.iterator();
        Iterator<Integer> iter2 = q2.iterator();
        assertEquals(q2.size(), q1.size(), "same size as filled que");
        while (iter1.hasNext()) {
            assertEquals(iter2.next(), iter1.next());
        }
    }

    @Test
    void testFilledAppendedToEmpty() {

    }

    @Test
    void testFilledAppendedToFilled() {

    }

    @Test
    void testSameQueueAppendedToItself() {

    }

}
