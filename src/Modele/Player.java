package Modele;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<int> relics;
    private SpaceShip spaceShip;

    Player(String name, Color color) { // Non terminé
        this.name = name;
        this.relics = new ArrayList<int>();
    }

    public void setMovement(int indice, Movement movement) {
    }

    public int getNumberOfRelics() {
        return this.relics.size();
    }

    public boolean hasRelic(int relic) {
        for (int i = 0; i<this.relics.size(); i++) {
            if (this.relics[i] == relic) {
                return true;
            }
        }
        return false;
    }
}