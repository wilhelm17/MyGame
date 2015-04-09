package Entities;

import java.util.ArrayList;

import Game.Screen;
import Input.Keyboard;
import Sprites.Sprite;

public class Player {

	Screen screen;
	public int color;
	double x, y;
	double alpha = 0;
	double beta = 0;
	public boolean moving = true, render = false;
	public ArrayList<Integer> a = new ArrayList<Integer>();
	public int lastPosX, lastPosY;
	int timer = 0, gap = (int) (Math.random() * (250 - 150) + 150);
	public double speed = 1;
	boolean colision = true;
	Keyboard key;
	public Sprite playerS = new Sprite("/Player.png");
	public int points = 0;

	public Player(int color, Screen screen, Keyboard key) {
		this.color = color;
		this.screen = screen;
		this.key = key;
		for (int i = 0; i <= 2000; i++) {
			a.add(0);
		}
		playerS.load();
	}

	public void setPosition(double x, double y, int alpha) {
		this.x = x;
		this.y = y;
		this.alpha = alpha;
	}

	public double[] getPosition() {
		double i[] = new double[3];
		i[0] = x;
		i[1] = y;
		i[2] = alpha;
		return i;
	}

	public void update(boolean left, boolean right) {
		timer++;
		if (moving) {
			if (left && right) {
			} else {
				if (left) {
					alpha -= 1.2;
				}
				if (right) {
					alpha += 1.2;
				}
			}
			beta = Math.toRadians(alpha);
			y += speed * Math.sin(beta);
			x += speed * Math.cos(beta);
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
				if (timer > gap + 30) {
					timer = 0;
					gap = (int) (Math.random() * (250 - 150) + 150);
				}
			}
			if (y >= 0 && y + playerS.h < screen.getHeight() - 1 && x >= 0
					&& x + playerS.w < screen.getWidth() - 1) {
				screen.renderPlayer(color, (int) x, (int) y, this, playerS,
						playerS.w, playerS.h, colision);
				lastPosX = (int) x;
				lastPosY = (int) y;
			} else {
				if (y > screen.getHeight() - 1) {
					y = 0;
				} else if (y < 0) {
					y = screen.getHeight() - (playerS.h * 2 + 1);
				}
				if (x > screen.getWidth() - 1) {
					x = 0;
				} else if (x < 0) {
					x = screen.getWidth() - (playerS.w * 2 + 1);
				}
			}
		}
	}
}
