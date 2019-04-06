package compiler.styles;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Styles.
 * @author yonggqiii
 */
public class Style {

    private List<String> tags;
    private Map<String, String> attributes;

    private static final ArrayList<String> VALID_ATTRIBUTE_NAMES =
            new ArrayList<>(Arrays.asList(new String[] {"font", "font-size"}));

    /**
     * Constructs a new Style.
     * @param tags The list of tags.
     */
    public Style(List<String> tags) {

        this.tags = tags;
        this.attributes = new HashMap<>();

    }

    /**
     * Adds attributes to the style.
     * @param attributeName hi.
     * @param attributeValue lol.
     */
    public void addAttribute(String attributeName, String attributeValue) {

        checkValidAttributeName(attributeName);
        attributes.put(attributeName, attributeValue);

    }

    /**
     * Checks if attribute name is valid.
     * @param attributeName lol.
     * @throws IllegalArgumentException lol.
     */
    public void checkValidAttributeName(String attributeName)
            throws IllegalArgumentException {

        if (!VALID_ATTRIBUTE_NAMES.contains(attributeName)) {
            throw new IllegalArgumentException();
        }

    }

}
