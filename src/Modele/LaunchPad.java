package Modele;

/**
* @author Maylis
*/

public class LaunchPad extends Cell {

    // Constructeurs
    public LaunchPod(String imagePath, int orientation, int x, int y){
        super(imagePath, orientation, x, y , true);              
    }

    public LaunchPod(String imagePath, int orientation, Position pos){
        super(imagePath, orientation, pos, true);
    }
}  

