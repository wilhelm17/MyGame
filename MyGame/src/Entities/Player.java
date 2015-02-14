package Entities;

import java.util.ArrayList;

import Game.Screen;
import Input.Keyboard;

public class Player {

	Keyboard key;
	Screen screen;
	int color, xspawn, yspawn;
	double xOffset, yOffset;
	double alpha = 0;
	double beta = 0;
	public boolean moving = true;
	public ArrayList<Integer> a = new ArrayList<Integer>();

	public Player(int color, Keyboard key, Screen screen, int xspawn, int yspawn) {
		this.key = key;
		this.color = color;
		this.screen = screen;
		this.xOffset += xspawn;
		this.yOffset += yspawn;
		for(int i = 0; i <= 640;i++){
			a.add(0);
		}
	}

	public void update() {
	}

	public void render() {
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this);
	}

	public void setMove(boolean b) {
		this.moving = b;
	}
}
