package Game;

import Entities.Player;
import Sprites.Sprite;

public class Screen {

	public int width, height;
	public int[] pixels;
	int aIndex = 0;
	Player p;

	public Screen(int width, int height) {

		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
		}
//		border();
	}

	public void renderPixel(int x, int y, int color) {
		pixels[x + y * width] = color;
	}

	public boolean isFreePosition(int x, int y, Sprite s) {
		for (int xb = 0; xb < s.w; xb++) {
			for (int yb = 0; yb < s.h; yb++) {
				if (s.pixels[xb + yb * s.w] == -16777216
						&& pixels[x + xb + (y + yb) * width] != 0x000000) {
					return false;
				}
			}
		}
		return true;
	}

	public void border() {
		for (int i = 0; i < width * 2; i++) {
			pixels[i] = 0xffffff;
		}
		for (int i = (height - 2) * width; i < (height - 0) * width; i++) {
			pixels[i] = 0xffffff;
		}

		for (int i = 0; i < pixels.length; i += width) {
			pixels[i] = 0xffffff;
		}
		for (int i = 1; i < pixels.length; i += width) {
			pixels[i] = 0xffffff;
		}
		for (int i = width - 1; i < pixels.length; i += width) {
			pixels[i] = 0xffffff;
		}
		for (int i = width - 2; i < pixels.length; i += width) {
			pixels[i] = 0xffffff;
		}
	}

	public void Colision(int x, int y, int color) {
		if (pixels[x + y * width] != 0x000000 && color != 0x000000) {
			if (pixels[x + y * width] != color) {
				p.moving = false;
			} else if (!p.a.contains(x + y * width)) {
				p.moving = false;
			}
		}
	}

	public void renderPlayer(int color, int x, int y, Player p, Sprite s,
			int w, int h, boolean colision) {
		this.p = p;
		for (int xb = 0; xb < w; xb++) {
			for (int yb = 0; yb < h; yb++) {
				if (s.pixels[xb + yb * w] == -16777216) {
					if (colision) {
						Colision(x + xb, y + yb, color);
					}
					pixels[x + xb + (y + yb) * width] = color;
					if (!p.a.contains(x + xb + (y + yb) * width)) {
						p.a.set(aIndex, x + xb + (y + yb) * width);
						aIndex++;
					}
					if (aIndex > 1000) {
						aIndex = 0;
					}
				}
			}
		}
	}
}