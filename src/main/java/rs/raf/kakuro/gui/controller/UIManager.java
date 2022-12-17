package rs.raf.kakuro.gui.controller;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import rs.raf.kakuro.gui.util.KeyStrokesUtils;
import rs.raf.kakuro.gui.util.LanguageUtils;
import rs.raf.kakuro.gui.util.model.KeyStrokes;
import rs.raf.kakuro.gui.util.model.Language;

public class UIManager {

    //region Setup

    /**
     * Setups the UI components.
     */
    public static void setup() {
        FlatLaf.setup(new FlatDarkLaf());

        setupLanguage();
        setupKeyStrokes();
    }

    /**
     * Setups keystrokes.
     * @param keyStrokes keyStrokes
     */
    public static void setupKeyStrokes() {
        KeyStrokesUtils.loadKeyStrokes(KeyStrokes.DEFAULT);

        ActionManager.setupKeyStrokes();
    }

    /**
     * Setups language.
     * @param language language
     */
    public static void setupLanguage() {
        LanguageUtils.loadLanguage(Language.ENGLISH_US);

        ActionManager.setupNames();
        ActionManager.setupTooltips();
    }

}
