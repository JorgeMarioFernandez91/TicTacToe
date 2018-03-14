/**
 * This class is meant to throw an exception if a key already exists in a collection
 * 
 * @author Jorge Fernandez
 *
 */
public class DuplicatedKeyException extends Exception{
	
	public DuplicatedKeyException (String config)
	  {
		super ("The " + config + "already exists in the dictionary!");
	  }

}
