package game;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public final class SpritesheetEnum {
	public static final int MONSTER = 0;
	public static final int PLAYER = 1;
	public static final int ARENA = 2;
	public static final int WALL = 3;
	
	public static final int ENUMSIZE = 4;
	
	private static Spritesheet getSpritesheet(int val){
		switch(val){
			case MONSTER:
				try{
					return new Spritesheet(ImageIO.read(new File("Resources/Images/monster_bird.png")),4,4, new int[]{4,4,4,2});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}
			
			case PLAYER:
				try{
					return new Spritesheet(ImageIO.read(new File("Resources/Images/player.png")),6,3, new int[]{6,6,1});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}
				
			case ARENA:
				try{
					return new Spritesheet(ImageIO.read(new File("Resources/Images/arena.png")),1,1, new int[]{1});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}
				
			case WALL:
				try{
					return new Spritesheet(ImageIO.read(new File("Resources/Images/wall.png")),1,1, new int[]{1});
				} catch (IOException e) {
					System.err.println(e.getStackTrace());
					System.err.println(e.getMessage());
					System.exit(1);
				}	
		}
		return null;
	}
	
	private static Spritesheet[] sprites = new Spritesheet[ENUMSIZE];
	
	public static Spritesheet getSprite(int val){
		try{
			if(sprites[val]==null){
				sprites[val] = getSpritesheet(val);
			}
		} catch(Exception e){}
		
		return sprites[val];
	}
}
