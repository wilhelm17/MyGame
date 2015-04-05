package Game;

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
			"/Itemx05.png", "/Itemx2.png" };
	boolean[] blueItem = { false, true, true, false, false };
	Item[] i = new Item[5];
	int itemIndex = 0;

	public Effects(Player[] p, Screen s) {
		this.p = p;
		this.s = s;
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
		}, 2);
	}

	public void update() {
		if (t >= 10 * 60) {
			t = 0;
			if (Math.random() > 0.5) {
				color = 0x00ff00;
			} else {
				color = 0xff0000;
			}
			int p = (int) (Math.random() * 5);
			if (blueItem[p]) {
				color = 0x0000ff;
			}
			sprite = new Sprite(path[p]);
			sprite.load();
			if (itemIndex > 4) {
				itemIndex = 0;
			}
			if (i[itemIndex] != null) {
				i[itemIndex].delete();
			}
			i[itemIndex] = new Item(sprite, s, color);
			itemIndex++;
		}
		t++;
	}

	public Item getItem(int x, int y) {
		for (int k = 0; k < 5; k++) {
			for (int xx = 0; xx < 64; xx++) {
				for (int yy = 0; yy < 64; yy++) {
					if (i[k].x + xx == x && i[k].y + yy == y) {
						return i[k];

					}
				}
			}
		}
		return null;
	}
}
