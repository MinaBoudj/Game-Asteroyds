package view;

import javafx.scene.Scene;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class EndScene extends Scene {
    private String ranking[];
	
    public EndScene(double screenWidth, double screenHeight, Executable exit, Executable mainMenu, String[] players){
		super(new Group());
		Group root = (Group)getRoot();
    
        // Background construction
		try {
            root.getChildren().add(ShapeConstructor.newImage("menu_background", screenWidth*1.5,screenHeight*1.5, screenWidth/2,screenHeight/2, 1));
        } catch (Exception e) {
            setFill(Color.BLACK);
        }
		
        Text resultslabel = ShapeConstructor.newText("Results : ", Color.WHITE, screenWidth*0.28,screenHeight*0.1, screenWidth*0.5,screenHeight*0.5);

		Rectangle paneMenu = ShapeConstructor.newRectangle(Color.TRANSPARENT, screenWidth, screenHeight*0.7, screenWidth/2, screenHeight*0.8);
		Rectangle paneRes = ShapeConstructor.newRectangle(Color.web("A9A9A9",0.6), screenWidth, screenHeight*0.3, screenWidth/2, screenHeight*0.2);

		Text backmainButton = ControlConstructor.newButton("Start", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.800, Color.BLACK, mainMenu);
		Text exitButton = ControlConstructor.newButton("Exit Game", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.875, Color.BLACK, exit)

		root.getChildren().addAll(paneMenu, paneRes, namefield, colorBoxLabel, colorBox, cellLaunchBoxLabel, cellLaunchBox, orientatoBoxLabel, orientationBox, nextButton, exitButton);
	}

    public void ranking(String player[]){
        for (int i = 0; i < player.length; i++){

        }
    }
}