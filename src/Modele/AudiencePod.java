package Modele;

/**
* @author Maylis
*/

public class AudiencePod extends Cell {

    // Constructeurs
    public AudiencePod(String imagePath, int orientation, int x, int y){
        super(imagePath, orientation, pos, false);               
    }

    public AudiencePod(String imagePath, int orientation, Position position){
        super(imagePath, orientation, pos, false);
    }
}