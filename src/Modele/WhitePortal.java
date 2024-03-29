package Modele;
/**
 * @author amina
*/

public class WhitePortal extends WhiteAsteroyd {

	private int relic;

	/* constructeur d'un portail blanc */
	public WhitePortal(int orientation, Position pos , int relic, int priority)throws Exception{
		super(orientation,pos, priority);
		if(relic <=0 || relic>4) throw new Exception("valeur de relic incorrect");
		this.setCanContainSpaceShips(true);
		this.relic = relic;
	}

	public WhitePortal(int orientation, int x, int y, int relic, int priority)throws Exception{
		this(orientation, new Position(x,y), relic, priority);
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
		
	@Override
	public String toString() {
		String str = "portal-white-" + getOrientation() + "-" + getPriority() + "-" + this.relic;
        for(SpaceShip sp : getLSpaceShips())
            str += "/" + sp;
		return str;
	}
}