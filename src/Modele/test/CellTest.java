package Modele.test;

import org.junit.Test;

import Modele.Cell;
import Modele.EmptyCell;
import Modele.SpaceShip;
import Modele.Position;
import Modele.Color;

public class CellTest {
    private Cell cell;

    @Before
    public void constructCell() {
        cell = new EmptyCell(1, 5 ,3);
    }

    // /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE TESTER LE CONSTRUCTEUR /!\
    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() {
        new Cell(0, 2, 4, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() {
        new Cell(-3, 1, 6, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() {
        new Cell(7, 8, 0, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() {
        new Cell(2, -3, 4, true);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() {
        new Cell(2, 7, -12, true);
    }

    @Test
    public void testConstructorWithGoodArguments() {
        new Cell(3, 7, 14, false);
    }
    // /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE TESTER LE CONSTRUCTEUR /!\

    @Test
    public void testListOfSpaceShipsIsEmptyByDefault() {
        assertTrue(cell.getLSpaceShip().size() == 0);
    }

    @Test(expected = Exception.class)
    public void testAddLSpaceShipWithNullSpaceShip() {
        cell.addLSpaceShip(null);
    }

    @Test
    public void testRemoveSpaceShipNotInside() {
        cell.addLspaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        cell.removeLSpaceShip(new SpaceShip(3, new Position(2, 8), Color.Blue));
        assertTrue(cell.getLSpaceShip().size() == 1);
    }

    @Test
    public void testRemoveSpaceShip() {
        cell.addLspaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        cell.removeLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        assertTrue(cell.getLSpaceShip().size() == 0);
    }
}