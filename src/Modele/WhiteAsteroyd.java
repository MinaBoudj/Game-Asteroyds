package Modele;

public class WhiteAsteroyd extends Asteroyd {
    
    /* Constructeurs */
    public WhiteAsteroyd(int x, int y){
        super(imagePath, x, y);
    }

    public WhiteAsteroyd(Position position){
        super(imagePath, position);
    }

    // WhiteAsterod hérite de move d'Asteroyd.
}
