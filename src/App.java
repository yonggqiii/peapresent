import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import filehandler.InputFile;

public class App {

    static BufferedReader bf;

    public static void main(String args[]) {
        try {
            readFile(args[0]);
        } catch (IOException e) {
            System.err.println(e);
        }
        return;
    }

    private static void readFile(String args) throws IOException {
        bf = new BufferedReader(new FileReader(args));
    }

}