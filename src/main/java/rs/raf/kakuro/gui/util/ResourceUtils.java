package rs.raf.kakuro.gui.util;

import rs.raf.kakuro.gui.util.model.Fonts;
import rs.raf.kakuro.gui.util.model.KeyStrokes;
import rs.raf.kakuro.gui.util.model.Language;

import java.io.InputStream;
import java.net.URL;

public class ResourceUtils {

    private static final String separator = "/";

    private static final String propertiesFile = ".properties";
    private static final String pngFile = ".png";

    private static final String imagesRoot    = "images";
    private static final String keyStrokeRoot = "keystrokes";
    private static final String languageRoot  = "languages";
    private static final String themeRoot     = "themes";
    private static final String fontRoot      = "fonts";

    //region Auxiliary Methods

    /**
     * Returns the resource URL.
     * @param resource resource
     * @return resource URL
     */
    private static URL getResource(String resource) {
        return ResourceUtils.class.getResource(resource);
    }

    /**
     * Returns the resource stream.
     * @param resource resource
     * @return resource stream
     */
    private static InputStream getStream(String resource) {
        return ResourceUtils.class.getResourceAsStream(resource);
    }

    //endregion

    //region Image Resource Utilities

    /**
     * Returns the image directory.
     * @return image directory
     */
    private static String getImageResourceDirectory() {
        return separator + imagesRoot + separator;
    }

    /**
     * Returns the image path.
     * @param imageName image name
     * @return image path
     */
    public static URL getImagePath(String imageName) {
        return getResource(getImageResourceDirectory() + imageName + pngFile);
    }

    //endregion

    //region KeyStrokes Utilities

    /**
     * Returns the keystroke's directory.
     * @return keystrokes directory
     */
    private static String getKeyStrokesResourceDirectory() {
        return separator + keyStrokeRoot + separator;
    }

    /**
     * Returns the keystrokes stream.
     * @param keyStrokes keystorkes
     * @return keystrokes stream
     */
    public static InputStream getKeyStrokesStream(KeyStrokes keyStrokes) {
        return getStream(getKeyStrokesResourceDirectory() + keyStrokes.getId() + propertiesFile);
    }

    //endregion

    //region Language Utilities

    /**
     * Returns the language directory.
     * @return language directory
     */
    private static String getLanguageResourceDirectory() {
        return separator + languageRoot + separator;
    }

    /**
     * Returns the language stream.
     * @param language language
     * @return language stream
     */
    public static InputStream getLanguageStream(Language language) {
        return getStream(getLanguageResourceDirectory() + language.getId() + propertiesFile);
    }

    //endregion

    //region Theme Utilities

    /**
     * Returns the theme directory.
     * @return theme directory
     */
    private static String getThemeResourceDirectory() {
        return separator + themeRoot + separator;
    }

    /**
     * Returns the theme URL.
     * @return theme URL
     */
    public static URL getThemeDirectory() {
        return getResource(getThemeResourceDirectory());
    }

    //endregion

    //region Font Utilities

    public static String getFontResourceDirectory() {
        return separator + fontRoot + separator;
    }

    public static InputStream getFontStream(Fonts font) {
        return getStream(getFontResourceDirectory() + font.getName() + font.getFile());
    }

    //endregion

}
