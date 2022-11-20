package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polyline;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCombination;

public class View extends Application {
    private Stage stage;
    private Group root;
    private double screenWidth,
                   screenHeight;

    @Override
    public void start(Stage s) throws Exception {
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
            ImageView background = ShapeConstructor.newImage("background", 1,1, 0,0);
            background.setFitHeight(screenWidth*1.1);
            background.setFitWidth(screenWidth*1.1);
            root.getChildren().add(background);
        } catch (Exception e) {
            scene.setFill(Color.BLACK);
        }

        displayGameBoard(new String[][]{
            {"", "", "", " ", " ", " ", " ", "", "", " ", " ", " ", " ", "", "", ""},
            {"", "", " ", "portal-red-21-1/", " ", "asteroyd-white-5", " ", "audience_pod", " ", " ", "portal-white-23-2", " ", " ", "", "", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", "asteroyd-white-6", " ", " ", "asteroyd-white_red-15", " ", "", ""},
            {"", "", " ", "asteroyd-red-2", " ", "asteroyd-blue-11", " ", " ", " ", " ", "asteroyd-red-3", " ", " ", "", "", ""},
            {"", " ", " ", " ", " ", " ", " ", " ", "audience_pod", " ", " ", " ", "asteroyd-white_blue-17", " ", " ", ""},
            {" ", " ", "asteroyd-white_red-13", " ", "asteroyd-white_blue-20", " ", " ", "launchpad", "launchpad/space_ship-red-2/space_ship-blue-6", " ", "asteroyd-blue-10", " ", " ", " ", " ", ""},
            {" ", "audience_pod", " ", " ", " ", " ", " ", "launchpad", " ", "launchpad", " ", " ", " ", " ", "audience_pod", " "},
            {" ", " ", "asteroyd-white_red-14", " ", "asteroyd-blue-9", " ", " ", "launchpad/space_ship-green-4", "launchpad", " ", " ", " ", "asteroyd-white_red-16", " ", " ", ""},
            {"", " ", " ", "asteroyd-white-8", " ", " ", " ", " ", "audience_pod", " ", " ", "asteroyd-red-4", " ", " ", " ", ""},
            {"", "", " ", " ", "asteroyd-red-1", " ", " ", " ", " ", "asteroyd-blue-12", " ", " ", " ", "", "", ""},
            {"", "", " ", "portal-white-24-3", " ", " ", "asteroyd-white_blue-19", " ", " ", " ", " ", " ", "asteroyd-white_blue-18", " ", "", ""},
            {"", "", " ", " ", " ", " ", " ", "audience_pod", " ", "asteroyd-white-7", " ", "portal-red-22-4", " ", "", "", ""},
            {"", "", "", " ", " ", " ", " ", "", "", " ", " ", " ", " ", "", "", ""}
        });

        Rectangle rect = new Rectangle(screenWidth*0.8,0, screenWidth*0.2,screenHeight);
        rect.setFill(Color.GRAY);
        root.getChildren().add(rect);
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
                            throw new Exception(/*TODO*/);
                    }
                    group.getChildren().addAll(ShapeConstructor.newHexagon(color1,color2, hexSize, x,y));
                }

                group.getChildren().add(ShapeConstructor.newText(asteroydPriority, Color.WHITE,Color.BLACK, hexWidth/4, x,y));
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
                        case "red":
                            color = Color.BROWN;
                            break;

                        case "blue":
                            color = Color.BLUE;
                            break;

                        case "green":
                            color = Color.GREEN;
                            break;

                        case "yellow":
                            color = Color.YELLOW;
                            break;

                        case "orange":
                            color = Color.ORANGE;
                            break;

                        case "purple":
                            color = Color.PURPLE;
                            break;

                        default:
                            throw new Exception(/*TODO*/);
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
                            throw new Exception(/*TODO*/);
                    }
                    group.getChildren().add(ShapeConstructor.newCircle(color, hexWidth/3, x,y));
                }
                
                group.getChildren().add(ShapeConstructor.newText(portalPriority, Color.WHITE,Color.BLACK, hexWidth/4, x,y));
                break;

            case " ":
                break;

                default:
                    throw new Exception(/*TODO*/);
        }
    }
}