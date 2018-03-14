/**
 * CS-2210 Assignment 2
 * BlockedTicTacToe.java
 * The purpose of this class is for it to be used by the AI to play tic-tac-toe against a human opponent. This class will create a dictionary
 * (a hash table) to store information used by the AI to help determine what move to perform next. This class will also determine if who has won
 * and what the state of the board looks like.
 * 
 * @author Jorge Fernandez
 * @version 1.0 2017-10-19
 */
public class BlockedTicTacToe {
	
	private int board_size;
	
	private int inline;
	
	private int max_levels;
	
	private char[][] gameBoard;
	
	/**
	 * Creates a game board of a certain size, with a win condition , and the amount of levels used by the tree
	 * 
	 * @param board_size the size of one side of the game board square
	 * @param inline the amount of x's or o's in a row needed for a win
	 * @param max_levels the number of levels explored by the tree
	 */
	public BlockedTicTacToe (int board_size, int inline, int max_levels)
	{
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		
		this.gameBoard = new char[this.board_size][this.board_size];
		/*
		 * this for loop initializes all spots in the game board 
		 * with empty spots rather than leaving it null
		 */
		for (int i = 0; i < this.board_size; i++)
		{
			for (int j = 0; j < this.board_size; j++)
			{
				this.gameBoard[i][j] = ' ';
			}
		}
	}
	
	/**
	 * creates an empty dictionary
	 * 
	 * @return returns the dictionary created 
	 */
	public TTTDictionary createDictionary()
	{
		TTTDictionary<Object> dict = new TTTDictionary<Object>();
		
		return dict;
	}
	
	/**
	 * checks if a configuration exists in the dictionary
	 * 
	 * @param configurations this is the dictionary which stores all types of game board configurations
	 * @return return -1 if configuration is not found
	 */
	public int repeatedConfig(TTTDictionary configurations)
	{
		String data = "";
		//for loop converts gets every character from the game board and concatenates them into a string
		for (int i = 0; i < this.board_size; i++)
		{
			for (int j = 0; j < this.board_size; j++)
			{
				{
					data = data + Character.toString(this.gameBoard[i][j]);
				}
			}
		}
		//check to see if the string of the game board configuration exists in the dictioanry
		if (configurations.get(data) != null)
		{
			return  (configurations.get(data)).getScore();
		}
		
		return -1;
	}
	
	/**
	 * inserts the configuration, score, and level into a record
	 * 
	 * @param configurations the key used to identify the record
	 * @param score score assigned to each configuration
	 * @param level the number of levels searched by the tree
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level)
	{
		String data = "";
		//converts the game board into a string
		for (int i = 0; i < this.board_size; i++)
		{
			for (int j = 0; j < this.board_size; j++)
			{
				{
					data = data + Character.toString(this.gameBoard[i][j]);
				}
			}
		}	
		//create a record object to store data
		TTTRecord rec = new TTTRecord(data,score,level);
		
		//try to insert the record into the dictionary or else throw exception
		try 
		{
			configurations.put(rec);
		} 
		catch (DuplicatedKeyException e) 
		{
			System.out.println(e);
		}
	}
	
	/**
	 * Stores the a play into the game board
	 * 
	 * @param row the row of the game board
	 * @param col the column of the game board
	 * @param symbol the symbol used (x or o)
	 */
	public void storePlay(int row, int col, char symbol)
	{
		this.gameBoard[row][col] = symbol;
	}
	
	/**
	 * Checks to see if a square is empty in the game board
	 * 
	 * @param row the row of the game board
	 * @param col the column of the game board
	 * @return return true if square is empty or else return false
	 */
	public boolean squareIsEmpty(int row, int col)
	{
		char empty = ' ';
		
		boolean result = false;
		
		if (this.gameBoard[row][col] == empty)
		{
			result = true;
		}
		
		return result;
	}
	
	/**
	 * checks to see if either the computer has won or if the human has won
	 * 
	 * @param symbol the symbol used, if 'x' then check if human won, else if 'o' check if computer has won
	 * @return return true if there is a winner, winner depends on the symbol
	 */
	public boolean wins(char symbol)
	{
		//return false if there is no winner
		boolean result = false;
		
		char x = 'x';
		char o = 'o';
		
		int k = inline;
		
		int horizontalX = 0;
		int horizontalO = 0;
		
		int verticalX = 0;
		int verticalO = 0;
		
		int diagonalX = 0;
		int diagonalO = 0;
		
		
		if (symbol == 'x')
		{
			//checked if human has matched k horizontally
			for (int i = 0; i < this.board_size; i++)
			{
				for (int j = 0; j < this.board_size; j++)
				{
					if (this.gameBoard[i][j] == x)
					{
						horizontalX = horizontalX + 1;
					}
					if (j == (this.board_size - 1) && horizontalX != k || this.gameBoard[i][j] != x)
					{
						horizontalX = 0;
					}
					else if (horizontalX == k)
					{
						horizontalX = 0;
						return true;
					}
				}
			}
			//checks if human has matched k vertically
			for (int m = 0; m < this.board_size; m++)
			{
				for (int l = 0; l < this.board_size; l++)
				{
					if (this.gameBoard[l][m] == x)
					{
						verticalX = verticalX + 1;
					}
					if (l == (this.board_size - 1) && verticalX != k  || this.gameBoard[l][m] != x)
					{
						verticalX = 0;
					}
					else if (verticalX == k)
					{
						verticalX = 0;
						return true;
					}
				}
			}
			//checks if human has matched k diagonally left-right
			for (int p = 0; p < this.board_size; p++)
			{
				for (int q = 0; q < this.board_size; q++)
				{
					if (this.gameBoard[p][q] == x)
					{
						int a = p;
						int b = q;
						while (a < this.board_size && b < this.board_size && this.gameBoard[a][b] == x)
						{
							diagonalX = diagonalX + 1;
							
							a++;
							b++;
						}
					}
					if (diagonalX != k)
					{
						diagonalX = 0;
					}
					else if (diagonalX == k)
					{
						diagonalX = 0;
						return true;
					}
				}
			}
			//checks if human has matched k diagonally right-left
			for (int p = 0; p < this.board_size; p++)
			{
				for (int q = 0; q < this.board_size; q++)
				{
					if (this.gameBoard[p][q] == x)
					{
						int a = p;
						int b = q;
						while (a < this.board_size && b >= 0 && b < this.board_size && this.gameBoard[a][b] == x && diagonalX != k)
						{
							diagonalX = diagonalX + 1;
							
							a++;
							b--;
						}
					}
					if (diagonalX != k)
					{
						diagonalX = 0;
					}
					else if (diagonalX == k)
					{
						diagonalX = 0;
						return true;
					}
				}
			}
		}
		//checks if computer has matched k horizontally
		else if (symbol == 'o')
		{
			for (int i = 0; i < this.board_size; i++)
			{
				for (int j = 0; j < this.board_size; j++)
				{
					if (this.gameBoard[i][j] == o)
					{
						horizontalO = horizontalO + 1;
					}
					if (j == (this.board_size - 1) && horizontalO != k || this.gameBoard[i][j] != o)
					{
						horizontalO = 0;
					}
					else if (horizontalO == k)
					{
						horizontalO = 0;
						return true;
					}
				}
			}
			//checks if computer has matched k vertically
			for (int m = 0; m < this.board_size; m++)
			{
				for (int l = 0; l < this.board_size; l++)
				{
					if (this.gameBoard[l][m] == o)
					{
						verticalO = verticalO + 1;
					}
					if (l == (this.board_size - 1) && verticalO != k || this.gameBoard[l][m] != o)
					{
						verticalO = 0;
					}
					else if (verticalO == k)
					{
						verticalO = 0;
						return true;
					}
				}
			}
			//checks if computer has matched k diagonally left-right
			for (int p = 0; p < this.board_size; p++)
			{
				for (int q = 0; q < this.board_size; q++)
				{
					if (this.gameBoard[p][q] == o)
					{
						int a = p;
						int b = q;
						while (a < this.board_size && b < this.board_size && this.gameBoard[a][b] == o)
						{
					
							diagonalO = diagonalO + 1;
							
							a++;
							b++;
						}
					}
					if (diagonalO != k)
					{
						diagonalO = 0;
					}
					else if (diagonalO == k)
					{
						diagonalO = 0;
						return true;
					}
				}
			}
			//checks if computer has matched k diagonally right-left
			for (int p = 0; p < this.board_size; p++)
			{
				for (int q = 0; q < this.board_size; q++)
				{
					if (this.gameBoard[p][q] == o)
					{
						int a = p;
						int b = q;
						
						while (a < this.board_size && b >= 0 && b < this.board_size && this.gameBoard[a][b] == o && diagonalO != k)
						{
							diagonalO = diagonalO + 1;
							
							a++;
							b--;
						}
					}
					if (diagonalO != k)
					{
						diagonalO = 0;
					}
					else if (diagonalO == k)
					{
						diagonalO = 0;
						return true;
					}
				}
			}
		}

		return result;
	}
	/**
	 * Checks to see if the whole board has been filled,
	 * if it has and no one has won, then return true
	 * 
	 * @return returns true if no one has won or false if there is a winner or there is an empty spot available
	 */
	public boolean isDraw()
	{
		boolean result = true;
		
		String data = "";
		//converst the board to a string
		for (int i = 0; i < this.board_size; i++)
		{
			for (int j = 0; j < this.board_size; j++)
			{
				{
					data = data + Character.toString(gameBoard[i][j]);
				}
			}
		}
		//checks the string to see if there are any empty spots left in the table
		for (int i = 0; i < data.length(); i++)
		{
			if (data.charAt(i) == ' ')
			{
				return false;
			}
		}
		//checks if there is an empty square and that no one has won
		for (int l = 0; l < this.board_size; l++)
		{
			for (int m = 0; m < this.board_size; m++)
			{
				if (squareIsEmpty(l, m) == false && wins('x') == false && wins('o') == false)
				{
					return true;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Evaluates the board to see if there is winner, a loser, or if the game is still in progress
	 * 
	 * @return returns 0 if human wins, returns 1 if game is a draw, returns 2 if game is in progress, returns 3 if computer has won
	 */
	public int evalBoard()
	{
		int result = 2;
		
		char x = 'x';
		char o = 'o';
		
		if (wins(x) == true)
		{
			result = 0;
		}
		else if (wins(o) == true)
		{
			result = 3;
		}
		else if (isDraw() == true)
		{
			result = 1;
		}
		else
		{
			//check to see if game is still in progress 
			for (int i = 0; i < this.board_size; i++)
			{
				for (int j = 0; j < this.board_size; j++)
				{
					if (squareIsEmpty(i, j) == true)
					{
						result = 2;
					}
				}
			}
		}
		
		return result;
	}
}
