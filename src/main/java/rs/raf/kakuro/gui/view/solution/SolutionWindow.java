package rs.raf.kakuro.gui.view.solution;

import rs.raf.kakuro.gui.util.ImageUtils;
import rs.raf.kakuro.gui.view.ApplicationWindow;
import rs.raf.kakuro.gui.view.solution.render.IRenderer;
import rs.raf.kakuro.gui.view.solution.render.RendererBase;
import rs.raf.kakuro.gui.view.solution.render.StepListRenderer;
import rs.raf.kakuro.gui.view.solution.toolbar.SolutionToolbar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class SolutionWindow extends JDialog {

    public static final SolutionWindow window = instantiate();

    private static SolutionWindow instantiate() {
        SolutionWindow instance = new SolutionWindow();
        instance.initialize();

        return instance;
    }

    private final ApplicationWindow application = ApplicationWindow.window;

    private JScrollPane scrollPane = new JScrollPane();
    private JList listSteps = new JList();
    private DefaultListModel<IRenderer> listModel = new DefaultListModel();


    private SolutionWindow() {
        super(ApplicationWindow.window);
    }

    private void initialize() {
        setup();
        initializeComponents();
        addComponents();
    }

    private void setup() {
        setTitle("Solution");
        setLayout(new BorderLayout());
        setIconImage(ImageUtils.loadImage("Solution"));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setSize(300, 400);
        //setResizable(false);

        setLocationRelativeTo(ApplicationWindow.window);
    }

    private void initializeComponents() {
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); //TODO remove

        listSteps.setCellRenderer(new StepListRenderer());
        listSteps.setModel(listModel);

        scrollPane.setViewportView(listSteps);
    }

    private void addComponents() {
        add(new SolutionToolbar(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addStep(RendererBase step) {
        listModel.addElement(step);
    }

}
