package Modele;

/**
* @author Maylis
*/

public class LaunchPod extends Cell {

    // Constructeurs
    public LaunchPod(String imagePath, int orientation, int x, int y){
        super(imagePath, orientation, pos, true);              
    }

    public LaunchPod(String imagePath, int orientation, Position position){
        super(imagePath, orientation, pos, true);
    }
}  

