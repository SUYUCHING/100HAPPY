package com.aj.casino.constant;

import java.util.HashMap;
import java.util.Map;

public class BaccaratContant {

	public final static Map<Integer,String> BANCO_TYPE_MAP=new HashMap<Integer,String>();
	public final static Map<Integer,String> PLAYER_TYPE_MAP=new HashMap<Integer,String>();
	public final static Map<Integer,String> WIN_TYPE_MAP=new HashMap<Integer,String>();
	
	public final static Integer BANCO_TYPE_WIN=10;
	public final static Integer BANCO_TYPE_TREASURE=20;
	public final static Integer BANCO_TYPE_TWO_BANCO=30;
	public final static Integer BANCO_TYPE_TWO_SUP_SIX=40;
	
	public final static Integer PLAYER_TYPE_WIN=10;
	public final static Integer PLAYER_TYPE_TREASURE=20;
	public final static Integer PLAYER_TYPE_TWO_PLAYER=30;
	
	public final static Integer TIE_TYPE_WIN_0=0;
	public final static Integer TIE_TYPE_WIN_1=1;
	public final static Integer TIE_TYPE_WIN_2=2;
	public final static Integer TIE_TYPE_WIN_3=3;
	public final static Integer TIE_TYPE_WIN_4=4;
	public final static Integer TIE_TYPE_WIN_5=5;
	public final static Integer TIE_TYPE_WIN_6=6;
	public final static Integer TIE_TYPE_WIN_7=7;
	public final static Integer TIE_TYPE_WIN_8=8;
	public final static Integer TIE_TYPE_WIN_9=9;
	
	public final static Integer WINNER_TYPE_PLAYER=1;
	public final static Integer WINNER_TYPE_BANCO=2;
	public final static Integer WINNER_TYPE_TIE=3;
	
	static {
		BANCO_TYPE_MAP.put(BANCO_TYPE_WIN, "Win");
		BANCO_TYPE_MAP.put(BANCO_TYPE_TREASURE, "Treasure");
		BANCO_TYPE_MAP.put(BANCO_TYPE_TWO_BANCO, "TwoBanco");
		BANCO_TYPE_MAP.put(BANCO_TYPE_TWO_SUP_SIX, "supSix");

		PLAYER_TYPE_MAP.put(PLAYER_TYPE_WIN, "Win");
		PLAYER_TYPE_MAP.put(PLAYER_TYPE_TREASURE, "Treasure");
		PLAYER_TYPE_MAP.put(PLAYER_TYPE_TWO_PLAYER, "TwoPlayer");
		
		WIN_TYPE_MAP.put(WINNER_TYPE_PLAYER, "Player");
		WIN_TYPE_MAP.put(WINNER_TYPE_BANCO, "Banco");
		WIN_TYPE_MAP.put(WINNER_TYPE_TIE, "Tie");
		
	}
	
}
