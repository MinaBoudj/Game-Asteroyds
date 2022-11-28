package Modele.test;

/**
 * @author Matéo
 */

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

import Modele.EmptyCell;
import Modele.SpaceShip;
import Modele.Position;
import Modele.Color;

public class EmptyCellTest {
    private EmptyCell eCell;

    @Before
    public void constructEmptyCell() throws Exception {
        eCell = new EmptyCell(5 ,3);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new EmptyCell(-3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new EmptyCell(7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new EmptyCell(null);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new EmptyCell(7, 14);
    }

    @Test
    public void testListOfSpaceShipsIsEmptyByDefault() throws Exception {
        assertTrue(eCell.getLSpaceShips().size() == 0);
    }

    @Test(expected = Exception.class)
    public void testAddLSpaceShipWithNullSpaceShip() throws Exception {
        eCell.addLSpaceShip(null);
    }

    @Test
    public void testRemoveSpaceShipNotInside() throws Exception {
        eCell.addLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        eCell.removeLSpaceShip(new SpaceShip(3, new Position(2, 8), Color.Blue));
        assertTrue(eCell.getLSpaceShips().size() == 1);
    }

    @Test
    public void testRemoveSpaceShip() throws Exception {
        eCell.addLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        eCell.removeLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        assertTrue(eCell.getLSpaceShips().size() == 0);
    }

    @Test
    public void testCanContainSpaceShipsIsTrueByDefault() throws Exception {
        assertTrue(eCell.getCanContainSpaceShips());
    }
}
