package testqueue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {
    private FifoQueue<Integer> testQueue;
    private FifoQueue<Integer> anotherQueue;

    @BeforeEach
    public void setUp() {
        testQueue = new FifoQueue<Integer>();
        anotherQueue = new FifoQueue<Integer>();
    }

    @AfterEach
    public void tearDown() {
        testQueue = null;
    }

    @Test
    public void testAppendTwoEmptyQueues() {
        testQueue.append(anotherQueue);
        assertTrue("Appending two empty queues should result in an empty queue", testQueue.size() == 0);
        assertTrue("The queue to be appended should be empty after operation", anotherQueue.size() == 0);
    }

    @Test
    public void testAppendNonEmptyQueueToEmptyQueue() {
        anotherQueue.offer(1);
        anotherQueue.offer(2);
        testQueue.append(anotherQueue);
        assertEquals("Appending a non-empty queue to an empty queue should result in a non-empty queue", 2,
                testQueue.size());
        assertTrue("The queue to be appended should be empty after operation", anotherQueue.size() == 0);
    }

    @Test
    public void testAppendEmptyQueueToNonEmptyQueue() {
        testQueue.offer(1);
        testQueue.offer(2);
        testQueue.append(anotherQueue);
        assertEquals("Appending an empty queue to a non-empty queue should not change the size", 2, testQueue.size());
    }

    @Test
    public void testAppendNonEmptyQueues() {
        testQueue.offer(1);
        testQueue.offer(2);
        anotherQueue.offer(3);
        anotherQueue.offer(4);
        testQueue.append(anotherQueue);
        assertEquals("Appending a non-empty queue should result in the correct size", 4, testQueue.size());
        assertArrayEquals("Appending a non-empty queue should have the correct order of elements",
                new int[] { 1, 2, 3, 4 }, queueToArray(testQueue));
    }

    @Test
    public void testAppendQueueToItself() {
        testQueue.offer(1);
        assertThrows(IllegalArgumentException.class, () -> testQueue.append(testQueue));
    }

    @Test
    public void testAppendToEnsureFirstQueueIsEmpty() {
        testQueue.offer(1);
        anotherQueue.offer(2);
        testQueue.append(anotherQueue);
        assertTrue("The appended queue should be empty after operation", anotherQueue.isEmpty());
    }

    private int[] queueToArray(FifoQueue<Integer> queue) {
        int[] result = new int[queue.size()];
        int i = 0;
        for (int elem : queue) {
            result[i++] = elem;
        }
        return (int[]) result;
    }
}
