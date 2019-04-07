package filehandler.presentation;

/**
 * Presentation class allows the creation of presentations which hold the
 * properties of the presentation intended by the user.
 * @author yonggqiii
 */
public class Presentation {

    private String presentationTitle;

    /**
     * Creates a blank presentation.
     */
    public Presentation() {

    }

    /**
     * Sets the title of the presentation.
     * @param title The title of the presentation.
     */
    public void setTitle(String title) {
        presentationTitle = title;
    }

    /**
     * Gets the title of the presentation.
     * 
     * @return The title of the presentation.
     */
    public String getTitle() {
        return this.presentationTitle;
    }

}