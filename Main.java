
public class Main {
    public static void main(String... s) {
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

        ArrayVector myVector2 = ArrayVector.mult(myVector, 5);
        System.out.println("MmVector2: myVector MULT 5");
        for (int i = 0; i < myVector2.getSize(); ++i) {
            System.out.println(myVector2.getElement(i) + " ");
        }
        System.out.println("getNorm: " + myVector.getNorm());

        ArrayVector myShortVector = new ArrayVector(2);
        myShortVector.setElement(0, 3.4);
        myShortVector.setElement(1, 112.7);
        ArrayVector myVector3 = ArrayVector.sum(myVector2, myShortVector);
        System.out.println("Array after SUM:");
        for (int i = 0; i < myVector3.getSize(); ++i) {
            System.out.println(myVector3.getElement(i) + " ");
        }

        ArrayVector myVector4 = ArrayVector.scalarMult(myVector3, myShortVector);
        System.out.println("Array after scalarMult:");
        for (int i = 0; i < myVector4.getSize(); ++i) {
            System.out.println(myVector4.getElement(i) + " ");
        }

    }
    
}
