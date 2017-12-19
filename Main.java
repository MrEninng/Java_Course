import vectors.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {
        ArrayVector myVector = new ArrayVector(6);
        System.out.println("GetSize: " + myVector.getSize());
        myVector.setElement(0, 8.2);
        myVector.setElement(1, 12.0);
        myVector.setElement(2, 1.3);
        myVector.setElement(3, 6.1);
        myVector.setElement(4, 0.);
        myVector.setElement(5, -1.);

        System.out.println("Get Element: " + myVector.getElement(3));

        System.out.println("Find 12: " + myVector.find(12));

        System.out.println("Sorted Array:");
        myVector.sort();
        for (int i = 0; i < myVector.getSize(); ++i) {
            System.out.println(myVector.getElement(i) + " ");
        }

        System.out.println("getAverage: " + myVector.getAverage());
        System.out.println("getMax: " + myVector.getMax());
        System.out.println("getMin: " + myVector.getMin());

        IVector myVector2 = SVectors.mult(myVector, 5);
        System.out.println("MmVector2: myVector MULT 5");
        for (int i = 0; i < myVector2.getSize(); ++i) {
            System.out.println(myVector2.getElement(i) + " ");
        }
        System.out.println("getNorm: " + myVector.getNorm());

        ArrayVector myShortVector = new ArrayVector(2);
        myShortVector.setElement(0, 3.4);
        myShortVector.setElement(1, 112.7);
        IVector myVector3 = SVectors.sum(myVector2, myShortVector);
        System.out.println("Array after SUM:");
        for (int i = 0; i < myVector3.getSize(); ++i) {
            System.out.println(myVector3.getElement(i) + " ");
        }

        IVector myVector4 = SVectors.scalarMult(myVector3, myShortVector);
        System.out.println("Array after scalarMult:");
        for (int i = 0; i < myVector4.getSize(); ++i) {
            System.out.println(myVector4.getElement(i) + " ");
        }

        System.out.println("\n*\n*\n*\nLinkedListVector\n*\n*\n*\n");

        LinkedListVector lVector1 = new LinkedListVector();
        lVector1.addElement(5.3);
        lVector1.addElement(1);
        lVector1.addElement(29.23);
        lVector1.addElement(72.0);

        for (int i = 0; i < lVector1.getSize(); ++i) {
            System.out.print(lVector1.getElement(i) + " ");
        }

        System.out.println("\nNorm: " + lVector1.getNorm());

        lVector1.deleteElement(0);

        lVector1.setElement(0, 3.2);

        for (int i = 0; i < lVector1.getSize(); ++i) {
            System.out.print(lVector1.getElement(i) + " ");
        }

        System.out.println("\nMult: ");
        IVector tmp = SVectors.mult(lVector1, 6);
        for (int i = 0; i < tmp.getSize(); ++i) {
            System.out.print(tmp.getElement(i) + " ");
        }

        System.out.println("\nScalar Mult: ");
        tmp = SVectors.scalarMult(lVector1, myVector);
        for (int i = 0; i < tmp.getSize(); ++i) {
            System.out.print(tmp.getElement(i) + " ");
        }

        System.out.println("\nMyVector ");
        for (int i = 0; i < myVector.getSize(); ++i) {
            System.out.print(myVector.getElement(i) + " ");
        }
        System.out.println("\nSum: ");
        tmp = SVectors.sum(lVector1, myVector);
        for (int i = 0; i < tmp.getSize(); ++i) {
            System.out.print(tmp.getElement(i) + " ");
        }
    }

}
