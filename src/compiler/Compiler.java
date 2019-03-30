package compiler;

import java.util.ArrayList;

import filehandler.AppFile;
import compiler.exceptions.CompilationException;
import compiler.exceptions.SyntaxError;

public class Compiler {

    AppFile inputFile;
    AppFile outputFile;
    ArrayList<SyntaxError> syntaxErrors;
    int currentLine;

    public Compiler() {
        syntaxErrors = new ArrayList<>();
        currentLine = 0;
    }

    public boolean hasSyntaxErrors() {
        return syntaxErrors.size() > 0;
    }

    public void compile(AppFile inputFile) {

        this.inputFile = inputFile;
        ArrayList<String> outputFileContents = new ArrayList<>();
        String presentationName = getFileName();
        // TODO: Add file contents
        appendHTMLHeader(outputFileContents, presentationName);
        appendHTMLEnd(outputFileContents);
        outputFile = new AppFile(outputFileContents);
    }

    private void appendHTMLHeader(ArrayList<String> contents, String title) {
        String output = "";
        output += ("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\"><title>");
        output += (title);
        output += ("</title></head><body>");
        contents.add(output);
    }

    private void appendHTMLEnd(ArrayList<String> contents) {
        contents.add("</body></html>");
    }

    private String getFileName() {
        String line = inputFile.get();
        currentLine++;
        while (line.equals("")) {
            line = inputFile.get();
            currentLine++;
        }
        checkHeaderSyntax(line);
        return line.substring(5, line.length() - 5).trim();
    }

    private void checkHeaderSyntax(String line) {

        int lineIndex = 0;
        while (true) {
            if (lineIndex >= 5) {
                break;
            }
            if (line.charAt(lineIndex) != '#') {
                syntaxErrors.add(new SyntaxError(currentLine, "Invalid header line", line, lineIndex));
                return;
            }
            lineIndex++;
        }

        lineIndex = line.length() - 1;
        while (true) {
            if (lineIndex < line.length() - 5) {
                break;
            }
            if (line.charAt(lineIndex) != '#') {
                syntaxErrors.add(new SyntaxError(currentLine, "Invalid header line", line, lineIndex));
                return;
            }
            lineIndex--;
        }

    }

    public void printSyntaxErrors() {
        for (SyntaxError s : syntaxErrors) {
            System.err.println(s.toString());
        }
    }

    public AppFile getOutputFile() {
        return this.outputFile;
    }

}