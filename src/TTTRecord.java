/**
 * TTTRecord represents a record which will store the information needed
 * for the program to play. The record is then stored in the hash table (dictionary)
 *
 * @author Jorge Fernandez
 */

public class TTTRecord {

	private String config;
	
	private int score;
	
	private int level;

	/**
	 * Creates a record containing a configuration, a score, and a level
	 * 
	 * @param config the key used to store and access the record from the dictionary
	 * @param score a value assigned to the record which helps the AI determine if it's won, lost, tied, or still playing
	 * @param level the level of the tree
	 */
	public TTTRecord(String config, int score, int level)
	{
		this.config = config;
		this.score = score;
		this.level = level;
	}
	
	/**
	 * Gets the string configuration stored in the record
	 * 
	 * @return returns the configuration
	 */
	public String getConfiguration()
	{
		return this.config;
	}
	
	/**
	 * Gets the score stored in the record
	 * 
	 * @return returns the score
	 */
	public int getScore()
	{
		return this.score;
	}
	
	/**
	 * Gets the level stored in the record
	 * 
	 * @return returns the level
	 */
	public int getLevel()
	{
		return this.level;
	}
}
