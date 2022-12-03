package view;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color; 
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.VBox;
import javafx.collections.*;


public class Control_creator {
    public static Button Buttom(String text, double X, double Y, EventHandler<ActionEvent> event) {
        Button btn = new Button(text);  
        btn.setLayoutX(X);
        btn.setLayoutY(Y); 
        btn.setOnAction(event);
        return btn;
    }

    public static ComboBox<String> ComboBox(String list[], String text, double X, double Y, EventHandler<ActionEvent> event) {
        ComboBox<String> cb = new ComboBox<String>(FXCollections.observableArrayList(list));        
        cb.setLayoutX(X);
        cb.setLayoutY(Y); 
        cb.setOnAction(event);
        return cb;
    }

    public static VBox VBox(double padding, double spacing, double PrefWidth, double PrefHeight) {
        VBox vb = new VBox();

        vb.setPadding(new Insets(padding));
        vb.setSpacing(spacing);
        vb.setPrefWidth(PrefWidth);
        vb.setPrefHeight(PrefHeight);
            
        return vb;
    }    

    public static StackPane BG(Stage stage, String URL) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        StackPane back = new StackPane();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());

        back.setStyle("-fx-background-image: url(" + URL +"); " + "-fx-background-size: cover;");
        return back;
    }

    public static Background Background(String color) {
        BackgroundFill background_fill = new BackgroundFill(Color.web("#"+ color), CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(background_fill);
        return background;
    }
}
