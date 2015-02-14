package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	private boolean[] tkeys = new boolean[65536];
	public boolean left1, right1;
	public boolean left2, right2;
	public boolean left3, right3;
	public boolean fps, esc, clear;

	public void update() {
		left1 = keys[KeyEvent.VK_A];
		right1 = keys[KeyEvent.VK_D];
		left2 = keys[KeyEvent.VK_LEFT];
		right2 = keys[KeyEvent.VK_RIGHT];
		left3 = keys[KeyEvent.VK_J];
		right3 = keys[KeyEvent.VK_L];
		fps = tkeys[KeyEvent.VK_F1];
		esc = keys[KeyEvent.VK_ESCAPE];
		clear = keys[KeyEvent.VK_F2];
		if (esc) {
			System.exit(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		tkeys[e.getKeyCode()] = !tkeys[e.getKeyCode()];

	}

	public void keyTyped(KeyEvent e) {

	}
}
