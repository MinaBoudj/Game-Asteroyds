package view;

/**
 * @author Matéo
 */

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;



public class Mainscene extends Scene {
    double screenWidth,
           screenHeight;

    public Mainscene(double screenWidth, double screenHeight) {
        super(new Group());
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void update(Group gameBoardGroup, String[] players, Executable nextTurn, Executable mainMenu, Executable simpInterface) throws Exception {
        Group root = new Group(gameBoardGroup);
        setRoot(root);

        double maxTextHeight = screenHeight/18.5,
               maxRelicSize = screenWidth/32.5;

        for(int i = 0 ; i < players.length ; i++) {
            String[] playerInfos = players[i].split("-"),
                     relics = playerInfos[0].split("/");
            String playerString = playerInfos[1] + " : " + playerInfos[2] + "/6 Structure points";
            
            Color color;
            switch(playerInfos[3]) {
                case "Red":
                    color = Color.BROWN;
                    break;

                case "Blue":
                    color = Color.BLUE;
                    break;

                case "Green":
                    color = Color.GREEN;
                    break;

                case "Yellow":
                    color = Color.YELLOW;
                    break;

                case "Orange":
                    color = Color.ORANGE;
                    break;

                case "Purple":
                    color = Color.PURPLE;
                    break;

                default:
                    throw new Exception();
            }

            root.getChildren().add(ShapeConstructor.newText(playerString, color, screenWidth*0.18,maxTextHeight, screenWidth*0.9,maxTextHeight*(i*2.5 + 1)));

            for(int j = 0 ; j < relics.length ; j++) {
                double centerX = screenWidth*0.8 + maxRelicSize*(j*1.5 + 1);
                try {
                    root.getChildren().add(ShapeConstructor.newImage("relic" + relics[j], maxRelicSize,maxTextHeight*0.8, centerX,maxTextHeight*(i*2.5 + 2)));
                } catch (Exception e) {
                    root.getChildren().add(ShapeConstructor.newText(relics[j], Color.CHARTREUSE, maxRelicSize,maxTextHeight*0.8, centerX,maxTextHeight*(i*2.5 + 2)));
                }
            }
        }

        Text nextTurnButton = ControlConstructor.newButton("Next Turn", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*16, Color.BLACK, nextTurn),
             mainMenuButton = ControlConstructor.newButton("Main Menu", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*17.5, Color.BLACK, mainMenu);

        double boxSize = screenHeight*0.02;
        Text simpInterfaceText = ShapeConstructor.newText("Simplified Interface", Color.WHITE, screenWidth,boxSize, (screenWidth*0.8)/2 + boxSize,screenHeight*0.9875);
        CheckBox simpInterfaceTextBox = ControlConstructor.newCheckBox(boxSize, (screenWidth*0.8 + boxSize - simpInterfaceText.getBoundsInLocal().getWidth())/2,screenHeight*0.9875);
        simpInterfaceTextBox.setOnAction((ae) -> {simpInterface.execute(ae);});
        simpInterfaceTextBox.setSelected(ShapeConstructor.NOIMAGE);

        root.getChildren().addAll(nextTurnButton, mainMenuButton, simpInterfaceText,simpInterfaceTextBox);
    }
}