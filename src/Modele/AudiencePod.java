package Modele;

/**
* @author Maylis
*/

public class AudiencePod extends Cell {

    // Constructeurs
    public AudiencePod(String imagePath, int orientation, int x, int y){
        super(imagePath, orientation, x, y, false);               
    }

    public AudiencePod(String imagePath, int orientation, Position pos){
        super(imagePath, orientation, pos, false);
    }
}