package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import Modele.BlueAsteroyd;
import Modele.Cell;
import Modele.EmptyCell;
import Modele.LaunchPad;

public class BlueAsteroydTest {
    private Cell[][] gm;
    private BlueAsteroyd bAst1,
                         bAst2,
                         bAst3;

    @Before
    public void constructGameBoard() throws Exception {
        bAst1 = new BlueAsteroyd(2, 1,1);
        bAst2 = new BlueAsteroyd(2, 0,2);
        bAst3 = new BlueAsteroyd(1, 1,2);
        gm = new Cell[][]{{null, null, new EmptyCell(2,0)}, {new LaunchPad(0,1), bAst1, new EmptyCell(1,2)}, {bAst2, bAst3, null}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new BlueAsteroyd(0, 2, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new BlueAsteroyd(-3, 1, 6);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new BlueAsteroyd(7, 8, 0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new BlueAsteroyd(2, -3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new BlueAsteroyd(2, 7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new BlueAsteroyd(4, null);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new BlueAsteroyd(3, 7, 14);
    }

    @Test(expected = Exception.class)
    public void testMoveWhileNotInGameBoard() throws Exception {
        gm[1][1] = new EmptyCell(1,1);
        bAst1.move(gm, new int[]{1,2,3});
    }

    @Test(expected = Exception.class)
    public void testMoveWithNotEnoughtDirections() throws Exception {
        bAst1.move(gm, new int[]{2});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadBlueDirection() throws Exception {
        bAst1.move(gm, new int[]{2,4,0});
    }

    @Test
    public void testMoveToEmptyCell() throws Exception {
        bAst1.move(gm, new int[]{5,4,3});
        assertTrue(gm[1][1] == bAst1 && gm[1][2] instanceof EmptyCell);
    }

    @Test
    public void testMoveToFullCell() throws Exception {
        bAst1.move(gm, new int[]{2,1,6});
        assertTrue(gm[1][1] == bAst1 && gm[1][0] instanceof LaunchPad);
    }

    @Test
    public void testMoveToNullCell() throws Exception {
        bAst1.move(gm, new int[]{2,1,5});
        assertTrue(gm[1][1] == bAst1 && gm[2][2] == null);
    }

    @Test
    public void testMoveOutsideGameBoard() throws Exception {
        bAst2.move(gm, new int[]{2,1,3});
        assertTrue(gm[2][0] == bAst2);
    }

    @Test
    public void testMoveToAsteroydCell() throws Exception {
        bAst3.move(gm, new int[]{2,1,1});
        assertTrue(gm[2][1] == bAst3 && gm[1][1]==bAst1);
    }
}
