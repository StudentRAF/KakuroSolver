package rs.raf.kakuro.gui.view.toolbar;

import rs.raf.kakuro.gui.controller.ActionManager;
import rs.raf.kakuro.gui.controller.action.EditStateAction;
import rs.raf.kakuro.gui.controller.action.SwitchStateAction;
import rs.raf.kakuro.gui.view.KakuroEditor;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import java.awt.Color;

public class KakuroToolbar extends JToolBar {

    private final ButtonGroup group = new ButtonGroup();

    public KakuroToolbar() {
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(47, 49, 51)),
                                                     BorderFactory.createEmptyBorder(1, 3, 1, 3)));

        group.add((AbstractButton) add(new JToggleButton(ActionManager.getAction(SwitchStateAction.class))));
        group.add((AbstractButton) add(new JToggleButton(ActionManager.getAction(EditStateAction.class))));

        group.setSelected(group.getElements().nextElement().getModel(), true);
        KakuroEditor.editor.setSwitchState();
    }

}
