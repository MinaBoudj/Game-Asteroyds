package controller;

/**
 * @author : Matéo
 */

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;

import Modele.*;
import view.View;

public class Controller extends Application {
    private View view;
    private Cell[][] gameBoard;
    private Asteroyd[] asteroyds;
    private Player[] players;
    private int difficulty;

    @Override
    public void start(Stage stage) throws Exception {
        //view = new Mainscene(stage);
        view = new View(stage);
        constructGameBoard(view.readTextFile("res\\gameboard.txt"));
        view.displayGameBoard(gameBoardToString(gameBoard));
    }

    private void constructGameBoard(ArrayList<String> text) throws Exception {
        String[] size = text.get(0).split("x");
        int width = Integer.parseInt(size[0]),
            height = Integer.parseInt(size[1]),
            asteroydsRemaining = Integer.parseInt(text.get(1));
        gameBoard = new Cell[height][width];
        asteroyds = new Asteroyd[asteroydsRemaining];
        

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
                            int pOrientation = (int)(Math.random() * 6 + 1);

                            switch(content[2]) {
                                case "red":
                                    portal = new RedPortal(pOrientation, columnCursor,lineCursor, relic);
                                    break;

                                case "white":
                                    portal = new WhitePortal(pOrientation, columnCursor,lineCursor, relic);
                                    break;

                                default:
                                    throw new Exception();
                            };

                            gameBoard[lineCursor][columnCursor] = portal;
                            asteroyds[asteroydsRemaining-1] = portal;
                            asteroydsRemaining--;
                            break;

                        case "asteroyd":
                            Asteroyd ast;
                            int aOrientation = (int)(Math.random() * 6 + 1);

                            switch(content[2]) {
                                case "red":
                                    ast = new RedAsteroyd(aOrientation, columnCursor,lineCursor);
                                    break;

                                case "blue":
                                    ast = new BlueAsteroyd(aOrientation, columnCursor,lineCursor);
                                    break;
                            
                                case "white":
                                    ast = new WhiteAsteroyd(aOrientation, columnCursor,lineCursor);
                                    break;
                            
                                case "white_red":
                                    ast = new WhiteRedAsteroyd(aOrientation, columnCursor,lineCursor);
                                    break;
                            
                                case "white_blue":
                                    ast = new WhiteBlueAsteroyd(aOrientation, columnCursor,lineCursor);
                                    break;

                                default:
                                    throw new Exception();
                            }

                            gameBoard[lineCursor][columnCursor] = ast;
                            asteroyds[asteroydsRemaining-1] = ast;
                            asteroydsRemaining--;
                            break;

                        case "launchpad":
                            gameBoard[lineCursor][columnCursor] = new LaunchPad(columnCursor,lineCursor);
                            break;

                        case "audience_pod":
                            gameBoard[lineCursor][columnCursor] = new AudiencePod(columnCursor,lineCursor);
                            break;

                        default:
                            throw new Exception();
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