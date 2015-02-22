package Entities;

import java.util.ArrayList;

import Game.Screen;
import Input.Keyboard;

public class Player {

	Screen screen;
	int color;
	double xOffset, yOffset;
	double alpha = 0;
	double beta = 0;
	public boolean moving = true;
	public ArrayList<Integer> a = new ArrayList<Integer>();
	public int[] lastPos = new int[2];
	int timer = 0;
	boolean colision = true;
	Keyboard key;

	public Player(int color, Screen screen, Keyboard key) {
		this.color = color;
		this.screen = screen;
		this.key = key;
		for (int i = 0; i <= 1000; i++) {
			a.add(0);
		}
	}

	public void setSpawn(int x, int y, int alpha) {
		xOffset = x;
		yOffset = y;
		this.alpha = alpha;
	}

	public void update(boolean left, boolean right) {
		timer++;
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

	public void setColision(boolean b) {
		this.colision = b;
	}

	public void render() {
		if (timer > 150) {
			screen.renderPlayer(0x000000, lastPos[0], lastPos[1], this, 2, 5,
					0, 1, colision);
			screen.renderPlayer(0x000000, lastPos[0], lastPos[1], this, 1, 6,
					1, 2, colision);
			screen.renderPlayer(0x000000, lastPos[0], lastPos[1], this, 0, 7,
					2, 5, colision);
			screen.renderPlayer(0x000000, lastPos[0], lastPos[1], this, 1, 6,
					5, 6, colision);
			screen.renderPlayer(0x000000, lastPos[0], lastPos[1], this, 2, 5,
					6, 7, colision);
			if (timer > 165) {
				timer = 0;
			}
		}
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 2, 5, 0,
				1, colision);
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 1, 6, 1,
				2, colision);
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 0, 7, 2,
				5, colision);
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 1, 6, 5,
				6, colision);
		screen.renderPlayer(color, (int) xOffset, (int) yOffset, this, 2, 5, 6,
				7, colision);
		lastPos[0] = (int) xOffset;
		lastPos[1] = (int) yOffset;
	}
}
