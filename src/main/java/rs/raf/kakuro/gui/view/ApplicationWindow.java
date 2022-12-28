package rs.raf.kakuro.gui.view;

import rs.raf.kakuro.gui.util.ImageUtils;
import rs.raf.kakuro.gui.view.toolbar.KakuroToolbar;

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(1236, 1015);
        setResizable(false);

        setLocationRelativeTo(null);
    }

    private void initializeComponents() { }

    private void addComponents() {
        add(new KakuroToolbar(), BorderLayout.NORTH);
        add(KakuroEditor.editor, BorderLayout.CENTER);
    }

}
