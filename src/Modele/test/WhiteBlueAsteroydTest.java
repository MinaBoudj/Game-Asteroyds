package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import Modele.WhiteBlueAsteroyd;
import Modele.Position;
import Modele.Cell;
import Modele.EmptyCell;
import Modele.LaunchPad;

public class WhiteBlueAsteroydTest {
    private Cell[][] gm;
    private WhiteBlueAsteroyd wbAst,
                              wbAst2;

    @Before
    public void constructGameBoard() throws Exception {
        wbAst = new WhiteBlueAsteroyd(6, 0,2);
        wbAst2 = new WhiteBlueAsteroyd(3, 1,1);
        gm = new Cell[][]{{new EmptyCell(0,0), null, null}, {new EmptyCell(0,1), wbAst2, new EmptyCell(2,1)}, {wbAst, new LaunchPad(1,2), null}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new WhiteBlueAsteroyd(0, 2, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new WhiteBlueAsteroyd(-3, 1, 6);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new WhiteBlueAsteroyd(7, 8, 0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new WhiteBlueAsteroyd(2, -3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new WhiteBlueAsteroyd(2, 7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new WhiteBlueAsteroyd(4, null);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new WhiteBlueAsteroyd(3, 7, 14);
    }

    @Test(expected = Exception.class)
    public void testMoveWhileNotInGameBoard() throws Exception {
        gm[2][0] = new EmptyCell(1,1);
        wbAst.move(gm, new int[]{1,2,3});
    }

    @Test(expected = Exception.class)
    public void testMoveWithNotEnoughtDirections() throws Exception {
        wbAst.move(gm, new int[]{2});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadWhiteDirection() throws Exception {
        wbAst.move(gm, new int[]{2, 8, 2});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadBlueDirection() throws Exception {
        wbAst.move(gm, new int[]{2, 3, -2});
    }

    @Test
    public void testMoveToEmptyCell() throws Exception {
        wbAst.move(gm, new int[]{5,5,1});
        assertTrue(gm[1][0] == wbAst && gm[2][0] instanceof EmptyCell && gm[0][0] instanceof EmptyCell);
    }

    @Test
    public void testMoveWhiteToFullCell() throws Exception {
        wbAst.move(gm, new int[]{2,3,5});
        assertTrue(gm[1][0] == wbAst && gm[2][0] instanceof EmptyCell && gm[2][1] instanceof LaunchPad);
    }

    @Test
    public void testMoveBlueToNullCell() throws Exception {
        wbAst.move(gm, new int[]{2,5,5});
        assertTrue(gm[1][0] == wbAst && gm[2][0] instanceof EmptyCell && gm[0][1] == null);
    }

    @Test
    public void testMoveOutsideGameBoard() throws Exception {
        wbAst.move(gm, new int[]{2,4,1});
        assertTrue(gm[2][0] == wbAst);
    }

    @Test
    public void testMoveBlueToAsteroydCell() throws Exception {
        wbAst.move(gm, new int[]{2,5,3});
        assertTrue(gm[2][0] instanceof EmptyCell && gm[1][1] == wbAst2 && gm[1][0] == wbAst);
    }
}
