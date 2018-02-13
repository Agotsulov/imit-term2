import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class DemoMatrixTest {

    @org.junit.Test
    public void testSetGet() throws OutOfRangeException {
        DemoMatrix dm = new DemoMatrix(10);
        dm.set(3,6,142.3);
        dm.set(2,0,45.1616);
        assertEquals(142.3,dm.get(3,6),0.01);
        assertEquals(45.1616,dm.get(2,0),0.01);
    }


    @org.junit.Test
    public void testStream() throws OutOfRangeException, IOException {
        DemoMatrix dm = new DemoMatrix(10);
        dm.set(3,6,142.3);
        dm.set(2,0,45.1616);
        FileOutputStream fout = new FileOutputStream("test");
        dm.write(fout);
        DemoMatrix dm2 = new DemoMatrix(1);
        FileInputStream fin = new FileInputStream("test");
        dm2.read(fin);

        assertEquals(142.3,dm2.get(3,6),0.01);
        assertEquals(45.1616,dm2.get(2,0),0.01);
    }

    @org.junit.Test
    public void testDeter() throws OutOfRangeException, IOException {
        DemoMatrix dm = new DemoMatrix(10);
        assertEquals(0, dm.det(), 0.00001);

        dm = new DemoMatrix(2);
        dm.set(0, 0, 1);
        dm.set(0, 1, 3);
        dm.set(1, 1, 2);
        assertEquals(2, dm.det(), 0.0001);

        dm = new DemoMatrix(3);
        dm.set(0, 0, 1);
        dm.set(1, 1, 1);
        dm.set(2, 2, 1);
        assertEquals(1, dm.det(), 0.00001);

        dm = new DemoMatrix(3);
        dm.set(0, 0, 1);
        assertEquals(0, dm.det(), 0.00001);

        dm = new DemoMatrix(3);
        dm.set(0, 0, 1);
        dm.set(0, 1, 2);
        dm.set(0, 2, 3);

        dm.set(1, 0, 9);
        dm.set(1, 1, 5);
        dm.set(1, 2, 4);

        dm.set(2, 0, 7);
        dm.set(2, 1, 8);
        dm.set(2, 2, 6);
        assertEquals(57, dm.det(), 0.01);


        dm = new DemoMatrix(3);
        dm.set(0, 0, 1);
        dm.set(0, 1, 1.2);
        dm.set(0, 2, 0.3);

        dm.set(1, 0, 15);
        dm.set(1, 1, -4);
        dm.set(1, 2, 12);

        dm.set(2, 0, 3);
        dm.set(2, 1, 4.14);
        dm.set(2, 2, -3.3);
        assertEquals(1767 / 20, dm.det(), 1);
    }


}