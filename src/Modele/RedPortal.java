package Modele;
/*
 * @auto amina
*/
public class RedPortal extends RedAsteroyd{

	private int relic;

	RedPortal(String image, int orientation, Position pos, int relic){
		super(image,orientation,pos, true);
		this.relic = relic;
	}
	
	/* getteur et setteur */
	public int getRelic(){ return this.relic; }
	
}


