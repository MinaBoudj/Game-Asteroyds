package view;

/*
 * @autor Maylis
*/

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
    private ImageView delaySpaceShip;
	
    public EndScene(double screenWidth, double screenHeight, Executable exit, Sendable backmenu, ){
		super(new Group());
		Group root = (Group)getRoot();
        
        // Background construction
		try {
            root.getChildren().add(ShapeConstructor.newImage("menu_background", screenWidth*1.5,screenHeight*1.5, screenWidth/2,screenHeight/2, 1));
        } catch (Exception e) {
            setFill(Color.BLACK);
        }
		
		String[] orientationShipChoices = new String[]{"1","2","3","4","5","6"},
				 cellLaunchChoices = new String[]{"n°1", "n°2", "n°3", "n°4", "n°5", "n°6"},
                 colorSpaceshipChoices = new String[]{"Green", "Blue", "Red", "Yellow", "Orange","Purple"};

		Rectangle paneMenu = ShapeConstructor.newRectangle(Color.web("A9A9A9",0.6), screenWidth*0.3, screenHeight, screenWidth*0.85, screenHeight/2);

		Text namefieldLabel = ShapeConstructor.newText("Select your name : ", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.125);
        TextField namefield = new TextField(); 
            namefield.setMaxWidth(screenWidth*0.28);
            namefield.setMaxHeight(screenHeight*0.05);
            namefield.setLayoutX(screenWidth*0.15);
            namefield.setLayoutY(screenWidth*0.175);

		Text colorBoxLabel = ShapeConstructor.newText("Color : ", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.300);
        ComboBox<String> colorBox = ControlConstructor.newComboBox(colorSpaceshipChoices, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.350);
        EventHandler<ActionEvent> delay_color = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                String spaceShipColor = new String();
                switch(colorBox.getValue()){
                    case "Green" :
                        spaceShipColor = "green";
                        break;

                    case "Blue" : 
                        spaceShipColor  = "blue";
                        break;

                    case "Red" :    
                        spaceShipColor = "red";
                        break;

                    case "Yellow" : 
                        spaceShipColor  = "yellow";
                        break;

                    case "Purple" :
                        spaceShipColor = "purple";
                        break;

                    case "Orange" :
                        spaceShipColor  = "orange";
                        break;
                    default : spaceShipColor = "green"; break;
                }
                try {
                    delaySpaceShip = ShapeConstructor.newImage(spaceShipColor +"_space_ship.png", hexSize/2,hexSize/2, screenHeight*0.5,screenWidth*0.5, 1);
               } catch (Exception e){ }
            }
        }; 
        colorBox.setOnAction(delay_color);

		Text cellLaunchBoxLabel = ShapeConstructor.newText("Cell of LaunchPad : ", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.450);
        ComboBox<String> cellLaunchBox = ControlConstructor.newComboBox(cellLaunchChoices, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.500);
        EventHandler<ActionEvent> delay_position = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                Integer spaceShipLaunch;
                switch(cellLaunchBox.getValue()){
                    case "n°1" :
                        spaceShipLaunch = 1;
                        break;

                    case "n°2" : 
                        spaceShipLaunch  = 2;
                        break;

                    case "n°3" :    
                        spaceShipLaunch = 3;
                        break;

                    case "n°4" : 
                        spaceShipLaunch  = 4;
                        break;

                    case "n°5" :
                        spaceShipLaunch = 5;
                        break;

                    case "n°6" :
                        spaceShipLaunch  = 6;
                        break;
                    default : 
                        spaceShipLaunch  = 1;
                        break;
                }
                try{
                    delaySpaceShip = ShapeConstructor.newImage("green_space_ship.png", hexSize/2,hexSize/2, cellLaunchPositions[spaceShipLaunch],cellLaunchPositions[spaceShipLaunch+1], 1);
                } catch (Exception e) { };
            }
        };
        cellLaunchBox.setOnAction(delay_position);

        Text orientatoBoxLabel = ShapeConstructor.newText("Orientation : ", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.650);
        ComboBox<String> orientationBox = ControlConstructor.newComboBox(orientationShipChoices, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.700);
        EventHandler<ActionEvent> delay_orientation = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                Integer orientationSpaceShip;
                switch(orientationBox.getValue()){
                    case "1" :
                        orientationSpaceShip = 1;
                        break;

                    case "2" : 
                        orientationSpaceShip = 2;
                        break;

                    case "3" :    
                        orientationSpaceShip = 3;
                        break;

                    case "4" : 
                        orientationSpaceShip = 4;
                        break;

                    case "5" :
                        orientationSpaceShip = 5;
                        break;

                    case "6" :
                        orientationSpaceShip = 6;
                        break;
                    default : 
                        orientationSpaceShip  = 1;
                        break;
                }
                try {
                delaySpaceShip = ShapeConstructor.newImage("green_space_ship.png", hexSize/2,hexSize/2, cellLaunchPositions[1],cellLaunchPositions[1+1], orientationSpaceShip);
                } catch(Exception e) {}
            }
        };
        cellLaunchBox.setOnAction(delay_orientation);


		Executable next = (me) -> {playerinfo.send(new String[]{namefield.getText(), colorBox.getValue(), cellLaunchBox.getValue(), orientationBox.getValue()});};
		Text nextButton = ControlConstructor.newButton("Start", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.800, Color.BLACK, next);
		Text exitButton = ControlConstructor.newButton("Exit Game", Color.WHITE, screenWidth*0.28,screenHeight*0.05, screenWidth*0.15,screenHeight*0.875, Color.BLACK, exit);

		root.getChildren().addAll(paneMenu, namefieldLabel, namefield, colorBoxLabel, colorBox, cellLaunchBoxLabel, cellLaunchBox, orientatoBoxLabel, orientationBox, nextButton, exitButton);
	}
}

