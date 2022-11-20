package Modele;

/**
* @author Juba
*/

import java.util.ArrayList;

public class Player {
    public String name; // Nom du joueur
    private ArrayList<Integer> relics; // Ensemble de reliques qu'il possède
    private SpaceShip spaceShip; // Son vaisseau VOIR LE CONSTRUCTEUR DE SPACESHIP
    public Movement[] movements; // Sa liste de mouvements

    /* Constructeur */
    Player(String name, Color color, String image, int orientation, Position pos) {
        this.name = name;
        this.relics = new ArrayList<Integer>();
        this.spaceShip = new SpaceShip(image, orientation, pos, color);
        this.movements = new Movement[6];
    }

    /* Setter */
    public void setMovement(int indice_tab, Movement movement) {
        this.movements[indice_tab] = movement;
    }

    /* Getter */
    public int getNumberOfRelics() { // Retourne le nombre de reliques que possède le joueur.
        return this.relics.size();
    }

    /* Méthode */
    public boolean hasRelic(int relic) { // Vérifie si le joueur possède une relique passée en paramètre.
        for (int i : relics) {
            if (this.relics.get(i).equals(relic)) {
                return true;
            }
        }
        return false;
    }
}