package rs.raf.kakuro.gui.view.solution;

import rs.raf.kakuro.gui.util.ImageUtils;
import rs.raf.kakuro.gui.view.ApplicationWindow;
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

    private final JScrollPane scrollPane = new JScrollPane();

    private final JList<RendererBase> listSteps = new JList<>();

    private final DefaultListModel<RendererBase> listModel = new DefaultListModel<>();

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

        setSize(425, 1002);
        //setResizable(false);

        setLocationRelativeTo(ApplicationWindow.window);
    }

    private void initializeComponents() {
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        listSteps.setCellRenderer(new StepListRenderer());
        listSteps.setModel(listModel);

        scrollPane.setViewportView(listSteps);
    }

    private void addComponents() {
        add(new SolutionToolbar(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addStepRenderer(RendererBase step) {
        listModel.addElement(step);
    }

    public void setSelectedStepIndex(int index) {
        listSteps.setSelectedIndex(index);
        listSteps.ensureIndexIsVisible(index);
    }

    public void clear() {
        if (listModel.size() > 0)
            listSteps.ensureIndexIsVisible(0);

        listModel.clear();
    }

}
