package Modele;

public class WhitePortal extends WhiteAsteroyd {

	private int relic;

	WhitePortal(String image, int orientation, Position pos , int relic){
		super(image,orientation,pos, true);
		this.relic = relic;
	}

	/* getteur et setteur */
	public int getRelic(){ return this.relic; }
	
		
}