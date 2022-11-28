package Modele;

/**
* @author Maylis
*/

public class AudiencePod extends Cell {

    // Constructeurs
    public AudiencePod(int x, int y)throws Exception{
        super(1, x, y, false);               
    }

    public AudiencePod(Position pos)throws Exception{
        super(1, pos, false);
    }
}