package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {
    /**
     * Top level search workflow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * Traverse a given directory and return all files
     * @param rootDir
     * @return
     */
    List<File> listFiles(String rootDir);

    /**
     * Read a file and return all the lines
     * Explain FileReader, BufferedReader, and character encoding
     * @param inputFile
     * @return
     */
    List<String> readFLines(File inputFile);

    /**
     * Check if a line contains the regex pattern (passed by user)
     * @param line
     * @return
     */
    boolean containsPattern(String line);

    /**
     * Write lines to a file
     * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
     * @param lines
     * @throws IOException
     */
    void writeToFile(List<String> lines) throws IOException;

    String getRootPath();

    void setRootPath(String rootPath);

    String getRegex();

    void setRegex(String regex);

    String getOutFile();

    void setOutFile(String outFile);
}
