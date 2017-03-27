package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import game.Circle;
import game.FirstAid;
import game.Game;
import game.KeyboardInput;
import game.MouseInput;
import game.Player;
/**
 * 
 * @author Farrah Aina Mohd Zulkifli
 *
 */

public class FirstAidTest {

	public FirstAid fa;
	public Player player;
	public double heals;
	public Circle hitbox;
	public boolean delete;
	
	@Mock
	KeyboardInput key;
	@Mock
	MouseInput mouse;
	
	@Mock
	Game game;
	
	@Before
	public void init(){
		
		key = Mockito.mock(KeyboardInput.class);
		mouse = Mockito.mock(MouseInput.class);
		
		hitbox = new Circle(3.0, new Point2D.Double(1,0));
		heals = 10;
		player = new Player(game,key,mouse);
		
		player.hp = 50;
		player.hitbox = hitbox;
		fa = new FirstAid(heals,new Point2D.Double(1,1),0.3);
	}
	
	@Test
	public void test() {
		assertEquals(heals,fa.heals,0.0);
		
		fa.playerInteracted(player, KeyEvent.VK_E);
		assertEquals(60,player.hp,0);
		
		fa.playerInteracted(player, KeyEvent.VK_E);
		fa.playerInteracted(player, KeyEvent.VK_E);
		assertEquals(80,player.hp,0);
	}

}
