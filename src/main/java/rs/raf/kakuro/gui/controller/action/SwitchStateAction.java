package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.view.KakuroEditor;

import java.awt.event.ActionEvent;

public class SwitchStateAction extends KakuroAction {

    public SwitchStateAction() {
        super(SwitchStateAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        KakuroEditor.editor.setSwitchState();
    }

}
