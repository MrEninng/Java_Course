package myThreads;

import vectors.IVector;

import java.util.Random;

public class WriteThread extends Thread {
    IVector mVector;

    @Override
    public void run() {
        if (mVector == null) {
            throw new NullPointerException("There is no Vector to work with");
        }
        Random r = new Random();
        for (int i = 0; i < mVector.getSize(); ++i) {
            double val = r.nextDouble();
            mVector.setElement(i, val);
            System.out.println("Write : " + val + " to position " + i);
            try {
                sleep(1000); //sleep 1 second
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Set vector to work with
     * @param v - input vector
     */
    public WriteThread(IVector v) {
        if (v == null) {
            throw new NullPointerException("Input vector is null");
        }
        mVector = v;
    }

    /**
     * Forbid base construct
     */
    private WriteThread() {
    }

}

