package view;

/**
 * @author Matéo
 */

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.Group;

public class TurnScene extends Scene {
    private double screenWidth,
                   screenHeight;

    public TurnScene(double screenWidth, double screenHeight) {
        super(new Group());
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void updateRoot(Group gameBoardGroup, String player, int difficulty, Executable mainMenu) throws Exception {
        Group root = new Group(gameBoardGroup);
        setRoot(root);

        double maxTextHeight = screenHeight/18.5,
               maxRelicSize = screenWidth/32.5;

        String[] playerInfos = player.split("-"),
                 relics = playerInfos[0].split("/");
        String playerString = playerInfos[1] + " : " + playerInfos[2] + "/6 Structure points";
        Color color = ControlConstructor.getPlayerColor(playerInfos[3]);

        root.getChildren().add(ShapeConstructor.newText(playerString, color, screenWidth*0.18,maxTextHeight, screenWidth*0.9,maxTextHeight));

        for(int j = 0 ; j < relics.length ; j++) {
            double centerX = screenWidth*0.8 + maxRelicSize*(j*1.5 + 1);
            try {
                root.getChildren().add(ShapeConstructor.newImage("relic" + relics[j], maxRelicSize,maxTextHeight*0.8, centerX,maxTextHeight*2));
            } catch (Exception e) {
                root.getChildren().add(ShapeConstructor.newText(relics[j], Color.CHARTREUSE, maxRelicSize,maxTextHeight*0.8, centerX,maxTextHeight*2));
            }
        }

        Text timer = ShapeConstructor.newText(Integer.toString(difficulty), Color.WHITE, screenWidth*0.18,screenHeight*0.8/18.5, screenWidth*0.9,screenHeight*16/18.5);
        Executable playerTurnEnd = (ev) -> {
            mainMenu.execute(ev);
        };
        
        Text mainMenuButton = ControlConstructor.newButton("Main Menu", Color.WHITE, screenWidth*0.18,maxTextHeight*0.8, screenWidth*0.9,maxTextHeight*17.25, Color.BLACK, mainMenu),
             noSave = ShapeConstructor.newText("(game will not be saved)", Color.RED, screenWidth*0.18,maxTextHeight*0.4, screenWidth*0.9,maxTextHeight*17.75);

        root.getChildren().addAll(timer, mainMenuButton,noSave);

        Timer tm = new Timer();
        tm.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                timer.setText(Double.toString(Double.parseDouble(timer.getText()) - 0.01));
            }
        }, 0, 10);
        Timer tr = new Timer();
        tr.schedule(new TimerTask() {
            @Override
            public void run() {
                tm.cancel();
                tr.interrupt();
                mainMenu.execute(new Event(null));
            }
        }, difficulty*1000);
    }
}