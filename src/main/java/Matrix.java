import java.util.Arrays;

public class Matrix implements IMatrix{

    private double []data;
    private int n;
    private double det;
    private boolean isDet;

    public Matrix(int n){
        data = new double[n * n];
        for (int i = 0;i < data.length;i++) {
            data[i] = 0;
        }
        det = 0;
        isDet = false;
        this.n = n;
    }

    public Matrix(Matrix other){
        this.n = other.getN();
        data = other.getData().clone();
        det = other.det;
        isDet = false;
    }


    public double get(int i, int j) throws OutOfRangeException {
        if (((j + i * n) > data.length) && ((j + i * n) > 0)) throw new OutOfRangeException();
        //System.out.println("GET MATRIX [" + i + "][" + j + "]=" + data[j + i * n]);
        return data[j + i * n];
    }

    public void set(int i, int j, double value) throws OutOfRangeException {
        if((j + i * n) > data.length) throw new OutOfRangeException();
        //System.out.println("SET MATRIX [" + i + "][" + j + "]=" + value);
        data[j + i * n] = value;
        isDet = false;
    }

    public double det() throws OutOfRangeException{
        if(isDet) return det;
        double[] lastData = data.clone();
        double result = 1;
        for (int i=0; i<n; ++i) {
            int k = i;
            for (int j=i+1; j<n; ++j)
                if (Math.abs (get(j,i)) > Math.abs (get(k,i)))
                    k = j;
            if (Math.abs (get(k,i)) < 1E-9) {
                result = 0;
                break;
            }
            //swap (a[i], a[k]);
            for(int r = 0;r < n;r++){
                double bubble = get(r,i);
                set(r,i,get(r,k));
                set(r,k,bubble);
            }
            if (i != k)
                result = -result;
            result *= get(i,i);
            for (int j = i + 1; j<n;j++)
                //a[i][j] /= a[i][i];
                set(i,j,get(i,j) / get(i,i) );
            for (int j = 0;j < n;j++)
                if (j != i && Math.abs (get(j,i)) > 1E-9)
                    for (int h=i+1; h<n; h++)
                        set(j,h, get(j,h) - (get(i,h) * get(j,i)));
        }
        isDet = true;
        data = lastData;
        return result;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
        isDet = false;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
        isDet = false;
    }

    public void resetDet(){
        isDet = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;

        Matrix matrix = (Matrix) o;

        if (n != matrix.n) return false;
        return Arrays.equals(data, matrix.data);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(data);
        result = 31 * result + n;
        return result;
    }
}
