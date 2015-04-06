package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import Entities.Player;
import Input.Keyboard;
import Sprites.Sprite;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int scale = 1;
	public static int width = Toolkit.getDefaultToolkit().getScreenSize().width
			/ scale;
	public static int height = Toolkit.getDefaultToolkit().getScreenSize().height
			/ scale;

	private static Thread thread;
	public static boolean running = false, end = false, respawnrunning = false;
	private Keyboard key;
	private static JFrame frm;
	int playercount = 2;
	int[] crash = new int[playercount * 2];
	int crashcounter = 0;
	Player[] p = new Player[playercount];
	int[] spawn = new int[3];
	Timer timer = new Timer();
	String s = "";
	Graphics g;
	private static Screen screen;
	private Effects eff;
	int fps;
	int respawncount = 0;
	static Sprite playerS = new Sprite("/Player.png");

	private BufferedImage img = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer())
			.getData();

	public Game() {

		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		key = new Keyboard();
		for (int i = 0; i < playercount; i++) {
			p[i] = new Player(createRandomColor(), screen, key);
			spawn = createRandomSpawn();
			p[i].setPosition(spawn[0], spawn[1], spawn[2]);
		}
		eff = new Effects(p, screen);
		screen.setEff(eff);
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

	int ups;

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 120.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while (running == true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				ups = updates;
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	public void update() {
		key.update();
		if (!key.pause || end) {
			p[0].update(key.left1, key.right1);
			p[1].update(key.left2, key.right2);
			eff.update();
			crash();
		}
	}

	public int[] createRandomSpawn() {
		int x = 0, y = 0;
		int alpha = (int) (Math.random() * 360);
		while (x == 0 && y == 0) {
			x = (int) (Math.random() * (width - 400) + 200);
			y = (int) (Math.random() * (height - 400) + 200);
			if (!screen.isFreePosition(x, y, playerS)) {
				x = 0;
				y = 0;
			}
		}
		return new int[] { x, y, alpha };
	}

	public int createRandomColor() {
		int color = (int) (Math.random() * 0xFFFFFF);
		return color;
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
		for (int i = 0; i < playercount; i++) {
			p[i].render();
		}
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		g = bs.getDrawGraphics();
		g.fillRect(0, 0, width * scale, height * scale);
		g.drawImage(img, 0, 0, width, height, null);
		overlay();
		g.dispose();
		bs.show();
	}

	public void respawn() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				screen.clear();
				for (int i = 0; i < playercount; i++) {
					p[i].points += crash[(i * 2) + 1];
					crash[(i * 2) + 1] = 0;
					spawn = createRandomSpawn();
					p[i].setPosition(spawn[0], spawn[1], spawn[2]);
					p[i].moving = true;
					crash[i * 2] = 0;
					if (p[i].points >= playercount * 5) {
						end = true;
						int winner = 0;
						for (int k = 0; k < playercount; k++) {
							if (p[k].points > p[winner].points) {
								winner = k;
							}
							p[k].render = false;
						}
						s = "Player " + (winner + 1) + " wins!";
					}
					screen.clear();
				}
				if (s.equals("1")) {
					s = "";
				}
				crashcounter = 0;
				respawnrunning = false;
			}
		}, 3 * 1000);
		s = "3";
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				s = "2";
			}
		}, 1 * 1000);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				s = "1";
			}
		}, 2 * 1000);
	}

	public void overlay() {
		if (key.fps) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 0, 20));
			g.drawString(fps + " fps, " + ups + " ups", 20, 20);
		}
		if (key.pause) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 0, 100));
			g.drawString("Pause", (width - 200) / 2, height / 2);
		} else {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 0, 100));
			g.drawString(s, width / 2, height / 2);
		}
		for (int i = 0; i < playercount; i++) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial", 0, 20));
			g.drawString("Player " + (i + 1) + ": " + p[i].points, 20,
					(50 + i * 20));
		}
	}

	public void crash() {
		for (int i = 0; i < playercount; i++) {
			if (!p[i].moving && crash[i * 2] == 0) {
				crashcounter++;
				crash[i * 2] = 1;
				crash[(i * 2) + 1] = crashcounter;
			}
		}
		if (playercount - crashcounter == 1 && !respawnrunning
				|| playercount - crashcounter == 0 && !respawnrunning) {
			respawnrunning = true;
			for (int i = 0; i < playercount; i++) {
				if (p[i].moving) {
					crash[(i * 2) + 1] = playercount;
				}
			}
			respawn();
		}
	}

	public static void main(String[] args) {

		Game game = new Game();
		playerS.load();
		frm = new JFrame();
		frm.add(game);
		frm.setUndecorated(true);
		frm.pack();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
		game.start();
		screen.clear();
	}
}
