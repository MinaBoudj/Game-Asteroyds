package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import Modele.RedAsteroyd;
import Modele.Cell;
import Modele.EmptyCell;
import Modele.LaunchPad;

public class RedAsteroydTest {
    private Cell[][] gm;
    private RedAsteroyd rAst;

    @Before
    public void constructGameBoard() throws Exception {
        rAst = new RedAsteroyd(4, 1,2, 1);
        gm = new Cell[][]{{new LaunchPad(0,0), null, new EmptyCell(2,0)}, {null, new EmptyCell(1,1), null}, {new LaunchPad(0,2), rAst, null}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new RedAsteroyd(0, 2, 4, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new RedAsteroyd(-3, 1, 6, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new RedAsteroyd(7, 8, 0, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new RedAsteroyd(2, -3, 4, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new RedAsteroyd(2, 7, -12, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new RedAsteroyd(4, null, 1);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new RedAsteroyd(3, 7, 14, 1);
    }

    @Test(expected = Exception.class)
    public void testMoveWhileNotInGameBoard() throws Exception {
        gm[2][1] = new EmptyCell(1,2);
        rAst.move(gm, new int[]{1,2,3});
    }

    @Test(expected = Exception.class)
    public void testMoveWithNotEnoughtDirections() throws Exception {
        rAst.move(gm, new int[]{});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadRedDirection() throws Exception {
        rAst.move(gm, new int[]{0, 4, 2});
    }

    @Test
    public void testMoveTwoCellsToEmptyCell() throws Exception {
        rAst.move(gm, new int[]{6,4,4});
        assertTrue(gm[0][2] == rAst && gm[1][1] instanceof EmptyCell && gm[2][1] instanceof EmptyCell);
    }

    @Test
    public void testMoveOneCellToEmptyCell() throws Exception {
        rAst.move(gm, new int[]{3,4,1});
        assertTrue(gm[2][1] == rAst && gm[1][1] instanceof EmptyCell && gm[2][0] instanceof LaunchPad);
    }

    @Test
    public void testMoveToFullCell() throws Exception {
        rAst.move(gm, new int[]{5,6,1});
        assertTrue(gm[2][1] == rAst && gm[2][0] instanceof LaunchPad);
    }

    @Test
    public void testMoveToNullCell() throws Exception {
        rAst.move(gm, new int[]{2,5,1});
        assertTrue(gm[2][1] == rAst && gm[2][2] == null);
    }

    @Test
    public void testMoveOutsideGameBoard() throws Exception {
        rAst.move(gm, new int[]{1,3,1});
        assertTrue(gm[2][1] == rAst);
    }
}
