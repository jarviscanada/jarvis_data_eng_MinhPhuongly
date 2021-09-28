package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp{
    public static void main(String[] args) {
        if (args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }
        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try{
            javaGrepImp.process();
        } catch (Exception e){
            javaGrepImp.logger.error("Error: Unable to process", e);
        }
    }

    @Override
    public List<File> listFiles(String rootDir) throws RuntimeException {
        List<File> result = new ArrayList<>();
        try{
            Files.walk(Paths.get(rootDir)) //Files.walk will recursively scan through the directory
                    .filter(Files::isRegularFile)
                    .forEach(file-> result.add(file.toFile()));
        }
        catch (IOException e){
            throw new RuntimeException("Error: IO Exception issue",e);
        }
        return result;
    }

    @Override
    public List<String> readFLines(File inputFile) {
        List<String> result = new ArrayList<>();
        try{
            Files.lines(inputFile.toPath())
                    .forEach(line -> result.add(line));
        }
        catch (FileNotFoundException e){
            throw new RuntimeException("Error: File not found!",e);
        }
        catch (Exception e){
            throw new RuntimeException("Error happened!!",e);
        }
        return result;
    }

}
