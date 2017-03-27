package game;
import javax.swing.JFrame;
import audio.BGM;

//import audio.BGM;


public class Main extends Thread {
	public GamePanel gamePane;
	public BGM bgm = new BGM(50,"/Music/BGM_Asian_Gravedrum.wav");
	
	public Main(){
		super();
		
	}
	
	@Override
	public void run()
	{
		this.gamePane = new GamePanel();
		
		JFrame window = new JFrame("Test");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(1,1);
        window.add(this.gamePane);
        
        window.addKeyListener(this.gamePane.keyboard);
		window.addMouseListener(this.gamePane.mouse);
		window.addMouseMotionListener(this.gamePane.mouse);
        
        window.setVisible(true);
        
		
	//	bgm.play();
        //	bgm.update();
		
	   long lastLoopTime = System.nanoTime();
	   final int TARGET_FPS = 60;
	   final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	   
	   long lastFpsTime = 0;
	   int fps = 0; 

	   // keep looping round til the game ends
	   while (gamePane.game.isRunning)
	   {
		  // work out how long its been since the last update, this
		  // will be used to calculate how far the entities should
		  // move this loop
		  long now = System.nanoTime();
		  long updateLength = now - lastLoopTime;
		  lastLoopTime = now;

		  // update the frame counter
		  lastFpsTime += updateLength;
		  fps++;
		  
		  // update our FPS counter if a second has passed since
		  // we last recorded
		  if (lastFpsTime >= 1000000000)
		  {
		     System.out.println("(FPS: "+fps+")");
		     lastFpsTime = 0;
		     fps = 0;
		  }
		  
		  // update the game logic
		  gamePane.game.update(updateLength / 1000000000.0);
		  
		  // draw everything
		  gamePane.paintImmediately(gamePane.getBounds());
		  
		  //this is in ms, whereas our lastLoopTime etc. vars are in ns.
		  try{Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );}
		  catch(Exception e){System.out.println(e.getMessage());}
	   }
	   window.dispose();
	}
	
	public static void main(String args[]){
		Main m = new Main();
		m.gamePane = new GamePanel();

		JFrame window = new JFrame("Test");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(640,420);
        window.add(m.gamePane);
        
        window.addKeyListener(m.gamePane.keyboard);
		window.addMouseListener(m.gamePane.mouse);
		
		window.setVisible(true);
//		JFrame window = new JFrame("Test");
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setSize(640,420);
//        window.add(m.gamePane);
//        
//        window.addKeyListener(m.gamePane.keyboard);
//		window.addMouseListener(m.gamePane.mouse);
//		window.addMouseMotionListener(m.gamePane.mouse);
//        
//        window.setVisible(true);
//		m.start();

//		
//
//        
//        window.setVisible(true);
//

	}
}
