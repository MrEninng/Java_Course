package vectors;

public class ArrayVector implements IVector {

    public ArrayVector(int size) {
        if (size > 0) {
            mArray = new double[size];
            mMax = 0;
            mMin = 0;
            mAverage = 0;
            mNorm = 0;
            mArrayChanged = true;
            mSorted = false;
        } else {
            throw new NegativeArraySizeException();
        }
    }

    @Override
    public int getSize() {
        return mArray.length;
    }

    //may be return element
    @Override
    public void setElement(int i, double value) {
        if (i > mArray.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        mArrayChanged = true;
        mSorted = false;
        mArray[i] = value;
        return;
    }

    @Override
    public double getElement(int i) {
        if (i > mArray.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return mArray[i];
    }

    public int find(double value) {
        for (int i = 0; i < mArray.length ; ++i) {
            if (mArray[i] == value) {
                return i;
            }
        }
        //Did not found;
        return -1;
    }

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

    public double getMax() {
        if (mArrayChanged == false) {
            return mMax;
        }

        if (mArray.length == 0) {
            // can not be such condition
            throw new IllegalStateException();
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

    public double getMin() {
        if (mArrayChanged == false) {
            return mMin;
        }

        if (mArray.length == 0) {
            throw new IllegalStateException();
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

//TODO: probably better to change to local size virable in order to save Time
//TODO: add private function like CheckChanged() (check if mArrayChanged = true -> set to false)
//TODO: add check of array size to all functions