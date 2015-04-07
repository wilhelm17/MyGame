package Game;

import Input.Keyboard;
import Sprites.Sprite;

public class Menu {

	private Screen sc;
	private int width;
	private int height;
	boolean mainHUB = true, settings = false;
	Sprite startS = new Sprite("/Start.png");
	Sprite settingsS = new Sprite("/Settings.png");
	Sprite exitS = new Sprite("/Exit.png");
	int startColor, settingsColor, exitColor;
	Keyboard key;

	public Menu(Screen sc, int width, int height, Keyboard key) {
		this.sc = sc;
		this.width = width;
		this.height = height;
		this.key = key;
		startS.load();
		settingsS.load();
		exitS.load();
		startColor = 0x808080;
		settingsColor = 0x6400FF;
		exitColor = 0xffffff;
	}

	public void update(Game game) {
		if (mainHUB) {
			if (key.enter) {
				if (startColor == 0x6400FF) {
					game.start();
					sc.clear();
				} else if (settingsColor == 0x6400FF) {
					mainHUB = false;
					sc.clear();
					settings = true;
				} else if (exitColor == 0x6400FF) {
					System.exit(0);
				}
			}
			if (key.up) {
				if (settingsColor == 0x6400FF && startColor != 0x808080) {
					settingsColor = 0xffffff;
					startColor = 0x6400FF;
				} else if (exitColor == 0x6400FF) {
					exitColor = 0xffffff;
					settingsColor = 0x6400FF;
				}
			}
			if (key.down) {
				if (startColor == 0x6400FF) {
					startColor = 0xffffff;
					settingsColor = 0x6400FF;
				} else if (settingsColor == 0x6400FF) {
					settingsColor = 0xffffff;
					exitColor = 0x6400FF;
				}
			}
		}
	}

	public void render() {
		if (mainHUB) {
			sc.renderItem(startColor, width / 2 - 100, height / 2 - 100,
					startS, 200, 50);
			sc.renderItem(settingsColor, width / 2 - 100, height / 2 - 25,
					settingsS, 200, 50);
			sc.renderItem(exitColor, width / 2 - 100, height / 2 + 50, exitS,
					200, 50);
		} else if (settings) {
			
		}
	}
}
