package compiler;

import filehandler.AppFile;
import filehandler.presentation.Presentation;

/**
 * PresentationConverter converts a Presentation into an HTML AppFile.
 * @author yonggqiii
 */
public final class PresentationConverter {

    private Presentation presentation;
    private AppFile appFile;

    /**
     * Creates a PresentationConverter with the presentation it is meant to
     * convert.
     * 
     * @param p The presentation to convert.
     */
    public PresentationConverter(Presentation p) {
        this.presentation = p;
        this.appFile = new AppFile();
    }

    /**
     * Converts a presentation into an AppFile.
     * 
     * @return The resulting AppFile.
     */
    public AppFile convert() {
        appendHTMLHeader();
        return this.appFile;
    }

    /**
     * Appends the standard HTML header and the file title.
     */
    private void appendHTMLHeader() {

        String title = presentation.getTitle();
        String output = "";
        output += "<!DOCTYPE html><html lang=\"en\"><head>";
        output += "<meta charset=\"UTF-8\"><meta name=\"viewport\" ";
        output += "content=\"width=device-width, initial-scale=1.0\">";
        output += "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">";
        output += "<title>" + title + "</title></head><body>";
        appFile.append(output);

    }

    /**
     * Appends the stadard HTML closing tags to the output file.
     */
    private void appendHTMLEnd() {
        appFile.append("</body></html>");
    }
}