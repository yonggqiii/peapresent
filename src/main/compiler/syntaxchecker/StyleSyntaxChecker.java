package compiler.syntaxchecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import compiler.errors.SyntaxError;
import filehandler.Line;

/**
 * StyleSyntaxChecker checks if the lines denoted as styles is correctly
 * written.
 * @author yonggqiii
 */
public final class StyleSyntaxChecker extends SyntaxChecker {

    private ArrayList<SyntaxError> errors = new ArrayList<>();

    public static final ArrayList<String> PERMISSIBLE_ATTRIBUTE_NAMES =
            new ArrayList<>(Arrays
                    .asList(new String[] {"transition", "font", "font-size",
                            "font-weight", "vertical-align", "text-align"}));

    /**
     * Checks if the given lines allow the compiler to generate the styles.
     * 
     * @param lines The lines of code that supposedly denote the
     *              styles of the presentation.
     * @return      Any syntax errors observed by the compiler.
     */

    Optional<ArrayList<SyntaxError>> checkSyntax(Line... lines) {

        checkForIllegalAttributeNames(lines);

        if (this.errors.size() < 1) {
            return Optional.empty();
        }

        return Optional.of(this.errors);

    }

    /**
     * Checks if the lines have any illegal attribute names.
     * @param lines The lines to check.
     */
    private void checkForIllegalAttributeNames(Line... lines) {

        for (int i = 0; i < lines.length; ++i) {
            int[] indices = lines[i].indicesOfChar(':');
            iterateColonsInLine(lines, indices, i);
        }

    }

    /**
     * Check that all colons in this line are preceded by a valid attribute
     * name.
     * 
     * @param lines     The lines of code.
     * @param indices   The indices that contain the colons.
     * @param i         The ith line of code checking (relative).
     */
    private void iterateColonsInLine(Line[] lines, int[] indices, int i) {

        for (int j = 0; j < indices.length; ++j) {
            int lastIndex = indices[j] - 1;
            findWord(lines, indices, i, j, lastIndex);
        }
    }

    /**
     * Find the word before the colon and check if it is a legal attribute name.
     * 
     * @param lines     The lines of code.
     * @param indices   The index of the colon.
     * @param i         The ith line being checked.
     * @param j         The jth colon being checked.
     * @param lastIndex The index containing the last letter of the word.
     */
    private void findWord(Line[] lines, int[] indices, int i, int j,
            int lastIndex) {

        int k;
        for (k = 0; i - k >= 0; ++k) {
            while (lastIndex > 0 && (lines[i - k].charAt(lastIndex) == ' '
                    || lines[i - k].charAt(lastIndex) == '\n')) {
                lastIndex--;
            }
            if (lastIndex >= 1) {
                break;
            }

            if (i - k == 0) {
                errors.add(new SyntaxError("No attribute name provided",
                        lines[i], indices[j] - 1));
                return;
            }
            lastIndex = lines[i - k - 1].length();
        }
        int firstIndex = findIndexOfFirstCharacter(lines[i - k], lastIndex);

        String word =
                lines[i - k].toString().substring(firstIndex - 1, lastIndex);
        checkIfWordIsLegalAttribute(lines[i - k], firstIndex, word);

    }

    /**
     * Finds the index of the first character of a word given its last index.
     * 
     * @param line      The line the word is in.
     * @param lastIndex The index of the word's last character.
     * @return          The index of the first character.
     */
    private int findIndexOfFirstCharacter(Line line, int lastIndex) {
        int firstIndex = lastIndex;
        while (firstIndex > 1 && line.charAt(firstIndex) != ' '
                && line.charAt(firstIndex) != '\n') {
            firstIndex--;
        }
        if (line.charAt(firstIndex) == ' ' || line.charAt(firstIndex) == '\n') {
            firstIndex++;
        }
        return firstIndex;
    }

    /**
     * Checks if the word is a valid attribute.
     * 
     * @param line          The line that contains this word.
     * @param firstIndex    The index of the first character of this word.
     * @param word          The word to check.
     */
    private void checkIfWordIsLegalAttribute(Line line, int firstIndex,
            String word) {

        if (!PERMISSIBLE_ATTRIBUTE_NAMES.contains(word)) {
            errors.add(new SyntaxError("Cannot resolve attribute " + word, line,
                    firstIndex));
        }

    }

}