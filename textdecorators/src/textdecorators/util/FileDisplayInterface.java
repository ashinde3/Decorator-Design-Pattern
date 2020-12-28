package textdecorators.util;

import java.io.IOException;

public interface FileDisplayInterface {
    /**
     * Declared writeToFile method for writing output to file
     * @param sentence
     */
    void writeToFile(String sentence) throws IOException;

    void writeToFile();
}
