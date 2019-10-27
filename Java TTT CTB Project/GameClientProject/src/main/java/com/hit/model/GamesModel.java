package com.hit.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GamesModel implements Model {
	
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public GamesModel() { 
		
	}
	
	@Override
	public void newGame(String gameType, String opponentType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePlayerMove(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	public void addPropertyChangeListener(PropertyChangeListener controller) {
		// TODO Auto-generated method stub
		pcs.addPropertyChangeListener(controller);
	}	
	
}
