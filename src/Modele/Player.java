package Modele;

@author Juba

import java.util.ArrayList;

public class Player {
    public String name; // Nom du joueur
    private ArrayList<Integer> relics; // Ensemble de reliques qu'il possède
    private SpaceShip spaceShip; // Son vaisseau
    public ArrayList<Movement> movements; // Sa liste de mouvements

    /* Constructeur */
    Player(String name, SpaceShip spaceShip) { // A VERIFIER AVEC LE GROUPE
        this.name = name;
        this.relics = new ArrayList<Integer>();
        this.spaceShip = spaceShip;
        this.movements = new ArrayList<Movement>(); // Est-ce une Arraylist ou un tableau (list)
    }

    /* Setter */
    public void setMovement(int indice, Movement movement) { // A VERIFIER AVEC LE GROUPE : A-t-on vraiment besoinde l'indice ?
        this.movements.add(movement);
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