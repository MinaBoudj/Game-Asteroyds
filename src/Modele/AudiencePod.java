package Modele;

/**
* @author Maylis
*/

public class AudiencePod extends Cell {

    // Constructeurs
    public AudiencePod(String imagePath, int orientation, int x, int y)throws Exception{
        super(imagePath, orientation, x, y, false);               
    }

    public AudiencePod(String imagePath, int orientation, Position pos)throws Exception{
        super(imagePath, orientation, pos, false);
    }
}