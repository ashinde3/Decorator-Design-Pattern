package textdecorators.driver;

import textdecorators.*;
import textdecorators.util.FileDisplayInterface;
import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetails;
import textdecorators.util.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

public class Driver {
    private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

    public static void main(String[] args) throws IllegalArgumentException {

        /*
         * As the build.xml specifies the arguments as input,output or metrics, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         */
        try {
            if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}")) || (args[2].equals("${keywords}")) || (args[3].equals("${output}")) || (args[4].equals("${debug}"))) {
                System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
                System.exit(0);
            }
            System.out.println("Let's start Assignment 5..");
            System.out.println();
            /**
             * Instantiations to read input files cmd line arguments
             */
            FileProcessor input = new FileProcessor(args[0]);
            FileProcessor misspelled = new FileProcessor(args[1]);
            FileProcessor keywords = new FileProcessor(args[2]);
            int debugValue = Integer.parseInt(args[4]);
            if(!(debugValue > 0 && debugValue <=4)) {
                throw new NumberFormatException("Debug value should be between 1 and 4 including");
            }
            Logger.setDebugValue(debugValue);

            /**
             * Decorators instantiations
             */
            InputDetails inputD = new InputDetails(input, misspelled, keywords, args[3]);
            AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
            AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
            AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
            AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);
            inputD.readFile();
            mostFreqWordDecorator.processInputDetails();

            ((FileDisplayInterface) inputD).writeToFile();
            input.close();
            misspelled.close();
            keywords.close();
            inputD.close();
        }
        /**
         * Exceptions handling
         */
        catch (FileNotFoundException file) {
            System.err.println("No File found...Exiting");
            System.err.println(file.getMessage());
            file.printStackTrace();
            System.exit(0);
        }
        catch (IOException io) {
            System.err.println("Invalid I/O processed...Exiting");
            System.err.println(io.getMessage());
            io.printStackTrace();
            System.exit(0);
        }
        catch(InvalidPathException path) {
            System.err.println("Invalid path...Exiting");
            System.err.println(path.getMessage());
            path.printStackTrace();
            System.exit(0);
        }
        catch(SecurityException security) {
            System.err.println("Security Exception check...Exiting");
            System.err.println(security.getMessage());
            security.printStackTrace();
            System.exit(0);
        }
        catch(NumberFormatException number) {
            System.err.println("Invalid number detected...Exiting");
            System.err.println(number.getMessage());
            number.printStackTrace();
            System.exit(0);
        }
        catch(IllegalArgumentException arg) {
            System.err.println("Illegal arguments...Exiting");
            System.err.println(arg.getMessage());
            arg.printStackTrace();
            System.exit(0);
        }
        catch(NullPointerException nullPtr) {
            System.err.println("Null pointer exception...Exiting");
            System.err.println(nullPtr.getMessage());
            nullPtr.printStackTrace();
            System.exit(0);
        }
        finally {

        }
    }
}

