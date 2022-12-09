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
    private ArrayList<Player> winners;
    private String[] launchpadPositions;

    @Override
    public void start(Stage stage) throws Exception {
        view = new View(stage, (String[] args) -> {start(args);});
        winners = new ArrayList<Player>();
    }

    private void start(String[] gameInfos) {
        players = new Player[Integer.parseInt(gameInfos[0])];

        switch(gameInfos[1]) {
            case "Amateur - 50s":
                difficulty = 10;
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
            constructGameBoard(view.readTextFile("res\\gameboards\\" + gameInfos[2] + ".txt"));
        } catch (Exception e) {/*TODO*/}

        view.displayOptionsScene(gameBoardToString(gameBoard), 0, launchpadPositions, args -> {savePlayerInfo(args);});
    }

    public void savePlayerInfo(String[] playerInfos) {
        String name = playerInfos[0],
               color = playerInfos[1],
               launchpad = playerInfos[2],
               orientation = playerInfos[3];

        System.out.println(name);
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
                        //TODO
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
            } catch(Exception e) {/*TODO*/}
        } else
            try {
                endTurn();
            } catch(Exception e) {/*TODO*/}
    }

    public void endTurn() throws Exception {
        for(Asteroyd ast : asteroyds)
            ast.move(gameBoard, turnDirections);

        for(Player p : players) {
            if(p.hasSpaceShipInCondition())
                p.move(gameBoard);
            if(!p.hasSpaceShipInCondition())
                gameBoard[p.getSpaceShip().getPosition().getX()][p.getSpaceShip().getPosition().getY()].removeLSpaceShip(p.getSpaceShip());
            else if(p.getNumberOfRelics() == 4)
                winners.add(p);
        }

        if(isGameOver()) {}
        else
            try {
                view.displayMainScene(gameBoardToString(gameBoard), playersToString(players), ev -> {newTurn();});
            } catch (Exception e) {/*TODO*/}
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
                lineCursor++;
                if(columnCursor != width)
                    throw new Exception(/*TODO*/);
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
                                    throw new Exception(/*TODO*/);
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
                                    throw new Exception(/*TODO*/);
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
                            throw new Exception(/*TODO*/);
                    }

                    columnCursor++;
                }
            }

            textCursor++;
        }
    }

    public boolean isGameOver() {return winners.size() > 0;}

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