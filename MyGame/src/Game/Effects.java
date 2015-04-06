package Game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Entities.Item;
import Entities.Player;
import Sprites.Sprite;

public class Effects {

	Player[] p;
	Screen s;
	Sprite sprite;
	Timer timer = new Timer();
	int t = 0, color = 0x000000;
	String[] path = { "/ItemBigger.png", "/ItemBorder.png", "/ItemSwap.png",
			"/Itemx05.png", "/Itemx2.png", "/ItemColision.png" };
	boolean[] blueItem = { false, true, true, false, false, false };
	boolean all;
	ArrayList<Item> i = new ArrayList<Item>();

	public Effects(Player[] p, Screen s) {
		this.p = p;
		this.s = s;
	}

	public void bigger(Player pl, boolean all) {
		if (all) {
			for (int i = 0; i < p.length; i++) {
				if (p[i] != pl) {
					p[i].playerS.setPath("/Player2.png");
					p[i].playerS.load();
				}
			}
		} else {
			pl.playerS.setPath("/Player2.png");
			pl.playerS.load();
		}
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < p.length; i++) {
					p[i].playerS.setPath("/Player.png");
					p[i].playerS.load();
				}
			}
		}, 10 * 1000);
	}

	public void border() {
		s.border(false);
	}

	public void swap() {
		for (int i = 0; i < p.length; i++) {
			p[i].setColision(false);
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
					p[i].setColision(true);
				}
			}
		}, 10);
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
					p[i].setColision(false);
				}
			}
		} else {
			pl.setColision(false);
		}
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < p.length; i++) {
					p[i].setColision(true);
				}
			}
		}, 10 * 1000);
	}
}
