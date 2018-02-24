import vectors.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        IVector v = SVectors.createInstance(3);
        IVector v2 = v;
        System.out.println(v.equals(v2));

        v2 = SVectors.createInstance(3);
        System.out.println(v2.equals(v));

        v2.setElement(0, 1);
        System.out.println(v2.equals(v));
        v2 = SVectors.createInstance(2);
        System.out.println(v2.equals(v));
    }
}
