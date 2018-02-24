package vectors;

import java.io.*;

public class SVectors {

    private static IVectorFactory mFactory;

    /**
     *
     * @param vector - Input vector (that implements IVector)
     * @param num - Number for mult
     * @return - IVector where outputVector[i] =vector[i] * num
     */
    public static IVector mult(IVector vector, int num) {
        int size = vector.getSize();
        IVector tmp;
        try {
            tmp = createInstance(size);
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
     * @return - IVector where Vector[i] = lvl[i] + rvl[i]
     */
    public static IVector sum(IVector lvl, IVector rvl) {
        int sizeLvl = lvl.getSize();
        int sizeRvl = rvl.getSize();
        int minSize = (sizeLvl < sizeRvl) ? sizeLvl : sizeRvl;
        int maxSize = (sizeLvl > sizeRvl) ? sizeLvl : sizeRvl;
        IVector tmp;
        try {
            tmp = createInstance(maxSize);
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

        //continue fill new Vector with a tail of biggestVector
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
     * @return - IVector where Vector[i] = lvl[i] * rvl[i]
     */
    public static IVector scalarMult(IVector lvl, IVector rvl) {
        int sizeLvl = lvl.getSize();
        int sizeRvl = rvl.getSize();
        int minSize = (sizeLvl < sizeRvl) ? sizeLvl : sizeRvl;
        int maxSize = (sizeLvl > sizeRvl) ? sizeLvl : sizeRvl;
        IVector tmp;
        try {
            tmp = createInstance(maxSize);
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

        //continue fill new Vector with a tail of biggestVector
        IVector linkToBiggerVector = (sizeLvl > sizeRvl) ? lvl : rvl;
        for (; i < maxSize; ++i) {
            tmp.setElement(i, linkToBiggerVector.getElement(i));
        }

        return tmp;
    }

    /**
     * Write Vectors size and values into BYTE stream
     * @param v - input vector
     * @param output - Byte stream to write
     */
    public static void outputVector(IVector v, OutputStream output) {
        DataOutputStream out = new DataOutputStream(output);
        try {
            out.writeInt(v.getSize());
            for (int i = 0; i < v.getSize(); ++i) {
                out.writeDouble(v.getElement(i));
            }

        } catch (IOException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return;
    }


    /**
     * Read values from BYTE input stream
     * @param input - Byte Input Stream
     * @return new IVector
     */
    public static IVector inputVector(InputStream input) {
        IVector v = null;
        DataInputStream in = new DataInputStream(input);
        try {
            int size = in.readInt();
            v = createInstance(size);
            for (int i = 0; i < size; ++i) {
                v.setElement(i, in.readDouble());
            }
        } catch (IOException | VectorsExceptions.IncompatibleVectorSizesException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return v;
    }

    /**
     * Write vector values into OutputStream
     * @param v Input Vector
     * @param output OutputStream to write
     */
    public static void writeVector(IVector v, Writer output) {
        int size = v.getSize();
        PrintWriter out = new PrintWriter(output);
        out.write("" + size + " ");
        for (int i = 0; i < size; ++i) {
            out.write("" + v.getElement(i) + " ");
        }
        out.flush();
        return;
    }

    /**
     *
     * Create new Array Vector using values from symbol input stream
     * Input stream format: SIZE VALUE[0] ... VALUE[SIZE - 1]
     * @param input - input Reader
     * @return new Array Vector
     * @throws ArrayIndexOutOfBoundsException In case if there is not enough values in input stream
     */
    public static IVector readVector(Reader input) throws ArrayIndexOutOfBoundsException {
        BufferedReader in = new BufferedReader(input);
        IVector v = null;
        try {
            String s = in.readLine();
            String[] tokens = s.split(" "); //Split by spaces
            int size = Integer.parseInt(tokens[0]);
            v = createInstance(size);
            if (tokens.length < size + 1) {
                throw new ArrayIndexOutOfBoundsException("Not Enough values in input stream");
            }
            for (int i = 0; i < size; ++i) {
                v.setElement(i, Double.parseDouble(tokens[i + 1]));
            }

        } catch (IOException | VectorsExceptions.IncompatibleVectorSizesException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return v;
    }

    /**
     * Set Array Vector Factory
     * @param f - input factory
     * @throws NullPointerException - in case if input factory is null
     */
    public static void setFactory(IVectorFactory f) throws NullPointerException {
        if (f != null)
            mFactory = f;
        else
            throw new NullPointerException("Null input VectorFactory");
    }

    /**
     * Create Instance of IVector implementation using Size
     * @param size - needed size of vector
     * @return vector instance
     */
    public static IVector createInstance(int size)
                                     throws VectorsExceptions.IncompatibleVectorSizesException {
        if (size <= 0) {
            throw new VectorsExceptions.IncompatibleVectorSizesException("Zero or less input size");
        }
        if (mFactory == null) {
            mFactory =  new ArrayVector.ArrayVectorFactory();
        }
        return mFactory.createInstance(size);
    }


}
