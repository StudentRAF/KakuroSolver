package rs.raf.kakuro.gui.view;

import rs.raf.kakuro.gui.util.ImageUtils;
import rs.raf.kakuro.gui.util.PreferenceUtils;

import javax.swing.JFrame;

public class ApplicationWindow extends JFrame {

    public static final ApplicationWindow window = instantiate();

    private static ApplicationWindow instantiate() {
        ApplicationWindow instance = new ApplicationWindow();
        instance.initialize();

        return instance;
    }

    private ApplicationWindow() { }

    private void initialize() {
        setup();
        initializeComponents();
        addComponents();
    }

    private void setup() {
        setTitle("Kakuro");
        setIconImage(ImageUtils.loadImage("Logo"));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(PreferenceUtils.getWindowSize());

        if (PreferenceUtils.getWindowMaximized())
            setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLocationRelativeTo(null);
    }

    private void initializeComponents() {

    }

    private void addComponents() {

    }


}
