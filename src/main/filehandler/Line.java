package filehandler;

/**
 * Line specifies the line number and content of a line of code.
 * @author yonggqiii
 */
public class Line {

    private int oneBasedLineNumber;
    private final String code;

    /**
     * Constructs a line based on a one-based line number and the content of
     * the line.
     * @param oneBasedLineNumber    The line number.
     * @param code                  The content of the line.
     */
    public Line(int oneBasedLineNumber, String code) {

        this.oneBasedLineNumber = oneBasedLineNumber;
        this.code = code;

    }

    /**
     * Retrieves the character at the given one-based column index.
     * @param oneBasedColumnIndex           The one based column index.
     * @return                              The character at that index.
     * @throws IndexOutOfBoundsException    If reached end of line.
     */
    public char charAt(int oneBasedColumnIndex)
            throws IndexOutOfBoundsException {
        return code.charAt(oneBasedColumnIndex - 1);
    }

    /**
     * Gets the line number of this line.
     * @return  The line number of this line.
     */
    public int getOneBasedLineNumber() {
        return this.oneBasedLineNumber;
    }

    /**
     * Retrieves a deep copy of this line.
     * @return A deep copy of this line.
     */
    public Line clone() {
        return new Line(this.oneBasedLineNumber, this.code);
    }

    /**
     * Checks if this line is a comment or is empty.
     * @return  True if this line is a comment or is empty, false otherwise.
     */
    public boolean isCommentOrEmpty() {

        for (int i = 0; i < code.length(); ++i) {
            if (code.charAt(i) == '#') {
                return true;
            }
            if (code.charAt(i) != ' ' && code.charAt(i) != '\t') {
                return false;
            }
        }

        return true;

    }

    @Override
    public String toString() {
        return this.code;
    }

}