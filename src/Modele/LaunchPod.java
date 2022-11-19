package Modele;

/**
* @author Maylis
*/

public class LaunchPod extends Cell {

    // Constructeurs
    public LaunchPod(String imagePath, int orientation, int x, int y){
        super(imagePath, orientation, pos, true);               // a faire mettre direct le nom des images concernés
    }

    public LaunchPod(String imagePath, int orientation, Position position){
        super(imagePath, orientation, pos, true);
    }
}  

