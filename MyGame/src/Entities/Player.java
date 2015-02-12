package Entities;

import Game.Screen;
import Input.Keyboard;

public class Player {

	Keyboard key;
	Screen screen;
	int color, xspawn, yspawn;
	double xOffset, yOffset;
	double alpha = 0;
	double beta = 0;

	public Player(int color, Keyboard key, Screen screen, int xspawn, int yspawn) {
		this.key = key;
		this.color = color;
		this.screen = screen;
		this.xOffset += xspawn;
		this.yOffset += yspawn;
	}

	public void update() {
	
	}

	public void render() {
		screen.renderPlayer(color,(int) xOffset,(int) yOffset);
	}
}
