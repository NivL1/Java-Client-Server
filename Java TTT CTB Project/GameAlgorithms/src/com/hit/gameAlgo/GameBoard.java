package com.hit.gameAlgo;

import java.util.Scanner;

enum State
{
    WIN, LOSE , TIE, INPROG;
}

public abstract class GameBoard implements  IGameAlgo{

    State state;
    static String newLine = System.getProperty("line.separator");
    static Scanner scanIn = new Scanner(System.in);


    private  boolean end;
    private  char[][] board;

    public GameBoard(char[][] board) {
        this.end = false;
        this.board = board;
        this.state = State.INPROG;
    }

     public int getGameState()
     {
         //todo:print board
         if(end == true) {
             System.out.print("The Game Is Over, Result Is: ");
             System.out.println(this.state);
         }
         else System.out.println("The Game is still in progress");

             int rows = getBoardRows();
             for (int i = 0; i < rows; ++i) {
                 for (int k = 0; k < rows; k++)
                     System.out.print("+---");
                 System.out.println("+");
                 for (int j = 0; j < getBoardCols(); ++j)
                     System.out.printf("| %s ", board[i][j] == 0? ' ' : board[i][j]);
                 System.out.println("|");
             }
             for (int k = 0; k < rows; k++)
                 System.out.print("+---");
             System.out.println("+");
         
         if( state == State.WIN )
        	 return 1;
         else if (state == State.LOSE)
        	 return 2;
         else if (state == State.TIE)
        	 return 3;
         else return 4;
     }
     
    public char[][] getBoardState() {
    	return board;
    }

    public int getBoardRows(){ return board.length;}

    public int getBoardCols(){ return board[0].length;}

    public boolean isEnd() {
        return end;
    }



    public void setEnd(boolean end, int index) {
        this.end = end;
        if(end) {
            if (index == 1)
                this.state = State.WIN;
            else if (index == 2)
                this.state = State.LOSE;
            else if (index == 3)
                this.state = State.TIE;
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
