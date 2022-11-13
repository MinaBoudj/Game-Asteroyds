package Modele;
import javax.swing.ImageIcon;
import javax.swing.text.Position;

import javafx.scene.image.image;

public class VisualObject {
    Image image;
    int orientation;
    Position position;

    /* Constructeurs */
    VisualObject(String imagePath, int orientation, int x, int y) { // Besoin d'informations supplémentaires sur JavaFX.
    }

    VisualObject(String imagePath, int orientation, Position position) { // Besoin d'informations supplémentaires sur JavaFX.
    }

    Image getImage(){ // Besoin d'informations supplémentaires sur JavaFX.
    }

    void setOrientation() { // Un setter n'est pas censé avoir un paramètre ??
    }

    Position getPosition() { // Retourne la position.
        return this.position;
    }

    void updateImage() { //  Besoin d'informations supplémentaires sur JavaFX.
    }

    int getOrientation() { // Retourne l'orientation.
        return this.orientation;
    }
}
