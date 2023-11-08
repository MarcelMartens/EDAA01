package queue_delegate;

import java.util.*;

//todo 
//() impl. metoder
//() 

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private Queue<E> list;

	// changed "this."
	public FifoQueue() {
		super();
		this.list = new LinkedList<E>();
	}

	/**
	 * Inserts the specified element into this queue, if possible
	 * post: The specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element
	 *         to this queue, else false
	 */
	// changed +"this.list.offer(e)" -"false"
	public boolean offer(E e) {
		return this.list.offer(e);
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	// changed +"this.list.size()" -"0"
	public int size() {
		return this.list.size();
	}

	/**
	 * Retrieves, but does not remove, the head of this queue,
	 * returning null if this queue is empty
	 * 
	 * @return the head element of this queue, or null
	 *         if this queue is empty
	 */
	// changed +"this.list.peek()" -"null"
	public E peek() {
		return this.list.peek();
	}

	/**
	 * Retrieves and removes the head of this queue,
	 * or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	// changed +"this.list.poll()"
	public E poll() {
		return this.list.poll();
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	// changed +"this.list.iterator()"
	public Iterator<E> iterator() {
		return this.list.iterator();
	}

}
