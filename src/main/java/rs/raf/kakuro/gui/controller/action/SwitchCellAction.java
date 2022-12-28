package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.view.Editor;
import rs.raf.kakuro.gui.view.editor.EditorCellBase;

import java.awt.event.ActionEvent;

public class SwitchCellAction extends KakuroAction {

    public SwitchCellAction() {
        super(SwitchCellAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        EditorCellBase cell = (EditorCellBase) event.getSource();

        Editor.editor.changeToSuccessor(cell.getRow(), cell.getColumn());
    }

}
