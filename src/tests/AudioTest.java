package tests;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import org.junit.Before;
import org.junit.Test;

import audio.*;
/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

public class AudioTest {

	public BGM bgm;
	public BGMComponent panel;
	public JFrame frame;
	
	@Before
	public void init() {
		frame = new JFrame("BGM Tester");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bgm = new BGM(50,"/Music/BGM_Asian_Gravedrum.wav");
		panel = new BGMComponent(bgm, 0, 100, 50);
		frame.add(panel);
	}
	
	@Test
	public void test() {
		
		bgm.play();
		frame.setVisible(true);
		assertEquals(true,bgm.isplay());
		bgm.stop();
		assertEquals(false,bgm.isplay());
	}
	
	@Test
	public void volumeValue(){

		assertEquals(50,bgm.getValue());
		
		bgm.setValue(100);
		assertEquals(100,bgm.getValue());
	}
	
	@Test
	public void muteValue(){

		assertEquals(false,bgm.getMuteStatus());
		
		bgm.setMuteStatus(true);
		assertEquals(true,bgm.getMuteStatus());
		
	}
	
	@Test
	public void muteButton(){

		BGMModel model = new BGMModel(bgm);
		BGMMuteButton button = new BGMMuteButton(model);
		frame.add(button);
		
		assertEquals(false,bgm.getMuteStatus());
		
		button.mute.doClick();
		assertEquals(true,bgm.getMuteStatus());
	}

	
	
}

