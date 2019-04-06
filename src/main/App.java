import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import compiler.Compiler;
import filehandler.AppFile;

/**
 * App class is the entry point of the program and handles arguments and errors.
 * 
 * @author yonggqiii
 */
public final class App {

    private static final ArrayList<String> HELP_FLAGS =
            new ArrayList<>(Arrays.asList(new String[] {"--help", "--HELP"}));

    private static final String DEFAULT_FILE_EXTENSION = ".pp";

    private static final String RUN_ERROR_NOT_ENOUGH_ARGUMENTS =
            "peapresent:" + " missing operands\n"
                    + "Try 'peapresent --help' for more information.";

    private static final String RUN_ERROR_TOO_MANY_ARGUMENTS =
            "peapresent:" + " redundant arguments";

    private static final String RUN_ERROR_NOTHING_TO_COMPILE =
            "peapresent:" + " input file is empty.";

    private static BufferedReader bf;
    private static BufferedWriter bw;
    private static Compiler c;
    private static AppFile inputFile;
    private static AppFile outputFile;

    /**
     * App constructor is hidden.
     */
    private App() {
    }

    /**
     * Starts the application, terminates upon receiving an exception.
     * 
     * @param  args        List of arguments entered by the user.
     */
    public static void main(String[] args) {

        try {
            checkIfAskedForHelp(args);
            checkIfTooManyArguments(args);
            readFile(args);
            compile();
            checkForCompilationErrors();
            writeOutputFile(args);
        } catch (RuntimeException | IOException e) {
            return;
        }

    }

    /**
     * Checks if the args passed into the application contains any help flags.
     * 
     * @param  args             The arguments passed in.
     * @throws RuntimeException If the user passed in a help flag.
     */
    private static void checkIfAskedForHelp(String[] args)
            throws RuntimeException {
        if (args.length > 0 && HELP_FLAGS.contains(args[0])) {
            showHelp();
            throw new RuntimeException();
        }
        return;
    }

    /**
     * Checks if there are too many arguments passed in to the application, and
     * prints the extra arguments.
     * 
     * @param args The arguments passed in.
     */
    private static void checkIfTooManyArguments(String[] args) {
        if (args.length > 2) {
            System.err.print(RUN_ERROR_TOO_MANY_ARGUMENTS);
            for (int i = 2; i < args.length; ++i) {
                System.err.print(" " + args[i]);
            }
            System.err.println();
        }
    }

    /**
     * Reads the file in the user's desktop and stores it as an {@link AppFile}.
     * 
     * @param  args         The arguments where the input file will be in.
     * @throws IOException  If there is something wrong with file input;
     */
    private static void readFile(String[] args) throws IOException {
        try {
            if (args.length < 2) {
                throw new ArrayIndexOutOfBoundsException();
            }
            readInputFile(args[0]);
        } catch (FileNotFoundException e) {
            System.err.println("File " + args[0]
                    + " cannot be found.\nEnsure that file contains "
                    + DEFAULT_FILE_EXTENSION + " extension.");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(RUN_ERROR_NOT_ENOUGH_ARGUMENTS);
            return;
        } finally {
            if (bf != null) {
                bf.close();
            }
        }

    }

    /**
     * Reads the input file given the file name.
     * 
     * @param args                      The name of the file.
     * @throws FileNotFoundException    If the file cannot be found.
     * @throws IOException              Other reasons.
     */
    private static void readInputFile(String args)
            throws FileNotFoundException, IOException {

        bf = new BufferedReader(new FileReader(args + DEFAULT_FILE_EXTENSION));
        ArrayList<String> fileContents = new ArrayList<>();

        while (true) {
            String toAdd = bf.readLine();
            if (toAdd == null) {
                break;
            }
            fileContents.add(toAdd);
        }

        inputFile = new AppFile(fileContents);
        bf.close();

    }

    /**
     * Compiles the input file into the output file if possible.
     */
    private static void compile() {

        Compiler c = new Compiler(inputFile);
        c.compile();

    }

    /**
     * Checks for and prints any compilation errors collected by the
     * {@link Compiler}.
     * 
     * @throws RuntimeException If there are compilation errors.
     */
    private static void checkForCompilationErrors() throws RuntimeException {

        if (c.hasSyntaxErrors()) {
            c.printSyntaxErrors();
            throw new RuntimeException();
        }

        if (c.isFileEmpty()) {
            System.err.println(RUN_ERROR_NOTHING_TO_COMPILE);
            throw new RuntimeException();
        }

    }

    /**
     * Writes the output file on the user's computer.
     * 
     * @param  args         The arguments passed in by the user.         
     * @throws IOException  If there is an issue with file output.
     */
    private static void writeOutputFile(String[] args) throws IOException {

        outputFile = c.getOutputFile();
        bw = new BufferedWriter(new FileWriter(args[1] + ".html"));
        bw.write(outputFile.toString());
        bw.close();

    }

    /**
     * Shows help to the user.
     */
    private static void showHelp() {

        // TODO: Show help information
        System.out.println("Hello");

    }

}
