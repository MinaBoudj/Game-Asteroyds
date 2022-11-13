package Modele;

import java.util.ArrayList;

public class Player {
    public String name; // Nom du joueur
    private ArrayList<Integer> relics; // Ensemble de reliques qu'il possède
    SpaceShip spaceShip; // Son vaisseau
    ArrayList<Movement> movements; // Sa liste de mouvements

    /* Constructeur */
    Player(String name) { // Non terminé ! A définir en groupe
        this.name = name;
        this.relics = new ArrayList<Integer>();
    }

    /* Setter */
    public void setMovement(int indice, Movement movement) {
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