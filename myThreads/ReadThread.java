package myThreads;

import vectors.IVector;

public class ReadThread extends Thread {
    IVector mVector;

    @Override
    public void run() {
        if (mVector == null) {
            throw new NullPointerException("There is not Vector to work with");
        }
        for (int i = 0; i < mVector.getSize(); ++i) {
            double val = mVector.getElement(i);
            System.out.println("Read: " + val + " from position " + i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Create ReadThread that read from input vector
     * @param v - input vector
     */
    public ReadThread(IVector v) {
        if (v == null) {
            throw new NullPointerException("Input vector is null");
        }
        mVector = v;
    }

    /**
     * Forbid base constructor
     */
    private ReadThread() {
    }
}
