package rs.raf.kakuro.gui.util.prefs;

import java.awt.Toolkit;

public class PreferenceDefaultValues {

    public static final boolean MAXIMIZED     = false;
    public static final int     WINDOW_HEIGHT = calculateWindowHeight();
    public static final int     WINDOW_WIDTH  = calculateWindowWidth();

    /**
     * Calculates the height of the window based on the screen resolution. Target: 71.43%
     * @return height
     */
    private static int calculateWindowHeight() {
        return Toolkit.getDefaultToolkit().getScreenSize().height * 5 / 7;
    }

    /**
     * Calculates the width of the window based on the screen resolution. Target: 71.43%
     * @return width
     */
    private static int calculateWindowWidth() {
        return Toolkit.getDefaultToolkit().getScreenSize().width * 5 / 7;
    }

}
