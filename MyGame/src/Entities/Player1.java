package Entities;

import Game.Screen;
import Input.Keyboard;

public class Player1 extends Player {

	public Player1(int color, Keyboard key, Screen screen, int xspawn,
			int yspawn) {
		super(color, key, screen, xspawn, yspawn);
	}

	public void update() {
		if (key.up1) {
			yOffset -= 2;
		}
		if (key.down1) {
			yOffset += 2;
		}
		if (key.left1) {
			xOffset -= 2;
		}
		if (key.right1) {
			xOffset += 2;
		}
	}

}
