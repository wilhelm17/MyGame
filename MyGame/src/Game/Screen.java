package Game;

public class Screen {

	public int width, height;
	public int[] pixels;

	public int xOffset, yOffset;

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

	public void renderPlayer(int color, int xOffset, int yOffset) {
		for (int xb = 0; xb < 8; xb++) {
			for (int yb = 0; yb < 8; yb++) {
				pixels[xOffset + xb + (yOffset + yb) * width] = color;
			}
		}
	}
}