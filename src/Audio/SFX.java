package Audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

public class SFX{

	Clip clip;
	
	public SFX(String s){
		try {
		      
			AudioInputStream ais =
				AudioSystem.getAudioInputStream(
					getClass().getResourceAsStream(s)
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
			
	//		volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);			
	//		muteControl = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public void play(){
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	/**
	 * stop the audio / clip
	 */
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	/**
	 * close the audio / clip line
	 */
	public void close() {
		stop();
		clip.close();
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("SFX Tester");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		SFX sfx = new SFX("/Music/SFX_Powerups.wav");
		SFX sfx1 = new SFX("/Music/SFX_Health_Replenish.wav");
		
		sfx.play();
		sfx1.play();
	}
}
