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
    private Player[] players;
    private int difficulty;
    private int[] turnDirections;

    @Override
    public void start(Stage stage) throws Exception {
        view = new View(stage, (String[] args) -> {start(args);});
    }

    private void start(String[] gameInfos) {
        //players = new Player[Integer.parseInt(gameInfos[0])];
        try{
            players = new Player[]{new Player("Bobby", Color.Orange, 3, 1, 2), new Player("Billie", Color.Blue, 6, 4, 3)};
            players[0].getSpaceShip().minusStructurePoint(6);
        }
        catch (Exception e) {/*TODO*/}

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
                difficulty = 2;
                break;
        }

        try {
            constructGameBoard(view.readTextFile("res\\gameboards\\" + gameInfos[2] + ".txt"));
        } catch (Exception e) {/*TODO*/}

        try {
            view.displayMainScene(gameBoardToString(gameBoard), playersToString(players), (ev) -> {newTurn();});
        } catch (Exception e) {/*TODO*/}
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
            try {
                view.displayMainScene(gameBoardToString(gameBoard), playersToString(players), players[i].toString(), difficulty, (ev) -> {newPlayerTurn(i + 1);});
            } catch(Exception e) {/*TODO*/}
        }

        endTurn();
    }

    public void endTurn() {
        //TODO
    }

    private int rollDice() {
        return (int)(Math.random() * 6 + 1);
    }

    private void constructGameBoard(ArrayList<String> text) throws Exception {
        String[] size = text.get(0).split("x");
        int width = Integer.parseInt(size[0]),
            height = Integer.parseInt(size[1]),
            remainingAsteroyds = Integer.parseInt(text.get(1));
        gameBoard = new Cell[height][width];
        asteroyds = new Asteroyd[remainingAsteroyds];
        

        int textCursor = 3,
            lineCursor = 0,
            columnCursor = 0;

        while(textCursor < text.size()) {
            String[] content = text.get(textCursor).split("-");

            if(content.length == 0) {
                lineCursor++;
                if(columnCursor != width)
                    throw new Exception();
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
                            int relic = Integer.parseInt(content[3]);
                            int pOrientation = rollDice();

                            switch(content[2]) {
                                case "red":
                                    portal = new RedPortal(pOrientation, columnCursor,lineCursor, relic, remainingAsteroyds);
                                    break;

                                case "white":
                                    portal = new WhitePortal(pOrientation, columnCursor,lineCursor, relic, remainingAsteroyds);
                                    break;

                                default:
                                    throw new Exception(/*TODO*/);
                            };

                            gameBoard[lineCursor][columnCursor] = portal;
                            asteroyds[remainingAsteroyds-1] = portal;
                            remainingAsteroyds--;
                            break;

                        case "asteroyd":
                            Asteroyd ast;
                            int aOrientation = rollDice();

                            switch(content[2]) {
                                case "red":
                                    ast = new RedAsteroyd(aOrientation, columnCursor,lineCursor, remainingAsteroyds);
                                    break;

                                case "blue":
                                    ast = new BlueAsteroyd(aOrientation, columnCursor,lineCursor, remainingAsteroyds);
                                    break;
                            
                                case "white":
                                    ast = new WhiteAsteroyd(aOrientation, columnCursor,lineCursor, remainingAsteroyds);
                                    break;
                            
                                case "white_red":
                                    ast = new WhiteRedAsteroyd(aOrientation, columnCursor,lineCursor, remainingAsteroyds);
                                    break;
                            
                                case "white_blue":
                                    ast = new WhiteBlueAsteroyd(aOrientation, columnCursor,lineCursor, remainingAsteroyds);
                                    break;

                                default:
                                    throw new Exception(/*TODO*/);
                            }

                            gameBoard[lineCursor][columnCursor] = ast;
                            asteroyds[remainingAsteroyds-1] = ast;
                            remainingAsteroyds--;
                            break;

                        case "launchpad":
                            gameBoard[lineCursor][columnCursor] = new LaunchPad(columnCursor,lineCursor);
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

    public boolean isGameOver() {
        for(Player p : players) {
            if(p.getNumberOfRelics() == 4)
                return true;
        }

        return false;
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