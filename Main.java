import myRunnable.*;
import vectors.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        IVector v = SVectors.createInstance(15);
        VectorSynchronizer vs = new VectorSynchronizer(v);

        ReadRunnable rr = new ReadRunnable(vs);
        Thread readThread = new Thread(rr);

        WriteRunnable wr = new WriteRunnable(vs);
        Thread writeThread = new Thread(wr);

        writeThread.setPriority(Thread.MIN_PRIORITY);
        readThread.setPriority(Thread.MAX_PRIORITY);

        writeThread.start();
        try {
            Thread.sleep(500); // Let write thread start
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        readThread.start();
    }
}
