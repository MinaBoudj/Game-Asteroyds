package Modele.test;

/**
 * @author Amina
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import Modele.WhitePortal;
import Modele.SpaceShip;
import Modele.Position;
import Modele.Color;
import Modele.Cell;
import Modele.Movement;

public class WhitePortalTest {
    private WhitePortal portal1;
    private WhitePortal portal2;
    private Cell[][] gameBoard;

    @Before
    public void constructWhitePortal() {
        portal1 = new WhitePortal("white",3,new Position(2,4), 3);      
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() {
        new  WhitePortal("red",0,new Position(2,4), 3);
    }

    @Test
    public void testAddLSpaceShip() {
        SpaceShip spaceShip =  new SpaceShip("Green",4,new Position(2,4), Color.Green);
        portal1.addLSpaceShipt(spaceShip);
        assertTrue(spaceShip.hasRelic(portal1.getRelic()));
    }

    @Test(expected = Exception.class)
    public void testAddLSpaceShipWithNegativeRelic() {
        portal2 = new RedPortal("red",3,new Position(2,4), 0);
        SpaceShip spaceShip =  new SpaceShip("Green",4,new Position(2,4), Color.Green);
        portal1.addLSpaceShipt(spaceShip);
    }

    
}