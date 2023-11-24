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
		if (e == null)
			throw new NullPointerException();

		QueueNode<E> n0 = new QueueNode<E>(e);
		if (this.last == null)
			n0.next = n0;
		else {
			n0.next = this.last.next;
			this.last.next = n0;
		}
		this.last = n0;
		this.size++;
		return true;

		/*
		 * if (this.last == null) {
		 * this.last = new QueueNode<E>(e);
		 * this.last.next = this.last;
		 * this.size++;
		 * return true;
		 * }
		 * try {
		 * QueueNode<E> first = this.last.next;
		 * this.last.next = new QueueNode<E>(e);
		 * this.last = this.last.next;
		 * this.size++;
		 * return true;
		 * } catch (Exception error) {
		 * error.printStackTrace();
		 * return false;
		 * }
		 */
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
		if (this.last == null)
			return null;
		return this.last.next.element;
	}

	/**
	 * Retrieves and removes the head of this queue,
	 * or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	// change två ternaries i samma block?
	public E poll() {
		if (this.last == null) {
			return null;
		}
		E e1 = this.last.next.element;
		this.last.next = (this.last.next == this.last) ? null : this.last.next.next;
		this.last = (this.last.next == null) ? null : this.last;
		this.size = (this.last == null) ? 0 : this.size--;
		return e1;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator(this);
	}

	/**
	 * Appends the specified queue to this queue
	 * post: all elements from the specified queue are appended
	 * to this queue. The specified queue (q) is empty after the call.
	 * 
	 * @param q the queue to append
	 * @throws IllegalArgumentException if this queue and q are identical
	 */
	public void append(FifoQueue<E> q) {
		if (q == this) {
			throw new IllegalArgumentException("Cannot conkatenate queue with itself");
		}
		if (q.last != null) {
			if (this.last != null) {
				QueueNode<E> first = this.last.next;
				this.last.next = q.last.next;
				q.last.next = first;
				this.last = q.last;
				this.size += q.size;
			} else {
				this.last = q.last;
				this.size = q.size;
			}
			q.last = null;
			q.size = 0;
		}

	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> node = (last == null) ? null : last.next;
		private int remain = size;

		private QueueIterator(FifoQueue<E> q) {

			this.node = (q.last != null) ? q.last.next : null;
			this.remain = (q.last != null) ? q.size : 0;
		}

		public boolean hasNext() {
			return (this.remain > 0);
		}

		public E next() {
			if (this.remain <= 0)
				throw new NoSuchElementException();
			E e = this.node.element;
			this.node = this.node.next;
			this.remain--;
			return e;
		}

	}

}