package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	private boolean[] tkeys = new boolean[65536];
	public boolean up1, down1, left1, right1;
	public boolean up2, down2, left2, right2;
	public boolean fps, esc, clear, pause;

	public void update() {
		left1 = keys[KeyEvent.VK_A];
		right1 = keys[KeyEvent.VK_D];
		left2 = keys[KeyEvent.VK_LEFT];
		right2 = keys[KeyEvent.VK_RIGHT];
		fps = tkeys[KeyEvent.VK_F1];
		esc = keys[KeyEvent.VK_ESCAPE];
		clear = keys[KeyEvent.VK_F2];
		pause = tkeys[KeyEvent.VK_F3];
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
