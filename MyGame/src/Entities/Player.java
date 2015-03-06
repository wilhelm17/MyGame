package Entities;

import java.util.ArrayList;

import Game.Screen;
import Input.Keyboard;

public class Player {

	Screen screen;
	int color;
	double x, y;
	double alpha = 0;
	double beta = 0;
	public boolean moving = true;
	public ArrayList<Integer> a = new ArrayList<Integer>();
	public int[] lastPos = new int[2];
	int timer = 0, gap = (int) (Math.random() * (250 - 150) + 150);
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
		this.x = x;
		this.y = y;
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
			y += 2 * Math.sin(beta);
			x += 2 * Math.cos(beta);
		}
	}

	public void setColision(boolean b) {
		this.colision = b;
	}

	public void render() {
		if (timer > gap || !colision) {
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
			if (timer > gap + 15) {
				timer = 0;
				gap = (int) (Math.random() * (250 - 150) + 150);
			}
		}
		if (y >= 0 && y + 8 < screen.getHeight() - 1) {
			screen.renderPlayer(color, (int) x, (int) y, this, 2, 5, 0, 1,
					colision);
			screen.renderPlayer(color, (int) x, (int) y, this, 1, 6, 1, 2,
					colision);
			screen.renderPlayer(color, (int) x, (int) y, this, 0, 7, 2, 5,
					colision);
			screen.renderPlayer(color, (int) x, (int) y, this, 1, 6, 5, 6,
					colision);
			screen.renderPlayer(color, (int) x, (int) y, this, 2, 5, 6, 7,
					colision);
			lastPos[0] = (int) x;
			lastPos[1] = (int) y;
		} else {
			if (y > screen.getHeight() / 2) {
				y = 0;
			} else {
				y = screen.getHeight() - 18;
			}
		}
	}
}
