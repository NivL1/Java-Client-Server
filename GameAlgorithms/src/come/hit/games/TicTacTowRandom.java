package come.hit.games;


import java.util.List;
import java.util.Random;

public class TicTacTowRandom extends TicTacTow {


    public TicTacTowRandom() {
  //      super(new int [3][3]);


    }


    @Override
    public void calcComputerMove() {
        if (!super.isEnd()) {
            Random rand = new Random();
            int ran;
            List<Integer> posLeft = super.getPosLeft();
            ran = rand.nextInt(posLeft.size());
            super.getBoard()[calcRaw(posLeft.get(ran))][calcCol(posLeft.get(ran))] = 'O';
            posLeft.remove(ran);
            super.setPosLeft(posLeft);
            if (posLeft.size() < 5)
                checkWin(2);
        }
    }
}

