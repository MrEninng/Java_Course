package myRunnable;

import java.util.Random;

public class WriteRunnable implements Runnable {

    private VectorSynchronizer mVector;

    /**
     * Create WriteRunnable using input Vector Synchronizer
     * @param v - input Vector Synchronizer
     */
    public WriteRunnable(VectorSynchronizer v) {
        if (v == null) {
            throw new NullPointerException("Null pointer input Vector Synchronizer");
        }
        mVector = v;
    }

    /**
     * Forbid base constructor
     */
    private WriteRunnable() {
    }

    /**
     * Run Main job
     */
    @Override
    public void run() {
        for (int i = 0; i < mVector.getSize(); ++i) {
            double val = 0;
            try {
                Random r = new Random();
                val = r.nextDouble();
                mVector.write(val);
                Thread.sleep(1000); // sleep 1 sec
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
