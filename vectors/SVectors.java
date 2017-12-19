package vectors;

public class SVectors {

    /**
     *
     * @param vector - Input vector (that implements IVector)
     * @param num - Number for mult
     * @return - ArrayVector(in IVector representation) where outputVector[i] =vector[i] * num
     */
    public static IVector mult(IVector vector, int num) {
        int size = vector.getSize();
        IVector tmp;
        try {
            tmp = new ArrayVector(size);
        } catch (VectorsExceptions.IncompatibleVectorSizesException e) {
            System.out.println("Error: IncompatibleVectorSizesException");
            return null;
        }
        for (int i = 0; i < size; ++i) {
            tmp.setElement(i, vector.getElement(i) * num);
        }
        return tmp;
    }

    /**
     *
     * @param lvl - leftvalue Vector that implements IVector
     * @param rvl - rightvalue Vector that implements IVector
     * @return - ArrayVector(in IVector representation) where Vector[i] = lvl[i] + rvl[i]
     */
    public static IVector sum(IVector lvl, IVector rvl) {
        int sizeLvl = lvl.getSize();
        int sizeRvl = rvl.getSize();
        int minSize = (sizeLvl < sizeRvl) ? sizeLvl : sizeRvl;
        int maxSize = (sizeLvl > sizeRvl) ? sizeLvl : sizeRvl;
        IVector tmp;
        try {
            tmp = new ArrayVector(maxSize);
        } catch (VectorsExceptions.IncompatibleVectorSizesException e) {
            System.out.println("Error: IncompatibleVectorSizesException");
            return null;
        }
        int i;
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

    /**
     *
     * @param lvl - leftvalue Vector that implements IVector
     * @param rvl - rightvalue Vector that implements IVector
     * @return - ArrayVector(in IVector representation) where Vector[i] = lvl[i] * rvl[i]
     */
    public static IVector scalarMult(IVector lvl, IVector rvl) {
        int sizeLvl = lvl.getSize();
        int sizeRvl = rvl.getSize();
        int minSize = (sizeLvl < sizeRvl) ? sizeLvl : sizeRvl;
        int maxSize = (sizeLvl > sizeRvl) ? sizeLvl : sizeRvl;
        IVector tmp;
        try {
            tmp = new ArrayVector(maxSize);
        } catch (VectorsExceptions.IncompatibleVectorSizesException e) {
            System.out.println("Error: IncompatibleVectorSizesException");
            return null;
        }
        int i;
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
