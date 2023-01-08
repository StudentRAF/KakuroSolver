package rs.raf.kakuro.gui.view.solution.toolbar;

import rs.raf.kakuro.gui.controller.ActionManager;
import rs.raf.kakuro.gui.controller.action.NextAction;
import rs.raf.kakuro.gui.controller.action.PlayAction;
import rs.raf.kakuro.gui.controller.action.PreviousAction;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JToolBar;
import java.awt.Color;

public class SolutionToolbar extends JToolBar {

    public SolutionToolbar() {
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 2, 0, new Color(51, 53, 55)),
                                                                                             BorderFactory.createEmptyBorder(1, 3, 1, 3)));

        add(Box.createHorizontalGlue());

        add(ActionManager.getAction(PreviousAction.class));
        add(ActionManager.getAction(PlayAction.class));
        add(ActionManager.getAction(NextAction.class));

        add(Box.createHorizontalGlue());
    }

}
