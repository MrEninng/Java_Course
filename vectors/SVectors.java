package vectors;

public class SVectors {

    public static IVector mult(IVector vector, int num) {
        int size = vector.getSize();
        IVector tmp = new ArrayVector(size);
        for (int i = 0; i < size; ++i) {
            tmp.setElement(i, vector.getElement(i) * num);
        }
        return tmp;
    }

    public static IVector sum(IVector lvl, IVector rvl) {
        int sizeLvl = lvl.getSize();
        int sizeRvl = rvl.getSize();
        int minSize = (sizeLvl < sizeRvl) ? sizeLvl : sizeRvl;
        int maxSize = (sizeLvl > sizeRvl) ? sizeLvl : sizeRvl;
        IVector tmp = new ArrayVector(maxSize);
        int i = 0;
        for (i = 0; i < minSize; ++i) {
            tmp.setElement(i, lvl.getElement(i) + rvl.getElement(i));
        }
        //return if same vectors size
        if (sizeLvl == sizeRvl) {
            return tmp;
        }

        //continue fill new ArrayVector with a tail of biggestVector
        IVector linkToBiggerVector = (sizeLvl > sizeRvl) ? lvl : rvl;
        for (; i < maxSize; ++i) {
            tmp.setElement(i, linkToBiggerVector.getElement(i));
        }

        return tmp;
    }

    //still overflow problem
    public static IVector scalarMult(IVector lvl, IVector rvl) {
        int sizeLvl = lvl.getSize();
        int sizeRvl = rvl.getSize();
        int minSize = (sizeLvl < sizeRvl) ? sizeLvl : sizeRvl;
        int maxSize = (sizeLvl > sizeRvl) ? sizeLvl : sizeRvl;
        IVector tmp = new ArrayVector(maxSize);
        int i = 0;
        for (i = 0; i < minSize; ++i) {
            tmp.setElement(i, lvl.getElement(i) * rvl.getElement(i));
        }
        //return if same vectors size
        if (sizeLvl == sizeRvl) {
            return tmp;
        }

        //continue fill new ArrayVector with a tail of biggestVector
        IVector linkToBiggerVector = (sizeLvl > sizeRvl) ? lvl : rvl;
        for (; i < maxSize; ++i) {
            tmp.setElement(i, linkToBiggerVector.getElement(i));
        }

        return tmp;
    }
}
