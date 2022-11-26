package Modele.test;

/**
 * @author Matéo
 */

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

import Modele.LaunchPad;
import Modele.SpaceShip;
import Modele.Position;
import Modele.Color;

// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANER LA CALSSE DE TESTS /!\
public class LaunchPadTest {
    private LaunchPad lPad;

    @Before
    public void constructLaunchPad() {
        lPad = new LaunchPad(5 ,3);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() {
        new LaunchPad(-3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() {
        new LaunchPad(7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() {
        new LaunchPad(null);
    }

    @Test
    public void testConstructorWithGoodArguments() {
        new LaunchPad(7, 14);
    }

    @Test
    public void testListOfSpaceShipsIsEmptyByDefault() {
        assertTrue(lPad.getLSpaceShips().size() == 0);
    }

    @Test(expected = Exception.class)
    public void testAddLSpaceShipWithNullSpaceShip() {
        lPad.addLSpaceShip(null);
    }

    @Test
    public void testRemoveSpaceShipNotInside() {
        lPad.addLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        lPad.removeLSpaceShip(new SpaceShip(3, new Position(2, 8), Color.Blue));
        assertTrue(lPad.getLSpaceShips().size() == 1);
    }

    @Test
    public void testRemoveSpaceShip() {
        lPad.addLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        lPad.removeLSpaceShip(new SpaceShip(6, new Position(7, 4), Color.Green));
        assertTrue(lPad.getLSpaceShips().size() == 0);
    }

    @Test
    public void testCanContainSpaceShipsIsTrueByDefault() {
        assertTrue(lPad.getCanContainSpaceShips());
    }
}
// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANER LA CALSSE DE TESTS /!\ {
    
}
