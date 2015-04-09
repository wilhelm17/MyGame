package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import Game.Game;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	private boolean[] tkeys = new boolean[65536];
	private boolean[] dkeys = new boolean[65536];
	public boolean k1, k2, k3, k4, k5, k6, k7, k8, k9, k0, ka, kb, kc, kd, ke,
			kf;
	public boolean fps, esc, start, pause, swap;
	public boolean up, down, enter, back, tab;
	public boolean playerkeys[];
	public ArrayList<Integer> playerkeyEvents = new ArrayList<Integer>();
	public int playercount, counter = 0;
	public boolean setPlayerKeys;
	public Game g;

	public void update() {
		back = keys[KeyEvent.VK_BACK_SPACE];
		tab = keys[KeyEvent.VK_CONTROL];
		k0 = keys[KeyEvent.VK_0];
		k1 = keys[KeyEvent.VK_1];
		k2 = keys[KeyEvent.VK_2];
		k3 = keys[KeyEvent.VK_3];
		k4 = keys[KeyEvent.VK_4];
		k5 = keys[KeyEvent.VK_5];
		k6 = keys[KeyEvent.VK_6];
		k7 = keys[KeyEvent.VK_7];
		k8 = keys[KeyEvent.VK_8];
		k9 = keys[KeyEvent.VK_9];
		ka = keys[KeyEvent.VK_A];
		kb = keys[KeyEvent.VK_B];
		kc = keys[KeyEvent.VK_C];
		kd = keys[KeyEvent.VK_D];
		ke = keys[KeyEvent.VK_E];
		kf = keys[KeyEvent.VK_F];
		up = dkeys[KeyEvent.VK_UP];
		dkeys[KeyEvent.VK_UP] = false;
		down = dkeys[KeyEvent.VK_DOWN];
		dkeys[KeyEvent.VK_DOWN] = false;
		enter = dkeys[KeyEvent.VK_ENTER];
		dkeys[KeyEvent.VK_ENTER] = false;
		fps = tkeys[KeyEvent.VK_F1];
		esc = keys[KeyEvent.VK_ESCAPE];
		start = tkeys[KeyEvent.VK_F2];
		pause = tkeys[KeyEvent.VK_F3];
		if (g.p != null) {
			for (int i = 0; i < playercount; i++) {
				playerkeys[i * 2] = keys[playerkeyEvents.get(i * 2)];
				playerkeys[(i * 2) + 1] = keys[playerkeyEvents.get((i * 2) + 1)];
			}
		}
		if (esc) {
			System.exit(0);
		}
	}

	public void setPlayercount(int k) {
		playercount = k;
	}

	public void setPlayerKeys() {
		setPlayerKeys = true;
	}

	public boolean getPlayerKeys() {
		return setPlayerKeys;
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		dkeys[e.getKeyCode()] = true;
		if (setPlayerKeys && counter < playercount * 2) {
			playerkeyEvents.add(e.getKeyCode());
			counter++;
		}
		if (counter >= playercount * 2) {
			setPlayerKeys = false;
		}
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		tkeys[e.getKeyCode()] = !tkeys[e.getKeyCode()];

	}

	public void keyTyped(KeyEvent e) {

	}
}
