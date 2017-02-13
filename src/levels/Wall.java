/**
 * 
 */
package levels;

/**
 * @author Luke
 *
 */
public class Wall extends Prop {

	/**
	 * 
	 */
	public Wall() {
		this.setCircleHitbox(false);
		this.setHasHitbox(true);
		this.setHitboxLeft(0.0);
		this.setHitboxTop(0.0);
		this.setHitboxRight(1.0);
		this.setHitboxBottom(1.0);
		
		this.setImageLocation("wall.png");
		this.setImageW(1);
		this.setImageH(1);
	}

}
