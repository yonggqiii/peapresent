package compiler.syntaxchecker;

import java.util.ArrayList;
import java.util.Optional;

import compiler.errors.SyntaxError;
import filehandler.Line;

/**
 * SyntaxChecker checks syntax of given lines of code.
 * @author yonggqiii
 */
public abstract class SyntaxChecker {

    /**
     * Checks the syntax of the given lines of code.
     * @param c     The type of code to be checked.
     * @param lines The lines of code to be checked.
     * @return      Any syntax errors observed.
     */
    public static Optional<ArrayList<SyntaxError>> checkSyntax(CodeType c,
            Line... lines) {

        SyntaxChecker sc;

        switch (c) {
            case HEADER:
                sc = new HeaderSyntaxChecker();
                break;
            case STYLE:
                sc = new StyleSyntaxChecker();
                break;
            default:
                // Temporary.
                sc = new HeaderSyntaxChecker();
                break;
        }

        return sc.checkSyntax(lines);

    }

    /**
     * Checks the syntax of the given lines of code.
     * @param lines The lines of code to be checked.
     * @return      Any syntax errors observed.
     */
    abstract Optional<ArrayList<SyntaxError>> checkSyntax(Line... lines);

}
