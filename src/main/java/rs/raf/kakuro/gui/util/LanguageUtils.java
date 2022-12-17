package rs.raf.kakuro.gui.util;

import rs.raf.kakuro.gui.util.model.Language;

import java.io.IOException;
import java.util.Properties;

public class LanguageUtils {

    private static final String DEFAULT_VALUE = "NOT_FOUND";
    private static final String DISPLAY_NAME  = ".name";
    private static final String TOOLTIP       = ".tooltip";

    private static Properties properties = new Properties();

    /**
     * Loads language from the resource directory.
     * @param language language
     */
    public static void loadLanguage(Language language) {
        try {
            properties.load(ResourceUtils.getLanguageStream(language));
        }
        catch (IOException exception) {
            System.out.println(exception.getStackTrace());
        }
    }

    /**
     * Returns the name for the loaded language with the given key.
     * @param key key
     * @return name
     */
    public static String getNameProperty(String key) {
        return properties.getProperty(key + DISPLAY_NAME, DEFAULT_VALUE);
    }

    /**
     * Returns the tooltip for the loaded language with the given key.
     * @param key key
     * @return tooltip
     */
    public static String getTooltipProperty(String key) {
        return properties.getProperty(key + TOOLTIP, DEFAULT_VALUE);
    }

}
