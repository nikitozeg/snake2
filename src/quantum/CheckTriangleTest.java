package quantum;

import junit.framework.TestCase;

import java.io.IOException;

public class CheckTriangleTest extends TestCase {
    Triangles s;

    public void testRightTriangle() throws IOException {

        s = new Triangles();
        s.triangle(4, 3, 5);
        assertEquals("right", true, s.rightTriangle);
        assertEquals("equal", false, s.equalTriangle);
        assertEquals("usual", false, s.otherTriangle);

        s = new Triangles();
        s.triangle(6, 8, 10);
        assertEquals("right", true, s.rightTriangle);
        assertEquals("equal", false, s.equalTriangle);
        assertEquals("usual", false, s.otherTriangle);

    }

    public void testEqualTriangle() throws IOException {

        s = new Triangles();
        s.triangle(1,1,1);
        assertEquals("right", false, s.rightTriangle);
        assertEquals("equal", true, s.equalTriangle);
        assertEquals("usual", false, s.otherTriangle);

        s = new Triangles();
        s.triangle(1, 1, 2);
        assertEquals("right", false, s.rightTriangle);
        assertEquals("equal", true, s.equalTriangle);
        assertEquals("usual", false, s.otherTriangle);

        s = new Triangles();
        s.triangle(5, 5, 5);
        assertEquals("right", false, s.rightTriangle);
        assertEquals("equal", true, s.equalTriangle);
        assertEquals("usual", false, s.otherTriangle);

        s = new Triangles();
        s.triangle(10, 3, 10);
        assertEquals("right", false, s.rightTriangle);
        assertEquals("equal", true, s.equalTriangle);
        assertEquals("usual", false, s.otherTriangle);

    }

    public void testOtherTriangle() throws IOException {

        s = new Triangles();
        s.triangle(2, 11, 5);
        assertEquals("right", false, s.rightTriangle);
        assertEquals("equal", false, s.equalTriangle);
        assertEquals("usual", true, s.otherTriangle);

        s = new Triangles();
        s.triangle(17, 49, 31);
        assertEquals("right", false, s.rightTriangle);
        assertEquals("equal", false, s.equalTriangle);
        assertEquals("usual", true, s.otherTriangle);

    }


    public void testNegativeInput() throws IOException {
        try {
            s = new Triangles();
            s.triangle(0, 5, 5);
            fail("Should have thrown an exception!");
        } catch (IOException e) {
            System.out.println(" Good, that's what we expect");
        }

        try {
            s = new Triangles();
            s.triangle(5, -5, 5);
            fail("Should have thrown an exception!");
        } catch (IOException e) {
            System.out.println(" Good, that's what we expect");
        }

    }

}