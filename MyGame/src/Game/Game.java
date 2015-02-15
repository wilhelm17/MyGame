package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Entities.Player;
import Input.Keyboard;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int scale = 1;
	public static int width = Toolkit.getDefaultToolkit().getScreenSize().width
			/ scale;
	public static int height = (width / 16 * 9) - 30;

	private static Thread thread;
	public static boolean running = false;
	private Keyboard key;
	private static JFrame frm;
	Player player1, player2, player3;
	int[] spawn = new int[2];
	int p1xs = 0, p2xs = 0, p1ys = 0, p2ys = 0;

	Graphics g;
	private static Screen screen;
	int fps;

	private BufferedImage img = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer())
			.getData();

	public Game() {

		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		key = new Keyboard();
		player1 = new Player(0xE60EB0, screen);
		spawn = createRandomSpawn();
		p1xs = spawn[0];
		p1ys = spawn[1];
		player1.setSpawn(p1xs, p1ys);
		player2 = new Player(0x1FDB44, screen);
		spawn = createRandomSpawn();
		p2xs = spawn[0];
		p2ys = spawn[1];
		player2.setSpawn(p2xs, p2ys);
		this.setFocusable(true);
		this.requestFocus();
		addKeyListener(key);
	}

	public synchronized void start() {

		running = true;
		thread = new Thread(this, "Display");
		thread.start();

	}

	public synchronized static void stop() {

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
		}

	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		while (running == true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		key.update();
		player1.update(key.left1, key.right1);
		player2.update(key.left2, key.right2);
	}

	public int[] createRandomSpawn() {
		int x = 0, y = 0;
		while (x == 0 && y == 0) {
			x = (int) (Math.random() * width);
			y = (int) (Math.random() * height);
			if (!screen.isFreePosition(x, y)) {
				x = 0;
				y = 0;
			}
		}
		return new int[] { x, y };
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		if (key.clear) {
			screen.clear();
		}
		player1.render();
		player2.render();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		g = bs.getDrawGraphics();
		g.fillRect(0, 0, width * scale, height * scale);
		g.drawImage(img, 0, 0, width, height, null);

		if (key.fps) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 0, 20));
			g.drawString(fps + " fps", 20, 20);
		}

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {

		Game game = new Game();
		frm = new JFrame();
		GraphicsDevice ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getScreenDevices()[0];
		ge.setFullScreenWindow(frm);
		frm.add(game);
		frm.pack();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
		game.start();
		screen.clear();
		screen.border();
	}
}
