package compiler.syntaxchecker;

import java.util.ArrayList;
import java.util.Optional;

import compiler.errors.SyntaxError;
import filehandler.Line;

/**
 * HeaderSyntaxChecker checks if a line is a valid header line.
 * @author yonggqiii
 */
public final class HeaderSyntaxChecker extends SyntaxChecker {

    public static final int NUMBER_OF_LEADING_EQUALS = 5;

    /*
     * The first line of code must be a header. A valid header is preceded by
     * any amount of whitespace and succeeded by five successive '=' signs.
     */
    @Override
    Optional<ArrayList<SyntaxError>> checkSyntax(Line... lines) {

        assert lines.length == 1;

        Line line = lines[0];
        int colIndex = 1;
        int numberOfEquals = 0;
        ArrayList<SyntaxError> errors = new ArrayList<>();

        try {
            // Get to first non whitespace character.
            while (true) {
                if (line.charAt(colIndex) != ' ') {
                    break;
                }
                colIndex++;
            }

            // Check if there are five successive = characters.
            while (true) {
                if (numberOfEquals >= NUMBER_OF_LEADING_EQUALS) {
                    break;
                }
                if (line.charAt(colIndex) != '=') {
                    throw new Exception();

                }
                colIndex++;
                numberOfEquals++;
            }
        } catch (Exception e) {
            errors.add(new SyntaxError("First line must be valid header line",
                    line, colIndex));
        }

        if (errors.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(errors);

    }

}
