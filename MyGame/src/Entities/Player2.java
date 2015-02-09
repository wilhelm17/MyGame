package Entities;

import Game.Screen;
import Input.Keyboard;

public class Player2 extends Player {

	public Player2(int color, Keyboard key, Screen screen, int xspawn,
			int yspawn) {
		super(color, key, screen, xspawn, yspawn);
	}

	public void update() {
		if (key.up2) {
			yOffset -= 2;
		}
		if (key.down2) {
			yOffset += 2;
		}
		if (key.left2) {
			xOffset -= 2;
		}
		if (key.right2) {
			xOffset += 2;
		}
	}
}
