import vectors.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        ArrayVector av = new ArrayVector(5);
        av.setElement(0, 2);
        av.setElement(1, 2.3);
        av.setElement(4, 0.21);

        UnmodifiableVector uav = new  UnmodifiableVector(av);
        try {
            uav.deleteElement(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < uav.getSize(); ++i) {
            System.out.println(uav.getElement(i));
        }

        LinkedListVector lv = new LinkedListVector();
        lv.addElement(0.6);
        lv.addElement(0.0);
        lv.addElement(2.6);

        uav = new UnmodifiableVector(lv);
        try {
            uav.setElement(0, 3.7);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < uav.getSize(); ++i) {
            System.out.println(uav.getElement(i));
        }

    }
}
