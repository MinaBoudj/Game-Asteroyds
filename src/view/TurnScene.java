package view;

/**
 * @author Matéo
 */

import javafx.scene.Scene;
import javafx.scene.Group;

public class TurnScene extends Scene {
    private double screenWidth,
                   screenHeight;

    public TurnScene(double screenWidth, double screenHeight) {
        super(new Group());
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }
}