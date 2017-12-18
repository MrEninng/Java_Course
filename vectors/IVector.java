package vectors;

public interface IVector {
    int     getSize();
    double  getElement(int i);
    void    setElement(int i, double val);
    double  getNorm();
}
