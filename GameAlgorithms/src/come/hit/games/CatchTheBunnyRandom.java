package come.hit.games;

import java.util.*;

public class CatchTheBunnyRandom extends CatchTheBunny{

    public CatchTheBunnyRandom(int numOfMovesBegin){
        super(numOfMovesBegin);
    }

    @Override
    public void calcComputerMove(){
        if(!super.isEnd()) {
            Random rand = new Random();
            int BPosX = super.getBPosX();
            int BPosY = super.getBPosY();
            int ran, PPosX, PPosY;
            int count = 0;
            List<Integer> randSet = new ArrayList<Integer>();
            if (super.getBPosX() > 0) { //UP IS POSSIBLE
                randSet.add(0);
                count++;
            }
            if (super.getBPosX() < 8) { //DOWN IS POSSIBLE
                randSet.add(1);
                count++;
            }
            if (super.getBPosY() > 0) { //LEFT IS POSSIBLE
                randSet.add(2);
                count++;
            }
            if (super.getBPosY() < 8) { //RIGHT IS POSSIBLE
                randSet.add(3);
                count++;
            }
            ran = rand.nextInt(count);
            char board[][] = new char[9][9];
            if (randSet.get(ran) == 0) {
                BPosX -= 1;
                super.setBPosX(BPosX);
            } else if (randSet.get(ran) == 1) {
                BPosX += 1;
                super.setBPosX(BPosX);
            } else if (randSet.get(ran) == 2) {
                BPosY -= 1;
                super.setBPosY(BPosY);
            } else if (randSet.get(ran) == 3) {
                BPosY += 1;
                super.setBPosY(BPosY);
            }
            PPosX = super.getPPosX();
            PPosY = super.getPPosY();
            if (BPosX == PPosX && BPosY == PPosY) {
                super.setEnd(true, 1);
                board[BPosX][BPosY] = 'W';
            } else {
                board[BPosX][BPosY] = 'B';
                board[PPosX][PPosY] = 'P';
            }
            super.setBoard(board);
        }
        else
            System.out.println("The Game Is Already Over!");
    }


}
