package Game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Entities.Item;
import Entities.Player;
import Input.Keyboard;
import Sprites.Sprite;

public class Effects {

	Player[] p;
	Screen s;
	Sprite sprite;
	Timer timer = new Timer();
	int t = 0, color = 0x000000;
	String[] path = { "/ItemBigger.png", "/ItemBorder.png", "/ItemSwap.png",
			"/Itemx05.png", "/Itemx2.png", "/ItemColision.png", "/KeySwap.png" };
	boolean[] blueItem = { false, true, true, false, false, false, false };
	boolean all;
	ArrayList<Item> i = new ArrayList<Item>();
	Keyboard key;

	public Effects(Player[] p, Screen s, Keyboard key) {
		this.p = p;
		this.s = s;
		this.key = key;
	}

	public void bigger(Player pl, boolean all) {
		if (all) {
			for (int i = 0; i < p.length; i++) {
				if (p[i] != pl && p[i].moving) {
					p[i].render = false;
					p[i].playerS.setPath("/Player2.png");
					p[i].playerS.load();
					p[i].render = true;
				}
			}
		} else {
			pl.render = false;
			pl.playerS.setPath("/Player2.png");
			pl.playerS.load();
			pl.render = true;
		}
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < p.length; i++) {
					p[i].render = false;
					p[i].playerS.setPath("/Player.png");
					p[i].playerS.load();
					p[i].render = true;
				}
			}
		}, 10 * 1000);
	}

	public void border() {
		s.border(false);
	}

	public void keySwap(Player pl, boolean all) {
		if (all) {
			for (int i = 0; i < p.length; i++) {
				if (p[i] != pl) {
					p[i].inverted = !p[i].inverted;
				}
			}
			timer.schedule(new TimerTask() {
				public void run() {
					for (int i = 0; i < p.length; i++) {
						if (p[i] != pl) {
							p[i].inverted = !p[i].inverted;
						}
					}
				}
			}, 10 * 1000);
		} else {
			pl.inverted = !pl.inverted;
			timer.schedule(new TimerTask() {
				public void run() {
					pl.inverted = !pl.inverted;
				}
			}, 10 * 1000);
		}
	}

	public void swap() {
		for (int i = 0; i < p.length; i++) {
			p[i].setCollision(false);
		}
		double[] temp = p[0].getPosition();
		for (int i = 0; i < p.length - 1; i++) {
			double[] d = p[i + 1].getPosition();
			p[i].setPosition(d[0], d[1], (int) d[2]);
		}
		p[p.length - 1].setPosition(temp[0], temp[1], (int) temp[2]);
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < p.length; i++) {
					p[i].setCollision(true);
				}
			}
		}, 30);
	}

	public void x05(Player pl, boolean all) {
		if (all) {
			for (int i = 0; i < p.length; i++) {
				if (p[i] != pl) {
					p[i].speed = 0.5;
				}
			}
		} else {
			pl.speed = 0.5;
		}
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < p.length; i++) {
					p[i].speed = 1;
				}
			}
		}, 10 * 1000);
	}

	public void x2(Player pl, boolean all) {
		if (all) {
			for (int i = 0; i < p.length; i++) {
				if (p[i] != pl) {
					p[i].speed = 2;
				}
			}
		} else {
			pl.speed = 2;
		}
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < p.length; i++) {
					p[i].speed = 1;
				}
			}
		}, 10 * 1000);
	}

	public void update() {
		if (t >= 10 * 60) {
			t = 0;
			if (Math.random() > 0.5) {
				color = 0x00ff00;
				all = true;
			} else {
				color = 0xff0000;
				all = false;
			}
			int p = (int) (Math.random() * path.length);
			if (blueItem[p]) {
				color = 0x0000ff;
			}
			sprite = new Sprite(path[p]);
			sprite.load();
			i.add(new Item(sprite, s, color, all, this));
		}
		t++;
	}

	public Item getItem(int x, int y) {
		for (int k = 0; k < i.size(); k++) {
			for (int xx = 0; xx < 64; xx++) {
				for (int yy = 0; yy < 64; yy++) {
					if (i.get(k).x + xx == x && i.get(k).y + yy == y) {
						return i.get(k);
					}
				}
			}
		}
		return null;
	}

	public void colision(Player pl, boolean all) {
		if (all) {
			for (int i = 0; i < p.length; i++) {
				if (p[i] != pl) {
					p[i].setCollision(false);
				}
			}
		} else {
			pl.setCollision(false);
		}
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < p.length; i++) {
					p[i].setCollision(true);
				}
			}
		}, 10 * 1000);
	}
}
