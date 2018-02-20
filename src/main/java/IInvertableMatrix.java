public interface IInvertableMatrix extends IMatrix {
    IInvertableMatrix getInverse() throws OutOfRangeException;
}
