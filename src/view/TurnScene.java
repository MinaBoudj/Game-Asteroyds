package view;

/**
 * @author Matéo
 */

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class TurnScene extends Scene {
    private double screenWidth,
                   screenHeight;

    public TurnScene(double screenWidth, double screenHeight) {
        super(new Group());
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void updateRoot(Group gameBoardGroup, String player, int difficulty, int[] directions, Executable mainMenu) throws Exception {
        Group root = new Group(gameBoardGroup);
        setRoot(root);

        double maxTextHeight = screenHeight/18,
               maxRelicSize = screenWidth/32.5,
               maxBoxSize = Math.min(screenWidth/20, maxTextHeight*3),
               maxDirectionSize = screenWidth/25;
        String[] playerInfos = player.split("-"),
                 relics = playerInfos[0].split("/");
        String playerString = playerInfos[1] + " : " + playerInfos[2] + "/6 Structure points";
        Color color = ControlConstructor.getPlayerColor(playerInfos[3]);

        Text playerText = ShapeConstructor.newText(playerString, color, screenWidth*0.18,maxTextHeight, screenWidth*0.9,maxTextHeight);

        for(int j = 0 ; j < relics.length ; j++) {
            double centerX = screenWidth*0.8 + maxRelicSize*(j*1.5 + 1);
            try {
                root.getChildren().add(ShapeConstructor.newImage("relic" + relics[j], maxRelicSize,maxTextHeight*0.8, centerX,maxTextHeight*2));
            } catch (Exception e) {
                root.getChildren().add(ShapeConstructor.newText(relics[j], Color.CHARTREUSE, maxRelicSize,maxTextHeight*0.8, centerX,maxTextHeight*2));
            }
        }

        ComboBox<String>[] comboBoxes = (ComboBox<String>[]) new ComboBox[6];
        Executable updateComboBoxes = ev -> {
                ArrayList<Integer> nullCells = new ArrayList<Integer>();
                int lastSelected = -1;

                for(int i = 0 ; i < comboBoxes.length ; i++) {
                    if(comboBoxes[i].getValue() != null)
                        lastSelected = i;
                    else if(nullCells.size() == 0 || nullCells.get(nullCells.size() -1) == i -1)
                        nullCells.add(i);
                }

                if(nullCells.size() == 1 && nullCells.get(0) < lastSelected) {
                    for(int i = lastSelected ; i > nullCells.get(0) ; i--) {
                        String boxValue = comboBoxes[nullCells.get(0)].getValue();
                        comboBoxes[nullCells.get(0)].setValue(comboBoxes[i].getValue());
                        comboBoxes[i].setValue(boxValue);
                    }
                } else if(nullCells.size() > 1 && nullCells.get(0) < lastSelected) {
                    String boxValue = comboBoxes[lastSelected].getValue();
                    comboBoxes[lastSelected].setValue(null);
                    comboBoxes[nullCells.get(0)].setValue(boxValue);
                }
            };

        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 2 ; j++) {
                double x = screenWidth*0.8 + maxBoxSize*(j*2 + 1),
                       y = maxTextHeight*(i*3.5 + 4.5);

                ComboBox<String> comboBox = ControlConstructor.newMovementComboBox(maxBoxSize, x,y);
                comboBox.setOnAction(ae -> {updateComboBoxes.execute(ae);});
                comboBoxes[i*2 + j] = comboBox;

                root.getChildren().add(comboBox);
            }
        }

        Text redDir = ShapeConstructor.newText(Integer.toString(directions[0]), Color.WHITE, maxDirectionSize,maxTextHeight*0.8, screenWidth*0.8 + maxDirectionSize,maxTextHeight*14),
             whiteDir = ShapeConstructor.newText(Integer.toString(directions[1]), Color.WHITE, maxDirectionSize,maxTextHeight*0.8, screenWidth*0.8 + maxDirectionSize*2.5,maxTextHeight*14),
             blueDir = ShapeConstructor.newText(Integer.toString(directions[2]), Color.WHITE, maxDirectionSize,maxTextHeight*0.8, screenWidth*0.8 + maxDirectionSize*4,maxTextHeight*14);

        double diceSize = Math.max(redDir.getBoundsInLocal().getWidth(), redDir.getBoundsInLocal().getHeight()*0.7);
        Rectangle redDice = ShapeConstructor.newRectangle(Color.RED, diceSize,diceSize, screenWidth*0.8 + maxDirectionSize,maxTextHeight*14),
                  whiteDice = ShapeConstructor.newRectangle(Color.SILVER, diceSize,diceSize, screenWidth*0.8 + maxDirectionSize*2.5,maxTextHeight*14),
                  blueDice = ShapeConstructor.newRectangle(Color.AQUA, diceSize,diceSize, screenWidth*0.8 + maxDirectionSize*4,maxTextHeight*14);

        Text timerText = ShapeConstructor.newText(Integer.toString(difficulty), Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*15.5);
        Timer timer = new Timer();
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(difficulty*1000), new KeyValue(new WritableExecutable(), mainMenu)));

        Executable returnMainMenu = ev -> {
            timer.cancel();
            timeline.stop();
            mainMenu.execute(ev);
        };
        Text mainMenuButton = ControlConstructor.newButton("Main Menu", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*16.75, Color.BLACK, returnMainMenu),
             noSave = ShapeConstructor.newText("(game will not be saved)", Color.RED, screenWidth*0.18,maxTextHeight*0.4, screenWidth*0.9,maxTextHeight*17.25);

        root.getChildren().addAll(playerText, timerText, redDice,redDir, whiteDice,whiteDir, blueDice,blueDir, mainMenuButton,noSave);

        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                int timeLeft = Integer.parseInt(timerText.getText()) - 1;
                if(timeLeft >= 0)
                    timerText.setText(Integer.toString(timeLeft));
                else
                    timer.cancel();
            }
        }, 0, 1000);
        timeline.play();
    }
}
/* -1-1-1-
-
1
1
-
3
-
3
-
3
-
1
-
1
-
1
-
*/