import myThreads.*;
import vectors.*;
import java.lang.Thread;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        IVector v = SVectors.createInstance(20);
        WriteThread wThread = new WriteThread(v);
        ReadThread rThread = new ReadThread(v);

//        wThread.setPriority(Thread.MAX_PRIORITY);
//        rThread.setPriority(Thread.MIN_PRIORITY);
        wThread.start();
        try {
            Thread.sleep(500); // Let Write Thread to start Writing first
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        rThread.start();
    }
}
