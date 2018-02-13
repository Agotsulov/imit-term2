import java.io.Serializable;

public interface IMatrix extends Serializable{
    double get(int i, int j) throws OutOfRangeException;
    void set(int i, int j, double value) throws OutOfRangeException;
    double det() throws OutOfRangeException;
}
