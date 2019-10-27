package com.hit.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.EventListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.hit.model.GamesClient;
import com.hit.model.Model;
import com.hit.view.View;

public class GamesController implements Controller, PropertyChangeListener ,EventListener {

	private Model model;
	private View view;
	private GamesClient client;
	private int gameID = -1;
	
	public GamesController(Model model, View view) throws UnknownHostException, IOException {
		client = new GamesClient(34567);
		this.model = model;
		this.view = view;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getPropertyName().equals("Button Click"))
		{
			JSONObject obj = new JSONObject();
			if(gameID == -1) {
				obj.put("type", "New-Game");
				obj.put("game", view.getGameType());
				obj.put("opponent", view.getOpponent());
				try {
					JSONObject jsonOut = client.sendMessage(obj.toJSONString(), true);
					gameID = Integer.valueOf(jsonOut.get("ID").toString());
					
					if(gameID == -1) {
						view.Initialize();
					} else {
						if(view.getStartPlayer().equals("Computer")) {
							obj = new JSONObject();
							obj.put("type", "Start-Game");
							obj.put("ID", gameID);
							jsonOut = client.sendMessage(obj.toJSONString(), true);
						}
						Object[] respArray = ((JSONArray)jsonOut.get("board")).toArray();
						Character[] chars = new Character[respArray.length];
						for(int i = 0; i < chars.length; i ++)
							chars[i] = (Character)respArray[i];
						view.Started();	
						view.updateViewNewGame(chars);			
					}
				} catch(Exception e) { 
					gameID = -1;
					view.Initialize();
				}
			} else {
				obj.put("type", "Stop-Game");
				obj.put("ID", gameID);
				gameID = -1;
				view.Initialize();
				try {
					client.sendMessage(obj.toJSONString(), false);
				} catch(Exception e) { } 
			}
		} else if(evt.getPropertyName().equals("Panel Click"))
		{
			JSONObject obj = new JSONObject();
			obj.put("type", "Update-Move");
			obj.put("ID", gameID);
			obj.put("move", evt.getNewValue().toString());
			JSONObject jsonOut = client.sendMessage(obj.toJSONString(), true);
			int state = Integer.valueOf(jsonOut.get("state").toString());
			if(jsonOut.get("board") != null) {
				Object[] respArray = ((JSONArray)jsonOut.get("board")).toArray();
				Character[] chars = new Character[respArray.length];
				for(int i = 0; i < chars.length; i ++)
					chars[i] = (Character)respArray[i];
				view.updateViewGameMove(state, chars);	
			} else {
				view.updateViewGameMove(state, null);
			}
			if(state > 0 && state < 4) {
				gameID = -1;
			}
		}
	}

}
