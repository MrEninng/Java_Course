import vectors.*;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String... s) throws VectorsExceptions.IncompatibleVectorSizesException {

        InputStreamReader in = new InputStreamReader(System.in);
        OutputStreamWriter out = new OutputStreamWriter(System.out);

        IVector myVector = SVectors.readVector(in);
        SVectors.writeVector(myVector, out);
        System.out.flush();
    }

}
