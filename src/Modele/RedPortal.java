package Modele;
/*
 * @auto amina
*/
public class RedPortal extends RedAsteroyd{

	private int relic;

	/* construction d'un portail rouge */
	public RedPortal(int orientation, Position pos, int relic, int priority)throws Exception{
		super(orientation, pos, priority);
		if(relic <=0 || relic>4) throw new Exception("relic incorrect");
		this.setCanContainSpaceShips(true);
		this.relic = relic;
	}

	public RedPortal(int orientation, int x, int y, int relic, int priority)throws Exception{
		this(orientation, new Position(x,y), relic, priority);
	}
	
	/* getteur et setteur */
	public int getRelic(){ return this.relic; }

	@Override
	//redefinition de la methode ajout de spaceShip
	public void addLSpaceShip(SpaceShip newSpace)throws Exception{
		super.getLSpaceShips().add(newSpace);
		if(this.relic <1 || this.relic>4) throw new Exception("valeur de relic à ajouter < 1 ou > 4");
		else if(!newSpace.hasRelic(this.relic)) //s'il n'a pas cette relic l'ajouter à sa liste de relic
				newSpace.addRelic(this.relic);
	}

	@Override
	public void move(Cell[][] gameBoard, int[] directions) throws Exception {
		super.move(gameBoard, directions);
		for(SpaceShip sp : getLSpaceShips())
			sp.setPosition(getPosition());
	}
	
	@Override
	public String toString() {
		String str = "portal-red-" + getOrientation() + "-" + getPriority() + "-" + this.relic;
        for(SpaceShip sp : getLSpaceShips())
            str += "/" + sp;
		return str;
	}
}