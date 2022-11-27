package Modele;

/**
* @author Maylis
*/

public class LaunchPad extends Cell {

    // Constructeurs
    public LaunchPad(String imagePath, int orientation, int x, int y)throws Exception{
        super(imagePath, orientation, x, y , true);              
    }

    public LaunchPad(String imagePath, int orientation, Position pos)throws Exception{
        super(imagePath, orientation, pos, true);
    }
}  

