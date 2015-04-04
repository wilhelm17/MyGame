package Game;

import java.util.Timer;
import java.util.TimerTask;

import Entities.Player;

public class Effects {

	Player[] p;
	Screen s;
	Timer timer = new Timer();

	public Effects(Player[] p, Screen s) {
		this.p = p;
		this.s = s;
	}

	public void border() {
		s.border(false);
	}

	public void swap() {
		for (int i = 0; i < p.length; i++) {
			p[i].setColision(false);
		}
		double[] temp = p[0].getPosition();
		for (int i = 0; i < p.length - 1; i++) {
			double[] d = p[i + 1].getPosition();
			p[i].setPosition(d[0], d[1], (int) d[2]);
		}
		p[p.length - 1].setPosition(temp[0], temp[1], (int) temp[2]);
		timer.schedule(new TimerTask() {
			public void run() {
				for (int i = 0; i < p.length; i++) {
					p[i].setColision(true);
				}
			}
		}, 2);
	}
}
