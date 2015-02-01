package Entities;

import Game.Screen;
import Input.Keyboard;

public class Player {

	Keyboard key;
	Screen screen;
	int color, xspawn, yspawn, xOffset, yOffset;

	public Player(int color, Keyboard key, Screen screen, int xspawn, int yspawn) {
		this.key = key;
		this.color = color;
		this.screen = screen;
		this.xspawn = xspawn;
		this.yspawn = yspawn;
	}

	public void update() {
		if (key.up) {
			yOffset -= 2;
		}
		if (key.down) {
			yOffset += 2;
		}
		if (key.left) {
			xOffset -= 2;
		}
		if (key.right) {
			xOffset += 2;
		}
	}

	public void render() {
		screen.renderPlayer(color, xspawn, yspawn, xOffset, yOffset);
	}
}
