package Modele;
import javafx.scene.image.image;

public class VisualObject {
    String imagePath;
    int orientation;
    Position position;

    /* Constructeurs */
    public VisualObject(int orientation, int x, int y) {
        this.imagePath = " ";
        this.orientation = orientation;
        this.position.x = x;
        this.position.y = y; 
    }

    public VisualObject(String imagePath, int orientation, Position position) {
        this.imagePath = imagePath;
        this.orientation = orientation;
        this.position = position;
    }


    /* Getters */
    public Image getImage(){ // Besoin d'informations supplémentaires sur JavaFX.
    }

    public Position getPosition() { // Retourne la position.
        return this.position;
    }

    public int getOrientation() { // Retourne l'orientation.
        return this.orientation;
    }

    /* Setter */
    public void setOrientation() { // Un setter n'est pas censé avoir un paramètre ??
    }

    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }

    /* Méthodes */
    public void updateImage() { //  Besoin d'informations supplémentaires sur JavaFX.
    }
}
