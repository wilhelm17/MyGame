package Entities;

import java.util.ArrayList;

import Game.Screen;

public class Player {

	Screen screen;
	int color, xspawn, yspawn;
	double xOffset, yOffset;
	double alpha = 0;
	double beta = 0;
	public boolean moving = true;
	public ArrayList<Integer> a = new ArrayList<Integer>();

	public Player(int color, Screen screen, int xspawn, int yspawn) {
		this.color = color;
		this.screen = screen;
		this.xOffset += xspawn;
		this.yOffset += yspawn;
		for (int i = 0; i <= 500; i++) {
			a.add(0);
		}
	}

	public void update(boolean left, boolean right) {
		if (moving) {
			if (left && right) {
			} else {
				if (left) {
					alpha -= 2.5;
				}
				if (right) {
					alpha += 2.5;
				}
			}
			beta = Math.toRadians(alpha);
			yOffset += 2 * Math.sin(beta);
			xOffset += 2 * Math.cos(beta);
		}
	}

	public void render() {
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 2, 5, 0,
				1);
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 1, 6, 1,
				2);
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 0, 7, 2,
				5);
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 1, 6, 5,
				6);
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 2, 5, 6,
				7);
	}
}
