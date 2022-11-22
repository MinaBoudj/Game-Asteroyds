package Modele.test;

import org.junit.Test;

import Modele.VisualObject;
import Modele.EmptyCell;

public class VisualObjectTest {
    private VisualObject vObj;

    @Before
    public void constructVisualObject() {
        vObj = new EmptyCell(1, 5 ,3);
    }

    // /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE TESTER LE CONSTRUCTEUR /!\
    @Test(expected = Exception.class)
    public void testConstructorWithNullOrientation() {
        new VisualObject(0, 2, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeOrientation() {
        new VisualObject(-3, 1, 6);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithTooBigOrientation() {
        new VisualObject(7, 8, 0);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeX() {
        new VisualObject(2, -3, 4);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNegativeY() {
        new VisualObject(2, 7, -12);
    }

    @Test(expected = Exception.class)
    public void testConstructorWithNullPosition() {
        new VisualObject(2, null);
    }

    @Test
    public void testConstructorWithGoodArguments() {
        new VisualObject(3, 7, 14);
    }
    // /!\ NE PAS OUBLIER DE RENDRE LA CLASSE CONCRETE AVANT DE TESTER LE CONSTRUCTEUR /!\

    @Test(expected = Exception.class)
    public void testSetOrientationWithNullOrientation() {
        vObj.setOrientation(0);
    }

    @Test(expected = Exception.class)
    public void testSetOrientationWithNegativeOrientation() {
        vObj.setOrientation(-2);
    }

    @Test(expected = Exception.class)
    public void testSetOrientationWithTooBigOrientation() {
        vObj.setOrientation(18);
    }
}