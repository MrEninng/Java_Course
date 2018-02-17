import vectors.*;

import java.io.*;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        ArrayVector v = new ArrayVector(3);
        v.setElement(0,1);
        v.setElement(1,2.3);
        v.setElement(2,0.3);

        try {
            FileOutputStream fos = new FileOutputStream("temp.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(v);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayVector v2 =  (ArrayVector) ois.readObject();

            System.out.println("Array Vector:");
            System.out.println("Size: " + v2.getSize());
            for (int i = 0; i < v2.getSize(); ++i) {
                System.out.println("" + v2.getElement(i));
            }
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println("");
        LinkedListVector lv = new LinkedListVector();
        lv.addElement(5);
        lv.addElement(7.3);
        lv.addElement(1.3);
        lv.addElement(11.3);
        lv.addElement(100.32);
        try {
            FileOutputStream fos = new FileOutputStream("tempLV.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lv);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("tempLV.out");
            ObjectInputStream ois = new ObjectInputStream(fis);
            LinkedListVector lv2 =  (LinkedListVector) ois.readObject();

            System.out.println("SizeLV: " + lv2.getSize());
            for (int i = 0; i < lv2.getSize(); ++i) {
                System.out.println("" + lv2.getElement(i));
            }
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

}
