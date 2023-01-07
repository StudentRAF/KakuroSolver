package rs.raf.kakuro.gui.controller.action;

import rs.raf.kakuro.gui.controller.StepManager;

import java.awt.event.ActionEvent;

public class PreviousAction extends KakuroAction {

    public PreviousAction() {
        super(PreviousAction.class);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        StepManager.previousStep();
    }

}
