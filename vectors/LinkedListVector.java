package vectors;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedListVector implements IVector, Serializable {

    /**
     * LinkedListVector constructor. Init private variables
     */
    public LinkedListVector() {
        mSize = 0;

        mHead.prev = mHead;
        mHead.next = mHead;
        mTail = mHead;
    }

    /**
     * Add element into tail of list (like pushBack).
     * @param val - value to add
     */
    public void addElement(double val) {
        if (mSize == 0) {
            mHead.value = val;
            ++mSize;
            return;
        }
        Node tmp = mTail;
        mTail.next = new Node();

        mTail = mTail.next;

        mTail.value = val;
        mTail.prev = tmp;
        ++mSize;

    }

    /**
     * Delete element by index
     * @param i - index of element to remove
     */
    public void deleteElement(int i) {

        if (i >= mSize || i < 0) {
            throw new VectorsExceptions.VectorIndexOutOfBoundsException();
        }
        Node tmp = gotoNumber(i);
        if (tmp == null) {
            return;
        }

        if (i == 0) {
            mHead = tmp.next;
            --mSize;
            current = mHead;
            currentIndex = 0;
            return;
        }
        Node prev = tmp.prev;
        prev.next = tmp.next;

        --mSize;
        currentIndex--;
        current = prev;
    }

    /**
     * Returns size of vector
     * @return Size of vector
     */
    @Override
    public int getSize() {
        return mSize;
    }


    /**
     * Set value by index (vector[i] = val)
     *
     * @param i - index of element
     * @param val - value to set
     */
    @Override
    public void setElement(int i, double val) {
        if (i >= mSize || i < 0) {
            throw new VectorsExceptions.VectorIndexOutOfBoundsException();
        }
        gotoNumber(i);
        current.value = val;
    }

    /**
     * Return value by index
     *
     * @param i - index of element
     * @return element by index (vector[i])
     */
    @Override
    public double getElement(int i) {
        if (i >= mSize || i < 0) {
//            throw exception;
        }
        gotoNumber(i);
        return current.value;
    }

    /**
     * Calculate norm of vector: Norm = sqrt(E(i..n)Sum(Vector[i]^2))
     * @return Vector Norm
     */
    @Override
    public double getNorm() {
        double norm;
        Node tmp = mHead;
        double sum = 0;
        for (int i = 0; i < mSize; ++i) {
            sum += tmp.value * tmp.value;
            tmp = tmp.next;
        }
        norm = Math.sqrt(sum);
        return norm;
    }

    // Size of list
    private int mSize;

    //Private class. Node of List.
    private class Node implements Serializable {
        // Value in Node
        double value = Double.NaN;
        // Reference to previous element
        Node prev = null;
        // Reference to next element
        Node next = null;
    }

    // Head of list
    private Node mHead = new Node();
    // Tail of list
    private Node mTail;

    // Last used Node in list - Speed optimisation
    private Node current = mHead;

    // Index of last used Node in list - Speed optimisation
    private int currentIndex = 0;

    /**
     * Private method for access to vector[i] element by index
     * @param index - index of element
     * @return Node of element
     */
    private Node gotoNumber(int index) {
        if ((index < mSize) && (index >= 0)) {
            if (index == currentIndex) {
                return current;
            }

            if (index > currentIndex) {
                while (index != currentIndex) {
                    current = current.next;
                    ++currentIndex;
                }
                return current;
            } else if (index < currentIndex){
                while (index != currentIndex) {
                    current = current.prev;
                    --currentIndex;
                }
                return current;
            }
        } else {
            throw new VectorsExceptions.VectorIndexOutOfBoundsException();
        }
        return null;
    }

    public Node getHead() {
        return mHead;
    }

    public class LinkedListVectorIterator implements Iterator {

        private LinkedListVector.Node mHead;
        private LinkedListVector.Node mCurrent;

        LinkedListVectorIterator(LinkedListVector v) {
            mHead = v.getHead();
            mCurrent = mHead;
        }

        @Override
        public boolean hasNext() {
            if (mCurrent != null && mCurrent.next != null) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                mCurrent = mCurrent.next;
                return mCurrent.value;
            }
            return null;
        }

        @Override
        public void remove() {
            if (mCurrent == null)
                return;
            if (mCurrent == mHead) {
                mHead = mCurrent.next;
                mCurrent = mCurrent.next;
                return;
            }

            mCurrent.prev = mCurrent.next;
            mCurrent = mCurrent.prev; //get last element;
        }
    }
    @Override
    public Iterator iterator() {
        return new LinkedListVectorIterator(this);
    }

    public static class LinkedListVectorFactory implements IVectorFactory {
        @Override
        public IVector createInstance(int size) {
            LinkedListVector lv = new LinkedListVector();
            for (int i = 0; i < size; ++i) {
                lv.addElement(0);
            }
            return lv;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node current = mHead;
        while(current != null) {
            sb.append(current.value);
            sb.append(" ");
            current = current.next;
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ArrayVector || o instanceof LinkedListVector) {
            IVector v = (IVector)o;
            if (getSize() != v.getSize()) {
                return false;
            }
            int size = getSize();
            for (int i = 0; i < size; ++i) {
                if (getElement(i) != v.getElement(i))
                    return false;
            }
            return true;
        }
        return false;
    }

}
