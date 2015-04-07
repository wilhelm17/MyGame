package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	private boolean[] tkeys = new boolean[65536];
	private boolean[] dkeys = new boolean[65536];
	public boolean left1, right1;
	public boolean left2, right2;
	public boolean left3, right3;
	public boolean left4, right4;
	public boolean fps, esc, start, pause, swap;
	public boolean up, down, enter;

	public void update() {
		up = dkeys[KeyEvent.VK_UP];
		dkeys[KeyEvent.VK_UP] = false;
		down = dkeys[KeyEvent.VK_DOWN];
		dkeys[KeyEvent.VK_DOWN] = false;
		enter = keys[KeyEvent.VK_ENTER];
		left1 = keys[KeyEvent.VK_A];
		right1 = keys[KeyEvent.VK_D];
		left2 = keys[KeyEvent.VK_LEFT];
		right2 = keys[KeyEvent.VK_RIGHT];
		left3 = keys[KeyEvent.VK_J];
		right3 = keys[KeyEvent.VK_L];
		left4 = keys[KeyEvent.VK_F];
		right4 = keys[KeyEvent.VK_H];
		fps = tkeys[KeyEvent.VK_F1];
		esc = keys[KeyEvent.VK_ESCAPE];
		start = tkeys[KeyEvent.VK_F2];
		pause = tkeys[KeyEvent.VK_F3];
		if (esc) {
			System.exit(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		dkeys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		tkeys[e.getKeyCode()] = !tkeys[e.getKeyCode()];

	}

	public void keyTyped(KeyEvent e) {

	}
}
