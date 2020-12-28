package textdecorators.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputDetails implements FileDisplayInterface {
    private FileProcessor input, keywords, misspelled;
    private FileWriter output;
    private List<String> readInput = new ArrayList<>();
    private List<String> readKeyword = new ArrayList<>();
    private List<String> readMispelled = new ArrayList<>();
    String inputLine, keywordLine, misspelledLine;
    String pattern = "^[a-zA-Z0-9\\.,\\s\\n\\r\\t\\f\\v]*$";

    /**
     * InputDetails Constructor
     * @param input
     * @param misspelled
     * @param keywords
     * @param fileName
     * @throws IOException
     */
    public InputDetails(FileProcessor input, FileProcessor misspelled, FileProcessor keywords, String fileName) throws IOException {
        this.input = input;
        this.misspelled = misspelled;
        this.keywords = keywords;
        output = new FileWriter(new File(fileName));
    }

    /**
     * Read input files input.txt, keywords.txt, misspelled.txt
     * @throws IOException
     */
    public void readFile() throws IOException {
        boolean emptyInputFile = false;
        boolean emptyKeywordFile = false;
        boolean emptyMispelledFile = false;

        while((inputLine = input.poll()) != null ) {
            emptyInputFile = true;
            if(inputLine.isEmpty()) {
                continue;
            }
            String wrd[] = inputLine.split("\\s+");
            for(String s:wrd) {
                if(!s.matches(pattern)) {
                    System.err.println("wrong character found: "+s);
                    System.exit(0);
                }
            }
            //readInput.add("\n");
            readInput.add(inputLine);
        }
        while((keywordLine = keywords.poll()) != null ) {
            emptyKeywordFile = true;
            if(!keywordLine.matches(pattern)) {
                System.err.println("wrong character found: "+keywordLine);
                System.exit(0);
            }
            readKeyword.add(keywordLine);
        }
        while((misspelledLine = misspelled.poll()) != null ) {
            emptyMispelledFile = true;
            if(!misspelledLine.matches(pattern)) {
                System.err.println("wrong character found: "+misspelledLine);
                System.exit(0);
            }
            readMispelled.add(misspelledLine);
        }

        if(emptyInputFile == false) {
            System.err.println("Found empty input.txt file..Exiting");
            System.exit(0);
        }
        if(emptyKeywordFile == false) {
            System.err.println("Found empty keywords.txt file..Exiting");
            System.exit(0);
        }
        if(emptyMispelledFile == false) {
            System.err.println("Found empty misspelled.txt file..Exiting");
            System.exit(0);
        }
    }

    /**
     * Getter to return input.txt file content
     * @return readInput of String type
     */
    public List<String> getReadInput() {
        return readInput;
    }

    /**
     * Getter to return keywords.txt file content
     * @return readKeyword of String type
     */
    public List<String> getReadKeyword() {
        return readKeyword;
    }

    /**
     * Getter to return misspelled.txt file content
     * @return readMispelled of String type
     */
    public List<String> getReadMispelled() {
        return readMispelled;
    }

    @Override
    public void writeToFile(String sentence) throws IOException {
        output.write(sentence+"\n");
    }

    @Override
    public void writeToFile() {

    }

    public void close() throws IOException {
        output.close();
    }

    /**
     * Debugging purpose
     * @return class name of String type
     */
    @Override
    public String toString() {
        return "InputDetails CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

