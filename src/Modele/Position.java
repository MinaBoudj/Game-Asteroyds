package Modele;

@author Juba

public class Position {
    int x; // Abscisse
    int y; // Ordonnée

    
    public Position(int x, int y) {  /* Constructeur */
        this.x = x; this.y = y;
    }

    public Position getTopRight() { // Retourne la position de la case en haut à droite
        return new Position(x+1, y-1);
    }

    public Position getTopLeft() { // Retourne la position de la case en haut à gauche
        return new Position(x, y-1);
    }

    public Position getBottomRight() { // Retourne la posiiton de la case en bas à droite
        return new Position(x+1, y+1);
    }

    public Position getBottomLeft() { // Retourne la position de la case en bas à gauche
        return new Position(x, y+1);
    }

    public Position getLeft() { // Retourne la position de la case à gauche
        return new Position(x-1, y);
    }

    public Position getRight() { // Retourne la position de la case à droite
        return new Position(x+1, y);
    }

    public int getX(){ // Getter
        return this.x;
    }

    public int getY(){ // Getter
        return this.y;
    }


    public Position getForward(int orientation) { // Renvoie la position de la prochaine case selon l'orientation, ou la même position si il n'y a rien devant.
        switch (orientation) {
            case 1:
                if ((this.x == 0 && this.y%2 == 0) || this.y == 0) {
                    return this;
                } else {
                    return this.getTopLeft();
                }
            break;

            case 5:
                if ((this.x == 16 && this.y%2 != 0) || this.y == 0) {
                    return this;
                } else {
                    return this.getTopRight();
                }
            break;

            case 3:
                if (this.x == 16) {
                    return this;
                } else {
                    return this.getRight();
                }
                break;

            case 6: 
                if ((this.x == 16 && this.y%2 != 0) || this.y == 13) {
                    return this;
                } else {
                    return this.getBottomRight();
                }
                break;

            case 2:
                if ((this.x == 0 && this.y%2 == 0) || this.y == 13) {
                    return this;
                } else {
                    return this.getBottomLeft();
                }
                break;

            case 4: 
                if (this.x == 0) {
                    return this;
                }
                else {
                    return this.getLeft();
                }
        }
    }
}



}
