package Entities;

import Game.Screen;
import Input.Keyboard;

public class Player2 extends Player {

	public Player2(int color, Keyboard key, Screen screen, int xspawn,
			int yspawn) {
		super(color, key, screen, xspawn, yspawn);
	}

	public void update() {
		if (moving) {
			if (key.left2) {
				alpha -= 1.2;
			}
			if (key.right2) {
				alpha += 1.2;
			}
			beta = Math.toRadians(alpha);
			yOffset += 1 * Math.sin(beta);
			xOffset += 1 * Math.cos(beta);
		}
	}
}
