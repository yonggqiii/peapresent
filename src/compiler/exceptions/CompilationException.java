package compiler.exceptions;

public class CompilationException extends RuntimeException {
    public CompilationException() {
        super();
    }

    public CompilationException(String msg) {
        super(msg);
    }
}