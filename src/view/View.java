package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCombination;

import java.io.FileInputStream;
import java.io.InputStream;

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
            InputStream backgroundStream = new FileInputStream("./res/images/background.png");
            ImageView backgroundView = new ImageView();
            Image im = new Image(backgroundStream);
            backgroundView.setImage(im);
            backgroundView.setFitWidth(screenWidth*1.1);
            backgroundView.setPreserveRatio(true);
            backgroundView.setSmooth(true);
            root.getChildren().add(backgroundView);
        } catch (Exception e) {
            scene.setFill(Color.BLACK);
        }

        String[][] gm = new String[][]{
            {"", "", "", " ", " ", " ", " ", "", "", " ", " ", " ", " ", "", "", ""},
            {"", "", " ", " ", " ", " ", " ", "audience_pod", " ", " ", " ", " ", " ", "", "", ""},
            {"", "", " ", "asteroyd-white", " ", " ", "asteroyd-red", " ", " ", " ", " ", " ", " ", " ", "", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", "", ""},
            {"", "asteroyd-red", " ", " ", " ", " ", "launchpad", " ", " ", "asteroyd-blue", " ", "asteroyd-red", " ", " ", " ", ""},
            {" ", " ", " ", " ", " ", " ", " ", "launchpad", " ", "asteroyd-white_blue", " ", " ", " ", " ", " ", ""},
            {" ", "audience_pod", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "audience_pod", " "},
            {" ", " ", " ", " ", " ", "asteroyd-white", " ", " ", " ", " ", " ", " ", " ", " ", " ", ""},
            {"", "asteroyd-white_red", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", "", ""},
            {"", "", " ", "asteroyd-red", " ", " ", " ", " ", " ", " ", " ", " ", "asteroyd-red", " ", "", ""},
            {"", "", " ", "asteroyd-blue", " ", " ", " ", "audience_pod", " ", " ", " ", " ", " ", "", "", ""},
            {"", "", "", " ", " ", " ", " ", "", "", " ", " ", " ", " ", "", "", ""}
        };
        int[] a = new int[]{1};
        gameMenu(gm, a);

        Rectangle rect = new Rectangle(screenWidth*0.8,0, screenWidth*0.2,screenHeight);
        rect.setFill(Color.GRAY);
        root.getChildren().add(rect);
    }

    public void gameMenu(String[][] gameBoard, int[] turnDirections) {
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
                    hexLine.getPoints().addAll(newHexagonCorners(x, y, hexSize));
                    hexLine.setStroke(Color.WHITE);
                    root.getChildren().add(hexLine);

                    String[] objectsToDisplay = gameBoard[i][j].split("/");
                    for (String object : objectsToDisplay) {
                        displayObject(object.split("-"), hexWidth, hexSize, x, y);
                    }
                }

                x += hexWidth;
            }
            x = i % 2 == 0 ? initX + hexWidth/2 : initX;
            y += hexSize * 1.5;
        }
    }

    private void displayObject(String[] objectInformations, double hexWidth, double hexSize, double x, double y) {
        String objectType = objectInformations[0];
        switch (objectType) {
            case "asteroyd":
                String asteroydColor = objectInformations[1];
                /*int asteroydOrientation = Integer.parseInt(objectInformations[2]),
                    priority = Integer.parseInt(objectInformations[3]);*/
                try {
                    displayImage(asteroydColor + "_asteroyd", hexWidth, hexSize, x, y);
                } catch (Exception e) {
                    switch (asteroydColor) {
                        case "red":
                            displayColoredHexagon(Color.RED, hexSize, x, y);
                            break;

                        case "blue":
                            displayColoredHexagon(Color.AQUA, hexSize, x, y);
                            break;

                        case "white":
                            displayColoredHexagon(Color.SILVER, hexSize, x, y);
                            break;

                        case "white_red":
                            displayColoredHexagon(Color.SILVER, Color.RED, hexSize, x, y);
                            break;

                        case "white_blue":
                            displayColoredHexagon(Color.SILVER, Color.AQUA, hexSize, x, y);
                            break;
                    }
                }
                //displayAsteroydInformations(asteroydOrientation, priority, hexSize, x, y);
                break;

            case "space_ship":
                String spaceShipColor = objectInformations[1];
                break;

            case "launchpad":
                try {
                    displayImage(objectType, hexWidth, hexSize, x, y);

                } catch (Exception e) {
                    displayColoredHexagon(Color.DARKGREEN, hexSize, x, y);
                }
                break;

            case "audience_pod":
                try {
                    displayImage(objectType, hexWidth, hexSize, x, y);

                } catch (Exception e) {
                    displayColoredHexagon(Color.WHITE, hexSize, x, y);
                }
                break;
        }
    }

    /*private void displayAsteroydInformations(int orientation, int priority, double hexSize, double x, double y) {
        Font font = new Font(hexSize*0.3);
    }*/

    private Double[] newHexagonCorners(double centerX, double centerY, double size) {
        Double[] hexCorners = new Double[14];
        for(int i = 0 ; i < 12 ; i += 2) {
            double angleDeg = 30 * (i - 1);
            double angleRad = Math.PI / 180 * angleDeg;
            hexCorners[i] = centerX + size * Math.cos(angleRad);
            hexCorners[i+1] = centerY + size * Math.sin(angleRad);
        }
        hexCorners[12] = centerX + size * Math.cos(-Math.PI/180*30);
        hexCorners[13] = centerY + size * Math.sin(-Math.PI/180*30);

        return hexCorners;
    }

    private void displayImage(String imageName, double width, double height, double centerX, double centerY) throws Exception{
        InputStream imageStream = new FileInputStream("./res/images/" + imageName + ".png");
        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setX(centerX - imageView.getBoundsInLocal().getWidth()/2);
        imageView.setY(centerY - imageView.getBoundsInLocal().getHeight()/2);
        root.getChildren().add(imageView);
    }

    private void displayColoredHexagon(Color color, double size, double x, double y) {
        Polygon hexagonArea = new Polygon();
        hexagonArea.getPoints().addAll(newHexagonCorners(x, y, size));
        hexagonArea.setFill(color);
        root.getChildren().add(hexagonArea);
    }

    private void displayColoredHexagon(Color color1, Color color2, double size, double x, double y) {
        Double[] hexagonCorners = newHexagonCorners(x, y, size);

        Polygon halfHexagonArea1 = new Polygon();
        for (int i = 0 ; i < 8 ; i++) {
            halfHexagonArea1.getPoints().add(hexagonCorners[i]);
        }
        halfHexagonArea1.getPoints().add(hexagonCorners[0]);
        halfHexagonArea1.getPoints().add(hexagonCorners[1]);
        halfHexagonArea1.setFill(color1);
        root.getChildren().add(halfHexagonArea1);

        Polygon halfHexagonArea2 = new Polygon();
        for (int i = 6 ; i < 14 ; i++) {
            halfHexagonArea2.getPoints().add(hexagonCorners[i]);
        }
        halfHexagonArea2.getPoints().add(hexagonCorners[6]);
        halfHexagonArea2.getPoints().add(hexagonCorners[7]);
        halfHexagonArea2.setFill(color2);
        root.getChildren().add(halfHexagonArea2);
    }
}