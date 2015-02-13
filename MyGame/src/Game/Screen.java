package Game;

import Entities.Player;

public class Screen {

	public int width, height;
	public int[] pixels;

	public Screen(int width, int height) {

		this.width = width;
		this.height = height;
		pixels = new int[width * height];

	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
	}

	public void renderPlayer(int color, int x, int y, Player p) {

		for (int xb = 0; xb < 8; xb++) {
			for (int yb = 0; yb < 8; yb++) {
				if (pixels[x + xb + (y + yb) * width] != 0x000000
						&& pixels[x + xb + (y + yb) * width] != color) {
					p.moving = false;
				}
				pixels[x + xb + (y + yb) * width] = color;
			}
		}
	}
}