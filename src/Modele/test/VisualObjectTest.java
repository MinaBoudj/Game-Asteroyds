package Modele.test;

import org.junit.Test;

import Modele.VisualObject;

public class VisualObjectTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorsWithNullOrientation() {
        new VisualObject(0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeOrientation() {
        new VisualObject(-3, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithTooBigOrientation() {
        new VisualObject(7, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeX() {
        new VisualObject(2, -3, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeY() {
        new VisualObject(2, 0, -12);
    }

    @Test
    public void testConstructorWithGoodArguments() {
        new VisualObject(3, 7, 14);
    }
}