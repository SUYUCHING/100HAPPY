package com.aj.casino.baccarat.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aj.casino.baccarat.dto.GameCard;
import com.aj.casino.baccarat.service.BaccaratService;
import com.aj.casino.baccarat.service.CalculateService;
import com.aj.casino.common.module.controller.BaseController;
import com.aj.casino.constant.BaccaratContant;
import com.aj.casino.constant.ClassConstant;
import com.aj.casino.constant.ErrorCodeConstant;
import com.aj.casino.constant.RuleConstant;
import com.aj.casino.util.DataUtil2;
import com.aj.db.domain.baccarat.Card;
import com.aj.db.domain.baccarat.GameLog;
import com.aj.db.domain.baccarat.GameLogTotal;
import com.aj.db.domain.baccarat.Player;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/baccarat")
public class BaccaratAjaxController extends BaseController{

	private BaccaratService baccaratService;
	private CalculateService calculateService; 
	
	public void setBaccaratService(BaccaratService baccaratService) {
		this.baccaratService = baccaratService;
	}

	
	public void setCalculateService(CalculateService calculateService) {
		this.calculateService = calculateService;
	}


	private final static String REQ_NAME="name";
	private final static String REQ_EMAIL="email";
	private final static String REQ_BANCO="banco";
	private final static String REQ_CLASS="classType";
	private final static String REQ_BANCO_TYPE="bancoType";
	private final static String REQ_PLAYER="player";
	private final static String REQ_PLAYER_TYPE="playeType";
	private final static String REQ_WINNER="winner";
	private final static String REQ_TIE_TYPE="tieType";
	private final static String REQ_BOARD="board";
	private final static String REQ_SEQ="seq";
	private final static String SPLIT_STRING="-";
	
	
	@RequestMapping(value = "/getDropDown", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String getDropDown(HttpServletRequest req, HttpServletResponse res)throws Exception{ 
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);
		try {
			JsonObject classObj = (JsonObject)new Gson().toJsonTree(ClassConstant.CLASS_MAP,new TypeToken<Map<Integer,String>>() {}.getType());
			
			jsonObject.add("classObj", classObj);
			
		}catch (Exception e) {
			e.printStackTrace();
			jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_INTERNAL_ERROR);
			jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_INTERNAL_ERROR);
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "/createBaccarat", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String createBaccarat(HttpServletRequest req, HttpServletResponse res)throws Exception{ 
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);
		try {
			 String classTypeStr=req.getParameter(REQ_CLASS);
			 System.out.println("classTypeStr: "+classTypeStr);
			 System.out.println("user: "+getUserId(req));
			 
			 Object[] createTotalLog = baccaratService.createTotalLog(getUserId(req),Integer.parseInt(classTypeStr));
			
			jsonObject.addProperty("baccaratId", createTotalLog[0].toString());
			jsonObject.addProperty("totalBoard", createTotalLog[1].toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_INTERNAL_ERROR);
			jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_INTERNAL_ERROR);
		}
		return jsonObject.toString();
	}
	
	
	@RequestMapping(value = "/createMember", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String createMember(HttpServletRequest req, HttpServletResponse res)throws Exception{ 
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_INTERNAL_ERROR);
		jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_INTERNAL_ERROR);
		try {
			 String name=req.getParameter(REQ_NAME);
			 String email=req.getParameter(REQ_EMAIL);
			 
			 if(name ==null || name.trim().length()==0 || email ==null || email.trim().length()==0) {
				 jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_MANDATORY_ERROR);
				 jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_MANDATORY_ERROR);
				return jsonObject.toString();
			 }
			 
			 Player createMember = baccaratService.createMember(name, email);
			 
			 if(createMember!=null) {
				 jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
				 jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);
			 }
			
		}catch (Exception e) {
			e.printStackTrace();
			jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_INTERNAL_ERROR);
			jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_INTERNAL_ERROR);
		}
		return jsonObject.toString();
	}
	
	@RequestMapping(value = "/closeBaccarat", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String closeBaccarat(HttpServletRequest req, HttpServletResponse res)throws Exception{ 
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);
		try {
			 String banco=req.getParameter(REQ_BOARD);
			 
			GameLogTotal closeTotalLog = baccaratService.closeTotalLog(new Long(banco));
			
			jsonObject.addProperty("baccaratId", closeTotalLog.getId());
			
		}catch (Exception e) {
			e.printStackTrace();
			jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_INTERNAL_ERROR);
			jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_INTERNAL_ERROR);
		}
		return jsonObject.toString();
	}
	@RequestMapping(value = "/createLog", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String createLog(HttpServletRequest req, HttpServletResponse res)throws Exception{ 
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);
		try {
		    String banco=req.getParameter(REQ_BANCO);
		    String player=req.getParameter(REQ_PLAYER);
		    String board=req.getParameter(REQ_BOARD);
		    String seq=req.getParameter(REQ_SEQ);
			
//		    System.out.println("banco: "+banco);
//		    System.out.println("player: "+player);
//		    System.out.println("winner: "+banco);
//		    System.out.println("board: "+board);
//		    System.out.println("seq: "+seq);
//		    System.out.println("========================");
		    
		    if(board==null || board.trim().length()==0 ||board.equals("end")) {
		    	jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_BOARD_MANDATORY_ERROR);
				jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_BOARD_MANDATORY_ERROR);
				return jsonObject.toString();
		    }
		    
		    if(banco==null || banco.trim().length()==0 || banco.split(SPLIT_STRING).length<2) {
		    	jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_BANCO_MANDATORY_ERROR);
				jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_BANCO_MANDATORY_ERROR);
				return jsonObject.toString();
		    }
		    if(player==null || player.trim().length()==0 || player.split(SPLIT_STRING).length<2) {
		    	jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_PLAYER_MANDATORY_ERROR);
				jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_PLAYER_MANDATORY_ERROR);
				return jsonObject.toString();
		    }
		    
		    
		    GameCard cardObj = analysisData(banco, player);
		    System.out.println("cardObj: "+cardObj.toString());
		    String winType="";
		    Gson gson = new Gson();
		    
			for (Integer winner : cardObj.getWinTypeStr().keySet()) {
				if (!winner.equals(BaccaratContant.WINNER_TYPE_TIE)) {

					String[] winTypes = cardObj.getWinTypeStr().get(winner).split(",");
					Map<Integer, String> map = new HashMap<>();

					if (winner.equals(BaccaratContant.WINNER_TYPE_BANCO)) {
						map = BaccaratContant.BANCO_TYPE_MAP;
					} else if (winner.equals(BaccaratContant.WINNER_TYPE_PLAYER)) {
						map = BaccaratContant.PLAYER_TYPE_MAP;
					}
					for (String type : winTypes) 
						winType = winType + "," + map.get(Integer.parseInt(type));
					

					winType = winType.substring(1);
				} else {
					winType = cardObj.getWinTypeStr().get(winner);
				}
				jsonObject.addProperty("winner", "blanco: "+cardObj.getbTtPoint()+" player: "+cardObj.getpTtPoint()+", winner: "+BaccaratContant.WIN_TYPE_MAP.get(winner) + ":" + winType);
			}
			GameLog gameLog=baccaratService.createGameLog(new Long(board), cardObj, new Integer(seq));
			
			JsonObject cardJson = (JsonObject)gson.toJsonTree(gson.fromJson(gameLog.getLastCard(), Card.class),new TypeToken<Card>() {}.getType());
		    jsonObject.add("card",cardJson);
		    
		    boolean cWinner = calculateService.calculateBaccarat(gameLog);
		    jsonObject.addProperty("cWinner",cWinner);//true =banco, false=player
		    
		}catch (Exception e) {
			e.printStackTrace();
			jsonObject.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_INTERNAL_ERROR);
			jsonObject.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_INTERNAL_ERROR);
		}
		return jsonObject.toString();
	}
	
	private GameCard analysisData(String bancoDataStr, String playerDataStr) {
		String[] bancoArr = bancoDataStr.split(SPLIT_STRING);
		String[] playerArr = playerDataStr.split(SPLIT_STRING);
		
		Integer totalBCard=0;
		Integer totalPCard=0;
		String bancoType=null;
		String playerType=null;
		Integer tieType=null;
		BigDecimal bancoTwoCard=BigDecimal.ZERO;
		BigDecimal playerTwoCard=BigDecimal.ZERO;
		boolean bTreasure=false;
		boolean pTreasure=false;
		String bCardStr2="";
		String pCardStr2="";
		for(int i=0 ;i<3 ;i++) {
			String bCardStr="";
			String pCardStr="";
			if(bancoArr.length-1>=i ) 
			bCardStr=bancoArr[i].substring(1);
			if(playerArr.length-1>=i) 
			pCardStr=playerArr[i].substring(1);
			
			//treasure
			if(i<2) {
				if(bCardStr.length()==1)
				bancoTwoCard=bancoTwoCard.add(new BigDecimal(bCardStr));
				if(pCardStr.length()==1)
				playerTwoCard=playerTwoCard.add(new BigDecimal(pCardStr));
				//Two same
				if(i==0) {
					bCardStr2=bCardStr;
					pCardStr2=pCardStr;
				}else if(i==1) {
					if(bCardStr2.equals(bCardStr))
						bTreasure=true;
					if(pCardStr2.equals(pCardStr))
						pTreasure=true;
				}
				
			}
			
			if(bCardStr.length()==1)
			totalBCard=totalBCard+ Integer.parseInt(bCardStr);
			if(pCardStr.length()==1)
			totalPCard=totalPCard+ Integer.parseInt(pCardStr);
		}
		if(bancoTwoCard.toPlainString().length()>1)
		    bancoTwoCard=new BigDecimal(bancoTwoCard.toPlainString().substring(1));
		if(playerTwoCard.toPlainString().length()>1)
			playerTwoCard=new BigDecimal(playerTwoCard.toPlainString().substring(1));
		if(totalBCard.toString().length()>1)
			totalBCard=Integer.parseInt(totalBCard.toString().substring(1));
		if(totalPCard.toString().length()>1)
			totalPCard=Integer.parseInt(totalPCard.toString().substring(1));
//		System.out.println("bancoTwoCard: "+bancoTwoCard);
//		System.out.println("playerTwoCard: "+playerTwoCard);
//		System.out.println("totalBCard: "+totalBCard);
//		System.out.println("totalPCard: "+totalPCard);
//		System.out.println("bTreasure: "+bTreasure);
//		System.out.println("pTreasure: "+pTreasure);
//		System.out.println("=============================");
		//close an account
		/* banco */
		if(totalBCard==6)
			bancoType=bancoType+","+BaccaratContant.BANCO_TYPE_TWO_SUP_SIX.toString();
		if(RuleConstant.BANCO_TYPE_TREASUE.contains(bancoTwoCard.intValue()))
			bancoType=bancoType+","+BaccaratContant.BANCO_TYPE_TREASURE.toString();
		if(bTreasure)
			bancoType=bancoType+","+BaccaratContant.BANCO_TYPE_TWO_BANCO.toString();
		
		/* player */
		if(RuleConstant.BANCO_TYPE_TREASUE.contains(playerTwoCard.intValue()))
			playerType=playerType+","+BaccaratContant.PLAYER_TYPE_TREASURE.toString();
		if(pTreasure)
			playerType=playerType+","+BaccaratContant.PLAYER_TYPE_TWO_PLAYER.toString();
		
		if((totalPCard-totalBCard)>=4)
			if(playerType==null ||playerType.indexOf(BaccaratContant.PLAYER_TYPE_TREASURE.toString())==-1)
			playerType=playerType+","+BaccaratContant.PLAYER_TYPE_TREASURE.toString();
		if((totalBCard-totalPCard)>=4) {
			if(bancoType==null ||bancoType.indexOf(BaccaratContant.BANCO_TYPE_TREASURE.toString())==-1)
			bancoType=bancoType+","+BaccaratContant.BANCO_TYPE_TREASURE.toString();
		}
		
		if(totalBCard>totalPCard) {
			bancoType=bancoType+","+BaccaratContant.BANCO_TYPE_WIN.toString();
			playerType=null;
		}
		if(totalBCard<totalPCard) {
			playerType=playerType+","+BaccaratContant.PLAYER_TYPE_WIN.toString();
			bancoType=null;
		}
		/* tie */
		if(totalBCard==totalPCard) {
			tieType=totalBCard;
			bancoType=null;
			playerType=null;
		}
		GameCard gameCard = new GameCard();
		Map<Integer,String> map = new HashMap<>();
		
		Gson gson = new Gson();
		String playerJson = gson.toJson(playerArr);
		String bancoJson = gson.toJson(bancoArr);
		
		gameCard.setBancoStr(bancoArr);
		gameCard.setPlayerStr(playerArr);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty(REQ_BANCO, bancoJson);
		jsonObject.addProperty(REQ_PLAYER, playerJson);
		if (bancoType != null) {
			jsonObject.addProperty(REQ_BANCO_TYPE, bancoType);
			map.put(BaccaratContant.WINNER_TYPE_BANCO, bancoType.substring(5));
		}
		if (playerType != null) {
			jsonObject.addProperty(REQ_PLAYER_TYPE, playerType);
			map.put(BaccaratContant.WINNER_TYPE_PLAYER, playerType.substring(5));
		}
		if (tieType != null) {
			jsonObject.addProperty(REQ_TIE_TYPE, tieType);
			map.put(BaccaratContant.WINNER_TYPE_TIE, tieType.toString());
		}
		
		gameCard.setWinTypeStr(map);
		
//		return jsonObject;
		return gameCard;
		
	}
}
