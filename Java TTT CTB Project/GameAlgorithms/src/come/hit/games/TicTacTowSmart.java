package come.hit.games;

import java.util.ArrayList;
import java.util.List;

public class TicTacTowSmart extends TicTacTow {

    public TicTacTowSmart() {
        //      super(new int [3][3]);

    }
    
    private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;
        
		char[][] board = super.getBoard();
   
        // First cell
        if (board[row1][col1] == 'O') {
           score = 1;
        } else if (board[row1][col1] == 'X') {
           score = -1;
        }
   
        // Second cell
        if (board[row2][col2] == 'O') {
           if (score == 1) {   // cell1 is 'O'
              score = 10;
           } else if (score == -1) {  // cell1 is 'X'
              return 0;
           } else {  // cell1 is empty
              score = 1;
           }
        } else if (board[row2][col2] == 'X') {
           if (score == -1) { // cell1 is 'X'
              score = -10;
           } else if (score == 1) { // cell1 is 'O'
              return 0;
           } else {  // cell1 is empty
              score = -1;
           }
        }
   
        // Third cell
        if (board[row3][col3] == 'O') {
           if (score > 0) {  // cell1 and/or cell2 is 'O'
              score *= 10;
           } else if (score < 0) {  // cell1 and/or cell2 is 'X'
              return 0;
           } else {  // cell1 and cell2 are empty
              score = 1;
           }
        } else if (board[row3][col3] == 'X') {
           if (score < 0) {  // cell1 and/or cell2 is 'X'
              score *= 10;
           } else if (score > 1) {  // cell1 and/or cell2 is 'O'
              return 0;
           } else {  // cell1 and cell2 are empty
              score = -1;
           }
        }
        return score;
     }
    
    private int evaluate() {
        int score = 0;
        // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
        score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
        score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
        score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
        score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
        score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
        score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
        score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
        score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
        return score;
     }
    
    private int[] minimax(int depth, char player, int alpha, int beta) {
    	char[][] board = super.getBoard();
    	List<Integer> posLeft = new ArrayList<Integer>();
    	for(int i = 0; i < 3; i ++)
    		for(int j = 0; j < 3; j ++)
    			if(board[i][j] != 'O' && board[i][j] != 'X')
    				posLeft.add(i * 3 + j);
           
        // 'O' is maximizing; while 'X' is minimizing
        int score;
        int bestRow = -1;
        int bestCol = -1;
   
        if (posLeft.isEmpty() || depth == 0) {
           // Gameover or depth reached, evaluate score
           score = evaluate();
           return new int[] {score, bestRow, bestCol};
        } else {
           for (Integer pos : posLeft) {
        	   int[] move = new int[] {calcRaw(pos), calcCol(pos)};
              // try this move for the current "player"
              board[move[0]][move[1]] = player;
              if (player == 'O') {  // 'O' (computer) is maximizing player
                 score = minimax(depth - 1, 'X', alpha, beta)[0];
                 if (score > alpha) {
                    alpha = score;
                    bestRow = move[0];
                    bestCol = move[1];
                 }
              } else {  // 'X' is minimizing player
                 score = minimax(depth - 1, 'O', alpha, beta)[0];
                 if (score < beta) {
                    beta = score;
                    bestRow = move[0];
                    bestCol = move[1];
                 }
              }
              // undo move
              board[move[0]][move[1]] = '\0';
              // cut-off
              if (alpha >= beta) break;
           }
           return new int[] {(player == 'O') ? alpha : beta, bestRow, bestCol};
        }
     }

    @Override
    public void calcComputerMove() {
    	if (!super.isEnd()) {
    		int[] result2 = minimax(2, 'O', Integer.MIN_VALUE, Integer.MAX_VALUE);
    		int rx, ry;
    		rx = result2[1];
    		ry = result2[2];

    		char[][] board = super.getBoard();
    		board[rx][ry] = 'O';
        	List<Integer> posLeft = super.getPosLeft();
            posLeft.remove(new Integer(rx * 3 + ry));
            super.setPosLeft(posLeft);
            if (posLeft.size() < 5)
                checkWin(2);
        }
    }
}
