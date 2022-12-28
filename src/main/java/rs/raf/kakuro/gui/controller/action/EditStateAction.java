package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.view.Editor;

import java.awt.event.ActionEvent;

public class EditStateAction extends KakuroAction {

    public EditStateAction() {
        super(EditStateAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Editor.editor.setEditState();
    }

}
