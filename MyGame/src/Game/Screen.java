package Game;

import Entities.Item;
import Entities.Player;
import Sprites.Sprite;

public class Screen {

	public int width, height;
	public int[] pixels;
	int aIndex = 0;
	Player p;
	Effects eff;
	Item i;

	public Screen(int width, int height) {

		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void setEff(Effects eff) {
		this.eff = eff;
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
		eff.i.clear();
		border(true);
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

	public void border(boolean b) {
		int color = 0x000000;
		if (b) {
			color = 0xffffff;
		}
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = color;
			}
		}
		for (int y = height - 4; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = color;
			}
		}
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < 4; x++) {
				pixels[x + y * width] = color;
			}
		}
		for (int y = 0; y < height; y++) {
			for (int x = width - 4; x < width; x++) {
				pixels[x + y * width] = color;
			}
		}
	}

	public void Colision(int x, int y, int color) {
		if (pixels[x + y * width] != 0x000000 && color != 0x000000) {
			if (pixels[x + y * width] != color) {
				if (pixels[x + y * width] == 16711680) {
					i = eff.getItem(x, y);
					i.initEff(p);
					i.delete();
				} else if (pixels[x + y * width] == 65280) {
					i = eff.getItem(x, y);
					i.initEff(p);
					i.delete();
				} else if (pixels[x + y * width] == 255) {
					i = eff.getItem(x, y);
					i.initEff(p);
					i.delete();
				} else {
					p.moving = false;
				}
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
					if (aIndex > 2000) {
						aIndex = 0;
					}
				}
			}
		}
	}

	public void renderItem(int color, int x, int y, Sprite s, int w, int h) {
		for (int xb = 0; xb < w; xb++) {
			for (int yb = 0; yb < h; yb++) {
				if (s.pixels[xb + yb * w] == -16777216) {
					pixels[x + xb + (y + yb) * width] = color;
				}
			}
		}
	}
}