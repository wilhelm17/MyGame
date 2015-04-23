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
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
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
	public static boolean running = false;
	public boolean end = false;
	public static boolean respawnrunning = false;
	private static Keyboard key;
	private static JFrame frm;
	public int playercount = 0, rounds = 0;
	int[] crash;
	int crashcounter = 0;
	public Player[] p;
	public String[] playerC;
	int[] spawn = new int[3];
	Timer timer = new Timer();
	String s = " ", playercountString = "X", roundsString = "X";
	Graphics g;
	private static Screen screen;
	public Effects eff;
	static MusicPlayer mp;
	int fps;
	int respawncount = 0;
	static Sprite playerS = new Sprite("/Player.png");
	static Menu m;

	private BufferedImage img = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer())
			.getData();

	public Game() {

		screen = new Screen(width, height);
		key = new Keyboard();
		key.g = this;
		playerS.load();
		this.setFocusable(true);
		this.requestFocus();
		addKeyListener(key);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "GameLoop");
		thread.start();
		m = null;

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
	}

	public void update() {
		key.update();
		if (!key.pause && p != null && !end) {
			for (int i = 0; i < playercount; i++) {
				p[i].update(key.playerkeys[i * 2], key.playerkeys[(i * 2) + 1]);
			}
			if (eff != null) {
				eff.update();
			}
			if (p != null) {
				crash();
			}
		}
		if (m != null) {
			m.update();
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

	public String createRandomColor() {
		Integer color = (int) (Math.random() * 0xFFFFFF);
		return color.toString();
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		if (m != null) {
			m.render();
		}
		if (m == null && !end) {
			for (int i = 0; i < playercount; i++) {
				p[i].render();
			}
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

	public void respawn(Game game) {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				screen.clear(true);
				for (int i = 0; i < playercount; i++) {
					p[i].points += crash[(i * 2) + 1];
					crash[(i * 2) + 1] = 0;
					spawn = createRandomSpawn();
					p[i].setPosition(spawn[0], spawn[1], spawn[2]);
					p[i].moving = true;
					crash[i * 2] = 0;
					if (p[i].points >= playercount * rounds) {
						end = true;
						int winner = 0;
						for (int k = 0; k < playercount; k++) {
							if (p[k].points > p[winner].points) {
								winner = k;
							}
							p[k].render = false;
						}
						eff = null;
						s = "Player " + (winner + 1) + " wins!";
						timer.schedule(new TimerTask() {
							@Override
							public void run() {
								mp.stop();
								p = null;
								s = "";
								m = new Menu(screen, width, height, key, game);
							}
						}, 10 * 1000);
					}
					screen.clear(true);
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
		if (p != null && !end) {
			for (int i = 0; i < playercount; i++) {
				g.setColor(Color.white);
				g.setFont(new Font("Arial", 0, 20));
				g.drawString("Player " + (i + 1) + ": " + p[i].points, 20,
						(50 + i * 20));
			}
		}
		if (m != null && m.mainHUB) {
			BufferedImage img;
			try {
				img = ImageIO.read(Game.class.getResource("/LineHunter.png"));
				g.drawImage(img, width / 2 - 300, 4, null);
			} catch (IOException e) {
			}
		}
		if (m != null && m.settings) {
			BufferedImage img;
			try {
				img = ImageIO.read(Game.class.getResource("/LineHunter.png"));
				g.drawImage(img, width / 2 - 300, 4, null);
			} catch (IOException e) {
			}
			g.setColor(Color.white);
			g.setFont(new Font("Impact", 0, 30));
			g.drawString(playercountString, width / 2 + 100, height / 2 - 65);
			g.drawString(roundsString, width / 2 + 100, height / 2 + 10);
		}
		if (m != null && m.colorCreation && m.colorCounter < playercount) {
			g.setColor(Color.white);
			g.setFont(new Font("Impact", 0, 30));
			String ss = new Integer(m.colorCounter + 1).toString();
			g.drawString(ss, width / 2 - 60, height / 2 + 10);
			g.drawString(playerC[m.colorCounter], width / 2 + 170,
					height / 2 + 10);
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
			respawn(this);
		}
	}

	public static void main(String[] args) {

		Game game = new Game();
		mp = new MusicPlayer();
		frm = new JFrame();
		frm.add(game);
		frm.setUndecorated(true);
		Dimension size = new Dimension(width * scale, height * scale);
		frm.setPreferredSize(size);
		frm.pack();
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
		game.start();
		screen.clear(false);
		m = new Menu(screen, width, height, key, game);
	}
}
