package rs.raf.kakuro.gui.view.toolbar;

import rs.raf.kakuro.gui.controller.ActionManager;
import rs.raf.kakuro.gui.controller.action.EditStateAction;
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

        group.setSelected(group.getElements().nextElement().getModel(), true);
        Editor.editor.setSwitchState();
    }

}
