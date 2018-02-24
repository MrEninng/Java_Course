import vectors.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        IVector v =  SVectors.createInstance(5);
        for (int i = 0; i < v.getSize(); ++i) {
            System.out.println(v.getElement(i));
        }
        System.out.println("");

        SVectors.setFactory(new LinkedListVector.LinkedListVectorFactory());

        v = SVectors.createInstance(3);
        for (int i = 0; i < v.getSize(); ++i) {
            System.out.println(v.getElement(i));
        }
        System.out.println("");

        try {
            v = SVectors.createInstance(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
