package Audio;

import javax.sound.sampled.*;
import javax.swing.JFrame;

/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

public class BGMPlayer {
	
	private Clip clip;
	public FloatControl volume;
	public BooleanControl muteControl;
	public boolean mute;
	public float gain;
	public double value = .5;
	
	public BGMPlayer(String s) {
		
		
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
			
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);			
			muteControl = (BooleanControl)clip.getControl(BooleanControl.Type.MUTE);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public double getValue(){
		return this.value;
	}
	
	
	/**
	 * 
	 * @return the value for bgm volume
	 */
	public float getVolume(){
		return volume.getValue();
	}
	
	/**
	 * set the volume by the given value
	 */
	public void setVolume(double current){
		this.value = current;
		gain = (float)(Math.log(value)/Math.log(10.0)*20.0);
		
	}
	
	/**
	 * update the changes of bgm
	 */
	private void update(){
		volume.setValue(gain);
	}
	
	/**
	 * starts the bgm and always updating 
	 */
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
		while(clip.isRunning()){
			update();
		}
	}
	
	/**
	 * stop the bgm
	 */
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	/**
	 * close the bgm line
	 */
	public void close() {
		stop();
		clip.close();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Test Sound Clip");
	    frame.setSize(300, 200);
	    
	    frame.setVisible(true);
		BGMPlayer audio = new BGMPlayer("/Music/BGM_Asian_Gravedrum.wav");
		audio.play();

		
	}
	
}














