package rs.raf.kakuro.gui.util;

import rs.raf.kakuro.gui.util.prefs.PreferenceDefaultValues;
import rs.raf.kakuro.gui.util.prefs.PreferenceKeys;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferenceUtils {

    private static final Preferences preferences = Preferences.userRoot().node(PreferenceUtils.class.getName());

    //region Put Values

    /**
     * Puts the size in the preferences.
     * @param size window size
     */
    public static void putWindowSize(Dimension size) {
        putWindowSize(size.height, size.width);
    }

    /**
     * Puts the size in the preferences.
     * @param height window height
     * @param width window width
     */
    public static void putWindowSize(int height, int width) {
        putWindowHeight(height);
        putWindowWidth(width);
    }

    /**
     * Puts the window height in the preferences.
     * @param height window height
     */
    public static void putWindowHeight(int height) {
        preferences.putInt(PreferenceKeys.WINDOW_HEIGHT, height);
    }

    /**
     * Puts the window width in the preferences.
     * @param width window width
     */
    public static void putWindowWidth(int width) {
        preferences.putInt(PreferenceKeys.WINDOW_WIDTH, width);
    }

    /**
     * Puts the maximized state in the preferences.
     * @param isMaximized maximized state
     */
    public static void putWindowMaximized(boolean isMaximized) {
        preferences.putBoolean(PreferenceKeys.MAXIMIZED, isMaximized);
    }

    //endregion

    //region Get Values

    /**
     * Returns the window size from the preferences.
     * @return window size
     */
    public static Dimension getWindowSize() {
        return new Dimension(getWindowWidth(), getWindowHeight());
    }

    /**
     * Returns the window height from the preferences.
     * @return window height
     */
    public static int getWindowHeight() {
        return preferences.getInt(PreferenceKeys.WINDOW_HEIGHT, PreferenceDefaultValues.WINDOW_HEIGHT);
    }

    /**
     * Returns the window width from the preferences.
     * @return window width
     */
    public static int getWindowWidth() {
        return preferences.getInt(PreferenceKeys.WINDOW_WIDTH, PreferenceDefaultValues.WINDOW_WIDTH);
    }

    /**
     * Returns whether window state is maximized from the preferences.
     * @return maximized state
     */
    public static boolean getWindowMaximized() {
        return preferences.getBoolean(PreferenceKeys.MAXIMIZED, PreferenceDefaultValues.MAXIMIZED);
    }

    //endregion

    /**
     * Removes all the preferences.
     */
    public static void clear() {
        try {
            preferences.clear();
        }
        catch (BackingStoreException exception) {           System.out.println(Arrays.toString(exception.getStackTrace()));
        }
    }

}
