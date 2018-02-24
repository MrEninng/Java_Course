package vectors;

import java.util.Iterator;

public class SynchronizedVector implements IVector {

    private IVector mVector;

    public SynchronizedVector(IVector v) {
        if (v == null) {
            throw new NullPointerException("Null Pointer input Vector");
        }
        mVector = v;
    }

    /**
     * Forbid base constructor
     */
    private SynchronizedVector() {
    }

    @Override
    public synchronized int getSize() {
        return mVector.getSize();
    }

    @Override
    public synchronized double getElement(int i) {
        return mVector.getElement(i);
    }

    @Override
    public synchronized void setElement(int i, double val) {
        mVector.setElement(i, val);
    }

    @Override
    public synchronized void deleteElement(int i) {
        mVector.deleteElement(i);
    }

    @Override
    public synchronized double getNorm() {
        return mVector.getNorm();
    }

    /**
     * Does not implement it's iterator
     * @return null
     */
    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public synchronized int hashCode() {
        return mVector.hashCode();
    }

    @Override
    public synchronized boolean equals(Object o) {
        return mVector.equals(o);
    }

    @Override
    public synchronized String toString() {
        return mVector.toString();
    }
}
