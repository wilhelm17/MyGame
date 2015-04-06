package Sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	public String path;
	public int w, h;
	public int[] pixels;

	public Sprite(String path) {
		setPath(path);
	}

	public void load() {
		try {
			BufferedImage img = ImageIO.read(Sprite.class.getResource(path));
			w = img.getWidth();
			h = img.getHeight();
			pixels = new int[w * h];
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {

		}
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
