/**
 * Linear node represents a node which will store TTTRecords
 * These nodes are used for separate chaining
 *
 * @author Jorge Fernandez
 */

public class LinearNode<T> {
	
	LinearNode<T> next;
	T element;
	
	/**
	 * Creates an empty node.
	 */
	public LinearNode()
	{
		next = null;
		element = null;
	}
	
	/**
	 * Creates a node storing the specified element
	 * 
	 * @param rec an element to be stored within the new node
	 */
	public LinearNode(T rec) 
	{
		next = null;
		element = rec;
	}

	/**
	 * Returns the node that follows this one
	 * 
	 * @return the node that follows the current one
	 */
	public LinearNode<T> getNext()
	{
		return next;
	}
	
	/**
	 * Sets the node that follows this one
	 * 
	 * @param element  the node to be set to follow the current one
	 */
	public void setNext(LinearNode<T> element)
	{
		next = element;
	}
	
	
	/**
     * Sets the element stored in this node.
     *
     * @param elem  the element to be stored in this node
     */
	public void setElement(T rec)
	{
		element = rec;
	}
	
	/**
	 * Returns the element stored in this node
	 * 
	 * @return returns the element stored in this node
	 */
	public T getElement()
	{
		return element;
	}

}
