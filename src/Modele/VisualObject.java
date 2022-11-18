package Modele;
// import javafx.scene.image.image;

/**
* @author Juba
*/

public class VisualObject {
    String imagePath;
    int orientation;
    Position position;

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
    public String getImage(){ // A supprimer (remplacer par redef toString)
        return this.imagePath; 
    }

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
}
