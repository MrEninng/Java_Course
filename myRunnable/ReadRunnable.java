package myRunnable;

public class ReadRunnable implements Runnable {

    private VectorSynchronizer mVector;

    /**
     * Create ReadRunnable using input Vector Synchronizer
     * @param v - input Vector Synchronizer
     */
    public ReadRunnable(VectorSynchronizer v) {
        if (v == null) {
            throw new NullPointerException("Null pointer input Vector Synchronizer");
        }
        mVector = v;
    }

    /**
     * Forbid base constructor
     */
    private ReadRunnable() {
    }

    /**
     * Run Main job
     */
    @Override
    public void run() {
        for (int i = 0; i < mVector.getSize(); ++i) {
            double val = 0;
            try {
                val = mVector.read();
                val = val; // unused
                Thread.sleep(100); // sleep 1 sec
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
