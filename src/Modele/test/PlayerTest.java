package Modele.test;

/**
 * @author Amina
 */

import org.junit.Test;
import org.junit.Before;

import Modele.Player;
import Modele.SpaceShip;
import Modele.Color;
import Modele.Movement;

public class PlayerTest {
    private Player player;

    @Before
    public void constructPlayer() throws Exception {
        player = new Player("amina",Color.Red,3, new Position(1,3));
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new Player("amina",Color.Red,0, new Position(1,3));
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new Player("amina",Color.Red,-1, new Position(1,3));
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new Player("amina",Color.Red,18, new Position(1,3));
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new Player("amina",Color.Red,3, new Position(-1,3));
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new Player("amina",Color.Red,3, new Position(1,-3));
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new Player("amina",Color.Red,3, null);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new Player("amina",Color.Red,3, new Position(1,3));
    }

    @Test(expected = Exception.class)
    public void testSetMovementsIndiceTabNegative() throws Exception {
        player.setMovement(-1,Movement.Left);
    }

    @Test(expected = Exception.class)
    public void testSetMovementsIndiceTabBig() throws Exception {
        player.setMovement(7,Movement.Left);
    }
}
