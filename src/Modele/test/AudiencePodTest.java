package Modele.test;

/**
 * @author Matéo
 */

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

import Modele.AudiencePod;
import Modele.Position;

public class AudiencePodTest {
    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new AudiencePod(-3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new AudiencePod(7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new AudiencePod(null);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new AudiencePod(7, 14);
    }

    @Test
    public void testCanContainSpaceShipsIsFalseByDefault() throws Exception {
        AudiencePod aPod = new AudiencePod(5, 3);
        assertFalse(aPod.getCanContainSpaceShips());
    }
}