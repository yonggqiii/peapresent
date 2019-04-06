package filehandler;

import java.util.ArrayList;

/**
 * AppFile contains the text that is to be included in the file.
 * 
 * @author yonggqiii
 */
public class AppFile {

    private ArrayList<Line> fileContents;

    /**
     * Constructs an empty AppFile.
     */
    public AppFile() {
        this.fileContents = new ArrayList<>();
    }

    /**
     * Constructs an AppFile instance given its file contents.
     * 
     * @param fileContents  The List of strings, each string representing a line
     *                      in the file.
     */
    public AppFile(ArrayList<String> fileContents) {

        this.fileContents = new ArrayList<>();
        for (int i = 0; i < fileContents.size(); i++) {
            append(fileContents.get(i));
        }

    }

    /**
     * Add String(s) to the file's content.
     * 
     * @param s The line(s) to be added.
     */
    public void append(String... s) {

        for (String string : s) {
            fileContents.add(new Line(fileContents.size() + 1, string));
        }

    }

    /**
     * Add Line(s) to the file's content.
     * 
     * @param l The lines to be added.
     */
    public void append(Line... l) {

        for (Line line : l) {
            fileContents.add(line);
        }

    }

    /**
     * Gets a line in the file.
     * 
     * @param   oneBasedIndex               The line to be retrieved.
     * @return                              The line requested.
     * @throws  IndexOutOfBoundsException   If it is the end of the file.
     */
    public Line get(int oneBasedIndex) throws IndexOutOfBoundsException {
        return fileContents.get(oneBasedIndex - 1);
    }

    /**
     * Gets the first non-comment or empty line starting from a given one-
     * based index.
     * 
     * @param   oneBasedIndex               The starting index.
     * @return                              The non-comment or nonempty line.
     * @throws  IndexOutOfBoundsException   If reached EOF before finding a non
     *                                      comment or nonempty line.
     */
    public Line getUntilActualCode(int oneBasedIndex)
            throws IndexOutOfBoundsException {

        Line line;

        do {
            line = get(oneBasedIndex);
            oneBasedIndex++;
        } while (line.isCommentOrEmpty());

        return line;

    }

    /**
     * Checks if the file is empty.
     * @return  True if the file has no contents, false otherwise.
     */
    public boolean isEmpty() {
        return this.fileContents.isEmpty();
    }

    @Override
    public String toString() {
        String output = "";
        for (Line s : fileContents) {
            output += s.toString();
            output += "\n";
        }
        return output;
    }

}
