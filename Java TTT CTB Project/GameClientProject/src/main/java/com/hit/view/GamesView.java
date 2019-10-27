package com.hit.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

import org.json.simple.JSONArray;

public class GamesView implements View {
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
    private JLabel lblPickAGame;
    private JRadioButton rdTicTacTow;
    private JRadioButton rdCatchTheBunny;
    private JLabel lblPickAnOpponent;
    private JRadioButton rdRandom;
    private JRadioButton rdSmart;
    private JLabel lblStart;
    private JRadioButton rdPlayer;
    private JRadioButton rdComputer;
    private JButton btnNewGame;
    private JPanel panelBoard;
    private JButton[][] panelButtons;
    
    private String gameType = "Tic Tac Tow";
    private String opponent = "Random";
    private String startPlayer = "Player";

	@Override
	public void start() {
		// TODO Auto-generated method stub
        JFrame frame = new JFrame ("Game");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        //construct components
        lblPickAGame = new JLabel ("Pick a game");
        rdTicTacTow = new JRadioButton ("Tic Tac Tow", true);
        rdCatchTheBunny = new JRadioButton ("Catch The Bunny");
        lblPickAnOpponent = new JLabel ("Pick an opponent");
        rdRandom = new JRadioButton ("Random", true);
        rdSmart = new JRadioButton ("Smart");
        lblStart = new JLabel ("Start");
        rdPlayer = new JRadioButton ("Player", true);
        rdComputer = new JRadioButton ("Computer");
        btnNewGame = new JButton ("New Game");
        panelBoard = new JPanel ();

        //adjust size and set layout
        frame.setPreferredSize (new Dimension (641, 756));
        frame.setLayout (null);

        //add components
        frame.add (lblPickAGame);
        frame.add (rdTicTacTow);
        frame.add (rdCatchTheBunny);
        frame.add (lblPickAnOpponent);
        frame.add (rdRandom);
        frame.add (rdSmart);
        frame.add (lblStart);
        frame.add (rdPlayer);
        frame.add (rdComputer);
        frame.add (btnNewGame);
        frame.add (panelBoard);
        
        //group radio buttons
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdTicTacTow);
        bg.add(rdCatchTheBunny);
        bg = new ButtonGroup();
        bg.add(rdRandom);
        bg.add(rdSmart);
        bg = new ButtonGroup();
        bg.add(rdPlayer);
        bg.add(rdComputer);

        //set component bounds (only needed by Absolute Positioning)
        lblPickAGame.setBounds (50, 40, 100, 25);
        rdTicTacTow.setBounds (50, 70, 150, 25);
        rdCatchTheBunny.setBounds (50, 95, 175, 25);
        lblPickAnOpponent.setBounds (225, 40, 150, 25);
        rdRandom.setBounds (225, 70, 100, 25);
        rdSmart.setBounds (225, 95, 100, 25);
        lblStart.setBounds (385, 40, 100, 25);
        rdPlayer.setBounds (385, 70, 100, 25);
        rdComputer.setBounds (385, 95, 100, 25);
        btnNewGame.setBounds (495, 70, 100, 25);
        panelBoard.setBounds (45, 155, 550, 550);
        panelBoard.setBorder(new LineBorder(Color.blue));
        
        //setActionListener
        btnNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				pcs.firePropertyChange("Button Click", true, false);
			}
        	
        });
        rdTicTacTow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameType = "Tic Tac Tow";
			}
        	
        });
        rdCatchTheBunny.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gameType = "Catch The Bunny";
			}
        	
        });
        rdRandom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				opponent = "Random";
			}
        	
        });
        rdSmart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				opponent = "Smart";
			}
        	
        });     
        rdPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startPlayer = "Player";
			}
        	
        });
        rdComputer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				startPlayer = "Computer";
			}
        	
        });  

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				
				if(getGameType().equals("Catch The Bunny") && e.getID() == KeyEvent.KEY_PRESSED) {
					switch(e.getKeyCode()) {
					case 'W':
						pcs.firePropertyChange("Panel Click", "O", "W");
						break;
					case 'A':
						pcs.firePropertyChange("Panel Click", "O", "A");
						break;
					case 'S':
						pcs.firePropertyChange("Panel Click", "O", "S");
						break;
					case 'D':
						pcs.firePropertyChange("Panel Click", "O", "D");
						break;
					}
				}
				return false;
			}
		});
        
        frame.pack();
        frame.setVisible (true);
	}

	@Override
	public void updateViewNewGame(Character[] board) {
		// TODO Auto-generated method stub
		int size = board.length;
		if(gameType.equals("Tic Tac Tow") && size == 3 * 3) {
			size = 3;
		} else if(gameType.equals("Catch The Bunny") && size == 9 * 9) {
			size = 9;
		} else {
			return;
		}

		panelButtons = new JButton[size][size];
		panelBoard.setLayout(new GridLayout(size, size));
		for(int i = 0; i < size; i ++) {
			for(int j = 0; j < size; j ++) {
				panelButtons[i][j] = new JButton();
				panelButtons[i][j].setText(board[i * size + j].toString());
				panelButtons[i][j].putClientProperty("row", i);
				panelButtons[i][j].putClientProperty("col", j);
				panelButtons[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int row = Integer.valueOf(((JButton)e.getSource()).getClientProperty("row").toString());
						int col = Integer.valueOf(((JButton)e.getSource()).getClientProperty("col").toString());
						if(getGameType().equals("Tic Tac Tow")) {
							pcs.firePropertyChange("Panel Click", -1, row * 3 + col + 1);
						}
					}
					
				});
				panelBoard.add(panelButtons[i][j]);
			}
		}
		panelBoard.invalidate();
		panelBoard.repaint();
	}

	@Override
	public void updateViewGameMove(int gameState, Character[] board) {
		// TODO Auto-generated method stub
		if(board != null) {
			int size = board.length;
			if(gameType.equals("Tic Tac Tow") && size == 3 * 3) {
				size = 3;
			} else if(gameType.equals("Catch The Bunny") && size == 9 * 9) {
				size = 9;
			} else {
				return;
			}
			for(int i = 0; i < size; i ++) {
				for(int j = 0; j < size; j ++) {
					panelButtons[i][j].setText(board[i * size + j].toString());
				}
			}
		}
		switch(gameState) {
		case 1:	// Player Win
			JOptionPane.showMessageDialog(null, "Player Win");
			break;
		case 2:	// Player Lose
			JOptionPane.showMessageDialog(null, "Player Lose");
			break;
		case 3:	// Tie
			JOptionPane.showMessageDialog(null, "Tie");
			break;
		default:
			return;
		}
		Initialize();
	}

	public void addPropertyChangeListener(PropertyChangeListener controller) {
		// TODO Auto-generated method stub
		pcs.addPropertyChangeListener(controller);
	}

	@Override
	public String getGameType() {
		return gameType;
	}

	@Override
	public String getOpponent() {
		return opponent;
	}

	@Override
	public String getStartPlayer() {
		// TODO Auto-generated method stub
		return startPlayer;
	}

	@Override
	public void Initialize() {
		// TODO Auto-generated method stub
		rdTicTacTow.setEnabled(true);
		rdCatchTheBunny.setEnabled(true);
		rdRandom.setEnabled(true);
		rdSmart.setEnabled(true);
		rdComputer.setEnabled(true);
		rdPlayer.setEnabled(true);
		btnNewGame.setText("New Game");
		panelBoard.removeAll();
		panelBoard.invalidate();
		panelBoard.repaint();
	}

	@Override
	public void Started() {
		// TODO Auto-generated method stub
		rdTicTacTow.setEnabled(false);
		rdCatchTheBunny.setEnabled(false);
		rdRandom.setEnabled(false);
		rdSmart.setEnabled(false);
		rdComputer.setEnabled(false);
		rdPlayer.setEnabled(false);
		btnNewGame.setText("Stop Game");
	}

}
