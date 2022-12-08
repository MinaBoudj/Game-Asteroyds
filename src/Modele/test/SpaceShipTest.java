package Modele.test;

/**
 * @author Amina
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import Modele.SpaceShip;
import Modele.Position;
import Modele.Color;
import Modele.EmptyCell;
import Modele.Cell;
import Modele.Movement;


public class SpaceShipTest {
    private SpaceShip spaceShip1;
    private Cell[][] gameBoard;

    @Before
    public void constructSpaceShip() throws Exception {
        spaceShip1 = new SpaceShip(4,new Position(4,4), Color.Green);
        EmptyCell eCell = new EmptyCell(4,4);
        eCell.addLSpaceShip(spaceShip1);   
        gameBoard = new Cell[][]{{null,null,null,null,null},{null,null,null,null,null},{null,null,null,null,null},{null,null,null,null,null},{null,new EmptyCell(1,4), new EmptyCell(2,4), new EmptyCell(3,4), eCell}};  
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception{
        new SpaceShip(0,new Position(4,4), Color.Green);
    }
    
    @Test
    public void testMove() throws Exception {
        spaceShip1.move(gameBoard,new Movement[]{Movement.Right,Movement.Forward,Movement.Forward});
        assertTrue(gameBoard[4][1].getLSpaceShips().contains(spaceShip1) && gameBoard[4][4].getLSpaceShips().size()==0);
    }
}