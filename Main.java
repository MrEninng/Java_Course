import vectors.*;

import java.util.Iterator;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {


        LinkedListVector lv = new LinkedListVector();
        lv.addElement(1.0);
        lv.addElement(5);
        lv.addElement(0.1);

        Iterator lvI = lv.iterator();
        while(lvI.hasNext()) {
            System.out.println(lvI.next()); // Here 1st element is missed because next() returns
                                            // value of Next element and moves iterator
        }
        System.out.println("");

        ArrayVector av = new ArrayVector(3);
        av.setElement(0, 2);
        av.setElement(1, 1.32);
        av.setElement(2, 0.2);

        Iterator avI = av.iterator();
        while(avI.hasNext()) {
            System.out.println(avI.next()); //Same as for lvI (first element is missed)
        }

        System.out.println("");
        avI = av.iterator(); // Get at the beginning
        avI.remove();
        while(avI.hasNext()) {
            System.out.println(avI.next()); //Same as for lvI (first element is missed)
        }

        System.out.println("");
        lvI = lv.iterator();
        lvI.remove();
        while (lvI.hasNext()) {
            System.out.println(lvI.next());
        }
    }
}
