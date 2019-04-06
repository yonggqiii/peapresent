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

    private static ArrayList<String> helpFlags =
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
    private static AppFile inputFile;
    private static AppFile outputFile;

    /**
     * App constructor is hidden.
     */
    private App() {
    }

    /**
     * Driver method.
     * @param  args        List of arguments entered by the user.
     * @throws IOException If something goes wrong with file I/O.
     */
    public static void main(String[] args) throws IOException {

        // Checks if user enters help flag.
        if (args.length > 0 && helpFlags.contains(args[0])) {
            showHelp();
            return;
        }

        // Prompts user if too many arguments were entered.
        if (args.length > 2) {
            System.err.print(RUN_ERROR_TOO_MANY_ARGUMENTS);
            for (int i = 2; i < args.length; ++i) {
                System.err.print(" " + args[i]);
            }
            System.err.println();
        }

        // Read the input file.
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

        // Begin compiation process.
        Compiler c = new Compiler(inputFile);
        c.compile();

        // Do not write file if input has syntax errors.
        if (c.hasSyntaxErrors()) {
            c.printSyntaxErrors();
            return;
        }

        if (c.isFileEmpty()) {
            System.err.println(RUN_ERROR_NOTHING_TO_COMPILE);
            return;
        }

        // If compilation succeeds with no syntax errors, write the output file.
        outputFile = c.getOutputFile();
        bw = new BufferedWriter(new FileWriter(args[1] + ".html"));
        bw.write(outputFile.toString());
        bw.close();

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
     * Shows help to the user.
     */
    private static void showHelp() {

        // TODO: Show help information
        System.out.println("Hello");

    }

}
