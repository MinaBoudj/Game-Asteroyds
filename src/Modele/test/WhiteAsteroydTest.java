package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import Modele.WhiteAsteroyd;
import Modele.Position;
import Modele.Cell;

public class WhiteAsteroydTest {
    private Cell[][] gm;
    private WhiteAsteroyd wAst;

    @Before
    public void constructGameBoard() {
        wAst = new WhiteAsteroyd();
        gm = new Cell[][]{{null, null, null}, {null, null, null}, {null, null, null}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() {
        new WhiteAsteroyd(0, 2, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() {
        new WhiteAsteroyd(-3, 1, 6);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() {
        new WhiteAsteroyd(7, 8, 0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() {
        new WhiteAsteroyd(2, -3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() {
        new WhiteAsteroyd(2, 7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() {
        new WhiteAsteroyd(4, null);
    }

    @Test
    public void testConstructorWithGoodArguments() {
        new WhiteAsteroyd(3, 7, 14);
    }

    @Test(expected = Exception.class)
    public void testMoveWithNullGameBoard() {

    }
}
