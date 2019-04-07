package filehandler;

import java.util.stream.IntStream;

/**
 * Line specifies the line number and content of a line of code.
 * @author yonggqiii
 */
public class Line {

    private int oneBasedLineNumber;
    private final String code;
    private final String comment;

    /**
     * Constructs a line based on a one-based line number and the content of
     * the line.
     * @param oneBasedLineNumber    The line number.
     * @param line                  The content of the line.
     */
    public Line(int oneBasedLineNumber, String line) {

        this.oneBasedLineNumber = oneBasedLineNumber;
        int comment;
        for (comment = 0; comment < line.length(); ++comment) {
            if (line.charAt(comment) == '#') {
                break;
            }
        }
        this.code = line.substring(0, comment);
        this.comment = line.substring(comment);

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
     * Returns the length of this line.
     * @return  The length of this line.
     */
    public int length() {
        return code.length();
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
            if (!Character.isWhitespace(code.charAt(i))) {
                return false;
            }
        }

        return true;

    }

    @Override
    public String toString() {
        return this.code + this.comment;
    }

    /**
     * Checks if the line has three leading equals signs.
     * @return If it is true.
     */
    public boolean hasThreeLeadingEquals() {
        int i;
        int numOfEquals = 0;
        for (i = 0; i < code.length(); ++i) {
            if (code.charAt(i) != ' ' && code.charAt(i) != '\n') {
                break;
            }
        }
        while (i < code.length()) {
            if (code.charAt(i) == '=') {
                numOfEquals++;
            } else {
                break;
            }
            i++;
        }
        return numOfEquals == 3;
    }

    /**
     * Finds the indices of a given character.
     * @param c The character to find.
     * @return  The one based indices where this character is found.
     */
    public int[] indicesOfChar(char c) {
        return IntStream.rangeClosed(1, code.length())
                .filter(x -> charAt(x) == c).toArray();
    }

}
