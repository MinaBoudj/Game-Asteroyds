package Modele;
import javafx.scene.image.image;

public class VisualObject {
    Image image;
    int orientation;
    Position position;

    /* Constructeurs */
    public VisualObject(String imagePath, int orientation, int x, int y) { // Besoin d'informations supplémentaires sur JavaFX.
    }

    public VisualObject(String imagePath, int orientation, Position position) { // Besoin d'informations supplémentaires sur JavaFX.
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

    /* Méthodes */
    public void updateImage() { //  Besoin d'informations supplémentaires sur JavaFX.
    }
}
