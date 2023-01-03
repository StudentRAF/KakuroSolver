package rs.raf.kakuro.gui.view;

import rs.raf.kakuro.gui.util.ImageUtils;
import rs.raf.kakuro.gui.view.toolbar.Toolbar;

import javax.swing.JFrame;
import java.awt.BorderLayout;

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
        setLayout(new BorderLayout());

        setTitle("Kakuro");
        setIconImage(ImageUtils.loadImage("Logo"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1220, 1002);
        setResizable(false);

        setLocationRelativeTo(null);
    }

    private void initializeComponents() { }

    private void addComponents() {
        add(new Toolbar(), BorderLayout.NORTH);
        add(Editor.instance, BorderLayout.CENTER);
    }

}
