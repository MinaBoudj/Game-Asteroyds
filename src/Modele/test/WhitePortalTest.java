package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

import Modele.WhitePortal;
import Modele.Cell;
import Modele.EmptyCell;
import Modele.LaunchPad;
import Modele.SpaceShip;
import Modele.Color;

public class WhitePortalTest {
    private Cell[][] gm;
    private WhitePortal wPort1,
                        wPort2;
    private SpaceShip ship;

    @Before
    public void constructGameBoard() throws Exception {
        wPort1 = new WhitePortal(2, 1,1, 1, 1);
        wPort2 = new WhitePortal(2, 0,2, 2, 2);
        ship = new SpaceShip(1, 1,2, Color.Green);
        gm = new Cell[][]{{null, null, new EmptyCell(2,0)}, {new LaunchPad(0,1), wPort1, null}, {wPort2, null, null}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new WhitePortal(0, 2, 4, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new WhitePortal(-3, 1, 6, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new WhitePortal(7, 8, 0, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new WhitePortal(2, -3,4, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new WhitePortal(2, 7, -12, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new WhitePortal(4, null, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeRelic() throws Exception {
        new WhitePortal(2, 3,4, -3, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigRelic() throws Exception {
        new WhitePortal(2, 7,2, 6, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullRelic() throws Exception {
        new WhitePortal(4, 6,7, 0, 1);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new WhitePortal(3, 7, 14, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testMoveWhileNotInGameBoard() throws Exception {
        gm[1][1] = new EmptyCell(1,1);
        wPort1.move(gm, new int[]{1,2,3});
    }

    @Test(expected = Exception.class)
    public void testMoveWithNotEnoughtDirections() throws Exception {
        wPort1.move(gm, new int[]{2});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadWhiteDirection() throws Exception {
        wPort1.move(gm, new int[]{2, 8, 2});
    }

    @Test
    public void testMoveToEmptyCell() throws Exception {
        wPort1.move(gm, new int[]{5,4,4});
        assertTrue(gm[0][2] == wPort1 && gm[1][1] instanceof EmptyCell);
    }

    @Test
    public void testMoveToFullCell() throws Exception {
        wPort1.move(gm, new int[]{2,6,1});
        assertTrue(gm[1][1] == wPort1 && gm[1][0] instanceof LaunchPad);
    }

    @Test
    public void testMoveToNullCell() throws Exception {
        wPort1.move(gm, new int[]{2,5,1});
        assertTrue(gm[1][1] == wPort1 && gm[0][1] == null);
    }

    @Test
    public void testMoveOutsideGameBoard() throws Exception {
        wPort2.move(gm, new int[]{2,3,1});
        assertTrue(gm[2][0] == wPort2);
    }

    @Test(expected = Exception.class)
    public void testAddLSpaceShipWithNullSpaceShip() throws Exception {
        wPort1.addLSpaceShip(null);
    }

    @Test
    public void testAddLSpaceShipWhenAlreadyHasRelic() throws Exception {
        ship.addRelic(wPort1.getRelic());
        wPort1.addLSpaceShip(ship);
        assertTrue(Arrays.equals(ship.getRelics(), new int[]{wPort1.getRelic(),0,0,0}));
    }

    @Test
    public void testAddLSpaceShip() throws Exception {
        wPort1.addLSpaceShip(ship);
        assertTrue(ship.hasRelic(wPort1.getRelic()));
    }
}
