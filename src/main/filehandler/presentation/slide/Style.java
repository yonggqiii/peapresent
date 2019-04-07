package filehandler.presentation.slide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Style contains the styles of a slide, much like css styles.
 * @author yonggqiii
 */
public class Style {

    private final String tag;
    private final Map<String, String> attributes;
    private final List<Style> innerStyles;

    /**
     * Default constructor of a style.
     * 
     * @param tag The tag name of thie style.
     */
    public Style(String tag) {
        this.tag = tag;
        innerStyles = new ArrayList<>();
        attributes = new HashMap<>();
    }

    /**
     * Add an attribute to the style.
     * 
     * @param key   The name of the attribute.
     * @param value The value of the attribute.
     */
    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    /**
     * Creates a new inner style within this style.
     * 
     * @param tag The tag of the inner style.
     */
    public void addInnerStyle(String tag) {
        innerStyles.add(new Style(tag));
    }
}