package levels;


public class CardboardBox extends Prop {

	public CardboardBox() {
		this.setCircleHitbox(false);
		this.setHasHitbox(true);
		this.setHitboxLeft(0.1);
		this.setHitboxTop(0.1);
		this.setHitboxRight(0.9);
		this.setHitboxBottom(0.9);
		
		this.setImageLocation("cardboardbox.png");
		this.setImageW(1);
		this.setImageH(1);
		
	}

}
