package come.hit.games;

import com.hit.gameAlgo.GameBoard;

public abstract class CatchTheBunny extends GameBoard {

    private int numOfMovesLeft;
    private int BPosX; //COMPUTER X POSITION
    private int BPosY; //COMPUTER Y POSITION
    private int PPosX; //PLAYER X POSITION
    private int PPosY; //PLAYER Y POSITION

    public CatchTheBunny(int numOfMovesBegin) {
        super(new char[9][9]);
        this.numOfMovesLeft = numOfMovesBegin;
        BPosX=1;
        BPosY=1;
        PPosX=5;
        PPosY=6;
        char[][] board= new char[9][9];
        board[BPosX][BPosY] = 'B';
        board[PPosX][PPosY] = 'P';
        super.setBoard(board);
    }

     // todo: ending game by finishing moves.
    @Override
    public boolean updatePlayerMove(String move) {
    	
        if(!super.isEnd()) {

            if (move.equals("W")) {
                if (PPosX == 0) 
                    return false;
                PPosX -= 1;
            } else if (move.equals("S")) {
                if (PPosX == 8) 
                    return false;
                PPosX += 1;
            } else if (move.equals("A")) {
                if (PPosY == 0) 
                    return false;
                PPosY -= 1;
            } else if (move.equals("D")) {
                if (PPosY == 8) 
                    return false;
                PPosY += 1;
            }
            char[][] board = new char[9][9];
            if (BPosX == PPosX && BPosY == PPosY) {
                super.setEnd(true, 1);
                board[BPosX][BPosY] = 'W';
                super.setEnd(true, 1);
            } else {
                board[PPosX][PPosY] = 'P';
                board[BPosX][BPosY] = 'B';
                numOfMovesLeft --;
                if (numOfMovesLeft == 0)
                    super.setEnd(true, 2);
            }
            super.setBoard(board);
            return true;
        }
        else // the game is already over
            return false;
    }

    public int getBPosX(){
        return BPosX;
    }
    public int getNumOfMovesLeft(){
        return numOfMovesLeft;
    }
    public void setNumOfMovesLeft(int numOfMovesLeft){
        this.numOfMovesLeft = numOfMovesLeft;
    }
    public int getBPosY(){
        return BPosY;
    }
    public int getPPosX(){
        return PPosX;
    }
    public int getPPosY(){
        return PPosY;
    }
    public void setBPosX(int BPosX){
        this.BPosX=BPosX;
    }
    public void setBPosY(int BPosY){
        this.BPosY=BPosY;
    }
}
