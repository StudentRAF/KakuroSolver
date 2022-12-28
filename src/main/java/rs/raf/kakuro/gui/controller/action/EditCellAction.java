package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.view.editor.CellBase;

import java.awt.event.ActionEvent;

public class EditCellAction extends KakuroAction {

    public EditCellAction() {
        super(EditCellAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        ((CellBase) event.getSource()).editCell();
    }

}
