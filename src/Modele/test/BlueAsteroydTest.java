package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

import Modele.BlueAsteroyd;
import Modele.Position;
import Modele.Cell;
import Modele.EmptyCell;
import Modele.LaunchPad;

public class BlueAsteroydTest {
    private Cell[][] gm;
    private BlueAsteroyd bAst1,
                         bAst2,
                         bAst3;

    @Before
    public void constructGameBoard() {
        bAst1 = new BlueAsteroyd(2, 1,1);
        bAst2 = new BlueAsteroyd(2, 0,2);
        bAst3 = new BlueAsteroyd(5, 1,2);
        gm = new Cell[][]{{null, null, new EmptyCell(2,0)}, {new LaunchPad(0,1), bAst1, null}, {bAst2, bAst3, new EmptyCell(2,2)}};
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() {
        new BlueAsteroyd(0, 2, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() {
        new BlueAsteroyd(-3, 1, 6);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() {
        new BlueAsteroyd(7, 8, 0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() {
        new BlueAsteroyd(2, -3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() {
        new BlueAsteroyd(2, 7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() {
        new BlueAsteroyd(4, null);
    }

    @Test
    public void testConstructorWithGoodArguments() {
        new BlueAsteroyd(3, 7, 14);
    }

    @Test(expected = Exception.class)
    public void testMoveWhileNotInGameBoard() {
        gm[1][1] = new EmptyCell(1,1);
        bAst1.move(gm, new int[]{1,2,3});
    }

    @Test(expected = Exception.class)
    public void testMoveWithNotEnoughtDirections() {
        bAst1.move(gm, new int[]{2});
    }

    @Test(expected = Exception.class)
    public void testMoveWithBadBlueDirection() {
        bAst1.move(gm, new int[]{2,4,0});
    }

    @Test
    public void testMoveToEmptyCell() {
        bAst1.move(gm, new int[]{5,4,4});
        assertTrue(gm[0][2] == bAst1 && gm[1][1] instanceof EmptyCell);
    }

    @Test
    public void testMoveToFullCell() {
        bAst1.move(gm, new int[]{2,1,6});
        assertTrue(gm[1][1] == bAst1 && gm[1][0] instanceof LaunchPad);
    }

    @Test
    public void testMoveToNullCell() {
        bAst1.move(gm, new int[]{2,1,5});
        assertTrue(gm[1][1] == bAst1 && gm[1][1] == null);
    }

    @Test
    public void testMoveOutsideGameBoard() {
        bAst2.move(gm, new int[]{2,1,3});
        assertTrue(gm[2][0] == bAst2);
    }

    @Test
    public void testMoveToAsteroydCell() {
        bAst2.move(gm, new int[]{2,1,2});
        assertTrue(gm[2][0] instanceof EmptyCell && gm[2][1] == bAst2 && gm[2][2] == bAst3);
    }
}
