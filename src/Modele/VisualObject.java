package Modele;
// import javafx.scene.image.image;

/**
* @author Juba
*/

public abstract class VisualObject {
    private String imagePath;
    private int orientation;
    private Position position;


    /* Constructeurs */
    public VisualObject(int orientation, int x, int y) throws Exception{
        if(orientation<0 || orientation>6 || x<0 || y<0) throw new Exception("Erreur dans la valeur de l'orientation");
        else{
            this.imagePath = " ";
            this.orientation = orientation;
            this.position = new Position(x, y);
        }
    }

    public VisualObject(int orientation, Position position) throws Exception{
        if(orientation<0||orientation>6 || position==null) throw new Exception("Erreur dans la valeur de l'orientation");
        else{
            this.imagePath = " ";
            this.orientation = orientation;
            this.position = position;
        }
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
        if(orientation<0||orientation>6) throw new Exception("Erreur dans la valeur de l'orientation");
        else
            this.orientation = orientation;
    }

    public void setPosition(Position pos) {
        this.position = pos;
    }

    
    /* Méthode */
    @Override
    public String toString(){
        return this.imagePath; 
    }
}
