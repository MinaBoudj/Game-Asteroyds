package view;

/**
 * @author Matéo
 */

import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;



public class MainGroup extends Group {
    private double screenWidth,
                   screenHeight;

    public MainGroup(double screenWidth, double screenHeight) {
        super();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    private void updateRoot(Group gameBoardGroup, String[] players, Executable mainMenu, Executable simpInterface) throws Exception {
        double maxTextHeight = screenHeight/18.5,
               maxRelicSize = screenWidth/32.5;

        getChildren().removeAll(getChildren());
        getChildren().addAll(gameBoardGroup.getChildren());

        for(int i = 0 ; i < players.length ; i++) {
            String[] playerInfos = players[i].split("-"),
                     relics = playerInfos[0].split("/");
            String playerString = playerInfos[1] + " : " + playerInfos[2] + "/6 Structure points";
            Color color = ControlConstructor.getPlayerColor(playerInfos[3]);

            getChildren().add(ShapeConstructor.newText(playerString, color,Color.BLACK, screenWidth*0.18,maxTextHeight, screenWidth*0.9,maxTextHeight*(i*2.5 + 1)));

            for(int j = 0 ; j < relics.length ; j++) {
                double centerX = screenWidth*0.8 + maxRelicSize*(j*1.5 + 1);
                try {
                    getChildren().add(ShapeConstructor.newImage("relic" + relics[j], maxRelicSize,maxTextHeight*0.8, centerX,maxTextHeight*(i*2.5 + 2)));
                } catch (Exception e) {
                    getChildren().add(ShapeConstructor.newText(relics[j], Color.CHARTREUSE, maxRelicSize,maxTextHeight*0.8, centerX,maxTextHeight*(i*2.5 + 2)));
                }
            }
        }

        Text mainMenuButton = ControlConstructor.newButton("Main Menu", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*17.25, Color.BLACK, mainMenu),
             noSave = ShapeConstructor.newText("(game will not be saved)", Color.RED, screenWidth*0.18,maxTextHeight*0.4, screenWidth*0.9,maxTextHeight*17.75);

        double boxSize = screenHeight*0.02;
        Text simpInterfaceText = ShapeConstructor.newText("Simplified Interface", Color.WHITE, screenWidth,boxSize, (screenWidth*0.8)/2 + boxSize,screenHeight*0.9875);
        CheckBox simpInterfaceTextBox = ControlConstructor.newCheckBox(boxSize, (screenWidth*0.8 + boxSize - simpInterfaceText.getBoundsInLocal().getWidth())/2,screenHeight*0.9875);
        simpInterfaceTextBox.setOnAction(ae -> {simpInterface.execute(ae);});
        simpInterfaceTextBox.setSelected(ShapeConstructor.NOIMAGE);

        getChildren().addAll(mainMenuButton,noSave, simpInterfaceText,simpInterfaceTextBox);
    }

    public void newTurn(Group gameBoardGroup, String[] players, Executable newTurn, Executable mainMenu, Executable simpInterface) throws Exception {
        updateRoot(gameBoardGroup, players, mainMenu, simpInterface);
        getChildren().add(ControlConstructor.newButton("Next Turn", Color.WHITE, screenWidth*0.18,screenHeight*0.8/18.5, screenWidth*0.9,screenHeight*16/18.5, Color.BLACK, newTurn));
    }

    public void newPlayerTurn(Group gameBoardGroup, String[] players, String nextPlayer, Executable startPlayerTurn, Executable mainMenu, Executable simpInterface) throws Exception {
        updateRoot(gameBoardGroup, players, mainMenu, simpInterface);
        String[] playerInfos = nextPlayer.split("-");
        String name = playerInfos[1];
        Color color = ControlConstructor.getPlayerColor(playerInfos[3]);

        getChildren().add(ControlConstructor.newButton("Start " + name + "'s Turn", Color.WHITE, screenWidth*0.18,screenHeight*0.8/18.5, screenWidth*0.9,screenHeight*16/18.5, color, startPlayerTurn));
    }
}