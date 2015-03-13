package Entities;

import java.util.ArrayList;

import Game.Screen;
import Input.Keyboard;
import Sprites.PlayerSprite;
import Sprites.Sprite;

public class Player {

	Screen screen;
	int color;
	double x, y;
	double alpha = 0;
	double beta = 0;
	public boolean moving = true, render = true;
	public ArrayList<Integer> a = new ArrayList<Integer>();
	public int lastPosX, lastPosY;
	int timer = 0, gap = (int) (Math.random() * (250 - 150) + 150);
	boolean colision = true;
	Keyboard key;
	Sprite playerS = new PlayerSprite();
	public int points = 0;

	public Player(int color, Screen screen, Keyboard key) {
		this.color = color;
		this.screen = screen;
		this.key = key;
		for (int i = 0; i <= 1000; i++) {
			a.add(0);
		}
		playerS.load();
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
		if (render) {
			if (timer > gap || !colision) {
				screen.renderPlayer(0x000000, lastPosX, lastPosY, this,
						playerS, playerS.w, playerS.h, colision);
				if (timer > gap + 15) {
					timer = 0;
					gap = (int) (Math.random() * (250 - 150) + 150);
				}
			}
			if (y >= 0 && y + 8 < screen.getHeight() - 1 && x >= 0
					&& x + 8 < screen.getWidth() - 1) {
				screen.renderPlayer(color, (int) x, (int) y, this, playerS,
						playerS.w, playerS.h, colision);
				lastPosX = (int) x;
				lastPosY = (int) y;
			} else {
				if (y > screen.getHeight() - 1) {
					y = 0;
				} else if (y < 0) {
					y = screen.getHeight() - 17;
				}
				if (x > screen.getWidth() - 1) {
					x = 0;
				} else if (x < 0) {
					x = screen.getWidth() - 17;
				}
			}
		}
	}
}
