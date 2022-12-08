package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import Modele.WhiteRedAsteroyd;
import Modele.Cell;
import Modele.EmptyCell;
import Modele.LaunchPad;

public class WhiteRedAsteroydTest {
    private Cell[][] gm;
    private WhiteRedAsteroyd wrAst;

    @Before
    public void constructGameBoard() throws Exception {
        wrAst = new WhiteRedAsteroyd(6, 0,2, 1);
        gm = new Cell[][]{{new EmptyCell(0,0), new EmptyCell(1,0), null}, {new EmptyCell(0,1), null, new EmptyCell(2,1)}, {wrAst, new EmptyCell(1,2), new LaunchPad(2,2)}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new WhiteRedAsteroyd(0, 2, 4, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new WhiteRedAsteroyd(-3, 1, 6, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new WhiteRedAsteroyd(7, 8, 0, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new WhiteRedAsteroyd(2, -3, 4, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new WhiteRedAsteroyd(2, 7, -12, 1);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new WhiteRedAsteroyd(4, null, 1);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new WhiteRedAsteroyd(3, 7, 14, 1);
    }

    @Test(expected = Exception.class)
    public void testMoveWhileNotInGameBoard() throws Exception {
        gm[2][0] = new EmptyCell(1,1);
        wrAst.move(gm, new int[]{1,2,3});
    }

    @Test(expected = Exception.class)
    public void testMoveWithNotEnoughtDirections() throws Exception {
        wrAst.move(gm, new int[]{2});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadWhiteDirection() throws Exception {
        wrAst.move(gm, new int[]{2, 8, 2});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadRedDirection() throws Exception {
        wrAst.move(gm, new int[]{-2, 3, 2});
    }

    @Test
    public void testMoveToEmptyCell() throws Exception {
        wrAst.move(gm, new int[]{5,4,1});
        assertTrue(gm[0][0] == wrAst && gm[2][0] instanceof EmptyCell && gm[0][1] instanceof EmptyCell);
    }

    @Test
    public void testMoveToFullCell() throws Exception {
        wrAst.move(gm, new int[]{3,3,5});
        assertTrue(gm[2][1] == wrAst && gm[2][0] instanceof EmptyCell && gm[2][2] instanceof LaunchPad);
    }

    @Test
    public void testMoveWhiteToNullCell() throws Exception {
        wrAst.move(gm, new int[]{5,3,5});
        assertTrue(gm[0][1] == wrAst && gm[2][0] instanceof EmptyCell && gm[1][0] instanceof EmptyCell && gm[1][1] == null);
    }

    @Test
    public void testMoveOutsideGameBoard() throws Exception {
        wrAst.move(gm, new int[]{1,4,1});
        assertTrue(gm[2][0] == wrAst);
    }
}
