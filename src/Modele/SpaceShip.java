package Modele;
//import java.util.*;
/***
 * @autor amina
*/


public class SpaceShip extends VisualObject{
	
	private int structurePoints;
	private Color color;
    private int[] relics;
	
	
	/* Constructeur */
	public SpaceShip(String image,int orientation, Position pos, Color color){
        super(orientation, pos);// corriger demain avec juba
		this.structurePoints = 6;
        this.relics = new int[]{0,0,0,0};    
        this.color = color;
        if(color == Color.Red)
            super.setImagePath("rouge");
        else if(color ==Color.Blue)
                super.setImagePath("Bleu");
            else if(color == Color.Yellow)
                super.setImagePath("jaune");
                else if(color == Color.Green)
                    super.setImagePath("gris");
                    else if(color == Color.Purple)
                        super.setImagePath("violet");
                        else if(color == Color.Orange)
                            super.setImagePath("orange");
	}
	
	/* Getteur and setteur */
	public Color getColor() { return this.color; }
	public int getStructurePoints() { return this.structurePoints; }
    public int getNumberOfRelics() {  //nombre de relics que possede le vaisseau
        int nb =0;
        for (int i=0; i<=4; i++){
            if(relics[i] != 0){
                nb++;
            }
        }
        return nb;
    }
	public int[] getRelic(){ return this.relics; }
	public boolean hasRelic(int relic){
		boolean resul = false;
		for(int i=0; i<getNumberOfRelics(); i++){
			if(relic == relics[i])
				resul = true;
		}
		return resul;
	}

	public void setStucturepoints(int sttruct) { this.structurePoints = sttruct; }
	
	private void moveLeft() { 
		if(super.orientation == 1) 
			super.setOrientation(4);
		if(super.orientation == 2) 
			super.setOrientation(6);
		if(super.orientation == 3) 
			super.setOrientation(5);
		if(super.orientation == 4)
			super.setOrientation(2);
		if(super.orientation == 5)
			super.setOrientation(1);
		if(super.orientation == 6)
			super.setOrientation(3);
	}
	
	private void moveRight() {  //recuperer la position à droite de cette case
        if(super.orientation == 1) 
			super.setOrientation(5);
		if(super.orientation == 2) 
			super.setOrientation(4);
		if(super.orientation == 3) 
			super.setOrientation(6);
		if(super.orientation == 4)
			super.setOrientation(1);
		if(super.orientation == 5)
			super.setOrientation(3);
		if(super.orientation == 6)
			super.setOrientation(2);
    }
          
	private void turnAround() { 
        if( super.getOrientation() == 1)
            super.setOrientation(6);
        if( super.getOrientation() == 2)
            super.setOrientation(5);
        if( super.getOrientation() == 3)
            super.setOrientation(4);
        if( super.getOrientation() == 4)
            super.setOrientation(3);
        if( super.getOrientation() == 5)
            super.setOrientation(2);
        if( super.getOrientation() == 6)
            super.setOrientation(1);
    }
	
	//ajouter des relics dans le tableau de relics
	public void addRelic(int relic){
		for(int i=0; i< relics.length; i++){
			if(this.relics[i] != 0 ){
				relics[i] = relic;
			}
		}
	}


	// procedure qui deplace un vaisseau selon les mouvements choisis par le joureur
	public void move(Cell[][] gameBoard, Movement[] movement) {
		for(int i=0; i<movement.length; i++) { //parcourir tous les mouvements 
			Position pos;
			if(movement[i] == Movement.Left) //changer l'orientation
				moveLeft();
			if(movement[i] == Movement.Right)
				moveRight();
			if(movement[i] == Movement.TurnAround)
				turnAround();
			try {
				pos = super.getPosition().getForward(super.orientation);
				//demander si la suite va la ou apres le catch !!!!!!
				//ajouter dans la liste des vaiseaux (a l'exterieur du catch)
				gameBoard[pos.getX()][pos.getY()].addLSpaceShip(this); 
				//retiter de la liste ou il etait
				gameBoard[super.getPosition().x][super.getPosition().y].removeLSpaceShip(this);
				//changer sa position
				this.position = pos;
			} catch (Exception e) {
					//le vaisseau se prend des degats et s'arrete 
					this.structurePoints = this.structurePoints-2;
			}
            if(movement[i] == null)
				break;
		}
    }

}



	
