package Modele;

public class BlueAsteroyd extends Asteroyd{

     /* Constructeurs */
     public BlueAsteroyd(int x, int y){
        super(imagePath, x, y);
    }

    public BlueAsteroyd(Position position){
        super(imagePath, position);
    }

    // BlueAsterod hérite de move d'Asteroyd.
}
