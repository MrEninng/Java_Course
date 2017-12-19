package vectors;

public class ArrayVector implements IVector {

    /**
     * Create Vector with
     * @param size - Vector size
     * @throws VectorsExceptions.IncompatibleVectorSizesException in case of negative size
     */
    public ArrayVector(int size) throws VectorsExceptions.IncompatibleVectorSizesException {
        if (size > 0) {
            mArray = new double[size];
            mMax = 0;
            mMin = 0;
            mAverage = 0;
            mNorm = 0;
            mArrayChanged = true;
            mSorted = false;
        } else {
            throw new VectorsExceptions.IncompatibleVectorSizesException(
                                            "Can not create vector with negative size");
        }
    }

    /**
     * Return size of vector
     * @return int Size - size of vector
     */
    @Override
    public int getSize() {
        return mArray.length;
    }

    /**
     * Set element by index
     * @param i - index in vector
     * @param value - setting value
     */
    @Override
    public void setElement(int i, double value) {
        if (i > mArray.length - 1) {
            throw new VectorsExceptions.VectorIndexOutOfBoundsException();
        }
        mArrayChanged = true;
        mSorted = false;
        mArray[i] = value;
        return;
    }

    /**
     *
     * @param i - index in vector
     * @return - value by index
     */
    @Override
    public double getElement(int i) {
        if (i > mArray.length - 1) {
            throw new VectorsExceptions.VectorIndexOutOfBoundsException();
        }
        return mArray[i];
    }

    /**
     *
     * @param value - target value to find
     * @return - index in vector in case if value was found; else returns -1
     */
    public int find(double value) {
        for (int i = 0; i < mArray.length ; ++i) {
            if (mArray[i] == value) {
                return i;
            }
        }
        //Did not found;
        return -1;
    }

    /**
     * Sort vector
     */
    public void sort() {
        if (mSorted == true) {
            return;
        }
        mSorted = true;
        for (int i = 0; i < mArray.length; ++i) {
            for (int j = i + 1; j < mArray.length; ++j) {
                if (mArray[j] < mArray[i]) {
                    double tmp = mArray[i];
                    mArray[i] = mArray[j];
                    mArray[j] = tmp;
                }
            }
        }
    }

    /**
     * Calculate norm of vector: Norm = sqrt(E(i..n)Sum(Vector[i]^2))
     * @return Vector Norm
     */
    @Override
    public double getNorm() {
        if (mArrayChanged == false) {
            return mNorm;
        }
        mArrayChanged = false;
        double sum = 0;
        for (int i = 0; i < mArray.length; ++i) {
            sum += mArray[i] * mArray[i];
        }
        mNorm = Math.sqrt(sum);
        return mNorm;
    }

    /**
     * Calculate Average value of vector
     * @return Average value
     */
    //FIXME: Find a way - How to avoid overflow
    public double getAverage() {
        if (mArrayChanged == false) {
            return mAverage;
        }
        mArrayChanged = false;

        double sum = 0;
        for (int i = 0; i < mArray.length; ++i) {
            sum += mArray[i];
        }
        mAverage = (sum / mArray.length);

        return mAverage;
    }

    /**
     * Find Maximum value in vector
     * @return Max
     * @throws VectorsExceptions.IncompatibleVectorSizesException
     */
    public double getMax() throws VectorsExceptions.IncompatibleVectorSizesException {
        if (mArrayChanged == false) {
            return mMax;
        }

        if (mArray.length == 0) {
            // can not be such condition
            throw new VectorsExceptions.IncompatibleVectorSizesException();
        }

        mArrayChanged = false;

        mMax = mArray[0];
        for (int i = 0; i < mArray.length; ++i) {
            if (mArray[i] > mMax) {
                mMax = mArray[i];
            }
        }
        return mMax;
    }

    /**
     * Find Minimum Value in vector
     * @return Min
     * @throws VectorsExceptions.IncompatibleVectorSizesException
     */
    public double getMin() throws VectorsExceptions.IncompatibleVectorSizesException {
        if (mArrayChanged == false) {
            return mMin;
        }

        if (mArray.length == 0) {
            throw new VectorsExceptions.IncompatibleVectorSizesException();
        }

        mArrayChanged = false;

        mMax = mArray[0];
        for (int i = 0; i < mArray.length; ++i) {
            if (mArray[i] < mMax) {
                mMax = mArray[i];
            }
        }
        return mMax;
    }

    //variables:
    private double[] mArray;
    private double mAverage;
    private double mMax;
    private double mMin;
    private double mNorm;
    private boolean mArrayChanged;
    private boolean mSorted;
}

//TODO: probably better to change to local size variable in order to save Time
//TODO: add private function like CheckChanged() (check if mArrayChanged = true -> set to false)
//TODO: add check of array size to all functions