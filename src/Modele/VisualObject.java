package Modele;
// import javafx.scene.image.image;

/**
* @author Juba
*/

public abstract class VisualObject {
    private String imagePath;
    private int orientation;
    private Position position;

    /* Constructeurs */
    public VisualObject(int orientation, int x, int y) {
        this.imagePath = " ";
        this.orientation = orientation;
        this.position = new Position(x, y);
    }

    public VisualObject(int orientation, Position position) {
        this.imagePath = " ";
        this.orientation = orientation;
        this.position = position;
    }


    /* Getters */
    public Position getPosition() {
        return this.position;
    }

    public int getOrientation() {
        return this.orientation;
    }

    /* Setters */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setPosition(Position pos) {
        this.position = pos;
    }

    /* Méthode */
    @Override
    public String toString(){
        return this.imagePath; 
    }
}
