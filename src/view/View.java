package view;

/**
 * @author Matéo
 */

import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCombination;

public class View {
    private Stage stage;
    private Group menu;
    private GameKeyGroup gameKey;
    private OptionsGroup options;
    private EndGroup end;
    private MainGroup main;
    private TurnGroup turn;
    private Group gameBoardGroup;
    private double screenWidth,
                   screenHeight;
    private Executable mainMenu;
    private Scene scene;

    public View(Stage s, Sendable gameInfos) {
        scene = new Scene(new Group());
        stage = s;
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setTitle("Asteroyds");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();

        screenWidth = scene.getWidth();
        screenHeight = scene.getHeight();

        menu = new MenuGroup(screenWidth,screenHeight, ev -> {stage.close();}, gameInfos);
        options = new OptionsGroup(screenWidth, screenHeight);
        main = new MainGroup(screenWidth,screenHeight);
        turn = new TurnGroup(screenWidth, screenHeight);
        end = new EndGroup(screenWidth, screenHeight);
        gameKey = new GameKeyGroup(screenWidth, screenHeight);

        gameBoardGroup = new Group();

        scene.setRoot(menu);
        mainMenu = ev -> {scene.setRoot(menu);};
    }

    public void displayErrorMessage(String content) {
        Executable close = ev -> {
            scene.setRoot(menu);
        };
        Text error = ShapeConstructor.newText(content, Color.RED, screenWidth/3,screenHeight*0.05, screenWidth/2,screenHeight*0.45),
             closeButton = ControlConstructor.newButton("Close", Color.RED, screenWidth/3,screenHeight*0.05, screenWidth/2,screenHeight*0.55, Color.BLACK, close);
        Rectangle background = ShapeConstructor.newRectangle(Color.web("FFFFFF",0.3), screenWidth*1.2,screenHeight*1.2, screenWidth/2,screenHeight/2),
                  errorPane = ShapeConstructor.newRectangle(Color.WHITE, error.getBoundsInLocal().getWidth()*1.2,screenHeight*0.2, screenWidth/2,screenHeight/2);
        scene.setRoot(new Group(background, errorPane, error, closeButton));
    }

    public void displayOptionsScene(String[][] gameBoard, ArrayList<String> colorChoices, int playerIndex, String[] launchpadPositions, Sendable playerInfo) {
        Executable updateGameBoard = ev -> {
                try{
                    updateGameBoardGroup(gameBoard, false);
                } catch (Exception e) {/*TODO*/}
            };
        options.updateOptionsGroup(gameBoardGroup, colorChoices, playerIndex, updateGameBoard, mainMenu, playerInfo, launchpadPositions, gameBoard);
        scene.setRoot(options);
    }

    public void displayEndScene(String[] players) throws Exception {
        end.updateRoot(players, mainMenu);
        scene.setRoot(end);
    }

    public void displayMainScene(String[][] gameBoard, String[] players, Executable newTurn) throws Exception {
        updateGameBoardGroup(gameBoard, false);
        Executable simpInterface = ev -> {
                ShapeConstructor.NOIMAGE = !ShapeConstructor.NOIMAGE;
                try{
                    displayMainScene(gameBoard, players, newTurn);
                } catch (Exception e) {/*TODO*/}
            };
        Executable gk = ev -> {
                gameKey.displayGameKeyGroup(ev2 -> {
                        try{displayMainScene(gameBoard, players, newTurn);}
                        catch (Exception e) {/*TODO*/}
                    });
                scene.setRoot(gameKey);
            };
        main.newTurn(gameBoardGroup, players, newTurn, mainMenu, gk, simpInterface);
        scene.setRoot(main);
    }

    public void displayMainScene(String[][] gameBoard, String[] players, String nextPlayer, int difficulty, int[] directions, Sendable newPlayerTurn) throws Exception {
        updateGameBoardGroup(gameBoard, false);
        Executable simpInterface = ev -> {
                ShapeConstructor.NOIMAGE = !ShapeConstructor.NOIMAGE;
                try{
                    displayMainScene(gameBoard, players, nextPlayer, difficulty, directions, newPlayerTurn);
                } catch (Exception e) {/*TODO*/}
            };
        Executable gk = ev -> {
                    gameKey.displayGameKeyGroup(ev2 -> {
                            try{displayMainScene(gameBoard, players, nextPlayer, difficulty, directions, newPlayerTurn);}
                            catch (Exception e) {/*TODO*/}
                        });
                    scene.setRoot(gameKey);
                };
        Executable startPlayerTurn = ev -> {
                displayTurnScene(gameBoard, nextPlayer, difficulty, directions, newPlayerTurn);
            };
        main.newPlayerTurn(gameBoardGroup, players, nextPlayer, startPlayerTurn, mainMenu, gk, simpInterface);
        scene.setRoot(main);
    }

    public void displayTurnScene(String[][] gameBoard, String player, int difficulty, int[] directions, Sendable newPlayerTurn) {
        try {
            updateGameBoardGroup(gameBoard, true);
            turn.updateRoot(gameBoardGroup, player, difficulty, directions, mainMenu, newPlayerTurn);
        } catch(Exception e) {/*TODO*/}
        scene.setRoot(turn);
    }

    public void updateGameBoardGroup(String[][] gameBoard, boolean displayAstInfos) throws Exception {
        gameBoardGroup.getChildren().removeAll(gameBoardGroup.getChildren());

        try {
            gameBoardGroup.getChildren().add(ShapeConstructor.newImage("background", screenWidth*1.5,screenHeight*1.5, screenWidth/2,screenHeight/2, 1));
        } catch (Exception e) {
            gameBoardGroup.getChildren().add(ShapeConstructor.newRectangle(Color.BLACK, screenWidth*1.5, screenHeight*1.5, screenWidth/2, screenHeight/2));
        }

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
                    gameBoardGroup.getChildren().add(ShapeConstructor.newHexagonStroke(Color.WHITE, hexSize, x,y));

                    String[] objectsToDisplay = gameBoard[i][j].split("/");
                    for (String object : objectsToDisplay) {
                        displayObject(object.split("-"), gameBoardGroup, hexWidth,hexSize, x,y, displayAstInfos);
                    }
                }

                x += hexWidth;
            }
            x = i % 2 == 0 ? initX + hexWidth/2 : initX;
            y += hexSize * 1.5;
        }

        gameBoardGroup.getChildren().add(ShapeConstructor.newRectangle(Color.web("A9A9A9",0.6), screenWidth*0.2,screenHeight, screenWidth*0.9,screenHeight/2));
    }

    private void displayObject(String[] objectInformations, Group group, double hexWidth,double hexSize, double x,double y, boolean displayAstInfos) throws Exception {
        Group astInfoGroup = new Group();
        group.getChildren().add(astInfoGroup);
        astInfoGroup.setViewOrder(-1);
        astInfoGroup.setVisible(displayAstInfos);
        
        String objectType = objectInformations[0];
        switch (objectType) {
            case "asteroyd":
                String asteroydColor = objectInformations[1],
                       asteroydPriority = objectInformations[3];
                int asteroydOrientation = Integer.parseInt(objectInformations[2]);
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

                displayAstInfos(astInfoGroup, asteroydOrientation, asteroydPriority, hexWidth,hexSize, x,y);
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
                       portalPriority = objectInformations[3],
                       relic = objectInformations[4];
                int portalOrientation = Integer.parseInt(objectInformations[2]);
                try {
                    group.getChildren().add(ShapeConstructor.newImage("relic" + relic, hexWidth/2,hexSize/2, x,y));
                } catch (Exception e) {
                    group.getChildren().add(ShapeConstructor.newText(relic, Color.CHARTREUSE, hexWidth/2,hexWidth/2, x,y));
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

                displayAstInfos(astInfoGroup, portalOrientation, portalPriority, hexWidth,hexSize, x,y);
                break;

            case " ":
                break;

                default:
                    throw new Exception(/*TODO*/);
        }
    }

    private void displayAstInfos(Group group, double orientation, String priority, double hexWidth,double hexSize, double x,double y) {
        Text priorityText = ShapeConstructor.newText(priority, Color.WHITE, hexWidth/4,hexWidth, x,y);
        Rectangle priorityRect = ShapeConstructor.newRectangle(Color.BLACK, hexWidth/4,priorityText.getBoundsInLocal().getHeight()*0.8, x, y);
        group.getChildren().addAll(priorityRect, priorityText);

        for(int i = 0 ; i < 6 ; i++) {
            double centerX = x + hexSize*2/3 * Math.cos((60 * (orientation +i -2)) * ShapeConstructor.TORAD),
                   centerY = y + hexSize*2/3 * Math.sin((60 * (orientation +i -2)) * ShapeConstructor.TORAD);
            String direction = i == 0 ? "1" :
                                   i == 1 ? "5" :
                                       i == 2 ? "3" :
                                           i == 3 ? "6" :
                                               i == 4 ? "2" : "4";
            Text directionText = ShapeConstructor.newText(direction, Color.WHITE, hexSize/4,hexWidth, centerX,centerY);
            Rectangle directionRect = ShapeConstructor.newRectangle(Color.BLACK, hexSize/4,directionText.getBoundsInLocal().getHeight()*0.8, centerX, centerY);
            group.getChildren().addAll(directionRect, directionText);
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
