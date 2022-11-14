package Modele;

public class Position {
    int x; // Abscisse
    int y; // Ordonnée

    
    public Position(int x, int y) {  /* Constructeur */
        this.x = x; this.y = y;
    }

    public Position getBottom(){ // Retourne la position de la case en bas (mais hexagonale donc impossible) ?
    }

    public Position getTop(){ // Retourne la position de la case en haut (mais hexagonale donc impossible) ? 
    }

    public Position getTopRight() { // Retourne la position de la case en haut à droite
        return new Position(x+1, y+1);
    }

    public Position getTopLeft() { // Retourne la position de la case en haut à gauche
        return new Position(x-1, y+1);
    }

    public Position getBottomRight() { // Retourne la posiiton de la case en bas à droite
        return new Position(x+1, y-1);
    }

    public Position getBottomLeft() { // Retourne la position de la case en bas à gauche
        return new Position(x-1, y-1);
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

    public Position getForward(int orientation) {
        if ()
    }



}
