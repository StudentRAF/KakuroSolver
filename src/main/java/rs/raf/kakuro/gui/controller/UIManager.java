package rs.raf.kakuro.gui.controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import rs.raf.kakuro.gui.util.KeyStrokesUtils;
import rs.raf.kakuro.gui.util.LanguageUtils;
import rs.raf.kakuro.gui.util.ResourceUtils;
import rs.raf.kakuro.gui.util.model.Fonts;
import rs.raf.kakuro.gui.util.model.KeyStrokes;
import rs.raf.kakuro.gui.util.model.Language;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.Arrays;

public class UIManager {

    //region Setup

    /**
     * Setups the UI components.
     */
    public static void setup() {
        FlatLaf.registerCustomDefaultsSource(ResourceUtils.getThemeDirectory());
        FlatLaf.setup(new FlatDarkLaf());

        setupLanguage();
        setupIcons();
        setupKeyStrokes();
        setupFonts();
    }

    /**
     * Setups the keystrokes.
     */
    public static void setupKeyStrokes() {
        KeyStrokesUtils.loadKeyStrokes(KeyStrokes.DEFAULT);

        ActionManager.setupKeyStrokes();
    }

    /**
     * Setups the language.
     */
    public static void setupLanguage() {
        LanguageUtils.loadLanguage(Language.ENGLISH_US);

        ActionManager.setupNames();
        ActionManager.setupTooltips();
    }

    /**
     * Setups the icons.
     */
    public static void setupIcons() {
        ActionManager.setupIcons();
    }

    /**
     * Setups the fonts;
     */
    private static void setupFonts() {
        try {
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, ResourceUtils.getFontStream(Fonts.DIN)));
            graphicsEnvironment.registerFont(Font.createFont(Font.TRUETYPE_FONT, ResourceUtils.getFontStream(Fonts.DIN_MEDIUM)));
        }
        catch(FontFormatException | IOException exception) {
            System.out.println(Arrays.toString(exception.getStackTrace()));
        }
    }

}
