package Entities;

import Game.Effects;
import Game.Screen;
import Sprites.Sprite;

public class Item {

	Screen sc;
	Sprite s;
	Effects eff;
	public int x = 0;
	public int y = 0;
	public boolean all;

	public Item(Sprite s, Screen screen, int color, boolean all, Effects eff) {
		sc = screen;
		this.s = s;
		this.eff = eff;
		this.all = all;
		while (x == 0 && y == 0) {
			x = (int) (Math.random() * (screen.width - 69) + 4);
			y = (int) (Math.random() * (screen.height - 69) + 4);
			if (!screen.isFreePosition(x, y, s)) {
				x = 0;
				y = 0;
			}
		}
		sc.renderItem(color, x, y, s, 64, 64);
	}

	public void delete() {
		sc.renderItem(0x000000, x, y, s, 64, 64);
	}

	public void initEff(Player p) {
		if (s.getPath() == "/ItemBigger.png") {
			eff.bigger(p, all);
		}
		if (s.getPath() == "/ItemBorder.png") {
			eff.border();
		}
		if (s.getPath() == "/ItemSwap.png") {
			eff.swap();
		}
		if (s.getPath() == "/Itemx05.png") {
			eff.x05(p, all);
		}
		if (s.getPath() == "/Itemx2.png") {
			eff.x2(p, all);
		}
	}
}
