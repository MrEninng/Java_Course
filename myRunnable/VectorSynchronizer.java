package myRunnable;

import vectors.IVector;

public class VectorSynchronizer {
    private IVector mVector;
    private volatile int mCurrentIdx = 0;
    private Object mLock = new Object();
    private boolean mSet = false;

    /**
     * Create Vector Synchronizer using input IVector
     * @param v - input vector
     */
    public VectorSynchronizer(IVector v) {
        if (v == null) {
            throw new NullPointerException("Null pointer input vector");
        }
        mVector = v;
    }

    /**
     * Forbid Base constructor
     */
    private VectorSynchronizer() {
    }

    public double read() throws InterruptedException {
        double val;
        synchronized (mLock) {
            if (!canRead())
                throw new InterruptedException("Can't Read");
            while (!mSet) {
                mLock.wait();
            }
            val = mVector.getElement(mCurrentIdx);
            System.out.println("Read: " + val + " from " + mCurrentIdx++);
            mSet = false;
            mLock.notifyAll();
        }
        return val;
    }

    public void write(double val) throws InterruptedException {
        synchronized (mLock) {
            if (!canWrite())
                throw new InterruptedException("Can't write");
            while (mSet) {
                mLock.wait();
            }
            mVector.setElement(mCurrentIdx, val);
            System.out.println("Write: " + val + " to " + mCurrentIdx);
            mSet = true;
            mLock.notifyAll();
        }
    }

    public int getSize() {
        return mVector.getSize();
    }

    private boolean canRead() {
        return mCurrentIdx < mVector.getSize();
    }

    private boolean canWrite() {
        return (!mSet && mCurrentIdx < mVector.getSize()) || (mSet && mCurrentIdx < mVector.getSize() - 1);
    }
}
