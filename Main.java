import vectors.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        IVector v = SVectors.createInstance(1);
        v.deleteElement(0);
        System.out.println(v.toString()); //check that empty array doesn't cause problems

        SVectors.setFactory(new LinkedListVector.LinkedListVectorFactory());
        v = SVectors.createInstance(3);
        System.out.println(v.toString());
        int size = v.getSize();
        for (int i = 0; i < size; ++i) {
            v.deleteElement(0); //delete all elements (empty array);
        }
        System.out.println(v.toString()); //check that empty array doesn't cause problems
    }
}
