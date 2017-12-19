package vectors;

public interface IVector {
    /**
     * Returns size of vector
     * @return Size of vector
     */
    int     getSize();

    /**
     * Return value by index
     *
     * @param i - index of element
     * @return element by index (vector[i])
     */
    double  getElement(int i);

    /**
     * Set value by index (vector[i] = val)
     *
     * @param i - index of element
     * @param val - value to set
     */
    void    setElement(int i, double val);

    /**
     * Calculate norm of vector: Norm = sqrt(E(i..n)Sum(Vector[i]^2))
     * @return Vector Norm
     */
    double  getNorm();
}
