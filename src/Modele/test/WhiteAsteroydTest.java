package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import Modele.WhiteAsteroyd;
import Modele.Position;
import Modele.Cell;
import Modele.EmptyCell;
import Modele.LaunchPad;

public class WhiteAsteroydTest {
    private Cell[][] gm;
    private WhiteAsteroyd wAst1,
                          wAst2;

    @Before
    public void constructGameBoard() throws Exception {
        wAst1 = new WhiteAsteroyd(2, 1,1);
        wAst2 = new WhiteAsteroyd(2, 0,2);
        gm = new Cell[][]{{null, null, new EmptyCell(2,0)}, {new LaunchPad(0,1), wAst1, null}, {wAst2, null, null}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new WhiteAsteroyd(0, 2, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new WhiteAsteroyd(-3, 1, 6);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new WhiteAsteroyd(7, 8, 0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new WhiteAsteroyd(2, -3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new WhiteAsteroyd(2, 7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new WhiteAsteroyd(4, null);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new WhiteAsteroyd(3, 7, 14);
    }

    @Test(expected = Exception.class)
    public void testMoveWhileNotInGameBoard() throws Exception {
        gm[1][1] = new EmptyCell(1,1);
        wAst1.move(gm, new int[]{1,2,3});
    }

    @Test(expected = Exception.class)
    public void testMoveWithNotEnoughtDirections() throws Exception {
        wAst1.move(gm, new int[]{2});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadWhiteDirection() throws Exception {
        wAst1.move(gm, new int[]{2, 8, 2});
    }

    @Test
    public void testMoveToEmptyCell() throws Exception {
        wAst1.move(gm, new int[]{5,4,4});
        assertTrue(gm[0][2] == wAst1 && gm[1][1] instanceof EmptyCell);
    }

    @Test
    public void testMoveToFullCell() throws Exception {
        wAst1.move(gm, new int[]{2,6,1});
        assertTrue(gm[1][1] == wAst1 && gm[1][0] instanceof LaunchPad);
    }

    @Test
    public void testMoveToNullCell() throws Exception {
        wAst1.move(gm, new int[]{2,5,1});
        assertTrue(gm[1][1] == wAst1 && gm[1][1] == null);
    }

    @Test
    public void testMoveOutsideGameBoard() throws Exception {
        wAst2.move(gm, new int[]{2,3,1});
        assertTrue(gm[2][0] == wAst2);
    }
}
