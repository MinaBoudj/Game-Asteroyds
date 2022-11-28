package Modele;
/**
 * @author amina
*/

public class WhitePortal extends WhiteAsteroyd {

	private int relic;

	public WhitePortal(int orientation, Position pos , int relic)throws Exception{
		super(orientation,pos, true);
		this.relic = relic;
	}

	/* getteur et setteur */
	public int getRelic(){ return this.relic; }

	@Override
	public void addLSpaceShip(SpaceShip newSpace) throws Exception{
		super.getLSpaceShips().add(newSpace);
		if(this.relic <1 || this.relic>4) throw new Exception("valeur de relic de portail blanc < 1 ou > 4");
		else if(!newSpace.hasRelic(this.relic)) //s'il n'a pas cette relic l'ajouter à sa liste de relic
				newSpace.addRelic(this.relic);
	}
		
}