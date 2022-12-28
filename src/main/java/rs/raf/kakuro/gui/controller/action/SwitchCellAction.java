package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.view.KakuroEditor;
import rs.raf.kakuro.gui.view.editor.CellBase;

import java.awt.event.ActionEvent;

public class SwitchCellAction extends KakuroAction {

    public SwitchCellAction() {
        super(SwitchCellAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        CellBase cell = (CellBase) event.getSource();

        KakuroEditor.editor.changeToSuccessor(cell.getRow(), cell.getColumn());
    }

}
