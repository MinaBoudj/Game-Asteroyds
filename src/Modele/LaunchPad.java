package Modele;

/**
* @author Maylis
*/

public class LaunchPad extends Cell {

    // Constructeurs
    public LaunchPad(int x, int y)throws Exception{
        super(1, x, y , true);              
    }

    public LaunchPad(Position pos)throws Exception{
        super(1, pos, true);
    }

    @Override
    public String toString() {
        return "launchpad";
    }
}  

