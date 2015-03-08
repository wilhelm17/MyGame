package Sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayerSprite {

	public String path = "/Player.png";
	public int[] pixels = new int[8 * 8];

	public void load() {
		try {
			BufferedImage img = ImageIO.read(PlayerSprite.class
					.getResource(path));
			int w = img.getWidth();
			int h = img.getHeight();
			img.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {

		}
	}
}
