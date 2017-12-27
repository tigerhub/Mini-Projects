
public class TicTacToe implements GameGrid {
	/*  Cells in the grid have three possible values, -1, 0, or 1 as follows:
	 *      1 = "O".... User's turn
	 *      0 = Vacant
	 *      -1 = "X" - Computer's turn is ALWAYS "X"
	 */
	private int[][] grid;
	private int boardDim = 3;
	private int winNum = 3;
	private GridCoord [][] diagCoords;
	private int squaresFilled = 0;

	// constructor method
	public TicTacToe(int boardDim) {
		this.boardDim = boardDim;
		grid = new int[boardDim][boardDim];

		if(boardDim > 3) {
			winNum = 4;
		}
		squaresFilled = 0;
		
		generateDiagonals();
	}

	public void setBoardDim(int boardDim) {
		this.boardDim = boardDim;
	}

	//  This method returns the value of the grid array at [x][y]
	public int getValueAt(int x, int y){
		return grid[x][y];
	}

	public void setXAt(int x, int y){
		grid[x][y] = -1;
		squaresFilled++;
	}
	public void setOAt(int x, int y){
		grid[x][y] = 1;
		squaresFilled++;
	}

	// this method checks to see if the current player has won
	// and returns -1 if X wins, 1 if O wins, 2 if it's a tie, and 0 if neither has won yet
	public int whoWon ()
	{
		for(int i = 0; i < grid.length; i++) {
			int userInRow = 0;
			int compInRow = 0;
			for(int j = 0; j < grid[i].length; j++) {
				if(grid[i][j] == 1) {
					compInRow = 0;
					userInRow++;
				}
				else if(grid[i][j] == -1) {
					userInRow = 0;
					compInRow++;
				}
				else {
					userInRow = 0;
					compInRow = 0;
				}
				if(userInRow == winNum) {
					return 1;
				}
				if(compInRow == winNum) {
					return -1;
				}
			}
		}
		for(int i = 0; i < grid[0].length; i++) {
			int userInCol = 0;
			int compInCol = 0;
			for(int j = 0; j < grid.length; j++) {
				if(grid[j][i] == 1) {
					compInCol = 0;
					userInCol++;
				}
				else if(grid[j][i] == -1) {
					userInCol = 0;
					compInCol++;
				}
				else {
					userInCol = 0;
					compInCol = 0;
				}
				if(userInCol == winNum) {
					return 1;
				}
				if(compInCol == winNum) {
					return -1;
				}
			}
		}
		for(int i = 0; i < diagCoords.length; i++) {
			int userInDiag = 0;
			int compInDiag = 0;
			for(int j = 0; j < diagCoords[i].length; j++) {
				if(grid[diagCoords[i][j].x][diagCoords[i][j].y] == 1) {
					compInDiag = 0;
					userInDiag++;
				}
				else if(grid[diagCoords[i][j].x][diagCoords[i][j].y] == -1) {
					userInDiag = 0;
					compInDiag++;
				}
				else {
					userInDiag = 0;
					compInDiag = 0;
				}
				if(userInDiag == winNum) {
					return 1;
				}
				if(compInDiag == winNum) {
					return -1;
				}
			}
		}
		// Tie.
		if(squaresFilled == boardDim * boardDim) {
			return 2;
		}
		// The game isn't over yet.
		return 0;
	}

	//Artificial Intelligence:
	//Your code for the computer's next move should go here
	public void nextTurn(){
		
		if(squaresFilled == boardDim * boardDim) {
			return;
		}
		
		if(squaresFilled == 0) {
			int startMoveX = boardDim / 2;
			int startMoveY = boardDim / 2;
			setXAt(startMoveX, startMoveY);
			return;
		}
		
		
		int[][] scores = new int[boardDim][boardDim];
		for(int i = 0; i < scores.length; i++) {
			for(int j = 0; j < scores[i].length; j++) {
				scores[i][j] = -100;
			}
		}
		
		for(int col = 0; col < grid.length; col++) {
			for(int row = 0; row < grid[col].length; row++) {
				
				if(grid[col][row] != 0) {
					continue;
				}
		
		
		
				scores[col][row] = 0;
				
				int possibleRow = row;
				
				
				int rowScoreL = 0;
				int rowScoreR = 0;
				int colScoreB = 0;
				int colScoreT = 0;
				int diagScore1 = 0;
				int diagScore2 = 0;
				boolean curColLeftOk = true;
				boolean curColRightOk = true;
				boolean curRowBottomOk = true;
				boolean curRowTopOk = true;
				boolean [] curDiagOk = new boolean[4];
	
				for(int i = 0; i < 4; i++) {
					curDiagOk[i] = true;
				}
	
				System.out.println("testing1");
				for(int i = 1; i < winNum; i++) {
					int curColLeft = col - i;
					int curColRight = col + i;
					int curRowBottom = possibleRow + i;
					int curRowTop = possibleRow - i;
					int sameTeamValue = 50;
					int otherTeamValue = 50;
					int neutralSpaceVal = 1;
	
	
					System.out.println(possibleRow + " " + curColLeft + " " + curColRight + " " + curRowBottom);
	
					if(curColLeft >= 0 && curColLeftOk) {
						if(grid[curColLeft][possibleRow] == 1) {
							rowScoreL += otherTeamValue;
							//curColLeftOk = false;
						}
						else if(grid[curColLeft][possibleRow] == -1) {
							curColLeftOk = false;
							//rowScoreL += sameTeamValue;
						}
						else {
							rowScoreL += neutralSpaceVal;
						}
					}
	
					if(curColRight < boardDim && curColRightOk) {
						if(grid[curColRight][possibleRow] == 1) {
							rowScoreR += otherTeamValue;
							//curColRightOk = false;
						}
						else if(grid[curColRight][possibleRow] == -1) {
							curColRightOk = false;
							//rowScoreR += sameTeamValue;
						}
						else {
							rowScoreR += neutralSpaceVal;
						}
					}
	
					if(curRowBottom < boardDim && curRowBottomOk) {
						if(grid[col][curRowBottom] == 1) {
							colScoreB += otherTeamValue;
							//curRowBottomOk = false;
						}
						else if(grid[col][curRowBottom] == -1) {
							curRowBottomOk = false;
							//colScoreB += sameTeamValue;
						}
						else {
							colScoreB += neutralSpaceVal;
						}
					}
					
					if(curRowTop >= 0 && curRowTopOk) {
						if(grid[col][curRowTop] == 1) {
							colScoreT += otherTeamValue;
							//curRowTopOk = false;
						}
						else if(grid[col][curRowTop] == -1) {
							curRowTopOk = false;
							//colScoreT += sameTeamValue;
						}
						else {
							colScoreT += neutralSpaceVal;
						}
					}
	
					int diagCount = 0;
					while(diagCount < 4) {
						int curX = 0;
						int curY = 0;
						switch(diagCount) {
						case 0:
							curX = curColLeft;
							curY = curRowBottom;
							break;
						case 1:
							curX = curColRight;
							curY = curRowTop;
							break;
						case 2:
							curX = curColLeft;
							curY = curRowTop;
							break;
						case 3:
							curX = curColRight;
							curY = curRowBottom;
							break;
						}
						int curDiagScore = 0;
						
						System.out.println("case " + diagCount + " curX " + curX + " curY " + curY + " curcol " + col + " currow " + row);
						
						if((curX >= 0 && curX < boardDim) && (curY >= 0 && curY < boardDim)) {
							
							System.out.println("testing12345");
							
							if(curDiagOk[diagCount]) {
								
								System.out.println("CURX " + curX + " CURY " + curY);
								
								if(grid[curX][curY] == 1) {
									curDiagScore = otherTeamValue;
									System.out.println("curds " + curDiagScore);
									//curDiagOk[diagCount] = false;
								}
								else if(grid[curX][curY] == -1) {
									curDiagOk[diagCount] = false;
									//curDiagScore += sameTeamValue;
								}
								else {
									curDiagScore = neutralSpaceVal;
								}
							}
						}
						
						System.out.println(curDiagScore + " a " + diagScore1 + " b " + diagScore2);
						
						if(diagCount < 2) {
							diagScore1 += curDiagScore;
						}
						else {
							diagScore2 += curDiagScore;
						}
						
						diagCount++;
					}
				}
				int rowScore = rowScoreL + rowScoreR;
				int colScore = colScoreB + colScoreT;
				/*// Two diagonals means the weight of the diagonal score is twice as much
				// as it should be.
				diagScore = diagScore / 2;*/
				if(rowScore > colScore) {
					scores[col][row] = rowScore;
				}
				else {
					scores[col][row] = colScore;
				}
				if(diagScore1 > scores[col][row]) {
					scores[col][row] = diagScore1;
				}
				if(diagScore2 > scores[col][row]) {
					scores[col][row] = diagScore2;
				}
				System.out.println("scores: " + scores[col][row] + " r " + rowScore + " c " + colScore + " d1 " + diagScore1  + " d2 " + diagScore2 + " i " + col + " j " + row);
			}
		}

		// Get the maximum (best for computer) score.
		int maxScore = -100;
		GridCoord maxScoreIndex = new GridCoord(0, 0);
		for(int i = 0; i < scores.length; i++) {
			for(int j = 0; j < scores[i].length; j++) {
				if(scores[i][j] >= maxScore) {
					if(scores[i][j] == maxScore) {
						// If the index we are thinking of choosing is further from the center,
						// then it's more likely to block the opponent and is a better strategic move.
						if(getDistanceFromCenter(new GridCoord(i, j)) > getDistanceFromCenter(maxScoreIndex)) {
							maxScore = scores[i][j];
							maxScoreIndex = new GridCoord(i, j);
						}
					}
					else {
						maxScore = scores[i][j];
						maxScoreIndex = new GridCoord(i, j);
					}
				}
			}
		}
		
		System.out.println("FINAL SCORE: " + scores[maxScoreIndex.x][maxScoreIndex.y] + " r " + " i " + maxScoreIndex.x + " j " + maxScoreIndex.y);
		
		setXAt(maxScoreIndex.x, maxScoreIndex.y);
		
	}

	// Stores diagonal squares to make it much easier to deal with diagonals later on.
	private void generateDiagonals() {
		int numDiags = 0;
		if(boardDim == 3 || boardDim == 4) {
			numDiags = 2;
		}
		else {
			numDiags = 2 * ((boardDim - 4) * 2 + 1);
		}
		diagCoords = new GridCoord[numDiags][];


		int diagSquares = winNum;
		int halfDiags = numDiags / 2;
		int diagSet = 0;
		while(diagSet < numDiags) {
			diagSquares = winNum;
			int x = 0;
			int y = winNum - 1;
			if(diagSet > 0) {
				y = boardDim - winNum;
			}
			//System.out.println("diagSet " + diagSet);
			for(int i = 0; i < halfDiags; i++) {
				//System.out.println(" dss " + diagSquares);
				if(i < (int)(halfDiags / 2)) {
					diagCoords[diagSet + i] = new GridCoord[diagSquares];
					diagSquares++;
				}
				else if(i == (int)(halfDiags / 2)) {
					diagCoords[diagSet + i] = new GridCoord[boardDim];
				}
				else {
					diagSquares--;
					diagCoords[diagSet + i] = new GridCoord[diagSquares];
				}
				diagCoords[diagSet + i][0] = new GridCoord(x, y);
				//System.out.println(x + " abc " + y + " dsi " + (diagSet + i));
				if(diagSet == 0) {
					if(y < boardDim - 1) {
						y++;
					}
					else {
						x++;
					}
				}
				else {
					if(y > 0) {
						y--;
					}
					else {
						x++;
					}
				}
			}
			diagSet += numDiags / 2;
		}

		for(int i = 0; i < diagCoords.length; i++) {
			int x = diagCoords[i][0].x;
			int y = diagCoords[i][0].y;
			for(int j = 1; j < diagCoords[i].length; j++) {
				if(i < diagCoords.length / 2) {
					x++;
					y--;
				}
				else {
					x++;
					y++;
				}
				diagCoords[i][j] = new GridCoord(x, y);
			}
		}

		for(int i = 0; i < numDiags; i++) {
			for(int j = 0; j < diagCoords[i].length; j++) {
				System.out.println(diagCoords[i][j].x + " " + diagCoords[i][j].y);
			}
		}
	}
	
	private double getDistanceFromCenter(GridCoord coord) {
		return Math.sqrt(Math.pow((coord.x - (double)(boardDim / 2)), 2) + Math.pow((coord.y - (double)(boardDim / 2)), 2));
	}
}