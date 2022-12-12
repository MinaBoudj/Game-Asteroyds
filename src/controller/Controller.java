package controller;

/**
 * @author : Matéo
 */

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

import Modele.*;
import view.*;

public class Controller extends Application {
    private View view;
    private Cell[][] gameBoard;
    private Asteroyd[] asteroyds;
    private int difficulty;
    private int[] turnDirections;
    private Player[] players;
    private String[] launchpadPositions;
    private ArrayList<String> colorChoices;

    @Override
    public void start(Stage stage) throws Exception {
        view = new View(stage, (String[] args) -> {startGame(args);});
    }

    private void startGame(String[] gameInfos) {
        players = new Player[Integer.parseInt(gameInfos[0])];

        switch(gameInfos[1]) {
            case "Amateur - 50s":
                difficulty = 50;
                break;

            case "Co-Pilot - 40s":
                difficulty = 40;
                break;

            case "Captain - 30s":
                difficulty = 30;
                break;

            case "Flight champion - 20s":
                difficulty = 20;
                break;
        }

        try {
            constructGameBoard(view.readTextFile("res/gameboards/" + gameInfos[2] + ".txt"));
        } catch (Exception e) {view.displayErrorMessage(e.getMessage());}

        colorChoices = new ArrayList<String>();
        colorChoices.add("Green"); colorChoices.add("Blue"); colorChoices.add("Red");
        colorChoices.add("Yellow"); colorChoices.add("Orange"); colorChoices.add("Purple");

        view.displayOptionsScene(gameBoardToString(gameBoard), colorChoices, 0, launchpadPositions, args -> {savePlayerInfos(args, 0);});
    }

    public void savePlayerInfos(String[] playerInfos, int playerIndex) {
        String name = playerInfos[0],
               launchpad = playerInfos[2];
        int l = Integer.parseInt(launchpadPositions[(Integer.parseInt(launchpad) -1) * 2]),
            c = Integer.parseInt(launchpadPositions[(Integer.parseInt(launchpad) -1) * 2 +1]),
            orientation = Integer.parseInt(playerInfos[3]);
        Color color = null;

        colorChoices.remove(playerInfos[1]);

        switch(playerInfos[1]) {
            case "Green":
                color = Color.Green;
                break;

            case "Red":
                color = Color.Red;
                break;

            case "Blue":
                color = Color.Blue;
                break;

            case "Yellow":
                color = Color.Yellow;
                break;

            case "Orange":
                color = Color.Orange;
                break;

            case "Purple":
                color = Color.Purple;
                break;

            default:
                view.displayErrorMessage("Unknown color of space ship : " + playerInfos[1]);
                return;
        }
        try {
            players[playerIndex] = new Player(name, color, orientation, c,l);

            if(playerIndex + 1 == players.length) {
                for(Player p : players) {
                    SpaceShip sp = p.getSpaceShip();
                    gameBoard[sp.getPosition().getY()][sp.getPosition().getX()].addLSpaceShip(sp);
                    view.displayMainScene(gameBoardToString(gameBoard), playersToString(players), ev -> {newTurn();});
                }
            } else
                view.displayOptionsScene(gameBoardToString(gameBoard), colorChoices, playerIndex+1, launchpadPositions, args -> {savePlayerInfos(args, playerIndex+1);});
        } catch(Exception e) {view.displayErrorMessage(e.getMessage());}
    }

    public void savePlayerMovements(Player player, String[] movements) {
        for(int i = 0 ; i < movements.length ; i++) {
            if(movements[i] == null)
                player.setMovement(i, null);
            else
                switch(movements[i]) {
                    case "forward":
                        player.setMovement(i, Movement.Forward);
                        break;

                    case "right":
                        player.setMovement(i, Movement.Right);
                        break;
                        
                    case "left":
                        player.setMovement(i, Movement.Left);
                        break;
                        
                    case "turn_around":
                        player.setMovement(i, Movement.TurnAround);
                        break;
                        
                    default:
                    view.displayErrorMessage("Unknown movement : " + movements[i]);
                    return;
                }
        }
    }

    public String[] playersToString(Player[] players) {
        String[] playerStrings = new String[players.length];
        for(int i = 0 ; i < players.length ; i++)
            playerStrings[i] = players[i].toString();
        return playerStrings;
    }

    public void newTurn() {
        turnDirections = new int[]{rollDice(),rollDice(),rollDice()};
        newPlayerTurn(0);
    }

    public void newPlayerTurn(int nextPlayerIndex) {
        while(nextPlayerIndex < players.length && players[nextPlayerIndex].getSpaceShip().getStructurePoints() <= 0) {
            nextPlayerIndex++;
        }
        final int i = nextPlayerIndex;

        if(i < players.length) {
            Sendable newPlayerTurn = args -> {
                    savePlayerMovements(players[i], args);
                    newPlayerTurn(i + 1);
                };

            try {
                view.displayMainScene(gameBoardToString(gameBoard), playersToString(players), players[i].toString(), difficulty, turnDirections, newPlayerTurn);
            } catch(Exception e) {view.displayErrorMessage(e.getMessage());}
        } else
            try {
                endTurn();
            } catch(Exception e) {view.displayErrorMessage(e.getMessage());}
    }

    public void endTurn() throws Exception {
        for(Asteroyd ast : asteroyds)
            ast.move(gameBoard, turnDirections);

        for(Player p : players) {
            if(p.hasSpaceShipInCondition())
                p.move(gameBoard);
            if(!p.hasSpaceShipInCondition())
                gameBoard[p.getSpaceShip().getPosition().getX()][p.getSpaceShip().getPosition().getY()].removeLSpaceShip(p.getSpaceShip());
        }

        if(isGameOver())
            view.displayEndScene(playersToString(players));
        else
            try {
                view.displayMainScene(gameBoardToString(gameBoard), playersToString(players), ev -> {newTurn();});
            } catch (Exception e) {view.displayErrorMessage(e.getMessage());}
    }

    private int rollDice() {
        return (int)(Math.random() * 6 + 1);
    }

    private void constructGameBoard(ArrayList<String> text) throws Exception {
        String[] size = text.get(0).split("x");
        int width = Integer.parseInt(size[0]),
            height = Integer.parseInt(size[1]),
            nbAsteroyds = Integer.parseInt(text.get(1)),
            nbLaunchpads = Integer.parseInt(text.get(2));
        gameBoard = new Cell[height][width];
        asteroyds = new Asteroyd[nbAsteroyds];
        launchpadPositions = new String[nbLaunchpads*2];
        
        ArrayList<Integer> astPriorities = new ArrayList<Integer>();
        for(int i = 0 ; i < nbAsteroyds ; i++)
            astPriorities.add(i+1);

        int textCursor = 4,
            lineCursor = 0,
            columnCursor = 0;

        while(textCursor < text.size()) {
            String[] content = text.get(textCursor).split("-");

            if(content.length == 0) {
                if(columnCursor != width) {
                    view.displayErrorMessage("Not enought object in line : " + lineCursor + " (has " + columnCursor + ", expected " + width + ")");
                    return;
                }
                lineCursor++;
                columnCursor = 0;
            } else {
                for(int i = 0 ; i < Integer.parseInt(content[0]) ; i++) {
                    switch(content[1]) {
                        case "null":
                            gameBoard[lineCursor][columnCursor] = null;
                            break;

                        case "empty":
                            gameBoard[lineCursor][columnCursor] = new EmptyCell(columnCursor,lineCursor);
                            break;

                        case "portal":
                            Asteroyd portal = null;
                            int relic = Integer.parseInt(content[3]),
                                pOrientation = rollDice(),
                                portalPriority = astPriorities.get((int)(Math.random() * astPriorities.size()));
                            astPriorities.remove(Integer.valueOf(portalPriority));

                            switch(content[2]) {
                                case "red":
                                    portal = new RedPortal(pOrientation, columnCursor,lineCursor, relic, portalPriority);
                                    break;

                                case "white":
                                    portal = new WhitePortal(pOrientation, columnCursor,lineCursor, relic, portalPriority);
                                    break;

                                default:
                                view.displayErrorMessage("Unknown portal color : " + content[2]);
                                return;
                            };

                            gameBoard[lineCursor][columnCursor] = portal;
                            asteroyds[portalPriority-1] = portal;
                            break;

                        case "asteroyd":
                            Asteroyd ast;
                            int aOrientation = rollDice(),
                                astPriority = astPriorities.get((int)(Math.random() * astPriorities.size()));
                            astPriorities.remove(Integer.valueOf(astPriority));

                            switch(content[2]) {
                                case "red":
                                    ast = new RedAsteroyd(aOrientation, columnCursor,lineCursor, astPriority);
                                    break;

                                case "blue":
                                    ast = new BlueAsteroyd(aOrientation, columnCursor,lineCursor, astPriority);
                                    break;
                            
                                case "white":
                                    ast = new WhiteAsteroyd(aOrientation, columnCursor,lineCursor, astPriority);
                                    break;
                            
                                case "white_red":
                                    ast = new WhiteRedAsteroyd(aOrientation, columnCursor,lineCursor, astPriority);
                                    break;
                            
                                case "white_blue":
                                    ast = new WhiteBlueAsteroyd(aOrientation, columnCursor,lineCursor, astPriority);
                                    break;

                                default:
                                    view.displayErrorMessage("Unknown asteroyd color : " + content[2]);
                                    return;
                            }

                            gameBoard[lineCursor][columnCursor] = ast;
                            asteroyds[astPriority-1] = ast;
                            break;

                        case "launchpad":
                            gameBoard[lineCursor][columnCursor] = new LaunchPad(columnCursor,lineCursor);
                            nbLaunchpads--;
                            launchpadPositions[nbLaunchpads*2] = Integer.toString(lineCursor);
                            launchpadPositions[nbLaunchpads*2+1] = Integer.toString(columnCursor);
                            break;

                        case "audience_pod":
                            gameBoard[lineCursor][columnCursor] = new AudiencePod(columnCursor,lineCursor);
                            break;

                        default:
                            view.displayErrorMessage("Unknown object type : " + content[1]);
                            return;
                    }

                    columnCursor++;
                }
            }

            textCursor++;
        }

        if(lineCursor >= height) {
            view.displayErrorMessage("Not enought lines : (has " + lineCursor + ", expected " + height + ")");
            return;
        }
        if(astPriorities.size() != 0) {
            view.displayErrorMessage("Not enought asteroyds : (has " + (asteroyds.length - astPriorities.size()) + ", expected " + asteroyds.length + ")");
            return;
        }
        if(nbLaunchpads != 0) {
            view.displayErrorMessage("Not enought launchpads : (has " + (launchpadPositions.length/2 - nbLaunchpads) + ", expected " + launchpadPositions.length/2 + ")");
            return;
        }
    }

    public boolean isGameOver() {
        int spaceShipsRemaining = 0;

        for(Player p : players) {
            if(p.hasSpaceShipInCondition()) {
                if(p.getNumberOfRelics() == 4)
                    return true;
                spaceShipsRemaining++;
            }
        }

        return spaceShipsRemaining < 2;
    }

    public String[][] gameBoardToString(Cell[][] gameBoard) {
        String[][] stringGameBoard = new String[gameBoard.length][gameBoard[0].length];

        for(int i = 0 ; i < gameBoard.length ; i++) {
            for(int j = 0 ; j < gameBoard[0].length ; j++) {
                stringGameBoard[i][j] = gameBoard[i][j] == null ? "" : gameBoard[i][j].toString();
            }
        }

        return stringGameBoard;
    }
}