package filehandler;

import java.util.ArrayList;

/**
 * AppFile contains the text that is to be included in the file.
 * 
 * @author yonggqiii
 */
public class AppFile {

    private ArrayList<String> fileContents;

    /**
     * Constructs an AppFile instance given its file contents.
     * 
     * @param fileContents The List of strings, each string representing a line in
     *                     the file.
     */
    public AppFile(ArrayList<String> fileContents) {
        this.fileContents = fileContents;
    }

    public void addContent(String s) {
        fileContents.add(s);
    }

    public String get() {
        return fileContents.remove(0);
    }

    @Override
    public String toString() {
        String output = "";
        for (String s : fileContents) {
            output += s;
            output += "\n";
        }
        return output;
    }

}