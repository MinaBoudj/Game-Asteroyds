package Modele;

import java.util.ArrayList;

public class Player {
    public String name;
    private ArrayList<Integer> relics;

    /* Constructeur */
    Player(String name) { // Non terminé
        this.name = name;
        this.relics = new ArrayList<Integer>();
    }

    public void setMovement(int indice, Movement movement) {
    }

    public int getNumberOfRelics() { // Retourne le nombre de reliques que possède le joueur.
        return this.relics.size();
    }

    public boolean hasRelic(int relic) { // Vérifie si le joueur possède une relique passée en paramètre.
        for (int i : relics) {
            if (this.relics.get(i).equals(relic)) {
                return true;
            }
        }
        return false;
    }
}