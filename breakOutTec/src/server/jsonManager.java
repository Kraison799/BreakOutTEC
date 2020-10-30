package server;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import game_objects.Player;
import game_objects.Wall;

public class jsonManager {
	private JSONParser parser;
	private JSONObject all;
	private JSONObject player;
	private JSONArray easy;
	private JSONArray medium;
	private JSONArray hard;
	private JSONArray imp;
	private JSONArray balls;
	
	public jsonManager() {
		this.parser = new JSONParser();
		this.all = new JSONObject();
		this.player = new JSONObject();
		this.easy = new JSONArray();
		this.medium = new JSONArray();
		this.hard = new JSONArray();
		this.imp = new JSONArray();
		this.balls = new JSONArray();
	}
	
	public void updatePlayer(Player obj, int level, int score) {
		JSONObject jo = new JSONObject();
		jo.put("spdM", obj.isSpdM());
		jo.put("spdP", obj.isSpdP());
		jo.put("sizeH", obj.isSizeH());
		jo.put("sizeD", obj.isSizeD());
		jo.put("x", obj.getPosX());
		jo.put("level", level);
		jo.put("score", score);
		jo.put("hp", obj.getLifes());
		this.player = jo;
	}
	
	public void updateEasy(Wall wall) {
		JSONArray ja = new JSONArray();
		JSONArray line1 = new JSONArray();
		JSONArray line2 = new JSONArray();
		for (int i = 0; i < 14; i++) {
			JSONObject block1 = new JSONObject();
			JSONObject block2 = new JSONObject();
			
			block1.put("effect", wall.getLine1().get(13-i).getEffect());
			block2.put("effect", wall.getLine2().get(13-i).getEffect());
			block1.put("hp", wall.getLine1().get(13-i).getResistance());
			block2.put("hp", wall.getLine2().get(13-i).getResistance());
			
			line1.add(block1);
			line2.add(block2);
		}
		
		ja.add(line2);
		ja.add(line1);		
		this.easy = ja;
	}
	
	public void updateMedium(Wall wall) {
		JSONArray ja = new JSONArray();
		JSONArray line1 = new JSONArray();
		JSONArray line2 = new JSONArray();
		for (int i = 0; i < 14; i++) {
			JSONObject block1 = new JSONObject();
			JSONObject block2 = new JSONObject();
			
			block1.put("effect", wall.getLine1().get(i).getEffect());
			block2.put("effect", wall.getLine2().get(i).getEffect());
			block1.put("hp", wall.getLine1().get(i).getResistance());
			block2.put("hp", wall.getLine2().get(i).getResistance());
			
			line1.add(block1);
			line2.add(block2);
		}
		
		ja.add(line2);
		ja.add(line1);		
		this.medium = ja;
	}
	
	public void updateHard(Wall wall) {
		JSONArray ja = new JSONArray();
		JSONArray line1 = new JSONArray();
		JSONArray line2 = new JSONArray();
		for (int i = 0; i < 14; i++) {
			JSONObject block1 = new JSONObject();
			JSONObject block2 = new JSONObject();
			
			block1.put("effect", wall.getLine1().get(i).getEffect());
			block2.put("effect", wall.getLine2().get(i).getEffect());
			block1.put("hp", wall.getLine1().get(i).getResistance());
			block2.put("hp", wall.getLine2().get(i).getResistance());
			
			line1.add(block1);
			line2.add(block2);
		}
		
		ja.add(line2);
		ja.add(line1);		
		this.hard = ja;
	}
	
	public void updateImp(Wall wall) {
		JSONArray ja = new JSONArray();
		JSONArray line1 = new JSONArray();
		JSONArray line2 = new JSONArray();
		for (int i = 0; i < 14; i++) {
			JSONObject block1 = new JSONObject();
			JSONObject block2 = new JSONObject();
			
			block1.put("effect", wall.getLine1().get(i).getEffect());
			block2.put("effect", wall.getLine2().get(i).getEffect());
			block1.put("hp", wall.getLine1().get(i).getResistance());
			block2.put("hp", wall.getLine2().get(i).getResistance());
			
			line1.add(block1);
			line2.add(block2);
		}
		
		ja.add(line2);
		ja.add(line1);		
		this.imp = ja;
	}
	
	public void updateBalls(Player obj) {
		JSONArray ja = new JSONArray();
		for (int i = 0;  i < obj.getBalls().size(); i++) {
			JSONObject ball = new JSONObject();
			ball.put("y", obj.getBalls().get(i).getPosY());
			ball.put("x", obj.getBalls().get(i).getPosX());

			ja.add(ball);
		}
		this.balls = ja;
	}
	
	public void update() {
		JSONObject ja = new JSONObject();
		ja.put("player", this.player);
		ja.put("easy", this.easy);
		ja.put("medium", this.medium);
		ja.put("hard", this.hard);
		ja.put("hard", this.imp);
		ja.put("balls", this.balls);
		this.all = ja;
		
		try (FileWriter file = new FileWriter("server-data.json")) {
			file.write(this.all.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JSONObject getAll() {
		return all;
	}
	
	public String getAllStr() {
		return all.toJSONString();
	}
}
