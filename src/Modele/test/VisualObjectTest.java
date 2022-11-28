package Modele.test;

/**
 * @author Matéo
 */

import org.junit.Test;
import org.junit.Before;

import Modele.VisualObject;

// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANCER LA CLASSE DE TESTS /!\
public class VisualObjectTest {
    private VisualObject vObj;

    @Before
    public void constructVisualObject() throws Exception {
        vObj = new VisualObject(1, 5 ,3);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() throws Exception {
        new VisualObject(0, 2, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() throws Exception {
        new VisualObject(-3, 1, 6);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() throws Exception {
        new VisualObject(7, 8, 0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() throws Exception {
        new VisualObject(2, -3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() throws Exception {
        new VisualObject(2, 7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() throws Exception {
        new VisualObject(2, null);
    }

    @Test
    public void testConstructorWithGoodArguments() throws Exception {
        new VisualObject(3, 7, 14);
    }

    @Test(expected = Exception.class)
    public void testSetOrientationWithNullOrientation() throws Exception {
        vObj.setOrientation(0);
    }

    @Test(expected = Exception.class)
    public void testSetOrientationWithNegativeOrientation() throws Exception {
        vObj.setOrientation(-2);
    }

    @Test(expected = Exception.class)
    public void testSetOrientationWithTooBigOrientation() throws Exception {
        vObj.setOrientation(18);
    }
}
// /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE LANCER LA CLASSE DE TESTS /!\
