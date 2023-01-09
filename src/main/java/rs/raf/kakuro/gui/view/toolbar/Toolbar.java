package rs.raf.kakuro.gui.view.toolbar;

import rs.raf.kakuro.gui.controller.ActionManager;
import rs.raf.kakuro.gui.controller.action.EditStateAction;
import rs.raf.kakuro.gui.controller.action.Number0Action;
import rs.raf.kakuro.gui.controller.action.Number1Action;
import rs.raf.kakuro.gui.controller.action.Number2Action;
import rs.raf.kakuro.gui.controller.action.SolveAction;
import rs.raf.kakuro.gui.controller.action.SwitchStateAction;
import rs.raf.kakuro.gui.view.Editor;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.Color;

public class Toolbar extends JToolBar {

    public Toolbar() {
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(47, 49, 51)),
                                                     BorderFactory.createEmptyBorder(1, 3, 1, 3)));

        ButtonGroup group = new ButtonGroup();

        group.add((AbstractButton) add(new JToggleButton(ActionManager.getAction(SwitchStateAction.class))));
        group.add((AbstractButton) add(new JToggleButton(ActionManager.getAction(EditStateAction.class))));

        addSeparator();

        add(ActionManager.getAction(SolveAction.class));

        addSeparator();

        add(ActionManager.getAction(Number0Action.class));
        add(ActionManager.getAction(Number1Action.class));
        add(ActionManager.getAction(Number2Action.class));

        group.setSelected(group.getElements().nextElement().getModel(), true);

        Editor.instance.setSwitchState();
    }

}
