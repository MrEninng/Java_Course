import vectors.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        IVector v = SVectors.createInstance(3);

        SVectors.setFactory(new LinkedListVector.LinkedListVectorFactory());
        IVector v2 = SVectors.createInstance(2);

        v2.setElement(1, 2);

        System.out.println(v.hashCode() == v2.hashCode());
    }
}
