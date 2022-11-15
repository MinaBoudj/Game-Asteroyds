package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polyline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCombination;

import java.io.FileInputStream;
import java.io.InputStream;

public class View extends Application {
    Stage stage;
    Group root;
    double screenWidth,
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

        System.out.println(screenWidth);
        
        screenWidth = scene.getWidth();
        screenHeight = scene.getHeight();

        try {
            InputStream bgStream = new FileInputStream("./res/images/background.png");
            ImageView background = new ImageView();
            Image im = new Image(bgStream);
            background.setImage(im);
            background.setFitWidth(screenWidth*1.1);
            background.setPreserveRatio(true);
            background.setSmooth(true);
            root.getChildren().add(background);
        } catch (Exception e) {
            scene.setFill(Color.BLACK);
        }

        

        System.out.println(scene.getWidth());

        String[][] gm = new String[][]{
            {"", "", "", " ", " ", " ", " ", "", "", " ", " ", " ", " ", "", "", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", "", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", "", ""},
            {"", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", ""},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", ""},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", ""},
            {"", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", "", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", ""},
            {"", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "", "", ""},
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
                    Polyline hex = newHexagon(x, y, hexSize);
                    hex.setStroke(Color.WHITE);
                    root.getChildren().add(hex);
                }

                x += hexWidth;
            }
            x = i % 2 == 0 ? initX + hexWidth/2 : initX;
            y += hexSize * 1.5;
        }
    }

    private Polyline newHexagon(double centerX, double centerY, double size) {
        Polyline hex = new Polyline();
        for(int i = 0 ; i < 6 ; i++) {
            double angleDeg = 60 * i - 30;
            double angleRad = Math.PI / 180 * angleDeg;
            hex.getPoints().add(centerX + size * Math.cos(angleRad));
            hex.getPoints().add(centerY + size * Math.sin(angleRad));
        }
        hex.getPoints().add(centerX + size * Math.cos(-Math.PI/180*30));
        hex.getPoints().add(centerY + size * Math.sin(-Math.PI/180*30));

        return hex;
    }
}