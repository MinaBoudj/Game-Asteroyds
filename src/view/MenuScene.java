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



public class MenuScene extends Scene {
	public MenuScene(double screenWidth, double screenHeight, Group root, Executable exit, Sendable gameInfos){ 
		super(root);

		try {
            root.getChildren().add(ShapeConstructor.newImage("menu_background", screenWidth*1.5,screenHeight*1.5, screenWidth/2,screenHeight/2, 1));
        } catch (Exception e) {
            setFill(Color.BLACK);
        }
		
		String[] numberOfPlayersChoices = new String[]{"2","3","4","5","6"},
				 difficultyChoices = new String[]{"Amateur - 50s", "Co-pilot - 40s", "Captain - 30s", "Flight champion - 20s"},
				 gameBoardChoices = findAllFilesInFolder(new File("./res/gameboards"));

		Rectangle paneMenu = ShapeConstructor.newRectangle(Color.web("A9A9A9",0.6), screenWidth*0.3, screenHeight, screenWidth*0.15, screenHeight/2);

		Text playersBoxLabel = ShapeConstructor.newText("Number of players : ", Color.WHITE, screenWidth*0.28,screenHeight*0.1, screenWidth*0.15,screenHeight*0.1);
		ComboBox<String> playersBox = ControlConstructor.newComboBox(numberOfPlayersChoices, screenWidth*0.28,screenHeight*0.08, screenWidth*0.15,screenHeight*0.18);

		Text difficultyBoxLabel = ShapeConstructor.newText("Difficulty : ", Color.WHITE, screenWidth*0.28,screenHeight*0.1, screenWidth*0.15,screenHeight*0.3);
		ComboBox<String> difficultyBox = ControlConstructor.newComboBox(difficultyChoices, screenWidth*0.28,screenHeight*0.08, screenWidth*0.15,screenHeight*0.38);

		Text gameBoardBoxLabel = ShapeConstructor.newText("Game board file : ", Color.WHITE, screenWidth*0.28,screenHeight*0.1, screenWidth*0.15,screenHeight*0.5);
		ComboBox<String> gameBoardBox = ControlConstructor.newComboBox(gameBoardChoices, screenWidth*0.28,screenHeight*0.08, screenWidth*0.15,screenHeight*0.58);
		
		Executable start = (me) -> {gameInfos.send(new String[]{playersBox.getValue(), difficultyBox.getValue(), gameBoardBox.getValue()});};
		Text startButton = ControlConstructor.newButton("Start", Color.WHITE, screenWidth*0.28,screenHeight*0.1, screenWidth*0.15,screenHeight*0.75, Color.BLACK, start);

		Text exitButton = ControlConstructor.newButton("Exit Game", Color.WHITE, screenWidth*0.28,screenHeight*0.1, screenWidth*0.15,screenHeight*0.9, Color.BLACK, exit);

		root.getChildren().addAll(paneMenu, playersBoxLabel,playersBox, difficultyBoxLabel,difficultyBox, gameBoardBoxLabel,gameBoardBox, startButton,exitButton);
	}

	private String[] findAllFilesInFolder(File folder) {
		ArrayList<String> fileNames = new ArrayList<String>();

		for (File file : folder.listFiles()) {
			if (!file.isDirectory()){
				String fileName = file.getName().split(".txt")[0];
				if(fileName != "")
					fileNames.add(fileName);
			}
		}

		return fileNames.toArray(new String[fileNames.size()]);
	}
}
