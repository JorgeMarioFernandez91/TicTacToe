/**
 * TTTDictionary is a hash table which stores objects of type TTTRecord. The dictionary itself is 
 * an array but each index in the array references a linked list. This is called separate chaining.
 * 
 * @author Jorge Fernandez
 *
 */

public class TTTDictionary<T> implements TTTDictionaryADT{
	
	private LinearNode<T> front = null;
	
	/**
	 * x is used by the hash function to help distribute TTTRecords evenly in the dictionary
	 */
	private final int x = 47;
	
	private T[] dictionary;
	
	private int numEle = 0;
	
	private int size;
	
	//this variable sets the size of a dictionary
	private final int TABLE_SIZE = 4001;
	
	/**
	 * Creates a dictionary of predetermined size
	 */
	@SuppressWarnings("unchecked")
	public TTTDictionary()
	{
		this.dictionary = (T[]) new Object [TABLE_SIZE];
	}
	
	/**
	 * Creates a dictionary of custom size
	 * 
	 * @param size a custom size set by the user
	 */
	@SuppressWarnings("unchecked")
	public TTTDictionary(int size)
	{
		this.size = size;
		this.dictionary = (T[]) new Object [this.size];
	}
	
	/**
	 * This method returns an index position in the dictionary where TTTRecord will be stored
	 * 
	 * @param S the key used to determine where the TTTRecord will be stored
	 * @param size the size of the dictionary
	 * @param x the custom value used to help ensure uniform distribution within the dictionary
	 * @return
	 */
	private int hashFunction(String S, int size, int x)
	{
		//stores the value of the last character in the string
		int value = (int)S.charAt(S.length()-1);
		//keep looping until all characters in the string have been computed
		for (int i = S.length()-2; i >= 0; i--)
		{
			value = (value*x + (int)S.charAt(i))%size;
		}
		
		return value;
	}
	
	/**
	 * Inserts the record into the dictionary
	 * 
	 * @param record the object to be stored
	 * @return returns 0 if there is no collision or 1 if there is a collision
	 * @throws DuplicatedKeyException error is thrown if a key already exists in the dictioanry
	 */
	@SuppressWarnings("unchecked")
	public int put(TTTRecord record) throws DuplicatedKeyException
	{	
		//pos is the index in the dictionary where the key will be stored
		int pos = hashFunction(record.getConfiguration(), this.dictionary.length, x);
		//create a node to store the TTTRecord
		LinearNode<T> node = new LinearNode<T>();
		//store the TTTRecord in the node
		node.setElement((T) record);
		//this is a reference to the index where the node will be linked
		T a = this.dictionary[pos];
		//create a head pointer to keep track of the node
		LinearNode<T> head = (LinearNode<T>) a;
		//create a current pointer to make insertion simpler
		LinearNode<T> current = (LinearNode<T>) a;
		//search through list to see if configuration exists. If it does throw exception
		while (current != null && !(current.getElement()).equals(record.getConfiguration()))
		{
			if ((((TTTRecord) current.getElement()).getConfiguration()).equals(record.getConfiguration()))
			{
				throw new DuplicatedKeyException(record.getConfiguration());
			}
			
			current = current.getNext();
		}
		/*
		 * If no node exists at the designated index then make that index reference that node
		 * or else insert the node before the first node at that index.
		 * Also, return 1 if there is a collision or 0 if there is no collision
		 */
		if (this.dictionary[pos] != null)
		{
			node.setNext((LinearNode<T>) a);
			head = node;
			this.dictionary[pos] = (T) head;
			return 1;
		}
		else if (this.dictionary[pos] == null)
		{
			front = node;
			this.dictionary[pos] = (T) front;
			return 0;
		}
		//add 1 to the number of elements in the dictionary
		numEle = numEle + 1;
		
		return 0;
	}

	/**
	 * remove a configuration from the dicrionary if it exists, if not then return an error
	 * 
	 * @param config the key used to find the record in the dictionary
	 * @throws InexistentKeyException error thrown if key being looked for doesn't exist
	 */
	@SuppressWarnings({ "unchecked"})
	public void remove(String config) throws InexistentKeyException
	{
		//the positions where the string is stored
		int pos = hashFunction(config, this.dictionary.length, x);
		//this is a reference to the node first node at pos
		front = (LinearNode<T>) this.dictionary[pos];
		//current is a pointer used to help remove a node if it exists
		LinearNode<T> current = front;
		//if the position where the hash function references is empty then return exception
		if (front == null)
		{
			throw new InexistentKeyException(config);
		}
		//if the first key is the one to remove then set front = key.getNext()
		if ((((TTTRecord) front.getElement()).getConfiguration()).equals(config))
		{
			front = front.getNext();
			return;
		}
		while (current != null && (!((TTTRecord) current.getElement()).getConfiguration().equals(config)))
		{
			//previous = current;
			current = current.getNext();
		}
		//subtracts from the total elements in the dictionary
		numEle = numEle - 1;
	}

	/**
	 * get the string stored in the dictionary
	 * 
	 * @param config the key used to find the record
	 * @return return the configuration
	 */
	@SuppressWarnings("unchecked")
	public TTTRecord get(String config) 
	{
		int pos = hashFunction(config, dictionary.length, x);
		
		T a = this.dictionary[pos];
		
		LinearNode<T> current = (LinearNode<T>) a;
		
		if (a == null)
		{
			return null;
		}
		else if (((((TTTRecord) current.getElement()).getConfiguration()).equals(config)))
		{
			return (TTTRecord) current.getElement();
		}
		else
		{
			while ((!(((TTTRecord) current.getElement()).getConfiguration()).equals(config)))
			{
				current = current.getNext();
				
				if (current == null)
				{
					return null;
				}
			}
		}
		return  (TTTRecord) current.getElement();
	}

	/**
	 * returns the number of elements in the dictionary
	 * 
	 * @return return number of elements 
	 */
	public int numElements() 
	{
		return numEle;
	}
	
}

