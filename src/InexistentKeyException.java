/**
 * This class is meant to throw an exception when a key is searched for and not found
 * 
 * @author Jorge Fernandez
 */

public class InexistentKeyException extends Exception{
	
	public InexistentKeyException (String config)
	  {
		super ("The " + config + "does not exist!");
	  }

}
