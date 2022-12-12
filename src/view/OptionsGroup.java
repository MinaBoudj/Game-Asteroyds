package view;

/**
 * @author Maylis
 */

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

// /!\ Dimensions à vérifier /!\
public class OptionsGroup extends Group {
    private double screenHeight;
    private double screenWidth;
	
    public OptionsGroup(double screenWidth, double screenHeight){
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void updateOptionsGroup(Group gameBoardGroup, ArrayList<String> colorChoices, int playerIndex, Executable updateGameBoard, Executable mainMenu, Sendable playerInfo, String[] cellLaunchPositions, String[][] gameBoard) {
        getChildren().removeAll(getChildren());

        double maxTextHeight = screenHeight/13.5;
		String[] orientationShipChoices = new String[]{"1","2","3","4","5","6"},
				 cellLaunchChoices = new String[cellLaunchPositions.length/2];
        for(int i = 0 ; i < cellLaunchChoices.length ; i++)
            cellLaunchChoices[i] = "n°" + (i + 1);

        Sendable updateSpaceShip = args -> {
                gameBoard[Integer.parseInt(args[0])][Integer.parseInt(args[1])] += args[2];
                updateGameBoard.execute(null);
                gameBoard[Integer.parseInt(args[0])][Integer.parseInt(args[1])] = "launchpad";
            };

		Text namefieldLabel = ShapeConstructor.newText("Select your name : ", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight);
        TextField nameField = new TextField(); 
        nameField.setMinWidth(screenWidth*0.09);
        nameField.setMaxHeight(maxTextHeight);
        nameField.setLayoutX(screenWidth*0.855);
        nameField.setLayoutY(maxTextHeight*1.5);
        nameField.setText("Player" + (playerIndex + 1));
        nameField.setAlignment(Pos.CENTER);
        nameField.selectEnd();
        nameField.setOnKeyReleased(ke -> {
                while(nameField.getText().indexOf("-") != -1)
                    nameField.deleteText(nameField.getText().indexOf("-"), nameField.getText().indexOf("-")+1);

                if(nameField.getText() == "") {
                    nameField.setText("Player" + (playerIndex + 1));
                    nameField.selectEnd();
                } else
                    if(nameField.getText().length() > 10)
                        nameField.setText(nameField.getText().substring(0, 10));
            });

		Text colorBoxLabel = ShapeConstructor.newText("Color : ", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*3.5);
        ComboBox<String> colorBox = ControlConstructor.newComboBox(colorChoices.toArray(new String[]{}), screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*4.5);

		Text cellLaunchBoxLabel = ShapeConstructor.newText("Cell of LaunchPad : ", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*6);
        ComboBox<String> cellLaunchBox = ControlConstructor.newComboBox(cellLaunchChoices, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*7);

        Text orientationBoxLabel = ShapeConstructor.newText("Orientation : ", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*8.5);
        ComboBox<String> orientationBox = ControlConstructor.newComboBox(orientationShipChoices, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*9.5);

        Executable onAction = ev -> {
                String spaceShip = "/space_ship-" + colorBox.getValue() + "-" + orientationBox.getValue(),
                       launchpad = cellLaunchBox.getValue().substring(2,3),
                       l = cellLaunchPositions[(Integer.parseInt(launchpad) -1) * 2],
                       c = cellLaunchPositions[(Integer.parseInt(launchpad) -1) * 2 +1];

                updateSpaceShip.send(new String[]{l,c, spaceShip});
            };
        onAction.execute(null);

        colorBox.setOnAction(ae -> {onAction.execute(ae);});
        cellLaunchBox.setOnAction(ae -> {onAction.execute(ae);});
        orientationBox.setOnAction(ae -> {onAction.execute(ae);});

		Executable next = ev -> {playerInfo.send(new String[]{nameField.getText(), colorBox.getValue(), cellLaunchBox.getValue().substring(2,3), orientationBox.getValue()});};
		Text nextButton = ControlConstructor.newButton("Next", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*11.5, Color.BLACK, next), // next
             mainMenuButton = ControlConstructor.newButton("Main Menu", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*12.5, Color.BLACK, mainMenu); // mainMenu

		getChildren().addAll(gameBoardGroup, namefieldLabel, nameField, colorBoxLabel, colorBox, cellLaunchBoxLabel, cellLaunchBox, orientationBoxLabel, orientationBox, nextButton, mainMenuButton);
	}
}