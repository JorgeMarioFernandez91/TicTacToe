# TicTacToe
A TicTacToe like game (4 in a row wins)

The classes of interset are:

    TTTDictionary.java is a hash table which stores objects of type TTTRecord. The dictionary itself is 
    an array but each index in the array references a linked list. This is called separate chaining.
    
    BlockedTicTacToe.java is for an AI to play tic-tac-toe against a human opponent. This class will create a
    dictionary (a hash table) to store information used by the AI to help determine what move to perform next. 
    This class will also determine if who has won and what the state of the board looks like. 
    
    PlayTTT.java runs the necessary files for the application. 
  
