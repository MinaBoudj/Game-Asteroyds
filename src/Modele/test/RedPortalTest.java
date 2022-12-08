package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import Modele.RedPortal;
import Modele.Cell;
import Modele.EmptyCell;
import Modele.LaunchPad;
import Modele.SpaceShip;
import Modele.Color;

public class RedPortalTest {
    private Cell[][] gm;
    private RedPortal rPort;
    private SpaceShip ship;

    @Before
    public void constructGameBoard() throws Exception {
        rPort = new RedPortal(4, 1,2, 3, 1);
        ship = new SpaceShip(3, 0,1, Color.Blue);
        gm = new Cell[][]{{new LaunchPad(0,0), null, new EmptyCell(2,0)}, {new EmptyCell(0,1), new EmptyCell(1,1), null}, {new LaunchPad(0,2), rPort, null}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new RedPortal(0, 2,4, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new RedPortal(-3, 1,6, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new RedPortal(7, 8,0, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new RedPortal(2, -3,4, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new RedPortal(2, 7,-12, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new RedPortal(4, null, 2, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeRelic() throws Exception {
        new RedPortal(2, 3,4, -3, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigRelic() throws Exception {
        new RedPortal(2, 7,2, 6, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullRelic() throws Exception {
        new RedPortal(4, 6,7, 0, 1);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new RedPortal(3, 7,14, 1, 1);
    }

    @Test(expected = Exception.class)
    public void testMoveWhileNotInGameBoard() throws Exception {
        gm[2][1] = new EmptyCell(1,2);
        rPort.move(gm, new int[]{1,2,3});
    }

    @Test(expected = Exception.class)
    public void testMoveWithNotEnoughtDirections() throws Exception {
        rPort.move(gm, new int[]{});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadRedDirection() throws Exception {
        rPort.move(gm, new int[]{0, 4, 2});
    }

    @Test
    public void testMoveTwoCellsToEmptyCell() throws Exception {
        rPort.move(gm, new int[]{6,4,4});
        assertTrue(gm[0][2] == rPort && gm[1][1] instanceof EmptyCell && gm[2][1] instanceof EmptyCell);
    }

    @Test
    public void testMoveOneCellToEmptyCell() throws Exception {
        rPort.move(gm, new int[]{3,4,4});
        assertTrue(gm[1][0] == rPort  && gm[0][0] instanceof LaunchPad);
    }

    @Test
    public void testMoveToFullCell() throws Exception {
        rPort.move(gm, new int[]{5,6,1});
        assertTrue(gm[2][1] == rPort && gm[2][0] instanceof LaunchPad);
    }

    @Test
    public void testMoveToNullCell() throws Exception {
        rPort.move(gm, new int[]{2,5,1});
        assertTrue(gm[2][1] == rPort && gm[2][2] == null);
    }

    @Test
    public void testMoveOutsideGameBoard() throws Exception {
        rPort.move(gm, new int[]{1,3,1});
        assertTrue(gm[2][1] == rPort);
    }

    @Test(expected = Exception.class)
    public void testAddLSpaceShipWithNullSpaceShip() throws Exception {
        rPort.addLSpaceShip(null);
    }

    @Test
    public void testAddLSpaceShipWhenAlreadyHasRelic() throws Exception {
        ship.addRelic(rPort.getRelic());
        rPort.addLSpaceShip(ship);
        assertTrue(Arrays.equals(ship.getRelics(), new int[]{rPort.getRelic(),0,0,0}));
    }

    @Test
    public void testAddLSpaceShip() throws Exception {
        rPort.addLSpaceShip(ship);
        assertTrue(ship.hasRelic(rPort.getRelic()));
    }
}
