package Modele.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import Modele.Position;

public class PositionTest {
    Position pos;

    @Before
    public void constructPosition() throws Exception {
        pos = new Position(2,0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new Position(-1,5);
    }
    
    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new Position(1,-5);
    }
    
    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new Position(1,5);
    }

    @Test(expected = Exception.class)
    public void testGetForwardwithNullOrientation() throws Exception {
        pos.getForward(0);
    }

    @Test(expected = Exception.class)
    public void testGetForwardwithNegativeOrientation() throws Exception {
        pos.getForward(-3);
    }

    @Test(expected = Exception.class)
    public void testGetForwardwithTooBigOrientation() throws Exception {
        pos.getForward(7);
    }

    @Test(expected = Exception.class)
    public void testGetForwardOutOfGameBoard() throws Exception {
        pos.getForward(1);
    }

    @Test
    public void testGetForward() throws Exception {
        Position newPos = pos.getForward(4);
        assertTrue(newPos.getX() == 1 && newPos.getY() == 1);
    }
}
