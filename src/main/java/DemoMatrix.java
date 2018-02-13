import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class DemoMatrix extends InvertableMatrix{

    public DemoMatrix(int n) {
        super(n);
    }

    public static byte[] convertToByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putDouble(value);
        return buffer.array();
    }

    public static double convertToDouble(byte[] array) {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getDouble();
    }

    public void write(OutputStream out) throws IOException, OutOfRangeException {
        out.write(getN());
        for(int i = 0;i < getN();i++){
            for(int j = 0;j < getN();j++){
                out.write(convertToByteArray(get(i, j)));
            }
        }
    }


    public void read(InputStream in) throws IOException, OutOfRangeException {
        setN(in.read());
        setData(new double[getN() * getN()]);
        for(int i = 0;i < getData().length;i++){
            byte[] b = new byte[8];
            in.read(b);
            getData()[i] = convertToDouble(b);
        }
        resetDet();
    }

    public double sumAll(){
        double result = 0;
        for(double d: getData())
            result += d;
        return result;
    }

    public static void main(String[] args) throws IOException, OutOfRangeException, ClassNotFoundException {
        Matrix matrix = new Matrix(5);
        InvertableMatrix invertableMatrix = new InvertableMatrix(5);
        DemoMatrix demoMatrix= new DemoMatrix(5);


        Scanner sc = new Scanner(new File("main.txt"));
        int size = sc.nextInt();

        matrix = new Matrix(size);
        invertableMatrix = new InvertableMatrix(size);
        demoMatrix= new DemoMatrix(size);

        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++){
                double d = sc.nextDouble();
                System.out.println(d + " ");
                matrix.set(i,j,d);
                invertableMatrix.set(i,j,d);
                demoMatrix.set(i,j,d);
            }
        }
        sc.close();

        System.out.println("Matrix = " + matrix.det() + "\nInvertableMatrix = " + invertableMatrix.det() + "\nDemoMatrix = " + demoMatrix.det());

        demoMatrix.write(System.out);

        System.out.println();

        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++){
                System.out.print(matrix.get(i,j) + " ");
            }
            System.out.println();
        }

        System.out.println();

        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++){
                System.out.print(invertableMatrix.get(i,j) + " ");
            }
            System.out.println();
        }

        System.out.println();

        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++){
                System.out.print(demoMatrix.get(i,j) + " ");
            }
            System.out.println();
        }

        System.out.println();

        FileOutputStream fos = new FileOutputStream("serTest.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(demoMatrix);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("serTest.out");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        System.out.println("equals ser ? " + demoMatrix.equals(o));
    }
}
