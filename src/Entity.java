import java.awt.*;
import java.awt.geom.Point2D;

public interface Entity{
	//behaviour list
	public boolean disposable();
	public void update(double delta, Game game);
	public void draw(Graphics2D g, Viewport viewport);
}
