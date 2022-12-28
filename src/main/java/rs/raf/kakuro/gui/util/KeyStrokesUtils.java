package rs.raf.kakuro.gui.util;

import rs.raf.kakuro.gui.util.model.KeyStrokes;

import javax.swing.KeyStroke;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class KeyStrokesUtils {

    private static final String DEFAULT_VALUE = "NOT_FOUND";

    private static final Properties properties = new Properties();

    /**
     * Loads keystrokes from the resource directory.
     * @param keyStrokes keystrokes
     */
    public static void loadKeyStrokes(KeyStrokes keyStrokes) {
        try {
            properties.load(ResourceUtils.getKeyStrokesStream(keyStrokes));
        }
        catch (IOException exception) {
            System.out.println(Arrays.toString(exception.getStackTrace()));
        }
    }

    /**
     * Returns the KeyStrokes with the given key.
     * @param key key
     * @return keystrokes
     */
    public static KeyStroke getProperty(String key) {
        return KeyStroke.getKeyStroke(properties.getProperty(key, DEFAULT_VALUE));
    }

}
