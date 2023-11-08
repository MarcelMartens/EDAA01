package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		this.last = null;
		this.size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible
	 * post: The specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element
	 *         to this queue, else false
	 */
	public boolean offer(E e) {
		if (this.last == null) {
			this.last = new QueueNode<E>(e);
			this.last.next = this.last;
			this.size++;
			return true;
		}
		try {
			QueueNode<E> first = this.last.next;
			this.last.next = new QueueNode<E>(e);
			this.last = this.last.next;
			this.size++;
			return true;
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue,
	 * returning null if this queue is empty
	 * 
	 * @return the head element of this queue, or null
	 *         if this queue is empty
	 */
	// change bättre condition?
	public E peek() {
		/*
		 * if (this.last == null){
		 * return null;
		 * }
		 */
		E ret = this.last == null ? null : this.last.next.element;
		return ret;
	}

	/**
	 * Retrieves and removes the head of this queue,
	 * or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		return null;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return null;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}