package view;

/*
 * @autor Maylis
*/

import java.awt.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class List_color {

    public static ObservableList<Color> getColorList() {
        // Création des élémenst de la liste
        Color Green = Color.Green;
        Color Blue = Color.Blue;
        Color Red = Color.Red;
        Color Purple = Color.Purple;
        Color Orange = Color.Orange;
        Color Yellow = Color.Yellow;

        // Complétion de la liste
        ObservableList<Color> list = FXCollections.observableArrayList(Green, Blue, Red, Purple, Orange, Yellow);
        return list;
    }

    public void delete(Color c){
        this.remove(c);
    }
   
}
