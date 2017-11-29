public class FirstTask {

    public static void main (String args[]) {

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

    public static class ArrayVector {

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

        public int getSize() {
            return mArray.length;
        }

        //may be return element
        public void setElement(int i, double value) {
            if (i > mArray.length - 1) {
                throw new ArrayIndexOutOfBoundsException();
            }
            mArrayChanged = true;
            mSorted = false;
            mArray[i] = value;
            return;
        }
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

        // FIXME: How to avoid overflow?
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

//Static methods:

        public static ArrayVector mult(ArrayVector vector, int num) {
            int size = vector.getSize();
            ArrayVector tmp = new ArrayVector(size);
            for (int i = 0; i < size; ++i) {
                tmp.setElement(i, vector.getElement(i) * num);
            }
            return tmp;
        }

        public static ArrayVector sum(ArrayVector lvl, ArrayVector rvl) {
            int sizeLvl = lvl.getSize();
            int sizeRvl = rvl.getSize();
            int minSize = (sizeLvl < sizeRvl) ? sizeLvl : sizeRvl;
            int maxSize = (sizeLvl > sizeRvl) ? sizeLvl : sizeRvl;
            ArrayVector tmp = new ArrayVector(maxSize);
            int i = 0;
            for (i = 0; i < minSize; ++i) {
                tmp.setElement(i, lvl.getElement(i) + rvl.getElement(i));
            }
            //return if same vectors size
            if (sizeLvl == sizeRvl) {
                return tmp;
            }

            //continue fill new ArrayVector with a tail of biggestVector
            ArrayVector linkToBiggerVector = (sizeLvl > sizeRvl) ? lvl : rvl;
            for (; i < maxSize; ++i) {
                tmp.setElement(i, linkToBiggerVector.getElement(i));
            }

            return tmp;
        }

        //still overflow problem
        public static ArrayVector scalarMult(ArrayVector lvl, ArrayVector rvl) {
            int sizeLvl = lvl.getSize();
            int sizeRvl = rvl.getSize();
            int minSize = (sizeLvl < sizeRvl) ? sizeLvl : sizeRvl;
            int maxSize = (sizeLvl > sizeRvl) ? sizeLvl : sizeRvl;
            ArrayVector tmp = new ArrayVector(maxSize);
            int i = 0;
            for (i = 0; i < minSize; ++i) {
                tmp.setElement(i, lvl.getElement(i) * rvl.getElement(i));
                System.out.println(" " + tmp.getElement(i));
            }
            //return if same vectors size
            if (sizeLvl == sizeRvl) {
                return tmp;
            }

            //continue fill new ArrayVector with a tail of biggestVector
            ArrayVector linkToBiggerVector = (sizeLvl > sizeRvl) ? lvl : rvl;
            for (; i < maxSize; ++i) {
                tmp.setElement(i, linkToBiggerVector.getElement(i));
            }

            return tmp;
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
}

//TODO: probably better to change to local size virable in order to save Time
//TODO: add private function like CheckChanged() (check if mArrayChanged = true -> set to false)
//TODO: add check of array size to all functions
