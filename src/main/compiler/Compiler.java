package compiler;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import filehandler.AppFile;
import filehandler.Line;
import compiler.errors.SyntaxError;
import compiler.syntaxchecker.CodeType;
import compiler.syntaxchecker.SyntaxChecker;
import compiler.styles.Style;

public class Compiler {

    int currentLine;
    final AppFile inputFile;
    final AppFile outputFile;
    final ArrayList<SyntaxError> syntaxErrors;
    final Set<Style> styles = new HashSet<>();

    /**
     * Creates a new compiler.
     */
    public Compiler(AppFile inputFile) {

        this.currentLine = 1;
        this.inputFile = inputFile;
        this.outputFile = new AppFile();
        this.syntaxErrors = new ArrayList<>();

    }

    /**
     * Compiles the input file into an html file.
     */
    public void compile() {

        try {
            // Compile header.
            String presentationName = getFileName();
            appendHTMLHeader(presentationName);

            // Compile styles.
            getStyles();

            appendHTMLEnd();
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /**
     * Checks if the input file has syntax errors.
     * @return  True if the input file contains syntax errors.
     */
    public boolean hasSyntaxErrors() {
        return syntaxErrors.size() > 0;
    }

    /**
     * Checks if the output file is empty.
     * @return  True if the output file is empty, false otherwise.
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
     * @return  The compiled output file.
     */
    public AppFile getOutputFile() {
        return this.outputFile;
    }

    /**
     * Appends the standard HTML header and the file title.
     * @param title     The title of the output file.
     */
    private void appendHTMLHeader(String title) {

        String output = "";
        output += "<!DOCTYPE html><html lang=\"en\"><head>";
        output += "<meta charset=\"UTF-8\"><meta name=\"viewport\" ";
        output += "content=\"width=device-width, initial-scale=1.0\">";
        output += "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">";
        output += "<title>" + title + "</title></head><body>";
        outputFile.append(output);

    }

    /**
     * Appends the stadard HTML closing tags to the output file.
     */
    private void appendHTMLEnd() {
        outputFile.append("</body></html>");
    }

    /**
     * Parses and returns the title of the presentation within the input file.
     * @return  The title of the presentation.
     * @throws  IndexOutOfBoundsException If reached EOF before finding line.
     */
    private String getFileName() throws IndexOutOfBoundsException {

        Line line = inputFile.getUntilActualCode(currentLine);
        currentLine = line.getOneBasedLineNumber();

        Optional<ArrayList<SyntaxError>> errors = SyntaxChecker
            .checkSyntax(CodeType.HEADER, line);
        if (errors.isPresent()) {
            for (SyntaxError e : errors.get()) {
                syntaxErrors.add(e);
            }
        }

        return line.toString().trim().substring(5).trim();

    }

    /**
     * Get styles. To be implemented.
     */
    private void getStyles() {

        ArrayList<String> styleSettings = new ArrayList<>();
        int startLine = currentLine;
        int endLine;
        while (true) {
            return;
        }

    }

}
