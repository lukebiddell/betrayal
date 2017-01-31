package Audio;

import javax.sound.sampled.*;
import javax.swing.JFrame;

public class AudioPlayer {
	
	private Clip clip;
	public FloatControl volume;
	
	public AudioPlayer(String s) {
		
		try {
//			JFrame frame = new JFrame("Test");
//			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		    frame.setTitle("Test Sound Clip");
//		    frame.setSize(300, 200);
//		    frame.setVisible(true);
		      
		      
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream(
						s
					)
				);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			AudioInputStream dais =
				AudioSystem.getAudioInputStream(
					decodeFormat, ais);
			clip = AudioSystem.getClip();
			clip.open(dais);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
		
	}
	
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
	public static void main(String[] args) {
		AudioPlayer audio = new AudioPlayer("/Music/BGM_Havok.mp3");
		audio.play();
		

	}
	
}














