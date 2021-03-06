package vectors;

public class VectorsExceptions {

    /**
     * Exception - no need to declare
     */
    public static class VectorIndexOutOfBoundsException extends RuntimeException {
        /**
         * Constructor of Exception that tells that index is out of bounds
         * @param message Message for user
         */
        public VectorIndexOutOfBoundsException(String message) {
            super(message);
        }

        /**
         * Constructor of Exception that tells that index is out of bounds
         */
        public VectorIndexOutOfBoundsException() {
            super();
        }
    }

    /**
     * Exception - need to declare
     */
    public static class IncompatibleVectorSizesException extends Exception {
        /**
         * Constructor of Exception that tells that size of vector(s) is different
         * @param message Message for user
         */
        public IncompatibleVectorSizesException(String message) {
            super(message);
        }

        /**
         * Constructor of Exception that tells that index is out of bounds
         */
        public IncompatibleVectorSizesException() {
            super();
        }
    }
}
