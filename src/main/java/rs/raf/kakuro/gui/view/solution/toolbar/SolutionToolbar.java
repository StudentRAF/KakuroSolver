package rs.raf.kakuro.gui.view.solution.toolbar;

import rs.raf.kakuro.gui.controller.ActionManager;
import rs.raf.kakuro.gui.controller.action.KakuroAction;
import rs.raf.kakuro.gui.controller.action.NextAction;
import rs.raf.kakuro.gui.controller.action.PauseAction;
import rs.raf.kakuro.gui.controller.action.PlayAction;
import rs.raf.kakuro.gui.controller.action.PreviousAction;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import java.awt.Color;

public class SolutionToolbar extends JToolBar {

    private final JSpinner spinner = new KakuroSpinner(500);

    private KakuroAction timerAction = ActionManager.getAction(PlayAction.class);

    public SolutionToolbar() {
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 2, 0, new Color(51, 53, 55)),
                                                                                             BorderFactory.createEmptyBorder(1, 3, 1, 3)));

        addComponents();
    }

    private void addComponents() {
        add(Box.createHorizontalStrut(70));

        add(Box.createHorizontalGlue());

        add(ActionManager.getAction(PreviousAction.class));
        add(timerAction);
        add(ActionManager.getAction(NextAction.class));

        add(Box.createHorizontalGlue());

        add(spinner);

        add(Box.createHorizontalStrut(7));
    }

    public int getTimerPeriod() {
        return (int) spinner.getValue();
    }

    public void changeTimerAction() {
        timerAction = timerAction == ActionManager.getAction(PauseAction.class) ? ActionManager.getAction(PlayAction.class)
                                                                                : ActionManager.getAction(PauseAction.class);

        removeAll();
        addComponents();

        revalidate();
        repaint();
    }

}
