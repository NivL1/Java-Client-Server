package come.hit.games;

import com.hit.gameAlgo.GameBoard;

import java.util.ArrayList;
import java.util.List;

public abstract class TicTacTow extends GameBoard {

    private List<Integer> posLeft;

    public TicTacTow() {
        super(new char[3][3]);
        posLeft = new ArrayList<Integer>();
        for(int i = 0 ; i<9 ; i++)
            posLeft.add(i);
    }

    public List<Integer> getPosLeft() {
        return posLeft;
    }

    public void setPosLeft(List<Integer> posLeft) {
        this.posLeft = posLeft;
    }

    @Override
    public boolean updatePlayerMove(String move) {
        if(!super.isEnd()) {
            boolean numeric = true;
            int playerMoveInt = -1;
            char[][] board = super.getBoard();
            try {
                playerMoveInt = Integer.parseInt(move);
            } catch (NumberFormatException e) {
                numeric = false;
                playerMoveInt = -1;
            }
            if (numeric)
                if (playerMoveInt < 10 && playerMoveInt > 0) {
                    if (board[calcRaw(playerMoveInt - 1)][calcCol(playerMoveInt - 1)] != 'X' &&
                            board[calcRaw(playerMoveInt - 1)][calcCol(playerMoveInt - 1)] != 'O')
                    {
                    	board[calcRaw(playerMoveInt - 1)][calcCol(playerMoveInt - 1)] = 'X';
                        super.setBoard(board);
                        posLeft.remove(new Integer(playerMoveInt - 1));
                        if (posLeft.size() < 5)
                            checkWin(1);
                        return true;
                    }
                    else  //cell is already been selected
                        return false;
                } else // wrong number
                    return false;
           else //wrong move
               return false;
        }
        else
            return false;
    }

    public int calcRaw(int playerMove){
        if(playerMove<3)
            return 0;
        if(playerMove<6)
            return 1;
        return 2;
    }

    public int calcCol(int playerMove){
        if(playerMove == 0 || playerMove == 3 || playerMove == 6)
            return 0;
        if(playerMove == 1 || playerMove == 4 || playerMove == 7)
            return 1;
        return 2;
    }

    public void checkWin(int index) {
        char [][] board = super.getBoard();
        if( board[0][0] == board[0][1] && board[0][1] == board[0][2] && (board[0][0] == 'X' || board[0][0] == 'O') )
            super.setEnd(true,board[0][0] == 'X' ? 1 : 2);
        else if( board[1][0] == board[1][1] && board[1][1] == board[1][2] && (board[1][0] == 'X' || board[1][0] == 'O') )
            super.setEnd(true,board[1][0] == 'X' ? 1 : 2);
        else if( board[2][0] == board[2][1] && board[2][1] == board[2][2] && (board[2][0] == 'X' || board[2][0] == 'O') )
            super.setEnd(true,board[2][0] == 'X' ? 1 : 2);
        else if( board[0][0] == board[1][0] && board[1][0] == board[2][0] && (board[0][0] == 'X' || board[0][0] == 'O') )
            super.setEnd(true,board[0][0] == 'X' ? 1 : 2);
        else if( board[0][1] == board[1][1] && board[1][1] == board[2][1] && (board[0][1] == 'X' || board[0][1] == 'O') )
            super.setEnd(true,board[0][1] == 'X' ? 1 : 2);
        else if( board[0][2] == board[1][2] && board[1][2] == board[2][2] && (board[0][2] == 'X' || board[0][2] == 'O') )
            super.setEnd(true,board[0][2] == 'X' ? 1 : 2);
        else if( board[0][0] == board[1][1] && board[1][1] == board[2][2] && (board[0][0] == 'X' || board[0][0] == 'O') )
            super.setEnd(true,board[0][0] == 'X' ? 1 : 2);
        else if( board[0][2] == board[1][1] && board[1][1] == board[2][0] && (board[0][2] == 'X' || board[0][2] == 'O') )
            super.setEnd(true,board[0][2] == 'X' ? 1 : 2); //set end index 1- PLAYER WIN 2- COMP WIN
        else if(posLeft.size() == 0)
            super.setEnd(true, 3); //set end TIE
    }

    public void printTicTacBoard(){

        char[][] board = super.getBoard();

        for (int i = 0; i < 3; ++i) {
            System.out.println("+---+---+---+");
            for (int j = 0; j < 3; ++j)
                System.out.printf("| %s ", board[i][j] == 'X' ? "X" : board[i][j] == 'O' ? "O" : ' ');
            System.out.println("|");
        }
        System.out.println("+---+---+---+");

    }

}
