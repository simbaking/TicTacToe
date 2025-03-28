package Main;

import java.util.Scanner;

public class tictactoe {
	
    private String[][] board;
    private String currentPlayer;
    //private int boardsize;

    public tictactoe(int boardsize) {
        board = new String[boardsize][boardsize];
        currentPlayer = "X"; // Starting player
        initializeBoard(boardsize);
    }

    public String[][] copyBoard(String[][] oldBoard, int boardsize) {
    	String[][] newBoard = new String[boardsize][boardsize];
    	for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                newBoard[i][j] = oldBoard[i][j];
            }
        }
    	return newBoard;
    }
    
    private void initializeBoard(int boardsize) {
        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                board[i][j] = "-";
            }
        }
    }

    public void printBoard(int boardsize) {
        System.out.println("Current Board:");
        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull(int boardsize) {
        for (int i = 0; i < boardsize; i++) {
            for (int j = 0; j < boardsize; j++) {
                if (board[i][j] == "-") {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkForWin(int boardsize) {
        // Check rows, columns, and diagonals
    	boolean thisWin = true;
        for (int i = 0; i < boardsize; i++) {
        	thisWin = true;
        	while (thisWin) {
        		for (int j = 0; j < boardsize; j++) {
        			if (board[i][j] != currentPlayer) {
        				j = boardsize + 1;
        				thisWin = false;
        			} 
        		}
        		if (thisWin) {
        			return true;
        		}
        	}
        	thisWin = true;
        	while (thisWin) {
        		for (int j = 0; j < boardsize; j++) {
        			if (board[j][i] != currentPlayer) {
        				j = boardsize + 1;
        				thisWin = false;
        			} 
        		}
        		if (thisWin) {
        			return true;
        		}
        	}
        }
    	thisWin = true;
        for (int i = 0; i < boardsize; i++) {
        	if (board[i][i] != currentPlayer) {
				thisWin = false;
			}
        }
        if (thisWin) {
			return true;
		}
    	thisWin = true;
        for (int i = 0; i < boardsize; i++) {
        	if (board[i][boardsize - (i+1)] != currentPlayer) {
				thisWin = false;
			}
        }
        if (thisWin) {
			return true;
		}
        return false;
    }
    
    public boolean checkForWin(String[][] board, int boardsize) {
        // Check rows, columns, and diagonals
    	boolean thisWin = true;
        for (int i = 0; i < boardsize; i++) {
        	thisWin = true;
        	while (thisWin) {
        		for (int j = 0; j < boardsize; j++) {
        			if (board[i][j] != currentPlayer) {
        				j = boardsize + 1;
        				thisWin = false;
        			} 
        		}
        		if (thisWin) {
        			return true;
        		}
        	}
        	thisWin = true;
        	while (thisWin) {
        		for (int j = 0; j < boardsize; j++) {
        			if (board[j][i] != currentPlayer) {
        				j = boardsize + 1;
        				thisWin = false;
        			} 
        		}
        		if (thisWin) {
        			return true;
        		}
        	}
        }
    	thisWin = true;
        for (int i = 0; i < boardsize; i++) {
        	if (board[i][i] != currentPlayer) {
        		i = boardsize + 1;
				thisWin = false;
			}
        }
        if (thisWin) {
			return true;
		}
    	thisWin = true;
        for (int i = 0; i < boardsize; i++) {
        	if (board[i][boardsize - (i+1)] != currentPlayer) {
        		i = boardsize + 1;
				thisWin = false;
			}
        }
        if (thisWin) {
			return true;
		}
        return false;
    }
    
    public boolean canWin(int boardsize) {
    	
        // Checks if theres a winning move
    	
    	boolean thisCanWin = true;
    	
    	String[][] winCheckBoard = copyBoard(board, boardsize);
    	
    	thisCanWin = false;
    	
        for (int i = 0; i < boardsize; i++) {
    		for (int j = 0; j < boardsize; j++) {
    			if (winCheckBoard[i][j] == "-") {
        			winCheckBoard[i][j] = currentPlayer;
                }
    			if (checkForWin(winCheckBoard, boardsize)) {
    				thisCanWin = true;
    			} 
    			winCheckBoard = copyBoard(board, boardsize);
    		}
    	}
        if (thisCanWin) {
			return true;
		}
        return false;
    }
    
    public boolean canLose(int boardsize) {
    	
        // Checks if theres a winning move
    	
    	boolean thisCanlose = true;
    	
    	String[][] loseCheckBoard = copyBoard(board, boardsize);
    	
    	thisCanlose = false;
    	
        for (int i = 0; i < boardsize; i++) {
    		for (int j = 0; j < boardsize; j++) {
    			changePlayer();
    			if (loseCheckBoard[i][j] == "-") {
        			loseCheckBoard[i][j] = currentPlayer;
                }
    			if (checkForWin(loseCheckBoard, boardsize)) {
    				thisCanlose = true;
    			} 
    			loseCheckBoard = copyBoard(board, boardsize);
    			changePlayer();
    		}
    	}
        if (thisCanlose) {
			return true;
		}
        return false;
    }
    
    public int[] winningMove(int boardsize) {
    	
        // gives winning move in int array
    	int[] winningCor = new int[2];
    	
    	String[][] winCheckBoard = copyBoard(board, boardsize);
    	
        for (int i = 0; i < boardsize; i++) {
    		for (int j = 0; j < boardsize; j++) {
    			winCheckBoard[i][j] = currentPlayer;
    			if (checkForWin(winCheckBoard, boardsize)) {
    				winningCor[0] = i;
    				winningCor[1] = j;
    				return winningCor;
    			} 
    			winCheckBoard = copyBoard(board, boardsize);
    		}
    	}
        
		return winningCor;
		
    }
    
    public int[] losingMove(int boardsize) {
    	
        // gives defeating move
    	int[] losingCor = new int[2];
    	
    	String[][] loseCheckBoard = copyBoard(board, boardsize);
    	
        for (int i = 0; i < boardsize; i++) {
    		for (int j = 0; j < boardsize; j++) {
    			changePlayer();
    			loseCheckBoard[i][j] = currentPlayer;
    			if (checkForWin(loseCheckBoard, boardsize)) {
    				losingCor[0] = i;
    				losingCor[1] = j;
    				i = boardsize + 1;
    				j = boardsize + 1;
    			}
    			loseCheckBoard = copyBoard(board, boardsize);
    			changePlayer();
    		}
    	}
        
		return losingCor;
		
    }
    
    public int[] bestMove(String[][] board, int boardsize, int current) {
    	
    	current = current + 1;
    	
    	
    	String[][] bestBoard = new String[boardsize][boardsize];
    	
    	for (int i = 0; i < boardsize; i++) {
    		for (int j = 0; j < boardsize; j++) {
    			
    			bestBoard[i][j] = board[i][j];
    			
    		}
    	}
    	
    	
    	int row;
    	int col;
    	int[] bestCor = new int[2];
    	int[] currentCor = new int[2];
    	
    	
    	if(nextWin(boardsize, bestBoard)) {
    		
    		
    		int[] winningCor = winningMove(boardsize);
    		row = winningCor[0];
    		col = winningCor[1];
    		
    		
    		while(row < 0 || col < 0 || row >= boardsize || col >= boardsize || bestBoard[row][col] != "-") {
        		
        		row = (int)((Math.random()*(boardsize)));
                col = (int)((Math.random()*(boardsize)));

        	}
        	
        	
            if (row < 0 || col < 0 || row >= boardsize || col >= boardsize || bestBoard[row][col] != "-") {
                System.out.println("This move is not valid");
            }
            
            bestCor = winningCor;
    		
    	}
    	
    	else if(nextLose(boardsize, bestBoard)) {
    		
    		
    		//plays move that would make them lose if other player played it
    		//System.out.println("oooooohhh no!");
    		int[] losingCor = losingMove(boardsize);
    		row = losingCor[0];
    		col = losingCor[1];
    		
    		
    		while(row < 0 || col < 0 || row >= boardsize || col >= boardsize || bestBoard[row][col] != "-") {
        		
        		row = (int)((Math.random()*(boardsize)));
                col = (int)((Math.random()*(boardsize)));

        	}
        	
        	
            if (row < 0 || col < 0 || row >= boardsize || col >= boardsize || bestBoard[row][col] != "-") {
                System.out.println("This move is not valid");
            }
            
            bestCor = losingCor;
    		
    	}
    	
    	else {
    		
    		row = (int)((Math.random()*(boardsize)));
            col = (int)((Math.random()*(boardsize)));
            
        	
        	while(row < 0 || col < 0 || row >= boardsize || col >= boardsize || bestBoard[row][col] != "-") {
        		
        		row = (int)((Math.random()*(boardsize)));
                col = (int)((Math.random()*(boardsize)));

        	}
        	
        	
            if (row < 0 || col < 0 || row >= boardsize || col >= boardsize || bestBoard[row][col] != "-") {
                System.out.println("This move is not valid");
            }
            
            if(current < 2) {
            	currentCor[0] = row;
            	currentCor[1] =	col;	
            }
            

            bestBoard[row][col] = currentPlayer;
            
            //changePlayer();
                        
            bestCor = bestMove(bestBoard, boardsize, current);
            
            
    	}
    	

        //changePlayer();
        return currentCor;
        
    }
    
    public boolean nextWin(int boardsize, String[][] bestBoard) {
    	
        // Checks if theres a winning move
    	
    	boolean thisCanWin = true;
    	
    	String[][] winCheckBoard = copyBoard(bestBoard, boardsize);
    	
    	thisCanWin = false;
    	
        for (int i = 0; i < boardsize; i++) {
    		for (int j = 0; j < boardsize; j++) {
    			if (winCheckBoard[i][j] == "-") {
        			winCheckBoard[i][j] = currentPlayer;
                }
    			if (checkForWin(winCheckBoard, boardsize)) {
    				thisCanWin = true;
    			} 
    			winCheckBoard = copyBoard(bestBoard, boardsize);
    		}
    	}
        if (thisCanWin) {
			return true;
		}
        return false;
    }
    
    public boolean nextLose(int boardsize, String[][] bestBoard) {
    	
        // Checks if theres a winning move
    	
    	boolean thisCanlose = true;
    	
    	String[][] loseCheckBoard = copyBoard(bestBoard, boardsize);
    	
    	thisCanlose = false;
    	
        for (int i = 0; i < boardsize; i++) {
    		for (int j = 0; j < boardsize; j++) {
    			changePlayer();
    			if (loseCheckBoard[i][j] == "-") {
    				//changePlayer();
        			loseCheckBoard[i][j] = currentPlayer;
        			//changePlayer();
                }
    			if (checkForWin(loseCheckBoard, boardsize)) {
    				thisCanlose = true;
    			} 
    			loseCheckBoard = copyBoard(bestBoard, boardsize);
    			changePlayer();
    		}
    	}
        if (thisCanlose) {
			return true;
		}
        return false;
    }
    

    public void changePlayer() {
        currentPlayer = (currentPlayer == "X") ? "O" : "X";
    }

    public void play(int boardsize) {
    	
    	Scanner playScanner = new Scanner(System.in);
        
        while (true) {
            printBoard(boardsize);
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            int row = (playScanner.nextInt())-1;
            int col = (playScanner.nextInt())-1;

            if (row < 0 || col < 0 || row >= boardsize || col >= boardsize || board[row][col] != "-") {
                System.out.println("This move is not valid");
                continue;
            }

            board[row][col] = currentPlayer;

            if (checkForWin(boardsize)) {
                printBoard(boardsize);
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull(boardsize)) {
                printBoard(boardsize);
                System.out.println("It's a tie!");
                break;
            }

            changePlayer();
        }
        playScanner.close();
    }
    
    
    public void play(int boardsize, boolean vsCpuBool) {
    	
    	Scanner playScanner = new Scanner(System.in);
        
        while (true) {
            printBoard(boardsize);
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            int row = (playScanner.nextInt())-1;
            int col = (playScanner.nextInt())-1;

            if (row < 0 || col < 0 || row >= boardsize || col >= boardsize || board[row][col] != "-") {
                System.out.println("This move is not valid");
                continue;
            }

            board[row][col] = currentPlayer;

            if (checkForWin(boardsize)) {
                printBoard(boardsize);
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull(boardsize)) {
                printBoard(boardsize);
                System.out.println("It's a tie!");
                break;
            }
            
            if(vsCpuBool) {
            	
            	
            	changePlayer();
            	
            	
            	if(canWin(boardsize)) {
            		
            		
            		System.out.println("ha ha ha");
            		int[] winningCor = winningMove(boardsize);
            		row = winningCor[0];
            		col = winningCor[1];
            		
            		
            		while(row < 0 || col < 0 || row >= boardsize || col >= boardsize || board[row][col] != "-") {
                		
                		row = (int)((Math.random()*(boardsize)));
                        col = (int)((Math.random()*(boardsize)));

                	}
                	
                	
                    if (row < 0 || col < 0 || row >= boardsize || col >= boardsize || board[row][col] != "-") {
                        System.out.println("This move is not valid");
                        continue;
                    }
            		
            	}
            	
            	else if(canLose(boardsize)) {
            		
            		
            		//plays move that would make them lose if other player played it
            		System.out.println("oooooohhh no!");
            		int[] losingCor = losingMove(boardsize);
            		row = losingCor[0];
            		col = losingCor[1];
            		
            		
            		while(row < 0 || col < 0 || row >= boardsize || col >= boardsize || board[row][col] != "-") {
                		
                		row = (int)((Math.random()*(boardsize)));
                        col = (int)((Math.random()*(boardsize)));

                	}
                	
                	
                    if (row < 0 || col < 0 || row >= boardsize || col >= boardsize || board[row][col] != "-") {
                        System.out.println("This move is not valid");
                        continue;
                    }
            		
            	}
            	
            	else if(vsCpuBool) {
            		int[] winningCor = bestMove(board, boardsize, 0);
            		row = winningCor[0];
            		col = winningCor[1];
            		
            	}
            	
            	else {
            		
            		row = (int)((Math.random()*(boardsize)));
                    col = (int)((Math.random()*(boardsize)));
                	
                	while(row < 0 || col < 0 || row >= boardsize || col >= boardsize || board[row][col] != "-") {
                		
                		row = (int)((Math.random()*(boardsize)));
                        col = (int)((Math.random()*(boardsize)));

                	}
                	
                	
                    if (row < 0 || col < 0 || row >= boardsize || col >= boardsize || board[row][col] != "-") {
                        System.out.println("This move is not valid");
                        continue;
                    }
            	}
            	

                board[row][col] = currentPlayer;

                if (checkForWin(boardsize)) {
                    printBoard(boardsize);
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                }

                if (isBoardFull(boardsize)) {
                    printBoard(boardsize);
                    System.out.println("It's a tie!");
                    break;
                }
            }
            changePlayer();
        }
        playScanner.close();
    }


    public static void main(String[] args) {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	
    	System.out.println("How many players will be playing; 1 or 2");
        int vsCpuInt = scanner.nextInt();
        
        boolean vsCpuBool = false;
        
        if(vsCpuInt == 1) {
        	vsCpuBool = true;
        }
        
        if(vsCpuInt == 2) {
        	vsCpuBool = false;
        }
        
        
    	System.out.println("How big will your board be.");
        int boardsize = scanner.nextInt();
        tictactoe game = new tictactoe(boardsize);
        game.play(boardsize,vsCpuBool);
        scanner.close();
        
    }
}
