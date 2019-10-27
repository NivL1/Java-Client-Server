package come.hit.games;

public class CatchTheBunnySmart extends CatchTheBunny{

    public CatchTheBunnySmart (int numOfMovesBegin){
        super(numOfMovesBegin);
    }

    @Override
    public void calcComputerMove() {
        if(!super.isEnd()) {
	        int BPosX = super.getBPosX();
	        int BPosY = super.getBPosY();
	        
	        int PPosX = super.getPPosX();
	        int PPosY = super.getPPosY();
	        
	        int[] dx = {-1, 0, 0, 1};
	        int[] dy = {0, -1, 1, 0};
	        
	        for(int i = 0; i < 4; i ++) {
	        	int tx = BPosX + dx[i];
	        	int ty = BPosY + dy[i];
	        	
	        	if(tx >= 0 && tx < 9 && ty >= 0 && ty < 9 && Math.abs(tx - PPosX) + Math.abs(ty - PPosY) > 1) {
	        		BPosX = tx;
	        		BPosY = ty;
	        		break;
	        	}
	        	
	        	if(i == 3) {
	    	        for(i = 0; i < 4; i ++) {
	    	        	int ttx = BPosX + dx[i];
	    	        	int tty = BPosY + dy[i];
	    	        	
	    	        	if(ttx >= 0 && ttx < 9 && tty >= 0 && tty < 9) {
	    	        		BPosX = ttx;
	    	        		BPosY = tty;
	    	        		break;
	    	        	}
	    	        }
	    	        i = 3;
	        	}
	        	
	        }
	        

            super.setBPosX(BPosX);
            super.setBPosY(BPosY);
            char board[][] = new char[9][9];
	        if (BPosX == PPosX && BPosY == PPosY) {
                super.setEnd(true, 1);
                board[BPosX][BPosY] = 'W';
            } else {
                board[BPosX][BPosY] = 'B';
                board[PPosX][PPosY] = 'P';
            }
            super.setBoard(board);
        }
    }
}
