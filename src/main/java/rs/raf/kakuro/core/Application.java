package rs.raf.kakuro.core;

import rs.raf.kakuro.gui.controller.ActionManager;
import rs.raf.kakuro.gui.controller.UIManager;
import rs.raf.kakuro.gui.view.ApplicationWindow;

public class Application {

    public static void run() {
        UIManager.setup();

        ApplicationWindow.window.setVisible(true);
    }

}
