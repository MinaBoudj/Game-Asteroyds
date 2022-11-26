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

// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANER LA CALSSE DE TESTS /!\
public class EmptyCellTest {
    private EmptyCell eCell;

    @Before
    public void constructEmptyCell() {
        eCell = new EmptyCell(5 ,3);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() {
        new EmptyCell(-3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() {
        new EmptyCell(7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() {
        new EmptyCell(null);
    }

    @Test
    public void testConstructorWithGoodArguments() {
        new EmptyCell(7, 14);
    }

    @Test
    public void testListOfSpaceShipsIsEmptyByDefault() {
        assertTrue(eCell.getLSpaceShips().size() == 0);
    }

    @Test(expected = Exception.class)
    public void testAddLSpaceShipWithNullSpaceShip() {
        eCell.addLSpaceShip(null);
    }

    @Test
    public void testRemoveSpaceShipNotInside() {
        eCell.addLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        eCell.removeLSpaceShip(new SpaceShip(3, new Position(2, 8), Color.Blue));
        assertTrue(eCell.getLSpaceShips().size() == 1);
    }

    @Test
    public void testRemoveSpaceShip() {
        eCell.addLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        eCell.removeLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        assertTrue(eCell.getLSpaceShips().size() == 0);
    }

    @Test
    public void testCanContainSpaceShipsIsTrueByDefault() {
        assertTrue(eCell.getCanContainSpaceShips());
    }
}
// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANER LA CALSSE DE TESTS /!\ {
    
}
