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
import Modele.Cell;
import Modele.Movement;


public class SpaceShipTest {
    private SpaceShip spaceShip1;
    private SpaceShip spaceShip2;
    private Cell[][] gameBoard;

    @Before
    public void constructSpaceShip() {
        spaceShip1 = new SpaceShip("Green",4,new Position(4,4), Color.Green);
        spaceShip2 = new SpaceShip("Green",5,new Position(1,4), Color.Green);        
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() {
        new SpaceShip("Green",0,new Position(4,4), Color.Green);
    }

    @Test
    public void testMoveLeft() {
        spaceShip1.moveLeft();
        assertTrue(spaceShip1.getOrientation()==3);
    }

    @Test
    public void testMoveRight() {
        spaceShip1.moveRight();
        assertTrue(spaceShip1.getOrientation()==5);
    }
    
    @Test
    public void testTurnAround() {
        spaceShip1.turnAround();
        assertTrue(spaceShip1.getOrientation()==1);
    }
    
    @Test
    public void testMove() {
        spaceShip1.Move(gameBoard,new Movement[]{Movement.Right,Movement.Forward,Movement.Forward});
        assertTrue(gameBoard[1][4] == spaceShip2);
    }
}