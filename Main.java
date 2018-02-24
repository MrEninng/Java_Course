import vectors.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        ArrayVector av = (ArrayVector)SVectors.createInstance(5);
        av.setElement(0, 2);
        av.setElement(3, 4);
        av.setElement(4, 2.1);
        ArrayVector av2 = null;
        try {
            av2 = (ArrayVector)av.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        av2.setElement(0, 0.282);
        System.out.println(av2.toString());

        SVectors.setFactory(new LinkedListVector.LinkedListVectorFactory());
        LinkedListVector v = (LinkedListVector)SVectors.createInstance(3);
        v.setElement(0, 2);
        v.setElement(1, 3);
        v.setElement(2, 0.2);

        LinkedListVector v2 = null;
        try {
            v2 = (LinkedListVector) v.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        v2.setElement(2, 2.232);
        v2.addElement(0.211);
        System.out.println(v2.toString());

    }
}
