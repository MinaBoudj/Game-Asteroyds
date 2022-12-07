package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertFalse;

import Modele.Asteroyd;
import Modele.Position;

// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANCER LA CLASSE DE TESTS /!\
public class AsteroydTest {
    private Asteroyd ast;

    @Before
    public void constructAsteroyd() throws Exception {
        ast = new Asteroyd(4, new Position(13, 12), 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new Asteroyd(0, 2, 4, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new Asteroyd(-3, 1, 6, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new Asteroyd(7, 8, 0, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new Asteroyd(2, -3, 4, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new Asteroyd(2, 7, -12, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new Asteroyd(4, null, 1);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new Asteroyd(3, 7, 14, 1);
    }

    @Test(expected = Exception.class)
    public void testCalculeOrientationWithNullDirection() throws Exception {
        ast.calculeOrientation(0);
    }

    @Test(expected = Exception.class)
    public void testCalculeOrientationWithNegativeDirection() throws Exception {
        ast.calculeOrientation(-2);
    }

    @Test(expected = Exception.class)
    public void testCalculeOrientationWithTooBigDirection() throws Exception {
        ast.calculeOrientation(8);
    }

    @Test
    public void testAsteroydCanNotContainSpaceShipByDefault() throws Exception {
        assertFalse(ast.getCanContainSpaceShips());
    }
}
// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANCER LA CLASSE DE TESTS /!\