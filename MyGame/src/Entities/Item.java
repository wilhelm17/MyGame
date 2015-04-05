package Entities;

import Game.Screen;
import Sprites.Sprite;

public class Item {

	Screen sc;
	Sprite s;
	public int x = 0;
	public int y = 0;

	public Item(Sprite s, Screen screen, int color) {
		sc = screen;
		this.s = s;
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
}
