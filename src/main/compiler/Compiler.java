package compiler;

import static compiler.syntaxchecker.HeaderSyntaxChecker.NUMBER_OF_LEADING_EQUALS;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import filehandler.AppFile;
import filehandler.Line;
import filehandler.presentation.Presentation;
import compiler.errors.SyntaxError;
import compiler.syntaxchecker.CodeType;
import compiler.syntaxchecker.SyntaxChecker;

/**
 * Compiles input file into a presentation.
 * @author yonggqiii
 */
public final class Compiler {

    private int currentLine;
    private final AppFile inputFile;
    private AppFile outputFile;
    private final List<SyntaxError> syntaxErrors;
    private final Presentation presentation;

    /**
     * Creates a new compiler.
     * @param inputFile The file that the user wants to compile.
     */
    public Compiler(AppFile inputFile) {

        this.currentLine = 1;
        this.inputFile = inputFile;
        this.syntaxErrors = new ArrayList<>();
        this.presentation = new Presentation();

    }

    /**
     * Compiles the input file into an html file.
     */
    public void compile() {

        try {

            getPresentationTitle();
            getStyles();
            PresentationConverter converter =
                    new PresentationConverter(presentation);
            outputFile = converter.convert();
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /**
     * Checks if the input file has syntax errors.
     * 
     * @return True if the input file contains syntax errors.
     */
    public boolean hasSyntaxErrors() {
        return syntaxErrors.size() > 0;
    }

    /**
     * Checks if the output file is empty.
     * 
     * @return True if the output file is empty, false otherwise.
     */
    public boolean isFileEmpty() {
        return outputFile.isEmpty();
    }

    /**
     * Prints the syntax errors observed by this compiler to stderr.
     */
    public void printSyntaxErrors() {
        syntaxErrors.stream().forEach(s -> {
            System.err.println(s.toString());
            System.err.println();
        });
    }

    /**
     * Retrieves the compiled output.
     * 
     * @return The compiled output file.
     */
    public AppFile getOutputFile() {
        return this.outputFile;
    }

    /**
     * Gets the next line of the file.
     * @return The next line.
     */
    private Line getNextLine() {
        Line line = inputFile.getUntilActualCode(currentLine);
        currentLine = line.getOneBasedLineNumber();
        currentLine++;
        return line;
    }

    /**
     * Parses and stores the title of the presentation.
     * 
     * @throws IndexOutOfBoundsException If reached EOF before finding line.
     */
    private void getPresentationTitle() throws IndexOutOfBoundsException {

        Line line = getNextLine();
        getSyntaxErrors(CodeType.HEADER, line);
        this.presentation.setTitle(line.toString().trim()
                .substring(NUMBER_OF_LEADING_EQUALS).trim());

    }

    /**
     * Get the styles from the directives of the input file.
     * 
     * @throws IndexOutOfBoundsException If reached the end of the file.
     */
    private void getStyles() throws IndexOutOfBoundsException {
        List<Line> lines = new ArrayList<>();
        while (true) {
            Line line = getNextLine();
            if (line.hasThreeLeadingEquals()) {
                currentLine--;
                break;
            }
            lines.add(line);
        }
        getSyntaxErrors(CodeType.STYLE, lines.toArray(new Line[0]));

    }

    /**
     * Gets and stores any syntax errors observed.
     * @param c     The code type.
     * @param lines The lines of code to be checked.
     */
    private void getSyntaxErrors(CodeType c, Line... lines) {
        Optional<ArrayList<SyntaxError>> errors =
                SyntaxChecker.checkSyntax(c, lines);
        if (errors.isPresent()) {
            for (SyntaxError e : errors.get()) {
                syntaxErrors.add(e);
            }
        }
    }

}
