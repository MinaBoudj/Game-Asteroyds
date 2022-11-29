package Modele;
// import javafx.scene.image.image;

/**
* @author Juba
*/

public abstract class VisualObject {
    private int orientation;
    private Position position;


    /* Constructeurs */
    public VisualObject(int orientation, Position position) throws Exception{
        if(orientation<=0 || orientation>6 || position.getX()<0 || position.getY()<0) throw new Exception("Erreur dans la valeur de l'orientation");
        else{
            this.orientation = orientation;
            this.position = position;
        }
    }

    public VisualObject(int orientation, int x, int y) throws Exception{
        this(orientation, new Position(x, y));
    }


    /* Getters */
    public Position getPosition() {
        return this.position;
    }

    public int getOrientation() {
        return this.orientation;
    }


    /* Setters */
    public void setOrientation(int orientation) throws Exception{
        if(orientation<=0||orientation>6) throw new Exception("Erreur dans la valeur de l'orientation");
        else
            this.orientation = orientation;
    }

    public void setPosition(Position pos) {
        this.position = pos;
    }

    
    
}
