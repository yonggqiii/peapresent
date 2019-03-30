package compiler.exceptions;

public class SyntaxError {

    private int lineNumber;
    private String message;
    private String code;
    private int indexOfError;

    public SyntaxError(int lineNumber, String message, String code, int indexOfError) {
        this.lineNumber = lineNumber;
        this.message = message;
        this.code = code;
        this.indexOfError = indexOfError;
    }

    @Override
    public String toString() {
        String output = "";
        output += String.format("In line %d: %s\n    %s\n    ", lineNumber, message, code);
        for (int i = 0; i < indexOfError; ++i) {
            output += " ";
        }
        output += "^";
        return output;
    }
}