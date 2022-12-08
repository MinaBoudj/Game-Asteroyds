package Modele;

/**
* @author Juba
*/

public class Player {
    private String name; // Nom du joueur
    private SpaceShip spaceShip; // Son vaisseau
    private Movement[] movements; // Sa liste de mouvements


    /* Constructeur */
    public Player(String name, Color color, int orientation, Position pos) throws Exception {
        this.name = name;
        this.spaceShip = new SpaceShip(orientation, pos, color);
        this.movements = new Movement[6]; // Chaque joueur peut choisir 6 mouvements par tour.
    }

    public Player(String name, Color color, int orientation, int x, int y) throws Exception {
        this(name, color, orientation, new Position(x,y));
    }


    /* Setter */
    public void setMovement(int indice_tab, Movement movement) {
        this.movements[indice_tab] = movement;
    }


    /* Getter */
    public int getNumberOfRelics() { // Retourne le nombre de reliques que possède le joueur.
        return spaceShip.getNumberOfRelics();
    }

    public SpaceShip getSpaceShip() {
        return spaceShip;
    }
    
    /* Méthode */
    public void move(Cell[][] gameBoard) throws Exception {
        spaceShip.move(gameBoard, movements);
    }

    public boolean hasSpaceShipInCondition() {return spaceShip.getStructurePoints() > 0;}
    
    @Override
    public String toString() {
        String str = "";
        for(int r : spaceShip.getRelics())
            if(r != 0)
                str += r + "/";
        str += "-" + name + "-" + spaceShip.getStructurePoints() + "-" + spaceShip.getColor();
        return str;
    }
}