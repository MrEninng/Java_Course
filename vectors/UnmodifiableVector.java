package vectors;

import java.util.Iterator;

public class UnmodifiableVector implements IVector {

    IVector mVector;
    private UnmodifiableVector() {
        //Forbid base constructor
    }

    /**
     * Create unmodifiable vector
     * @param v - Vector that should be wrapped
     */
    public UnmodifiableVector(IVector v) {
        if (v == null) {
            throw new NullPointerException("Null Point input Vector");
        }
        mVector = v;
    }

    @Override
    public int getSize() {
        return mVector.getSize();
    }

    @Override
    public double getElement(int i) {
        return mVector.getElement(i);
    }

    @Override
    public void setElement(int i, double val) {
        throw new UnsupportedOperationException("Unsupported Operation: Set Element");
    }

    @Override
    public void deleteElement(int i) {
        throw new UnsupportedOperationException("Unsupported Operation: Delete Element");
    }

    @Override
    public double getNorm() {
        return mVector.getNorm();
    }

    /**
     * Forbid this operation(user should not change vector);
     * @return null
     */
    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Unsupported Operation: Iterator");
    }
}
