package compiler.errors;

import filehandler.Line;

/**
 * Describes the syntax error with the location and message of the error.
 * @author yonggqiii
 */
public class SyntaxError {

    private final String message;
    private final Line code;
    private final int indexOfError;

    /**
     * Constructs a SyntaxError based on the given arguments.
     * 
     * @param message       The message to display to the user.
     * @param code          The line of code containing the syntax eror.
     * @param indexOfError  The one-based column containing the location of the
     *                      syntax error in the line.
     */
    public SyntaxError(String message, Line code, int indexOfError) {
        this.message = message;
        this.code = code;
        this.indexOfError = indexOfError;
    }

    @Override
    public String toString() {
        String output = "";
        output += String.format("In line %d: %s\n    %s\n    ",
                code.getOneBasedLineNumber(), message, code.toString());
        for (int i = 0; i < indexOfError - 1; ++i) {
            output += " ";
        }
        output += "^";
        return output;
    }
}
