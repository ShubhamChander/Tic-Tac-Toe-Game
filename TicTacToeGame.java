/**
  */

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;

//import java.lang.Math


public class TicTacToeGame{
  /** symbol for X */
  public static final Character ex = 'X';
  
  /** symbol for O */
  public static final Character oh = 'O';

  /** symbol for empty grid element */
  public static final Character empty = ' ';
  
  /** board is the grid that the game is played on; 
    * each element must be one of ex, oh or empty*/
  public static Character[][] board;
  
  
  /** rows is the number of rows (N) in the game board */
  public static int rows;
    
  /** columns is the number of columns (M) in the game board */
  public static int columns;
  
  /** win_condition is the value of K, the winning condition of the game */
  public static int win_condition; 
  
  /** specifies if the game is 1p (human-human) or 2p (human-computer) */
  public static boolean human_human_game;
  
  
  /** Checks for a win based on the last symbol played in the game
   * 
   * It is assumed that the position specified by the last_row_played
   * and last_column_played is valid and that the symbol in the board
   * at that position is not empty. (It must be <em>ex</em> or <em>oh</em>)
   * 
   * @param last_row_played is the row of the last symbol played
   * @param last_column_played is the column of the last symbol played
   * @return the length of the winning row/column/diagonal of symbols 
   * if the last move is a winning move, or -1 if the last move is not 
   * a winning move.
   */
   //createBoard() iterates through the board and intializes everything to empty
  public static void createBoard(){
	String lineDivide = ""; 
    // add code to initialize all elements to empty
    for(int i = 0; i < rows;i+=1){ 
		for(int j = 0; j<columns; j+=1){
			board[i][j] = empty;
		}
	}
  }
  // drawBoard() method iterates through the board and prints out a proper tic-tac-toe board
  public static void drawBoard(){
	  String lineDivide = "";
	  for(int i = 0; i < rows;i+=1){
		for(int j = 0; j<columns; j+=1){
			System.out.print((board[i][j]).toString() + "|");
		}
		System.out.print("\n");
		for(int k = 0; k < columns; k +=1){
			lineDivide += "-+";
		}
		System.out.print(lineDivide +" \n");
		lineDivide = "";
		
	}
	  
  }
  // WINS METHOD CHECKS THE BOARD FOR A WIN MOVE DETERMINED BY THE win_condition
  public static int win(int last_row_played, int last_column_played){
    // add your code and change the return value
	if((checkHorizontal(last_row_played,last_column_played)) == win_condition){
		return 12; 
	}
	
	if((checkVeritical(last_row_played,last_column_played)) == win_condition){
		return 12;
	}
	// (checks /    )
	// checkDiagonalRaise checks for a postive sloped diagonal win 
	if((checkDiagonalRaise(last_row_played,last_column_played)) == win_condition){
		return 12; 
	}
	/* (checks \   ) */
	// checkDiagonalRaise checks for a negative sloped diagonal win 
	if((checkDiagonalFall(last_row_played,last_column_played)) == win_condition){
		return 12;
	}
	

    return 0;
  }
  // Upon recieving the coordinates, the checkHorizontal method starts from the start of that row and iterates through that row and finds same symbols consecutively
  public static int checkHorizontal(int last_row_played,int last_column_played){
	  Character choice = board[last_row_played][last_column_played];
	  int count = 0;
	  int i = 0;
	  while(true){	 
		 if(count == win_condition || i == columns){
			 break;
		 }else{
			if(board[last_row_played][i] == choice){
				count +=1;
			}
			else{
				count = 0; 
			}
	  }  
	 i+=1;
  }
   return count; 
  }
  // Upon recieving the coordinates, the checkVertical method starts from the start of that row and iterates through that column and finds same symbols consecutively
  public static int checkVeritical(int last_row_played, int last_column_played){
	  Character choice = board[last_row_played][last_column_played];
	  int count = 0;
	  int i = 0;
	  while(true){
		  if(count == win_condition || i == rows){
			  break;
		  }else{
			  if(board[i][last_column_played] == choice){
				  count+=1;
			  }
			  else{
				  count = 0; 
			  }
		  }
		 i+=1;
	  }

	 return count; 
  }
  // Upon recieving the coordinates, the checkDiagonalRaise method starts from the end of the diagonal path and iterates through that diagonal path and finds same symbols consecutively
  public static int checkDiagonalRaise(int last_row_played,int last_column_played){
	  
		  Character choice = board[last_row_played][last_column_played];
		  int count = 0;
		  int count_max = 0;
		  int i = last_row_played;
		  int j = last_column_played;
		  
		  while(true){
			  if(i == rows - 1){
				  break;
			  }
			  else if(j == 0){
				  break;
			  }
			  else{
				  i+=1;
				  j-=1;
			  }
			  
			}
			if((i == 0 && j == 0) || (i == rows - 1 && j == columns - 1)){
				return 0; 
			}
			
			while(true){
			if(board[i][j] == choice){
				count+=1;
				if(count_max < count){
					count_max = count;
				}
			}
			else{
				count = 0;
			}
			i-=1;
			j+=1;
			if(i > rows-1 || i < 0){
				break;
			}
			else if(j > columns-1 ||j < 0){
				break;
			}
			}
	return count_max;
  }
  // Upon recieving the coordinates, the checkDiagonalFall method starts from the end of the diagonal path and iterates through that diagonal path and finds same symbols consecutively
 public static int checkDiagonalFall(int last_row_played, int last_column_played){
	 Character choice = board[last_row_played][last_column_played];
	 int count = 0; 
	 int count_max = 0;
	 int i = last_row_played;
	 int j = last_column_played;
	 
	 while(true){
		 if(i == 0){
			 break;
		 }
		 else if(j == 0){
			 break;
		 }
		 else{
			 i-=1;
			 j-=1;
		 }
		}
		 if((i== 0 && j == columns-1) || (i== rows-1 && j == columns-1)){
			 return count;
		 }
		 while(true){
			 if(board[i][j] == choice){
				count+=1;
				if(count_max < count){
					count_max = count;
				}
			}
			else{
				count = 0;
			}
			 
			 i+=1;
			 j+=1;
			 if(i > rows -1){
				 break;
			 }
			 else if(j > columns - 1){
				 break;
			 }
		 }
		 

	 return count_max;
 }
 //checkDraw() FUNCTION CHECKS FOR ANY EMPTY CHARACTER IN THE BOARD. IF THERE IS EVEN ONE EMPTY CHARACTER, THE FUNCTION RETURNS FALSE, ELSE IT RETURNS TRUE
 public static boolean checkDraw(){
	 for(int i = 0; i < rows;i+=1){ 
		for(int j = 0; j<columns; j+=1){
			if(board[i][j] == empty){
				return false;
			}
		}
	} 
	return true;
 }
  
  
  /** main method to run the game 
    * 
    * @param args optional command line arguments to 
    * specify if the game is 1p (human-computer) or
    * 2p (human-human). Expect the string "2p" if any
    * command line argument is given.
    */
  public static void main(String[] args){
	  //Variables used in program declared here
	  String Player_1;
	  String Player_2; 
	  int xCoor = -1;
	  int yCoor = -1;
	  int computer_x = -1;
	  int computer_y = -1; 
	  String comp_play = " ";
	  String yesNo = " "; 
	  String decision[];
	  int totalGames = 0; 
	  int Player_1_wins = 0;
	  int Player_2_wins = 0;
	  int Computer_wins = 0;
	  int draws = 0;
	  int bestWins = 0;
	  boolean swap = true; 
	  Character replace = ex; 
	  Character replace_2 = oh;  
	  
	  
	  // Variable declared for user input
	  Scanner keyboardScanner;
	  keyboardScanner = new Scanner(System.in);
    
    /*------------------------------------------
     * handle command line arguments if any     
     * to determine if the game is human-human  
     * or human-computer                        
     *------------------------------------------*/
    if( args.length > 0){
      /* there are commend line arguments present */
      // DETERMINES IF USER WISHES TO PLAY AGAINST A COMPUTER OR ANOTHER HUMAN
      // add your code here
	  String s = args[0];
	  if(s.equals("2p")){
		human_human_game = true;
	  }  
    }else{
      /* there are no command line argument present */
      // add your code here
	  human_human_game = false; 
    }
    
    /*------------------------------------
     * read N-M-K data from init file   
     * N = rows                         
     * M = columns                      
     * K = win_condition                
     *------------------------------------*/
    
    /*-------------------------------------
     *-------------------------------------
     * BEGIN : Do NOT change the code here
     *-------------------------------------*/
    BufferedReader file_input;
    FileReader     file;
    String         file_name = "init"; 
    String         line;
    
    try{
      file = new FileReader(file_name);
      file_input = new BufferedReader(file);
      
      line = file_input.readLine();
      rows = Integer.parseInt(line);
      
      line = file_input.readLine();
      columns = Integer.parseInt(line);
      
      line = file_input.readLine();
      win_condition = Integer.parseInt(line);
      
      /* always close your files you are done with them! */
      file_input.close();
      
    }catch(Exception e){
      /* somethine went wrong! */
      System.err.println("Failure to read data from init file properly");
      System.err.println(e);
      System.err.println("Program ending");
      return;
    }

    
    /*-------------------------------------
     * END : Do NOT change the code here
     *------------------------------------- 
     *-------------------------------------*/

    
    /* create and initialize the game board */
    
    /* allocate memory for the board array */
    board = new Character[rows][columns];
	// createBoard() FUNCTION WHICH INITIALIZES THE BOARD TO EMPTY. 
	createBoard();

    /* code to drive the game */
    
    // add your code here
	// --------------------------------------------------------- HUMAN VS HUMAN GAME STARTS HERE --------------------------------------
	if(human_human_game == true){
		replace = ex;
		replace_2 = oh;
		swap = true; 
		System.out.println("Welcome To Tic-Tac-Toe++ ");
		while(true){
		while(true){
		while(true){
		System.out.print("Player 1 input : ");
		Player_1 = keyboardScanner.nextLine();
		
		decision = Player_1.split(" ");
		if((decision[0].toUpperCase().equals("ROW")) || (decision[0].toUpperCase().equals("R"))){
			xCoor = Integer.parseInt(decision[1]);
			if((decision[2].toUpperCase().equals("COLUMN")) ||(decision[2].toUpperCase().equals("COL")) ||(decision[2].toUpperCase().equals("C"))){
			yCoor = Integer.parseInt(decision[3]);
			break;
			}
		}
		else if((decision[0].toUpperCase().equals("COLUMN")) ||(decision[0].toUpperCase().equals("COL")) ||(decision[0].toUpperCase().equals("C"))){
			yCoor = Integer.parseInt(decision[1]);
			if((decision[2].toUpperCase().equals("ROW")) || (decision[2].toUpperCase().equals("R"))){
				xCoor = Integer.parseInt(decision[3]);
				break;
			}
		}
		if(xCoor >= rows || xCoor < 0){
			System.out.println("That move is invalid. Please Try Again : ");
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
		}
		else if(yCoor >= columns || yCoor < 0){
			System.out.println("That move is invalid. Please Try Again : ");
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
		}
		else if(board[xCoor][yCoor] != empty){
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
			break;
		}		
		}
		
		board[xCoor][yCoor] = replace; 
		drawBoard();
		if((win(xCoor,yCoor)) == 12){
			System.out.println("Player 1 has won");
			totalGames +=1;
			Player_1_wins +=1;
			break; 
		}else{
			if(checkDraw() == true){
				System.out.println("The Game ends with a Draw.");
				draws +=1;
				totalGames +=1;
				break;
			}
		}
		for(int i = 0; i < decision.length;i+=1){
			decision[i] = " "; 
		}
		while(true){
		System.out.print("Player 2 input : ");
		Player_2 = keyboardScanner.nextLine();
		
		decision = Player_2.split(" ");
		
		if((decision[0].toUpperCase().equals("ROW")) || (decision[0].toUpperCase().equals("R"))){
			xCoor = Integer.parseInt(decision[1]);
			if((decision[2].toUpperCase().equals("COLUMN")) ||(decision[2].toUpperCase().equals("COL")) ||(decision[2].toUpperCase().equals("C"))){
				yCoor = Integer.parseInt(decision[3]);
			}
		}
		else if((decision[0].toUpperCase().equals("COLUMN")) ||(decision[0].toUpperCase().equals("COL")) ||(decision[0].toUpperCase().equals("C"))){
			yCoor = Integer.parseInt(decision[1]);
			if((decision[2].toUpperCase().equals("ROW")) || (decision[2].toUpperCase().equals("R"))){
				xCoor = Integer.parseInt(decision[3]);
			}
		}
		if(xCoor >= rows){
			System.out.println("That move is invalid. Please Try Again : ");
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
		}
		else if(yCoor >= columns){
			System.out.println("That move is invalid. Please Try Again : ");
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
		}
		else if(board[xCoor][yCoor] == empty){
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
			break;
		}
		else{ 
			System.out.println("That move is invalid. Please Try Again : ");
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
			
		}
		}
		board[xCoor][yCoor] = replace_2; 
		drawBoard();
		if((win(xCoor,yCoor)) == 12){
			System.out.println("Player 2 has won");
			totalGames +=1;
			Player_2_wins +=1;
			for(int i = 0; i < decision.length;i+=1){
			decision[i] = " "; 
			}
			break; 
		}else{
			if(checkDraw() == true){
				System.out.println("The Game ends with a Draw.");
				totalGames +=1;
				draws +=1;
				break;
			}
		}
		
		}
		createBoard();
		System.out.print("Would you like to play Again? : ");
		yesNo = keyboardScanner.nextLine();
		decision = yesNo.split(" ");
		String g = decision[0];
		if(g.equals("yes") || g.equals("y")){
			if(swap == true){
				replace = oh;
				replace_2= ex;
				System.out.print("Player 2 input : ");
				Player_2 = keyboardScanner.nextLine();
		
				decision = Player_2.split(" ");
		
				if((decision[0].toUpperCase().equals("ROW")) || (decision[0].toUpperCase().equals("R"))){
						xCoor = Integer.parseInt(decision[1]);
					if((decision[2].toUpperCase().equals("COLUMN")) ||(decision[2].toUpperCase().equals("COL")) ||(decision[2].toUpperCase().equals("C"))){
						yCoor = Integer.parseInt(decision[3]);
					}
				}
				else if((decision[0].toUpperCase().equals("COLUMN")) ||(decision[0].toUpperCase().equals("COL")) ||(decision[0].toUpperCase().equals("C"))){
					yCoor = Integer.parseInt(decision[1]);
					if((decision[2].toUpperCase().equals("ROW")) || (decision[2].toUpperCase().equals("R"))){
						xCoor = Integer.parseInt(decision[3]);
					}
				}
				
				board[xCoor][yCoor] = replace_2; 
				drawBoard();
			}
			else if(swap == false){
				swap = false; 
				replace = ex;
				replace_2 = oh;
				swap = true; 
			}
		}
		else{
			break;
		}
	}
		
	}
	/*
	-------------------------------------------- HUMAN VS HUMAN GAME ENDS HERE -----------------------------------------------
	*/
	/*
	--------------------------------------------- HUMAN VS COMPUTER GAME STARTS HERE -------------------------------------------
	*/
	
	swap = true; 
	if(human_human_game == false){
		// Player 1 plays here
		replace = ex; 
	    replace_2 = oh; 
		System.out.println("Welcome to Tic-Tac-Toe++");
		while(true){
		while(true){
		while(true){
		System.out.print("Player 1 input : ");
		Player_1 = keyboardScanner.nextLine();
		decision = Player_1.split(" ");

		if((decision[0].toUpperCase().equals("ROW")) || (decision[0].toUpperCase().equals("R"))){
			xCoor = Integer.parseInt(decision[1]);
			if((decision[2].toUpperCase().equals("COLUMN")) ||(decision[2].toUpperCase().equals("COL")) ||(decision[2].toUpperCase().equals("C"))){
			yCoor = Integer.parseInt(decision[3]);
			}
		}
		else if((decision[0].toUpperCase().equals("COLUMN")) ||(decision[0].toUpperCase().equals("COL")) ||(decision[0].toUpperCase().equals("C"))){
			yCoor = Integer.parseInt(decision[1]);
			if((decision[2].toUpperCase().equals("ROW")) || (decision[2].toUpperCase().equals("R"))){
				xCoor = Integer.parseInt(decision[3]);
			}
		}
		if(xCoor >= rows){
			System.out.println("That move is invalid. Please Try Again : ");
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
		}
		else if(yCoor >= columns){
			System.out.println("That move is invalid. Please Try Again : ");
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
		}
		else if(board[xCoor][yCoor] != empty){
			System.out.println("That move is invalid. Please Try Again : ");
			for(int i = 0; i < decision.length;i+=1){
				decision[i] = " "; 
			}
			
		}else{
			break;
		}
		}
		board[xCoor][yCoor] = replace; 
		drawBoard();
		if((win(xCoor,yCoor)) == 12){
			System.out.println("Player 1 has won");
			totalGames +=1;
			Player_1_wins +=1; 
			break;
		}else{
			if(checkDraw() == true){
				System.out.println("The Game ends with a Draw.");
				totalGames +=1;
				draws +=1;
				break;
			}
		}
		
		// Computer plays here
		while(true){
		computer_x =((int) Math.floor(Math.random()* rows));
		computer_y = ((int) Math.floor(Math.random() * columns));
		
			if(board[computer_x][computer_y] == empty){
				break;
			}
		
		}
			board[computer_x][computer_y] = replace_2;
		
		String x = Integer.toString(computer_x);
		String y = Integer.toString(computer_y);
		comp_play = "Computer Plays Row ";
		String COL = " COL ";
		comp_play += x;
		comp_play += COL;
		comp_play += y;
		
		System.out.println(comp_play);
		comp_play = " ";
		drawBoard();
		if((win(computer_x,computer_y)) == 12){
			totalGames +=1;
			System.out.println("Computer has won");
			Computer_wins +=1; 
			break;
		}
		else{
			if(checkDraw() == true){
				System.out.println("The Game ends with a Draw.");
				totalGames +=1;
				draws +=1;
				break;
			}
		}
	//totalGames +=1;
	}
	System.out.print("Would you like to Play Again? : ");
	yesNo = keyboardScanner.nextLine();
	decision = yesNo.split(" ");
	String f = decision[0];
	if(f.equals("no")|| f.equals("n")){
		break;
	}else if(f.equals("yes")|| f.equals("y")){
		createBoard();
		if(swap == true){
			//System.out.println("swap switch is working!");
			replace = oh;
			replace_2= ex;
 
			while(true){
				computer_x =((int) Math.floor(Math.random()* rows));
				computer_y = ((int) Math.floor(Math.random() * columns));
		
			if(board[computer_x][computer_y] == empty){
				break;
			}
			
			}
			board[computer_x][computer_y] = replace_2;
			String x = Integer.toString(computer_x);
			String y = Integer.toString(computer_y);
			comp_play = "Computer Plays Row ";
			String COL = " COL ";
			comp_play += x;
			comp_play += COL;
			comp_play += y;
		
			System.out.println(comp_play);
			comp_play = " ";
			drawBoard();
			swap = false;
		}
		else{
			swap = true;
			replace = ex;
			replace_2 = oh; 
		}
	}
	}
	}
	
	
	System.out.print("\n"); 
	System.out.print("Thanks for playing Tic-Tac-Toe++");
	System.out.print("\n");
	System.out.print("Total Games    : ");
	System.out.print(totalGames);
	System.out.print("\n");
	System.out.print("Player 1 wins  : ");
	System.out.print(Player_1_wins);
	System.out.print("\n");	
	if(Player_2_wins > 0){
	System.out.print("Player 2 wins  : ");
	System.out.print(Player_2_wins);
	System.out.print("\n");
	}
	else{
	System.out.print("Computer wins  : ");
	System.out.print(Computer_wins);
	System.out.print("\n");
	}
	System.out.print("Draws          : ");
	System.out.print(draws);
	System.out.print("\n");
	
	System.out.print("Best Wins      : ");
	if(Player_1_wins > 0 || Computer_wins > 0){
		System.out.print(win_condition);
	}else{
		System.out.print(0);
	}
	
	
	}
	
    
  }