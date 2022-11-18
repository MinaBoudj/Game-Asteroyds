package Modele;
/*
 * @auto amina
*/
public class RedPortal extends RedAsteroyd{

	private int relic;

	public RedPortal(String image, int orientation, Position pos, int relic){
		super(image,orientation,pos, true);
		this.relic = relic;
	}
	
	/* getteur et setteur */
	public int getRelic(){ return this.relic; }

	@Override
	public void addLSpaceShip(SpaceShip newSpace){
		super.getLSpaceShips().add(newSpace);
		if(!newSpace.hasRelic(this.relic)) //s'il n'a pas cette relic l'ajouter à sa liste de relic
			newSpace.addRelic(this.relic);
	}
	
}



