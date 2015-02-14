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
	public boolean b = true;
	public int errorx = 0, errory = 0;

	public Player(int color, Keyboard key, Screen screen, int xspawn, int yspawn) {
		this.key = key;
		this.color = color;
		this.screen = screen;
		this.xOffset += xspawn;
		this.yOffset += yspawn;
		for (int i = 0; i <= 704; i++) {
			a.add(0);
		}
	}

	public void update() {
	}

	public void render() {
		if (b) {
			screen.renderPlayer(color, (int) xOffset, (int) yOffset, this);
		} else {
			screen.renderPixel(errorx, errory, 0x00ff00);
		}
	}

	public void setMove(boolean b) {
		this.moving = b;
	}
}
