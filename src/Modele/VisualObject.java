package Modele;
// import javafx.scene.image.image;

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
    public String getImage(){ // Besoin d'informations supplémentaires sur JavaFX.
        return this.imagePath;
    }

    public Position getPosition() { // Retourne la position.
        return this.position;
    }

    public int getOrientation() { // Retourne l'orientation.
        return this.orientation;
    }

    /* Setter */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }


    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    /* Méthodes */
    public void updateImage() { //  Besoin d'informations supplémentaires sur JavaFX.
    }
}
