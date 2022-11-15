package Modele;

public abstract class Asteroyd extends Cell {
    
    public Asteroyd(String image, int orientation, Position pos){
        super(image,orientation,pos, false);
    }
    public abstract void move(Cell[][] gameBoard, int[] directions); // Besoin de plus d'informations
}
