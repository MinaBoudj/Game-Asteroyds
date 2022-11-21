package Modele;

/**
* @author Juba
*/

public class Position {
    int x; // Abscisse
    int y; // Ordonnée

    
    public Position(int x, int y) {  /* Constructeur */
        this.x = x; this.y = y;
    }

    private Position getTopRight() { // Retourne la position de la case en haut à droite
        return new Position(x+1, y-1);
    }

    private Position getTopLeft() { // Retourne la position de la case en haut à gauche
        return new Position(x, y-1);
    }

    private Position getBottomRight() { // Retourne la posiiton de la case en bas à droite
        return new Position(x+1, y+1);
    }

    private Position getBottomLeft() { // Retourne la position de la case en bas à gauche
        return new Position(x, y+1);
    }

    private Position getLeft() { // Retourne la position de la case à gauche
        return new Position(x-1, y);
    }

    private Position getRight() { // Retourne la position de la case à droite
        return new Position(x+1, y);
    }

    public int getX(){ // Getter
        return this.x;
    }

    public int getY(){ // Getter
        return this.y;
    }

    public void setX(int x){ // Setter
        this.x = x;
    }

    public void setY(int y){ // Setter
        this.y = y;
    }


    public Position getForward(int orientation) throws Exception {  // Renvoie la position de la prochaine case selon l'orientation, ou la même position si il n'y a rien devant.
        switch (orientation) {
            case 6:
                if ((this.x == 0 && this.y%2 == 0) || this.y == 0) {
                    throw new Exception("GameBoard Edge");
                } else {
                    return this.getTopLeft();
                }

            case 1:
                if ((this.x == 16 && this.y%2 != 0) || this.y == 0) {
                    throw new Exception("GameBoard Edge");
                } else {
                    return this.getTopRight();
                }
                

            case 2:
                if (this.x == 16) {
                    throw new Exception("GameBoard Edge");
                } else {
                    return this.getRight();
                }
                

            case 3: 
                if ((this.x == 16 && this.y%2 != 0) || this.y == 13) {
                    throw new Exception("GameBoard Edge");
                } else {
                    return this.getBottomRight();
                }
                

            case 4:
                if ((this.x == 0 && this.y%2 == 0) || this.y == 13) {
                    throw new Exception("GameBoard Edge");
                } else {
                    return this.getBottomLeft();
                }
                

            case 5: 
                if (this.x == 0) {
                    throw new Exception("GameBoard Edge");
                }
                else {
                    return this.getLeft();
                }
                

            default:
                return this;
        }
    }
}
