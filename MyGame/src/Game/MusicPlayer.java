package Game;

import java.io.BufferedInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer implements Runnable {

	private AudioInputStream audio;
	private Clip clip;

	public void run() {
		loadClip("/Monkeys_Spinning_Monkeys.wav");
	}

	public void loadClip(String path) {
		try {
			audio = AudioSystem.getAudioInputStream(new BufferedInputStream(
					MusicPlayer.class.getResourceAsStream(path)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			clip = AudioSystem.getClip();
			clip.open(audio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start(boolean loop) {
		clip.setFramePosition(0);
		clip.start();
		if (loop)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}
}
