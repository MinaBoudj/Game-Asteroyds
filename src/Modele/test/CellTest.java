package Modele.test;

/**
 * @author Matéo
 */

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

import Modele.Cell;
import Modele.SpaceShip;
import Modele.Position;
import Modele.Color;

// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANER LA CALSSE DE TESTS /!\
public class CellTest {
    private Cell cell;

    @Before
    public void constructCell() throws Exception {
        cell = new Cell(1, 5 ,3);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new Cell(0, 2, 4, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new Cell(-3, 1, 6, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new Cell(7, 8, 0, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new Cell(2, -3, 4, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new Cell(2, 7, -12, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new Cell(2, null, false);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new Cell(3, 7, 14, false);
    }

    @Test
    public void testListOfSpaceShipsIsEmptyByDefault() throws Exception {
        assertTrue(cell.getLSpaceShips().size() == 0);
    }

    @Test(expected = Exception.class)
    public void testAddLSpaceShipWithNullSpaceShip() throws Exception {
        cell.addLSpaceShip(null);
    }

    @Test
    public void testRemoveSpaceShipNotInside() throws Exception {
        cell.addLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        cell.removeLSpaceShip(new SpaceShip(3, new Position(2, 8), Color.Blue));
        assertTrue(cell.getLSpaceShips().size() == 1);
    }

    @Test
    public void testRemoveSpaceShip() throws Exception {
        cell.addLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        cell.removeLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        assertTrue(cell.getLSpaceShips().size() == 0);
    }
}
// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANER LA CALSSE DE TESTS /!\