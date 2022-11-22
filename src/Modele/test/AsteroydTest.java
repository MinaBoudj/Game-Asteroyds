package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import Modele.Asteroyd;
import Modele.WhiteAsteroyd;
import Modele.Position;

// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANER LA CALSSE DE TESTS /!\
public class AsteroydTest {
    private Asteroyd ast;

    @Before
    public void constructAsteroyd() {
        ast = new Asteroyd(4, new Position(13, 12));
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() {
        new Asteroyd(0, 2, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() {
        new Asteroyd(-3, 1, 6);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() {
        new Asteroyd(7, 8, 0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() {
        new Asteroyd(2, -3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() {
        new Asteroyd(2, 7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() {
        new Asteroyd(4, null);
    }

    @Test
    public void testConstructorWithGoodArguments() {
        new Asteroyd(3, 7, 14);
    }

    @Test(expected = Exception.class)
    public void testCalculeOrientationWithNullDirection() {
        ast.calculeOrientation(0);
    }

    @Test(expected = Exception.class)
    public void testCalculeOrientationWithNegativeDirection() {
        ast.calculeOrientation(-2);
    }

    @Test(expected = Exception.class)
    public void testCalculeOrientationWithTooBigDirection() {
        ast.calculeOrientation(8);
    }

    @Test
    public testAsteroydCanNotContainSpaceShipByDefault() {
        assertFalse(ast.getCanContainSpaceShips());
    }
}
// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANER LA CALSSE DE TESTS /!\