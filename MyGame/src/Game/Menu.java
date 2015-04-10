package Game;

import java.util.Timer;
import java.util.TimerTask;

import Entities.Player;
import Input.Keyboard;
import Sprites.Sprite;

public class Menu {

	private Screen sc;
	private int width;
	private int height;
	boolean mainHUB = true, settings = false, playerCreation = false,
			tabbed = false, colorCreation = false;
	Sprite startS = new Sprite("/Start.png");
	Sprite settingsS = new Sprite("/Settings.png");
	Sprite exitS = new Sprite("/Exit.png");
	Sprite playerNumberS = new Sprite("/PlayerNumber.png");
	Sprite roundsS = new Sprite("/Rounds.png");
	Sprite backS = new Sprite("/Back.png");
	Sprite playerCreationS = new Sprite("/PlayerCreation.png");
	Sprite tipp1S = new Sprite("/tipp1.png");
	Sprite tipp2S = new Sprite("/tipp2.png");
	Sprite colorCreationS = new Sprite("/colorCreation.png");
	Sprite musicS = new Sprite("/music.png");
	Sprite music2S = new Sprite("/music2.png");
	int startColor, settingsColor, exitColor, playerNumberColor, roundsColor,
			backColor;
	Keyboard key;
	Timer timer = new Timer();
	public int colorCounter = 0;
	Game game;

	public Menu(Screen sc, int width, int height, Keyboard key, Game g) {
		this.sc = sc;
		this.width = width;
		this.height = height;
		this.key = key;
		startS.load();
		settingsS.load();
		exitS.load();
		playerNumberS.load();
		roundsS.load();
		backS.load();
		playerCreationS.load();
		tipp1S.load();
		tipp2S.load();
		colorCreationS.load();
		musicS.load();
		music2S.load();
		startColor = 0x808080;
		settingsColor = 0x6400FF;
		exitColor = 0xffffff;
		playerNumberColor = 0x6400FF;
		roundsColor = 0xffffff;
		backColor = 0xffffff;
		game = g;
		game.end = false;
	}

	public void update() {
		if (mainHUB) {
			mainHUB();
		} else if (settings) {
			settings();
		} else if (playerCreation) {
			playerCreation();
		}
	}

	private void settings() {
		if (key.enter) {
			if (playerNumberColor == 0x6400FF) {
				if (key.tab && !game.playercountString.equals("X")
						&& !game.playercountString.equals("1")
						&& !game.playercountString.equals("")) {
					key.playerkeyEvents.clear();
					key.counter = 0;
					tabbed = true;
					game.playercount = Integer.parseInt(game.playercountString);
					key.playerkeys = new boolean[game.playercount * 2];
					key.setPlayercount(game.playercount);
					game.playerC = new String[game.playercount];
					for (int i = 0; i < game.playercount; i++) {
						game.playerC[i] = "X";
					}
					settings = false;
					sc.clear(false);
					key.setPlayerKeys();
					playerCreation = true;
				}
				if (game.playercountString.equals("X")) {
					game.playercountString = "";
				}
				if (key.back) {
					game.playercountString = "X";
				}
				if (key.k0 && !game.playercountString.equals("")
						&& game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "0";
				}
				if (key.k1 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "1";
				}
				if (key.k2 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "2";
				}
				if (key.k3 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "3";
				}
				if (key.k4 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "4";
				}
				if (key.k5 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "5";
				}
				if (key.k6 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "6";
				}
				if (key.k7 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "7";
				}
				if (key.k8 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "8";
				}
				if (key.k9 && game.playercountString.length() < 2) {
					game.playercountString = game.playercountString + "9";
				}
			} else if (roundsColor == 0x6400FF) {
				if (game.roundsString.equals("X")) {
					game.roundsString = "";
				}
				if (key.back) {
					game.roundsString = "X";
				}
				if (key.k0 && !game.roundsString.equals("")
						&& game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "0";
				}
				if (key.k1 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "1";
				}
				if (key.k2 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "2";
				}
				if (key.k3 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "3";
				}
				if (key.k4 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "4";
				}
				if (key.k5 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "5";
				}
				if (key.k6 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "6";
				}
				if (key.k7 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "7";
				}
				if (key.k8 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "8";
				}
				if (key.k9 && game.roundsString.length() < 9) {
					game.roundsString = game.roundsString + "9";
				}
			} else if (backColor == 0x6400FF) {
				settings = false;
				if (!game.roundsString.equals("X")) {
					game.rounds = Integer.parseInt(game.roundsString);
				}
				if (tabbed && !game.roundsString.equals("X")) {
					startColor = 0xffffff;
				} else {
					startColor = 0x808080;
				}
				sc.clear(false);
				mainHUB = true;
			}
		}
		if (key.up) {
			if (roundsColor == 0x6400FF) {
				roundsColor = 0xffffff;
				playerNumberColor = 0x6400FF;
			} else if (backColor == 0x6400FF) {
				backColor = 0xffffff;
				roundsColor = 0x6400FF;
			}
		}
		if (key.down) {
			if (playerNumberColor == 0x6400FF) {
				playerNumberColor = 0xffffff;
				roundsColor = 0x6400FF;
			} else if (roundsColor == 0x6400FF) {
				roundsColor = 0xffffff;
				backColor = 0x6400FF;
			}
		}
	}

	private void mainHUB() {
		if (key.enter) {
			if (startColor == 0x6400FF) {
				Game.mp.start(true);
				game.p = new Player[game.playercount];
				for (int i = 0; i < game.playercount; i++) {
					int hexInt = Long.decode(game.playerC[i]).intValue();
					game.p[i] = new Player(hexInt, sc, key);
					game.spawn = game.createRandomSpawn();
					game.p[i].setPosition(game.spawn[0], game.spawn[1],
							game.spawn[2]);
				}
				timer.schedule(new TimerTask() {
					public void run() {
						for (int i = 0; i < game.playercount; i++) {
							game.p[i].render = true;
						}
					}
				}, 100);
				game.crash = new int[game.playercount * 2];
				game.eff = new Effects(game.p, sc, key);
				sc.setEff(game.eff);
				game.start();
				sc.clear(false);
			} else if (settingsColor == 0x6400FF) {
				mainHUB = false;
				sc.clear(false);
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

	public void playerCreation() {
		if (!key.getPlayerKeys()) {
			sc.clear(false);
			colorCreation = true;
		}
		if (colorCreation) {
			if (colorCounter < game.playercount) {
				if (key.enter) {
					if (game.playerC[colorCounter] == "X") {
						game.playerC[colorCounter] = "";
					}
					if (key.back) {
						game.playerC[colorCounter] = "";
					}
					if (key.tab && game.playerC[colorCounter].length() == 6
							&& !game.playerC[colorCounter].equals("000000")
							&& !game.playerC[colorCounter].equals("ffffff")
							&& !game.playerC[colorCounter].equals("ff0000")
							&& !game.playerC[colorCounter].equals("00ff00")
							&& !game.playerC[colorCounter].equals("0000ff")) {
						colorCounter++;
						if (colorCounter >= game.playercount) {
							for (int i = 0; i < game.playercount; i++) {
								game.playerC[i] = "0x" + game.playerC[i];
							}
							colorCreation = false;
							colorCounter = 0;
							playerCreation = false;
							sc.clear(false);
							settings = true;
						}
					}
					if (key.k0 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "0";
					}
					if (key.k1 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "1";
					}
					if (key.k2 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "2";
					}
					if (key.k3 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "3";
					}
					if (key.k4 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "4";
					}
					if (key.k5 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "5";
					}
					if (key.k6 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "6";
					}
					if (key.k7 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "7";
					}
					if (key.k8 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "8";
					}
					if (key.k9 && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "9";
					}
					if (key.ka && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "a";
					}
					if (key.kb && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "b";
					}
					if (key.kc && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "c";
					}
					if (key.kd && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "d";
					}
					if (key.ke && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "e";
					}
					if (key.kf && game.playerC[colorCounter].length() < 6) {
						game.playerC[colorCounter] = game.playerC[colorCounter]
								+ "f";
					}
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
			sc.renderItem(0xffffff, width - music2S.w - 4, height - music2S.h
					- musicS.h - 4, music2S, music2S.w, music2S.h);
			sc.renderItem(0xffffff, width - musicS.w - 4,
					height - musicS.h - 4, musicS, musicS.w, musicS.h);
		} else if (settings) {
			sc.renderItem(playerNumberColor, width / 2 - 100, height / 2 - 100,
					playerNumberS, 200, 50);
			sc.renderItem(0xffffff, width / 2 - 300, height / 2 - 100, tipp1S,
					200, 200);
			sc.renderItem(0xffffff, width / 2 + 200, height / 2 - 100, tipp2S,
					200, 200);
			sc.renderItem(roundsColor, width / 2 - 100, height / 2 - 25,
					roundsS, 200, 50);
			sc.renderItem(backColor, width / 2 - 100, height / 2 + 50, backS,
					200, 50);
		} else if (playerCreation && !colorCreation) {
			sc.renderItem(0xffffff, width / 2 - 300, height / 2 - 100,
					playerCreationS, 600, 200);
		} else if (colorCreation) {
			sc.renderItem(0xffffff, width / 2 - 150, height / 2 - 25,
					colorCreationS, 300, 50);
		}
	}
}
