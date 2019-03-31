package compiler.styles;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Style {

    List<String> tags;
    Map<String, String> attributes;

    private static ArrayList<String> VALID_ATTRIBUTE_NAMES = new ArrayList<>(
            Arrays.asList(new String[] { "font", "font-size" }));

    public Style(List<String> tags) {

        this.tags = tags;
        this.attributes = new HashMap<>();

    }

    public void addAttribute(String attributeName, String attributeValue) {

        checkValidAttributeName(attributeName);
        attributes.put(attributeName, attributeValue);

    }

    public void checkValidAttributeName(String attributeName)
            throws IllegalArgumentException {

        if (!VALID_ATTRIBUTE_NAMES.contains(attributeName)) {
            throw new IllegalArgumentException();
        }
        
    }

}