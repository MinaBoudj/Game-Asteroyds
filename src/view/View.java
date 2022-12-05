package view;

/**
 * @author Matéo
 */

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polyline;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCombination;

public class View{
    private Stage stage;
    private Group root;
    private double screenWidth,
                   screenHeight;
<<<<<<< HEAD
                   
    @Override
    public void start(Stage s) throws Exception {
=======

    public View(Stage s) {
        stage = s;
>>>>>>> fe72e24d85f6973482ad747a4eed0326cf8e60af
        root = new Group();
        Scene scene = new Scene(root);

        stage = s;
        stage.setTitle("Asteroyds");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(null);
        stage.show();

        screenWidth = scene.getWidth();
        screenHeight = scene.getHeight();

        try {
            root.getChildren().add(ShapeConstructor.newImage("background", screenWidth,screenHeight, screenWidth/2,screenHeight/2, 1));
        } catch (Exception e) {
            scene.setFill(Color.BLACK);
        }
    }

    public View(Stage stage, Group root, double wid, double hei){
        this.stage = stage;
        this.root = root;
        this.screenWidth = wid;
        this.screenHeight = hei;
    }

    public void displayGameBoard(String[][] gameBoard) throws Exception {
        double gameBoardWidth = gameBoard[0].length + 0.5,
               gameBoardHeight = gameBoard.length * 1.5 + 0.5,
               hexSize = Math.min((screenWidth * 0.75 / gameBoardWidth) / Math.sqrt(3),
                                  screenHeight * 0.95 / gameBoardHeight),
               hexWidth = Math.sqrt(3) * hexSize,
               initX = hexSize + (screenWidth*0.8 - hexWidth*gameBoardWidth)/2,
               initY = hexSize + (screenHeight - hexSize*gameBoardHeight)/2,
               x = initX, y = initY;

        for (int i = 0 ; i < gameBoard.length ; i++) {
            for (int j = 0 ; j < gameBoard[i].length ; j++) {
                if (gameBoard[i][j] != "") {
                    Polyline hexLine = new Polyline();
                    hexLine.getPoints().addAll(ShapeConstructor.newHexagonCorners(x, y, hexSize));
                    hexLine.setStroke(Color.WHITE);
                    root.getChildren().add(hexLine);

                    String[] objectsToDisplay = gameBoard[i][j].split("/");
                    for (String object : objectsToDisplay) {
                        displayObject(object.split("-"), root, hexWidth, hexSize, x, y);
                    }
                }

                x += hexWidth;
            }
            x = i % 2 == 0 ? initX + hexWidth/2 : initX;
            y += hexSize * 1.5;
        }

        //Rectangle rect = new Rectangle(screenWidth*0.8,0, screenWidth*0.2,screenHeight);
        //rect.setFill(Color.GRAY);
        //root.getChildren().add(rect);
    }

    private void displayObject(String[] objectInformations, Group group, double hexWidth, double hexSize, double x, double y) throws Exception {
        String objectType = objectInformations[0];
        switch (objectType) {
            case "asteroyd":
                String asteroydColor = objectInformations[1],
                       asteroydPriority = objectInformations[2];
                /*int asteroydOrientation = Integer.parseInt(objectInformations[2]);*/
                try {
                    group.getChildren().add(ShapeConstructor.newImage(asteroydColor + "_asteroyd", hexWidth,hexSize, x,y));
                } catch (Exception e) {
                    Color color1, color2;
                    switch (asteroydColor) {
                        case "red":
                            color1 = Color.RED;
                            color2 = color1;
                            break;

                        case "blue":
                            color1 = Color.AQUA;
                            color2 = color1;
                            break;

                        case "white":
                            color1 = Color.SILVER;
                            color2 = color1;
                            break;

                        case "white_red":
                            color1 = Color.SILVER;
                            color2 = Color.RED;
                            break;

                        case "white_blue":
                            color1 = Color.SILVER;
                            color2 = Color.AQUA;
                            break;

                        default:
                            throw new Exception("Couleur d'astéroïde non reconnue : " + asteroydColor);
                    }
                    group.getChildren().addAll(ShapeConstructor.newHexagon(color1,color2, hexSize, x,y));
                }

                //group.getChildren().add(ShapeConstructor.newText(asteroydPriority, Color.WHITE,Color.BLACK, hexWidth/4, x,y));
                break;

            case "space_ship":
                String spaceShipColor = objectInformations[1];
                int spaceShipOrientation = Integer.parseInt(objectInformations[2]);
                double centerX = x + hexSize/2 * Math.cos((60 * (spaceShipOrientation -2)) * ShapeConstructor.TORAD),
                       centerY = y + hexSize/2 * Math.sin((60 * (spaceShipOrientation -2)) * ShapeConstructor.TORAD);

                try {
                    group.getChildren().add(ShapeConstructor.newImage(spaceShipColor + "_space_ship", hexSize/2,hexSize/2, centerX,centerY, spaceShipOrientation));
                } catch (Exception e) {
                    Color color;
                    switch (spaceShipColor) {
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
                            throw new Exception("Couleur de vaisseau non reconnue : " + spaceShipColor);
                    }
                    group.getChildren().add(ShapeConstructor.newTriangle(color, hexSize/2, centerX - hexSize/20,centerY, spaceShipOrientation));
                }
                break;

            case "launchpad":
                try {
                    group.getChildren().add(ShapeConstructor.newImage(objectType, hexWidth,hexSize, x,y));

                } catch (Exception e) {
                    group.getChildren().add(ShapeConstructor.newHexagon(Color.CHOCOLATE, hexSize, x,y));
                }
                break;

            case "audience_pod":
                try {
                    group.getChildren().add(ShapeConstructor.newImage(objectType, hexWidth,hexSize, x,y));

                } catch (Exception e) {
                    group.getChildren().add(ShapeConstructor.newHexagon(Color.WHITE, hexSize, x,y));
                }
                break;

            case "portal":
                String portalColor = objectInformations[1],
                       portalPriority = objectInformations[2],
                       relic = objectInformations[3];
                try {
                    group.getChildren().add(ShapeConstructor.newImage("relic" + relic, hexWidth/2,hexSize/2, x,y));
                } catch (Exception e) {
                    group.getChildren().add(ShapeConstructor.newText(relic, Color.CHARTREUSE, hexWidth/2, x,y));
                }

                try {
                    group.getChildren().add(ShapeConstructor.newImage(portalColor + "_portal", hexWidth,hexSize, x,y));
                } catch (Exception e) {
                    Color color;
                    switch (portalColor) {
                        case "red":
                            color = Color.RED;
                            break;
                    
                        case "white":
                            color = Color.SILVER;
                            break;

                        default:
                            throw new Exception("Couleur de portail non reconnue : " + portalColor);
                    }
                    group.getChildren().add(ShapeConstructor.newCircle(color, hexWidth/3, x,y));
                }
                
                //group.getChildren().add(ShapeConstructor.newText(portalPriority, Color.WHITE,Color.BLACK, hexWidth/4, x,y));
                break;

            case " ":
                break;

                default:
                    throw new Exception("Type d'objet visuel non reconnu : " + objectType);
        }
    }

    public ArrayList<String> readTextFile(String pathName) throws Exception {
        ArrayList<String> lines = new ArrayList<String>();
        File doc = new File(pathName);
        Scanner scan = new Scanner(doc);

        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }

        scan.close();
        return lines;
    }
}
