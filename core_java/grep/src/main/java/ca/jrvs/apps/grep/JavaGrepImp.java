package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class JavaGrepImp implements JavaGrep {

    final Logger logger = LoggerFactory.getLogger((JavaGrep.class));

    private String regex;
    private String rootPath;
    private String outFile;

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try {
            javaGrepImp.process();
        } catch (Exception e) {
            javaGrepImp.logger.error("Error: Unable to process", e);
        }
    }

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();
        List<File> files = listFiles(getRootPath());
        for (File file : files) {
            Stream<String> fileContent = readFLines(file);
            fileContent.forEach(line -> {
                if (containsPattern(line)) {
                    matchedLines.add(line);
                }
            });
        }
        writeToFile(matchedLines);
//        logger.info("Process has been completed");
    }

    @Override
    public List<File> listFiles(String rootDir) {
        List<File> result = new ArrayList<>();
        File directoryPath = new File(rootDir);
        File[] filesList = directoryPath.listFiles();
        if (filesList != null) {
            for (File file : filesList) {
                if (file.isFile()) {
                    result.add(file);
                } else if (file.isDirectory()) { //make recursive call if it's a directory
                    result.addAll(listFiles(file.getAbsolutePath()));
                }
            }
        }
        return result;
    }

    @Override
    public Stream<String> readFLines(File inputFile) {
        try {
            Stream<String> result = Files.lines(Paths.get(inputFile.getAbsolutePath()));
            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error: File not found!", e);
        } catch (Exception e) {
            throw new RuntimeException("Unexpected Error", e);
        }
    }

    @Override
    public boolean containsPattern(String line) {
        return Pattern.matches(getRegex(), line);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        try (PrintWriter myWriter = new PrintWriter(getOutFile())) {
            for (String line : lines) {
                myWriter.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        }
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}
