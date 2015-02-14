package Game;

import java.util.ArrayList;

import Entities.Player;

public class Screen {

	public int width, height;
	public int[] pixels;
	int aIndex = 0;
	ArrayList<Integer> error = new ArrayList<Integer>();

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
	
	public void renderPixel(int x, int y, int color){
		pixels[x+y*width] = color;
	}

	public void border() {
		for (int i = 0; i < width; i++) {
			pixels[i] = 0xffffff;
		}
		for (int i = (height - 1) * width; i < (height - 0) * width; i++) {
			pixels[i] = 0xffffff;
		}

		for (int i = 0; i < pixels.length; i += width) {
			pixels[i] = 0xffffff;
		}
		for (int i = width - 1; i < pixels.length; i += width) {
			pixels[i] = 0xffffff;
		}
	}

	public void renderPlayer(int color, int x, int y, Player p) {

		for (int xb = 0; xb < 8; xb++) {
			for (int yb = 0; yb < 8; yb++) {
				if (pixels[x + xb + (y + yb) * width] != 0x000000) {
					if (pixels[x + xb + (y + yb) * width] != color) {
						p.moving = false;
					} else if (pixels[x + xb + (y + yb) * width] == color
							&& !p.a.contains(x + xb + (y + yb) * width)) {
						p.moving = false;
						System.out.println("Colision: " + (x + xb) + " , "
								+ (y + yb) + "; Color: "
								+ pixels[x + xb + (y + yb) * width]);
						p.b = false;
						p.errorx = x + xb;
						p.errory = y + yb;
					}
				}
				pixels[x + xb + (y + yb) * width] = color;
				p.a.set(aIndex, x + xb + (y + yb) * width);
				aIndex++;
				if (aIndex > 704) {
					aIndex = 0;
				}
			}
		}
	}
}