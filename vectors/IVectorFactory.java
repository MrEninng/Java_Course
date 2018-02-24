package vectors;

public interface IVectorFactory {
    /**
     * Create Instance of IVector implementation using Size
     * @param size - needed size of vector
     * @return vector instance
     */
    IVector createInstance(int size);
}
